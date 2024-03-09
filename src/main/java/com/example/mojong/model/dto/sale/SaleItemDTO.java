package com.example.mojong.model.dto.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SaleItemDTO {

    //상품이름,단가,수량,총합계
    private String name;
    private int price;
    private int quantity;

}
