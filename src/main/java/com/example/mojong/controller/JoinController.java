package com.example.mojong.controller;

import com.example.mojong.model.dto.JoinDTO;
import com.example.mojong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(JoinDTO joinDTO){
        ResponseEntity<String> responseEntity = userService.joinProcess(joinDTO);
        return responseEntity;
    }

}
