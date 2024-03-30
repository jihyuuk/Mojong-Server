package com.example.mojong.model.entity;

import com.example.mojong.model.dto.item.ItemParam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private String description;

    private int price;

    @JsonIgnore
    private int seq;

    @JsonIgnore
    private boolean enabled;

    public Item(Category category, String name, String description, int price) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        enabled = true;
    }

    public Item(Category category, ItemParam itemParam){
        this.category = category;
        this.name = itemParam.getName();
        this.description = itemParam.getDescription();
        this.price = itemParam.getPrice();
        enabled = true;
    }

    public void delete(){
        seq = -1;
        enabled = false;
    }

    public void update(Category category, ItemParam itemParam) {

        //카테고리 변경 있을때
        if(this.category.getId() != category.getId()){
            //카테고리변경
            this.category = category;
            //순서변경
            this.seq = 0;
        }

        this.name = itemParam.getName();
        this.description = itemParam.getDescription();
        this.price = itemParam.getPrice();
    }

    public void seqChange(int seq){
        this.seq = seq;
    }
}
