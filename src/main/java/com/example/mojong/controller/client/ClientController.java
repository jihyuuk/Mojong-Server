package com.example.mojong.controller.client;

import com.example.mojong.model.dto.history.HistoryDTO;
import com.example.mojong.model.dto.InitDataDTO;
import com.example.mojong.model.dto.sale.SaleDTO;
import com.example.mojong.model.dto.sale.SaleDetailDTO;
import com.example.mojong.model.entity.Category;
import com.example.mojong.service.CategoryService;
import com.example.mojong.service.ReceiptService;
import com.example.mojong.service.SaleService;
import com.example.mojong.websocket.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final SaleService saleService;
    private final CategoryService categoryService;
    private final ReceiptService receiptService;
    private final WebSocketHandler webSocketHandler;

    //모종 조회
    @GetMapping("/mojongs")
    public List<Category> items(){
        return categoryService.getCategories();
    }

    //판매
    @PostMapping("/sale")
    public ResponseEntity<String> sale(@RequestBody SaleDTO saleDTO, Authentication authentication){
        return saleService.sale(saleDTO,authentication.getName());
    }

    //판매 상세 조회
    @GetMapping("/sale/{id}")
    public SaleDetailDTO saleDetail(@PathVariable Long id){
        return saleService.detail(id);
    }

    //판매 기록 조회
    @GetMapping("/history")
    public HistoryDTO history(Pageable pageable, Authentication authentication){
        return saleService.history(pageable, authentication.getName());
    }

    //영수증 출력
    @GetMapping("/receipt/{id}")
    public ResponseEntity<String> receipt(@PathVariable Long id){
        return receiptService.print(id);
    }

}
