package com.example.mojong.controller;

import com.example.mojong.InIt;
import com.example.mojong.model.dto.ItemDTO;
import com.example.mojong.service.ItemService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final InIt inIt;

//    @GetMapping("/itemList")
//    public List<ItemDTO> getItems(){
//        log.info("itemsList 호출됨");
//        List<ItemDTO> itemList = itemService.findAll();
//        return itemList;
//    }

    @PostConstruct
    public void run(){
        inIt.init();
    }


}
