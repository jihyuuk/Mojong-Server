package com.example.mojong.service;

import com.example.mojong.model.dto.AllTodaySale;
import com.example.mojong.model.dto.TodaySaleDTO;
import com.example.mojong.model.dto.history.HistoryDTO;
import com.example.mojong.model.dto.sale.SaleDTO;
import com.example.mojong.model.dto.sale.SaleDetailDTO;
import com.example.mojong.model.entity.Sale;
import com.example.mojong.model.entity.SaleItem;
import com.example.mojong.repository.SaleItemRepository;
import com.example.mojong.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final ReceiptService receiptService;

    //판매하기
    @Transactional
    public ResponseEntity<String> sale(SaleDTO saleDTO, String username){

        //검증
        if(username == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        if(saleDTO.getItems() == null || saleDTO.getItems().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //판매서
        Sale sale = new Sale(saleDTO, username);
        saleRepository.save(sale);

        //판매아이템들
        saleDTO.getItems().forEach(saleItemDTO -> {
            saleItemRepository.save(new SaleItem(sale,saleItemDTO));
        });
        
        //프린터 적용
        if(saleDTO.isPrint()){
            ResponseEntity<String> printRes = receiptService.print(sale.getId());
            //프린트 실패시
            if (printRes.getStatusCode().isError()){
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("영수증 출력실패");
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("판매 성공!");
    }

    //간단판매기록
    public HistoryDTO history(Pageable pageable, String username){
        Page<Sale> page = saleRepository.findAllByUsername(username, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()));
        return new HistoryDTO(page);
    }

    //상세판매기록
    public SaleDetailDTO detail(Long saleId){
        Sale sale = saleRepository.findById((saleId)).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        List<SaleItem> saleItems = saleItemRepository.findAllBySaleId(saleId);
        return new SaleDetailDTO(sale, saleItems);
    }

    //모든판매기록
    public HistoryDTO allHistory(Pageable pageable){
        Page<Sale> page = saleRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()));
        return new HistoryDTO(page);
    }

    //오늘의 개인 판매 정보
    public TodaySaleDTO myTodaySale(String name) {
        return saleRepository.findMyTodaySale(name);
    }

    //오늘의 전체 판매 정보
    public AllTodaySale allTodaySale(String name) {
        TodaySaleDTO myTodaySale = saleRepository.findMyTodaySale(name);
        TodaySaleDTO allTodaySale = saleRepository.findAllTodaySale();
        return new AllTodaySale(myTodaySale, allTodaySale);
    }
}
