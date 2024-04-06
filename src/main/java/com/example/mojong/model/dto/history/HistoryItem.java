package com.example.mojong.model.dto.history;

import com.example.mojong.model.entity.Sale;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class HistoryItem {
    /*
    1.제목 (오이 외 n개)
    2.판매번호 (27)
    3.판매자 (홍길동)
    4.판매시간 (2024-04-17 11:18)
    5.판매가격 (2000원)
     */

    private Long id;

    private String title;

    private String username;

    @JsonFormat(pattern = "YYYY.MM.dd HH:mm")
    private LocalDateTime date;

    private int price;

    public HistoryItem(Sale sale) {
        id = sale.getId();
        title = sale.getFirstItem();
        if(sale.getCountItem() > 1){
            title += " 외 "+(sale.getCountItem()-1)+"개";
        }
        username = sale.getUsername();
        date = sale.getCreatedDate();
        price = sale.getFinalPrice();
    }
}
