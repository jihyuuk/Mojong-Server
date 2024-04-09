package com.example.mojong.model.dto.history;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter @Setter
@NoArgsConstructor
public class PageInfo {

    private boolean isEmpty;
    private boolean isFirst;
    private boolean isLast;
    private int nowPage;
    private int totalPages;
    private Long totalElements;


    public PageInfo(Page page) {
        isEmpty = page.isEmpty();
        isFirst = page.isFirst();
        isLast = page.isLast();
        nowPage = page.getNumber();
        totalPages = page.getTotalPages();
        totalElements = page.getTotalElements();
    }
}
