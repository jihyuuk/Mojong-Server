package com.example.mojong.model.dto.history;

import org.springframework.data.domain.Page;

public class PageInfo {

    private boolean isEmpty;
    private boolean isFirst;
    private boolean isLast;
    private int number;
    private int totalPages;
    private Long totalElements;


    public PageInfo(Page page) {
        isEmpty = page.isEmpty();
        isFirst = page.isFirst();
        isLast = page.isLast();
        number = page.getNumber();
        totalPages = page.getTotalPages();
        totalElements = page.getTotalElements();
    }
}
