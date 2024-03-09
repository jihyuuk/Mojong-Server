package com.example.mojong.service;

import com.example.mojong.model.dto.AllHistoryDTO;
import com.example.mojong.model.dto.sale.SaleDTO;
import com.example.mojong.model.dto.sale.SaleDetailDTO;
import com.example.mojong.model.entity.Sale;
import com.example.mojong.model.entity.SaleItem;
import com.example.mojong.repository.SaleItemRepository;
import com.example.mojong.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;

    //판매하기
    @Transactional
    public Long sale(SaleDTO saleDTO, String username){

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

        return sale.getId();
    }

    //간단판매기록
    public List<Sale> history(String username){
        return saleRepository.findAllByUsernameOrderByIdDesc(username);
    }

    //상세판매기록
    public SaleDetailDTO detail(Long saleId){
        Sale sale = saleRepository.findById((saleId)).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        List<SaleItem> saleItems = saleItemRepository.findAllBySaleId(saleId);
        return new SaleDetailDTO(sale, saleItems);
    }

    //모든판매기록
    public AllHistoryDTO allHistory(Pageable pageable){
        Page<Sale> page = saleRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()));
        return new AllHistoryDTO(page);
    }
}
