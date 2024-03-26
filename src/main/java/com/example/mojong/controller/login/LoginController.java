package com.example.mojong.controller.login;

import com.example.mojong.model.dto.JoinDTO;
import com.example.mojong.model.entity.User;
import com.example.mojong.security.jwt.JWTUtil;
import com.example.mojong.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(JoinDTO joinDTO){
        ResponseEntity<String> responseEntity = userService.joinProcess(joinDTO);
        return responseEntity;
    }

    //자동로그인
    @PostMapping("/login/auto")
    public ResponseEntity<String> autoLogin(@RequestParam String token){

        try {
            //유효기간 검사
            jwtUtil.isExpired(token);

            //해당 유저 조회
            User user = userService.findByUsername(jwtUtil.getUsername(token));

            //유저 검증 존재x 또는 enabled 시 거부
            if(user == null || !user.isEnabled()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("존재하지 않거나 비활성화된 유저입니다.");
            }

            //권한 검증 다를시 새 토큰 부여
            if(!user.getRole().name().equals(jwtUtil.getRole(token))){
                System.out.println("role 틀림 새 토큰 발행");
                String newToken = jwtUtil.createJwt(user.getUsername(), user.getRole().name());
                return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body("{\"token\": \"" + newToken + "\"}");
            }

            //200 ok
            return ResponseEntity.ok("자동 로그인 성공!");

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }
    }

}
