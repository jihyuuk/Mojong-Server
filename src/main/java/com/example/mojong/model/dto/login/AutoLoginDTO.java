package com.example.mojong.model.dto.login;

import com.example.mojong.model.entity.ROLE;
import com.example.mojong.model.entity.User;
import lombok.Getter;

@Getter
public class AutoLoginDTO {

    private String username;
    private ROLE role;

    public AutoLoginDTO(User user) {
        username = user.getUsername();
        role = user.getRole();
    }
}
