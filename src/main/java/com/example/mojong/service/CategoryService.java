package com.example.mojong.service;

import com.example.mojong.model.dto.category.CategoryParam;
import com.example.mojong.model.dto.category.CategorySeqDTO;
import com.example.mojong.model.entity.Category;
import com.example.mojong.repository.CategoryRepository;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAllCustom();
    }

    //카테고리 생성
    public ResponseEntity<String> create(CategoryParam categoryParam) {

        String name = categoryParam.getName();

        //존재,빈값 확인
        if(name == null || name.trim().isEmpty()){
            return ResponseEntity.badRequest().body("카테고리명은 필수입니다.");
        }

        //중복확인
        if(categoryRepository.existsByNameAndEnabledTrue(name)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 카테고리명 입니다.");
        }

        //생성
        categoryRepository.save( new Category(name));

        return ResponseEntity.ok("생성성공");
    }

    //카테고리 논리적 삭제
    @Transactional
    public ResponseEntity<String> delete(Long id) {

        Category category = categoryRepository.findById(id).orElse(null);

        //없는 id 인경우
        if(category == null) return ResponseEntity.notFound().build();

        //논리적삭제
        category.delete();

        return ResponseEntity.ok("삭제성공");
    }

    //카테고리 수정
    @Transactional
    public ResponseEntity<String> update(Long id, CategoryParam categoryParam) {

        String name = categoryParam.getName();

        Category category = categoryRepository.findById(id).orElse(null);

        //없는 id 인경우
        if(category == null) return ResponseEntity.notFound().build();

        //중복확인
        if(categoryRepository.existsByNameAndEnabledTrue(name)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 카테고리명 입니다.");
        }

        //이름변경
        category.changeName(name);

        return ResponseEntity.ok("수정 성공");
    }

    //카테고리 순서 변경
    @Transactional
    public ResponseEntity<String> changeSeq(List<CategorySeqDTO> seqDTOS) {

        //카테고리 불러오기
        List<Category> categories = categoryRepository.findAllByEnabledTrue();

        int size = categories.size();

        if (seqDTOS.size() != size){
            return ResponseEntity.badRequest().body("누락된 카테고리가존재합니다.");
        }

        //맵으로 변환
        Map<Long, Category> map = categories.stream().collect(Collectors.toMap(Category::getId, category -> category));

        //적용
        for (CategorySeqDTO dto : seqDTOS) {
            map.get(dto.getId()).changeSeq(size--);
        }

        return ResponseEntity.ok("순서 변경 성공");
    }


}
