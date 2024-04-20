//package com.example.mojong;
//
//import com.example.mojong.model.entity.Category;
//import com.example.mojong.model.entity.Item;
//import com.example.mojong.model.entity.ROLE;
//import com.example.mojong.model.entity.User;
//import jakarta.persistence.EntityManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class InIt {
//
//    @Autowired
//    private EntityManager em;
//
//    @Transactional
//    public void init() {
//
//        //카테고리 저장
//        List<Category> list = new ArrayList<>();
//        list.add(new Category("고추"));
//        list.add(new Category("풋고추"));
//        list.add(new Category("청양고추"));
//        list.add(new Category("꽈리고추"));
//        list.add(new Category("호박"));
//        list.add(new Category("토마토"));
//        list.add(new Category("방울토마토"));
//        list.add(new Category("파프리카"));
//        list.add(new Category("오이/가지"));
//        list.add(new Category("수박/참외"));
//        list.add(new Category("기타"));
//        list.add(new Category("건너편"));
//
//        for (Category category : list) {
//            em.persist(category);
//        }
//
//        //아이템 저장
//        //고추
//        em.persist(new Item(list.get(0), "케이스타(판)", "72구 / 무색", 28000));
//        em.persist(new Item(list.get(0), "케이스타(낱개)", "1구 / 무색", 400));
//        em.persist(new Item(list.get(0), "불꽃스타", "50구 / 파란줄 / 매운맛약", 500));
//        em.persist(new Item(list.get(0), "티탄불패", "50구 / 분홍줄 / 중간맵기", 500));
//        em.persist(new Item(list.get(0), "매끄니", "50구 / 무색 / 하우스,중간맵기", 500));
//
//        //풋고추
//        em.persist(new Item(list.get(1), "오이고추", "50구 / 은판+녹색줄", 600));
//        em.persist(new Item(list.get(1), "미인풋고추", "50구 / 노란판", 600));
//
//        //청양
//        em.persist(new Item(list.get(2), "PR 청양", "50구 / 빨판+녹색줄", 600));
//        em.persist(new Item(list.get(2), "큰청양", "50구 / 빨판", 500));
//        em.persist(new Item(list.get(2), "청양", "72구 / 검판+빨간줄", 400));
//
//        //꽈리
//        em.persist(new Item(list.get(3), "더순한꽈리", "50구 / 녹판+노란줄", 600));
//        em.persist(new Item(list.get(3), "꽈리고추", "50구", 400));
//
//        //호박
//        em.persist(new Item(list.get(4), "애호박", "50구 / 은색판", 500));
//        em.persist(new Item(list.get(4), "풋호박", "50구 / 녹색판", 500));
//        em.persist(new Item(list.get(4), "맷돌호박", "50구 / 노란판", 600));
//        em.persist(new Item(list.get(4), "단호박", "50구 / 무색판", 500));
//        em.persist(new Item(list.get(4), "보우짱", "32구", 2500));
//
//        //토마토
//        em.persist(new Item(list.get(5), "도태랑", "50구", 800));
//        em.persist(new Item(list.get(5), "흑토마토", "컵포트", 3000));
//
//        //방울토마토
//        em.persist(new Item(list.get(6), "대추빨강", "50구 / 녹색판", 1000));
//        em.persist(new Item(list.get(6), "대추노랑", "50구 / 노랑판", 1000));
//
//        //파프리카
//        em.persist(new Item(list.get(7), "파프리카(노랑)", "노란색 컵포트", 1500));
//        em.persist(new Item(list.get(7), "파프리카(빨강)", "파란색 컵포트", 1500));
//        em.persist(new Item(list.get(7), "파프리카(주황)", "주황색 컵포트", 1500));
//
//        //오이/가지
//        em.persist(new Item(list.get(8), "가지", "50구", 400));
//        em.persist(new Item(list.get(8), "오이", "50구", 400));
//        em.persist(new Item(list.get(8), "노각오이", "50구 / 노란판", 400));
//
//        //수박/참외
//        em.persist(new Item(list.get(9), "참외", "50구", 400));
//        em.persist(new Item(list.get(9), "애플참외", "50구", 1000));
//
//        em.persist(new Item(list.get(9), "수박", "50구", 500));
//        em.persist(new Item(list.get(9), "복수박", "컵포트", 2000));
//        em.persist(new Item(list.get(9), "애플수박", "컵포트", 3000));
//
//        //기타
//        em.persist(new Item(list.get(10), "여주", "컵포트", 1000));
//        em.persist(new Item(list.get(10), "가지고추", "흰색컵", 3000));
//        em.persist(new Item(list.get(10), "당조고추", "무색컵", 3000));
//        //em.persist(new Item(list.get(10), "수세미", "컵포트", 1000));
//        //em.persist(new Item(list.get(10), "산마늘", "컵포트", 1000));
//
//        //건너편
//        em.persist(new Item(list.get(11), "쌈채류(판)", "72구", 8000));
//        em.persist(new Item(list.get(11), "쌈채류(줄)", "6구", 1000));
//        em.persist(new Item(list.get(11), "옥수수(판)", "72구", 15000));
//        em.persist(new Item(list.get(11), "옥수수(줄)", "6구", 1500));
//        em.persist(new Item(list.get(11), "땅콩(판)", "50구", 15000));
//        em.persist(new Item(list.get(11), "땅콩(줄)", "5구", 1500));
////        em.persist(new Item(list.get(11),"쌈채류","72구 / 줄판매(6개)",1000));
////        em.persist(new Item(list.get(11),"당귀","주황컵",3000));
////        em.persist(new Item(list.get(11),"작두콩","컵포트",1000));
////        em.persist(new Item(list.get(11),"감자","컵포트",1000));
////        em.persist(new Item(list.get(11),"들깻잎","72구 / 줄판매(6개)",1000));
//
//
//        //홍길동저장
////        em.persist(new User("홍길동", "123", ROLE.ROLE_ADMIN, true, true));
////        em.persist(new User("일길동", "123", ROLE.ROLE_USER, true, true));
////        em.persist(new User("이길동", "123", ROLE.ROLE_USER, true, true));
////        em.persist(new User("삼길동", "123", ROLE.ROLE_USER, true, true));
////        em.persist(new User("사길동", "123", ROLE.ROLE_USER, true, true));
////        em.persist(new User("오길동", "123", ROLE.ROLE_USER, true, true));
////        em.persist(new User("육길동", "123", ROLE.ROLE_USER, true, true));
////        em.persist(new User("칠길동", "123", ROLE.ROLE_USER, true, true));
////        em.persist(new User("팔길동", "123", ROLE.ROLE_USER, true, true));
////        em.persist(new User("구길동", "123", ROLE.ROLE_USER, true, true));
////
////        em.persist(new User("마리오", "123", ROLE.ROLE_USER, false, false));
////        em.persist(new User("루이지", "123", ROLE.ROLE_USER, false, false));
////        em.persist(new User("산리오", "123", ROLE.ROLE_USER, false, false));
////        em.persist(new User("모동숲", "123", ROLE.ROLE_USER, false, false));
//
//
//    }
//}
