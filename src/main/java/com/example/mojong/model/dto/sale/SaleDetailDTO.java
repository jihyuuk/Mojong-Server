package com.example.mojong.model.dto.sale;

import com.example.mojong.model.entity.Sale;
import com.example.mojong.model.entity.SaleItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailDTO {

    private Sale sale;
    private List<SaleItem> saleItems;

}
