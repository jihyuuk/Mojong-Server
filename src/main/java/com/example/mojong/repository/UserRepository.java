package com.example.mojong.repository;

import com.example.mojong.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    //차단,차단해제
    @Modifying
    @Transactional
    @Query("update User u set u.enabled = :enabled where u.id = :userId")
    int userEnabled(Long userId, Boolean enabled);

    //가입승인
    @Modifying
    @Transactional
    @Query("update User u set u.enabled = true, u.approved = true, u.createdDate = :createdDate where u.id = :userId")
    int userApprove(Long userId, LocalDateTime createdDate);

    //가입거부
    @Modifying
    @Transactional
    @Query("DELETE User u where u.id = :userId")
    int userReject(Long userId);

}
