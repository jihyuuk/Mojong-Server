package com.example.mojong.model.dto.sale;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class SaleDTO {
    
    //장바구니 아이템들
    private List<SaleItemDTO> items;
    //합계
    private int totalPrice;
    //할인금액
    private int salePrice;
    //최종금액
    private int finalPrice;
    //결제수단
    private String pay;
    //영수증 출력여부
    private boolean print;
}
