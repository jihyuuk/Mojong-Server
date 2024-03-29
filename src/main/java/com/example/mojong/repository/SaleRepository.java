package com.example.mojong.repository;

import com.example.mojong.model.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    //유저기록보기
    Page<Sale> findAllByUsername(String username,Pageable pageable);

    //모든기록보기
    Page<Sale> findAll(Pageable pageable);
    
}
