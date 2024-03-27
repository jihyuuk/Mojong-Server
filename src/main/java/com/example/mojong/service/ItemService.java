package com.example.mojong.service;

import com.example.mojong.model.dto.item.ItemParam;
import com.example.mojong.model.dto.item.ItemSeqDTO;
import com.example.mojong.model.entity.Category;
import com.example.mojong.model.entity.Item;
import com.example.mojong.repository.CategoryRepository;
import com.example.mojong.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    //상품 추가
    public ResponseEntity<String> addItem(ItemParam dto) {

        //빈값확인
        if(dto == null || dto.getName() == null || dto.getPrice() == 0 || dto.getCategoryId()==null){
            return ResponseEntity.badRequest().body("값을 입력해주세요");
        }

        //카테고리가 있는지확인
        Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
        if(category == null || !category.isEnabled()){
            return ResponseEntity.badRequest().body("존재하지 않는 카테고리입니다.");
        }

        //이름 중복 확인
        if(itemRepository.existsByNameAndEnabledTrue(dto.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 상품명입니다.");
        }

        //저장
        itemRepository.save(new Item(category,dto));

        return ResponseEntity.ok().body("상품 추가 성공");
    }

    //상품 삭제
    @Transactional
    public ResponseEntity<String> deleteItem(Long id){
        
        if(id == null){
            return ResponseEntity.badRequest().body("값을 입력해 주세요");
        }

        Item item = itemRepository.findById(id).orElse(null);
        if(item == null){
            return  ResponseEntity.badRequest().body("존재하지 않는 상품입니다.");
        }

        //논리적삭제
        item.delete();

        return ResponseEntity.ok("상품삭제성공");
    }

    //상품 수정
    @Transactional
    public ResponseEntity<String> updateItem(Long id, ItemParam dto) {

        //빈값확인
        if(dto == null || dto.getName() == null || dto.getPrice() == 0 || id == null || dto.getCategoryId()==null){
            return ResponseEntity.badRequest().body("값을 입력해주세요");
        }

        //상품존재확인
        Item item = itemRepository.findById(id).orElse(null);
        if(item == null){
            return  ResponseEntity.badRequest().body("존재하지 않는 상품입니다.");
        }

        //카테고리가 있는지확인
        Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
        if(category == null || !category.isEnabled()){
            return ResponseEntity.badRequest().body("존재하지 않는 카테고리입니다.");
        }

        //수정
        item.update(category,dto);

        return ResponseEntity.ok().body("상품 수정 성공");
    }

    //상품 순서변경
    @Transactional
    public ResponseEntity<String> seqChange(ItemSeqDTO dto) {

        //카테고리가 있는지확인
        Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
        if(category == null || !category.isEnabled()){
            return ResponseEntity.badRequest().body("존재하지 않는 카테고리입니다.");
        }

        //아이템 리스트 불러오기
        List<Item> items = itemRepository.findAllByCategoryAndEnabledTrue(category);

        int size = items.size();

        if(size != dto.getItemIds().size()){
            return ResponseEntity.badRequest().body("누락된 상품이 존재합니다.");
        }

        //맵으로 변환
        Map<Long, Item> map = items.stream().collect(Collectors.toMap(Item::getId, item -> item));

        //순서변경
        for (Long itemId : dto.getItemIds()) {
            map.get(itemId).seqChange(size--);
        }

        return ResponseEntity.ok("순서변경 성공");
    }
}
