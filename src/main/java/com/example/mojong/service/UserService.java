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
    public ResponseEntity<String> blocking(BlockDTO blockDTO){
        Boolean enabled = !blockDTO.getBlocking();
        Long userId = blockDTO.getUserId();

        int row = userRepository.userEnabled(userId, enabled);
        System.out.println("row = " + row);
        return row > 0 ? ResponseEntity.ok().body("사용자 활성화 상태가 변경되었습니다.") : ResponseEntity.notFound().build();
    }

    //가입승인
    public ResponseEntity<String> approval(ApprovalDTO approvalDTO) {
        Boolean approve = approvalDTO.getApprove();
        Long userId = approvalDTO.getUserId();

        int row = 0;

        if(approve){
            row = userRepository.userApprove(userId, LocalDateTime.now());
        }else{
            row = userRepository.userReject(userId);
        }

        return row > 0 ? ResponseEntity.ok().body("요청성공") : ResponseEntity.notFound().build();
    }
}
