package com.example.mojong.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TodaySaleDTO {

    private Long count;
    private Long price;

    public TodaySaleDTO(Long count, Long price) {
        this.count = count;
        this.price = price == null ? 0 : price;
    }
}
