package com.example.mojong.model.dto;

import com.example.mojong.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class InitDataDTO {

    private List<Category> mojongs;
    private String username;
    private String role;

}
