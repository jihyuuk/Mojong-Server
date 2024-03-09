package com.example.mojong.model.dto;

import com.example.mojong.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class MembersDTO {

    private List<User> members = new ArrayList<>();
    private List<User> joins = new ArrayList<>();

    public MembersDTO(List<User> allUser) {
        allUser.forEach(user->{
            if (user.isApproved()) {
                //가입승인된 맴버들
                members.add(user);
            } else {
                //가입대기자
                joins.add(user);
            }
        });
    }
}
