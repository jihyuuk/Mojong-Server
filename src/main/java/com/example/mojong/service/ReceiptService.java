package com.example.mojong.service;

import com.example.mojong.model.dto.ReceiptDTO;
import com.example.mojong.model.dto.sale.SaleDTO;
import com.example.mojong.model.entity.Sale;
import com.example.mojong.model.entity.SaleItem;
import com.example.mojong.repository.SaleItemRepository;
import com.example.mojong.repository.SaleRepository;
import com.example.mojong.websocket.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final WebSocketHandler webSocketHandler;

    public ResponseEntity<String> print(Long id){

        //프린터기 연결 x시에
        if (! webSocketHandler.isConnected()){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("프린터기가 연결되어있지 않습니다.");
        }

        //주문조회
        Sale sale = saleRepository.findById(id).orElse(null);

        //없는 주문번호시에
        if(sale == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("없는 주문 번호 입니다.");
        }

        //주문아이템 조회
        List<SaleItem> saleItems = saleItemRepository.findAllBySaleId(id);

        //웹소켓으로 전송
        try {
            webSocketHandler.sendData(new ReceiptDTO(sale,saleItems));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("영수증 출력 실패");
        }

        return ResponseEntity.ok("영수증 출력 성공!");
    }
}
