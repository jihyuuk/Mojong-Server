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
        String authorization= request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        System.out.println("authorization now");
        //Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        try{
            //만약 유효기간 만료라면 에러남
            jwtUtil.isExpired(token);
        }catch (ExpiredJwtException e){
            System.out.println("유효기간만료됨 후처리 시작");

            //만료 토큰에서 userName 추출하기
            String username = e.getClaims().get("username", String.class);
            //db username 으로 조회
            User findUser = userService.findByUsername(username);

            //user가 존재하고 enabled=ture 일때 새 토큰 발행
            if(findUser != null && findUser.isEnabled()){
                //유저 enabled시 새토큰발급해줌
                System.out.println("새 토큰발급");
                String reToken = jwtUtil.createJwt(username, findUser.getRole().name(), 60+60*1000L);
                response.addHeader("Authorization", "Bearer " + token);
                response.setStatus(201);
                //여기
                //PrincipalDetails에 회원 정보 객체 담기
                PrincipalDetails details = new PrincipalDetails(findUser);

                //스프링 시큐리티 인증 토큰 생성
                Authentication authToken = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
                //세션에 사용자 등록
                SecurityContextHolder.getContext().setAuthentication(authToken);
                filterChain.doFilter(request, response);
                //끝
                return;
            }
            System.out.println("새토큰 발급 못함");
            filterChain.doFilter(request, response);
            return;
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(token);
        ROLE role = ROLE.valueOf(jwtUtil.getRole(token));

        //user를 생성
        User tempUser = new User(username,"tempPassword",role,true,true);

        //PrincipalDetails에 회원 정보 객체 담기
        PrincipalDetails details = new PrincipalDetails(tempUser);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

}
