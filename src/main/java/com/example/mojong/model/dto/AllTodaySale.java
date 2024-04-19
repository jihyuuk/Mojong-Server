package com.example.mojong.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AllTodaySale {

    private Long count;
    private Long price;

    private Long allCount;
    private Long allPrice;

    public AllTodaySale(TodaySaleDTO dto1, TodaySaleDTO dto2) {
        this.count = dto1.getCount();
        this.price = dto1.getPrice();

        this.allCount = dto2.getCount();
        this.allPrice = dto2.getPrice();
    }
}
