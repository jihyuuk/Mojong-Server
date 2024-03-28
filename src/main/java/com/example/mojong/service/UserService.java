package com.example.mojong.service;

import com.example.mojong.model.dto.ApprovalDTO;
import com.example.mojong.model.dto.BlockDTO;
import com.example.mojong.model.dto.JoinDTO;
import com.example.mojong.model.dto.MembersDTO;
import com.example.mojong.model.entity.User;
import com.example.mojong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    
    //회원가입 서비스
    public ResponseEntity<String> joinProcess(JoinDTO joinDTO){
        //이름 존재 여부 판별
        Boolean isExist = userRepository.existsByUsername(joinDTO.getUsername());

        //존재시 에러
        if(isExist){
            return new ResponseEntity<>("이미 존재하는 직원명 입니다.", HttpStatus.CONFLICT);
        }
        
        //존재x 가입하기
        User user = new User(joinDTO);
        userRepository.save(user);

        return new ResponseEntity<>("가입신청이 성공적으로 완료되었습니다.",HttpStatus.CREATED);
    }

    //회원리스트
    public MembersDTO getMembers(){
        return new MembersDTO(userRepository.findAll());
    }

    //회원차단
    public ResponseEntity<String> block(Long id){
        userRepository.userEnabled(id, false);
        return ResponseEntity.ok().body("회원 차단 성공!");
    }

    //회원차단 해제
    public ResponseEntity<String> unBlock(Long id){
        userRepository.userEnabled(id, true);
        return ResponseEntity.ok().body("회원 차단 해제");
    }


    //가입승인
    public ResponseEntity<String> approval(Long id) {
            userRepository.userApprove(id, LocalDateTime.now());
        return ResponseEntity.ok().body("회원가입 승인 성공!");
    }

    //가입거부
    public ResponseEntity<String> disApproval(Long id) {
        userRepository.userReject(id);
        return ResponseEntity.ok().body("회원가입 거부 성공!");
    }
}
