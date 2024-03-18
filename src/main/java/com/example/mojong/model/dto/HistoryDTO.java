package com.example.mojong.model.dto;

import com.example.mojong.model.entity.Sale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class HistoryDTO {

    private List<Sale> content;
    private boolean empty;
    private boolean first;
    private boolean last;
    private int number;
    private int totalPages;
    private Long totalElements;

    public HistoryDTO(Page<Sale> page) {
        content = page.getContent();
        empty = page.isEmpty();
        first = page.isFirst();
        last = page.isLast();
        number = page.getNumber();
        totalPages = page.getTotalPages();
        totalElements = page.getTotalElements();
    }
}
