package com.example.mojong.model.entity;

import com.example.mojong.model.dto.JoinDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    Long id;

    String username;
    @JsonIgnore
    String password;

    @Enumerated(EnumType.STRING)
    ROLE role;
    private boolean enabled;
    //가입신청 승인여부
    @JsonIgnore
    private boolean approved;
    
    //가입일
    @JsonFormat(pattern = "YYYY.MM.dd HH:mm")
    private LocalDateTime createdDate;

    @PrePersist
    protected void prePersist(){
        createdDate = LocalDateTime.now();
    }

    public User(JoinDTO joinDTO) {
        this.username = joinDTO.getUsername();
        this.password = joinDTO.getPassword();
        role = ROLE.ROLE_USER;
        //실제론 false로바꿔야함
        enabled = false;
        approved = false;
    }

    //테스트용
    public User(String username, String password, ROLE role,boolean enabled, boolean approved) {
        this.username = username;
        this.password = password;
        this.role = role;
        //실제론 false로바꿔야함
        this.enabled = enabled;
        this.approved = approved;
    }
}
