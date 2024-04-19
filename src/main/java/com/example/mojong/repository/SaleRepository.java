package com.example.mojong.repository;

import com.example.mojong.model.dto.TodaySaleDTO;
import com.example.mojong.model.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    //유저기록보기
    Page<Sale> findAllByUsername(String username,Pageable pageable);

    //모든기록보기
    Page<Sale> findAll(Pageable pageable);

    //오늘 개인 판매 정보
    @Query("SELECT new com.example.mojong.model.dto.TodaySaleDTO(COUNT(s) , SUM(s.finalPrice)) " +
            "FROM Sale s " +
            "WHERE s.username = :username AND FUNCTION('DATE', s.createdDate) = CURRENT_DATE")
    TodaySaleDTO findMyTodaySale(String username);

    //오늘 전체 판매 정보
    @Query("SELECT new com.example.mojong.model.dto.TodaySaleDTO(COUNT(s) , SUM(s.finalPrice)) " +
            "FROM Sale s " +
            "WHERE FUNCTION('DATE', s.createdDate) = CURRENT_DATE")
    TodaySaleDTO findAllTodaySale();
}
