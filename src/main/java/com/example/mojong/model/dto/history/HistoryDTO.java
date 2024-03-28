package com.example.mojong.model.dto.history;

import com.example.mojong.model.entity.Sale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class HistoryDTO {

    private List<HistoryItem> contents;
    private PageInfo pageInfo;

    public HistoryDTO(Page<Sale> page) {
        contents = page.getContent().stream().map(sale -> new HistoryItem(sale)).collect(Collectors.toList());
        pageInfo = new PageInfo(page);
    }
}
