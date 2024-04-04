package com.example.mojong.model.dto;

import com.example.mojong.model.entity.Sale;
import com.example.mojong.model.entity.SaleItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ReceiptDTO {

    //판매번호
    private Long id;
    //판매일시
    private String date;

    //장바구니 아이템들
    private List<SaleItem> items;

    //합계
    private int totalPrice;
    //할인금액
    private int salePrice;
    //최종금액
    private int finalPrice;

    public ReceiptDTO(Sale sale, List<SaleItem> saleItems) {
        this.id = sale.getId();
        this.date = sale.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.totalPrice = sale.getTotalPrice();
        this.salePrice = sale.getSalePrice();
        this.finalPrice = sale.getFinalPrice();

        items = saleItems;
    }

}
