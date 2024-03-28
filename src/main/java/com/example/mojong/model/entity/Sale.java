package com.example.mojong.model.entity;

import com.example.mojong.model.dto.sale.SaleDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue
    @Column(name = "sale_id")
    private Long id;

    private String username;

    private String firstItem;

    private int countItem;

    private int totalPrice;

    private int salePrice;

    private int finalPrice;

    private String pay;


    private LocalDateTime createdDate;

    @PrePersist
    protected void prePersist(){
        createdDate = LocalDateTime.now();
    }

    public Sale(SaleDTO saleDTO, String username) {
        this.username = username;
        firstItem = saleDTO.getItems().get(0).getName();
        count = saleDTO.getItems().size();
        totalPrice = saleDTO.getTotalPrice();
        salePrice = saleDTO.getSalePrice();
        finalPrice = saleDTO.getFinalPrice();
        pay = saleDTO.getPay();
    }
}
