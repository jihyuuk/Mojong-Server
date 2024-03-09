package com.example.mojong.model.entity;

import com.example.mojong.model.dto.ItemParam;
import com.example.mojong.model.dto.sale.SaleItemDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaleItem {

    @JsonIgnore
    @Id @GeneratedValue
    @Column(name = "sale_item_id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    private String name;
    private int quantity;
    private int price;
    private int total;

    public SaleItem(Sale sale, SaleItemDTO dto) {
        this.sale = sale;
        name = dto.getName();
        quantity = dto.getQuantity();
        price = dto.getPrice();
        total = dto.getQuantity() * dto.getPrice();
    }
}
