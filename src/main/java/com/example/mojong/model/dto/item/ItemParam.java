package com.example.mojong.model.dto.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ItemParam {

   private Long categoryId;
   private String name;
   private String description;
   private int price;

}
