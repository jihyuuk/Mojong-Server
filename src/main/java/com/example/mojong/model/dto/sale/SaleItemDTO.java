package com.example.mojong.model.dto.sale;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SaleItemDTO {

    //상품이름,수량,단가,총합계
    private String name;
    private int quantity;
    private int price;
    private int total;

}
