package com.example.mojong.controller;

import com.example.mojong.InIt;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final InIt inIt;

    @PostConstruct
    public void run(){
        inIt.init();
    }


}
