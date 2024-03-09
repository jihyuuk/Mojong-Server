package com.example.mojong.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ApprovalDTO {

    private Boolean approve;
    private Long userId;

}
