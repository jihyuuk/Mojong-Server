package com.example.mojong.security.jwt;

import com.example.mojong.model.entity.ROLE;
import com.example.mojong.model.entity.User;
import com.example.mojong.security.PrincipalDetails;
import com.example.mojong.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserService userService;

    public JWTFilter(JWTUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //request에서 Authorization 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];

        //토큰 검사
        try {
            //만약 유효기간 만료라면 에러남
            jwtUtil.isExpired(token);

            //토큰에서 username과 role 획득
            String username = jwtUtil.getUsername(token);
            ROLE role = ROLE.valueOf(jwtUtil.getRole(token));

            //user를 생성
            User tempUser = new User(username, "tempPassword", role, true, true);

            //PrincipalDetails에 회원 정보 객체 담기
            PrincipalDetails details = new PrincipalDetails(tempUser);

            //스프링 시큐리티 인증 토큰 생성
            Authentication authToken = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
            //세션에 사용자 등록
            SecurityContextHolder.getContext().setAuthentication(authToken);

            //다음으로
            filterChain.doFilter(request, response);
            
        } catch (ExpiredJwtException e) {
            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("유효하지 않은 토큰입니다.");
        }
    }

}
