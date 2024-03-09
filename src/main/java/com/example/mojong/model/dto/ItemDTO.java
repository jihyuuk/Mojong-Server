package com.example.mojong.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ItemDTO {

   private Long id;
   private Long categoryId;
   private String name;
   private String description;
   private int price;

}
