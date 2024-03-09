package com.example.mojong.service;

import com.example.mojong.model.entity.Sale;
import com.example.mojong.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final SaleRepository saleRepository;

    public ResponseEntity<String> print(Long id){
        Sale sale = saleRepository.findById(id).orElse(null);
        if(sale == null) return new ResponseEntity<>("존재하지 않는 주문번호 입니다.", HttpStatus.BAD_REQUEST);

        //return new ResponseEntity<>("영수증 출력을 실패하였습니다.",HttpStatus.ACCEPTED);
        return new ResponseEntity<>("영수증 출력을 성공하였습니다.",HttpStatus.CREATED);
    }
}
