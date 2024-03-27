package com.example.mojong.model.dto.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ItemSeqDTO {

    private Long CategoryId;
    private List<Long> itemIds;

}
