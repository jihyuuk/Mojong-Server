package com.example.mojong.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @JsonIgnore
    private int seq;

    @JsonIgnore
    private boolean enabled;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    public Category() {
        enabled = true;
    }

    public Category(String name) {
        this();
        this.name = name;
    }


    public void delete(){
        enabled = false;
        seq = -1;
    }
    public void changeName(String name) { this.name = name; }
    public void changeSeq(int seq) { this.seq = seq; }
}
