package com.example.mojong.controller.admin;

import com.example.mojong.model.dto.*;
import com.example.mojong.model.dto.category.CategoryParam;
import com.example.mojong.model.dto.category.CategorySeqDTO;
import com.example.mojong.model.dto.history.HistoryDTO;
import com.example.mojong.model.dto.item.ItemParam;
import com.example.mojong.model.dto.item.ItemSeqDTO;
import com.example.mojong.service.CategoryService;
import com.example.mojong.service.ItemService;
import com.example.mojong.service.SaleService;
import com.example.mojong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final UserService userService;
    private final SaleService saleService;
    private final CategoryService categoryService;
    private final ItemService itemService;

    //회원 목록, 가입신청자 목록 출력
    @GetMapping("/members")
    public MembersDTO members(){
        return userService.getMembers();
    }

    //회원 차단
    @PutMapping("/members/{id}/block")
    public ResponseEntity<String> memberBlock(@PathVariable Long id){
        return userService.block(id);
    }

    //차단해제
    @PutMapping("/members/{id}/unBlock")
    public ResponseEntity<String> memberUnBlock(@PathVariable Long id){
        return userService.unBlock(id);
    }

    //가입승인
    @PutMapping("/members/{id}/approval")
    public ResponseEntity<String> memberApproval(@PathVariable Long id){
        return userService.approval(id);
    }

    //가입거부
    @DeleteMapping("/members/{id}/disApproval")
    public ResponseEntity<String> memberDisApproval(@PathVariable Long id){
        return userService.disApproval(id);
    }

    //모든기록보기===================================================================================
    @GetMapping("/allHistory")
    public HistoryDTO allHistory(Pageable pageable){
        return saleService.allHistory(pageable);
    }

    //카테고리 생성=======================================================================================
    @PostMapping("/category")
    public ResponseEntity<String> createCategory(@RequestBody CategoryParam categoryParam){
        return categoryService.create(categoryParam);
    }

    //카테고리 수정
    @PutMapping("/category/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody CategoryParam categoryParam){
        return categoryService.update(id, categoryParam);
    }

    //카테고리 삭제
    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return categoryService.delete(id);
    }

    //카테고리 순서 변경
    @PutMapping("/category/seqChange")
    public ResponseEntity<String> changeSeqCategory(@RequestBody CategorySeqDTO categorySeqDTO){
        return categoryService.changeSeq(categorySeqDTO);
    }
    
    //상품 추가=====================================================================================
    @PostMapping("/item")
    public ResponseEntity<String> createItem(@RequestBody ItemParam itemParam){
        return itemService.addItem(itemParam);
    }

    //상품 수정
    @PutMapping("/item/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody ItemParam itemParam){
        return itemService.updateItem(id, itemParam);
    }

    //상품 삭제
    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        return itemService.deleteItem(id);
    }

    //상품 순서변경
    @PutMapping("/item/seqChange")
    public ResponseEntity<String> changeSeqItem(@RequestBody ItemSeqDTO itemSeqDTO){
        return itemService.seqChange(itemSeqDTO);
    }

}
