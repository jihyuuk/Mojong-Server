package com.example.mojong.controller.admin;

import com.example.mojong.model.dto.*;
import com.example.mojong.service.CategoryService;
import com.example.mojong.service.ItemService;
import com.example.mojong.service.SaleService;
import com.example.mojong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    //회원 차단,차단해제
    @PostMapping("/block")
    public ResponseEntity<String> block(@RequestBody BlockDTO blockDTO){
        return userService.blocking(blockDTO);
    }

    //가입승인
    @PostMapping("/approval")
    public ResponseEntity<String> block(@RequestBody ApprovalDTO approvalDTO){
        return userService.approval(approvalDTO);
    }

    //모든기록보기
    @GetMapping("/allHistory")
    public AllHistoryDTO allHistory(Pageable pageable){
        return saleService.allHistory(pageable);
    }

    //카테고리 생성
    @PostMapping("/categories")
    public ResponseEntity<String> createCategory(@RequestBody Map<String, String> data){
        return categoryService.create(data.get("name"));
    }

    //카테고리 삭제
    @DeleteMapping("/categories")
    public ResponseEntity<String> deleteCategory(@RequestBody Map<String, Long> data){
        System.out.println("data = " + data.get("id"));
        return categoryService.delete(data.get("id"));
    }

    //카테고리 수정
    @PutMapping("/categories")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryDTO dto){
        return categoryService.update(dto);
    }

    //카테고리 순서 변경
    @PutMapping("/categories/seqChange")
    public ResponseEntity<String> changeSeqCategory(@RequestBody List<CategoryDTO> dtos){
        return categoryService.changeSeq(dtos);
    }
    
    //상품 추가
    @PostMapping("/item")
    public ResponseEntity<String> createItem(@RequestBody ItemDTO itemDTO){
        System.out.println("itemDTO = " + itemDTO.getCategoryId());
        System.out.println("itemDTO = " + itemDTO.getName());
        System.out.println("itemDTO = " + itemDTO.getDescription());
        System.out.println("itemDTO = " + itemDTO.getPrice());
        return itemService.addItem(itemDTO);
    }

    //상품 수정
    @PutMapping("/item")
    public ResponseEntity<String> updateItem(@RequestBody ItemDTO itemDTO){
        return itemService.updateItem(itemDTO);
    }

    //상품 삭제
    @DeleteMapping("/item")
    public ResponseEntity<String> deleteItem(@RequestBody Map<String,Long> data){
        return itemService.deleteItem(data.get("id"));
    }

    //상품 순서변경
    @PutMapping("/item/seqChange")
    public ResponseEntity<String> changeSeqItem(@RequestBody ItemSeqDTO itemSeqDTO){
        System.out.println(itemSeqDTO.getCategoryId()+"ㅋ테고리 아이디");
        for (Long itemId : itemSeqDTO.getItemIds()) {
            System.out.println("itemId = " + itemId);
        }
        return itemService.seqChange(itemSeqDTO);
    }



}
