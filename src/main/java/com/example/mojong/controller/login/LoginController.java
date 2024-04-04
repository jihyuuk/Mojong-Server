package com.example.mojong.controller.login;

import com.example.mojong.model.dto.login.AutoLoginDTO;
import com.example.mojong.model.dto.JoinDTO;
import com.example.mojong.model.entity.User;
import com.example.mojong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(JoinDTO joinDTO) {
        ResponseEntity<String> responseEntity = userService.joinProcess(joinDTO);
        return responseEntity;
    }

    //자동로그인
    @GetMapping("/login/auto")
    public ResponseEntity<Object> autoLogin(Authentication authentication) {

        //해당 유저 조회
        User user = userService.findByUsername(authentication.getName());

        //유저 검증 존재x 또는 enabled=false 시 거부
        if (user == null || !user.isEnabled()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("존재하지 않거나 비활성화된 회원입니다.");
        }

        //200 ok
        //이름, 권한 반환
        return ResponseEntity.ok(new AutoLoginDTO(user));
    }

}
