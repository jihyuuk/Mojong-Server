package com.example.mojong;

import com.example.mojong.model.entity.Category;
import com.example.mojong.model.entity.Item;
import com.example.mojong.model.entity.ROLE;
import com.example.mojong.model.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class InIt {

    @Autowired
    private EntityManager em;

    @Transactional
    public void init(){

        //카테고리 저장
        List<Category> list = new ArrayList<>();
        list.add(new Category("고추"));
        list.add(new Category("풋고추"));
        list.add(new Category("청양고추"));
        list.add(new Category("꽈리고추"));
        list.add(new Category("파프리카"));
        list.add(new Category("호박"));
        list.add(new Category("토마토"));
        list.add(new Category("방울토마토"));
        list.add(new Category("오이/가지"));
        list.add(new Category("수박/참외"));
        list.add(new Category("기타"));
        list.add(new Category("쌈채류"));

        for (Category category : list) {
            em.persist(category);
        }
        
        //아이템 저장
        //고추
        em.persist(new Item(list.get(0),"케이스타","72구 / 무색",300));
        em.persist(new Item(list.get(0),"점핑","50구 / 청색 / 매운맛약",400));
        em.persist(new Item(list.get(0),"칼라911","50구 / 무색 / 하우스,칼라",400));
        em.persist(new Item(list.get(0),"올복합","50구 / 금색",500));

        //풋고추
        em.persist(new Item(list.get(1),"롱그린","50구 / 녹색",500));
        em.persist(new Item(list.get(1),"오이고추","50구 / 흰색",500));
        em.persist(new Item(list.get(1),"미인풋고추","50구 / 흰판+검정",500));

        //청양
        em.persist(new Item(list.get(2),"PR 청양","50구 / 빨강",500));
        em.persist(new Item(list.get(2),"큰청양","50구 / 빨강",400));
        em.persist(new Item(list.get(2),"청양","50구 / 빨강",300));

        //꽈리
        em.persist(new Item(list.get(3),"더순한꽈리","50구 / 흰판+연청",500));
        em.persist(new Item(list.get(3),"꽈리고추","50구 / 노랑",300));

        //파프리카
        em.persist(new Item(list.get(4),"파프리카(노랑)","컵포트 / 녹색",300));
        em.persist(new Item(list.get(4),"파프리카(빨강)","컵포트 / 빨강",300));
        em.persist(new Item(list.get(4),"파프리카(청색)","컵포트 / 청색",300));
        em.persist(new Item(list.get(4),"피망","컵포트",300));

        //호박
        em.persist(new Item(list.get(5),"애호박","50구 / 흰색",400));
        em.persist(new Item(list.get(5),"풋호박","50구 / 녹색 / 매운맛약",400));
        em.persist(new Item(list.get(5),"맷돌호박","50구 / 노랑판 / 하우스,칼라",500));
        em.persist(new Item(list.get(5),"단호박","50구 / 무색",300));
        em.persist(new Item(list.get(5),"보우짱","32구",2000));

        //토마토
        em.persist(new Item(list.get(6),"일반토마토","50구 / 청색",400));
        em.persist(new Item(list.get(6),"흑토마토","컵포트",3000));
        em.persist(new Item(list.get(6),"도태랑","50구 / 무색",700));

        //방울토마토
        em.persist(new Item(list.get(7),"일반방울","50구 / 흰색",400));
        em.persist(new Item(list.get(7),"대추노랑","50구 / 노랑판",1000));
        em.persist(new Item(list.get(7),"대추빨강","50구 / 녹색판",1000));

        //오이/가지
        em.persist(new Item(list.get(8),"가지","50구",300));
        em.persist(new Item(list.get(8),"오이","50구",300));
        em.persist(new Item(list.get(8),"노각오이","50구 / 녹색판",300));

        //수박/참외
        em.persist(new Item(list.get(9),"참외","50구",300));
        em.persist(new Item(list.get(9),"애플참외","50구",1000));
        em.persist(new Item(list.get(9),"수박","50구",300));
        em.persist(new Item(list.get(9),"복수박","빨강컵",1000));
        em.persist(new Item(list.get(9),"애플수박","검은컵",1000));

        //기타
        em.persist(new Item(list.get(10),"가지고추","흰색컵",3000));
        em.persist(new Item(list.get(10),"당조고추","무색컵",3000));
        em.persist(new Item(list.get(10),"여주","컵포트",1000));
        em.persist(new Item(list.get(10),"수세미","컵포트",1000));
        em.persist(new Item(list.get(10),"산마늘","컵포트",1000));

        //쌈채류
        em.persist(new Item(list.get(11),"쌈채류","72구 / 줄판매(6개)",1000));
        em.persist(new Item(list.get(11),"당귀","주황컵",3000));
        em.persist(new Item(list.get(11),"옥수수","72구 / 줄판매(6개)",1000));
        em.persist(new Item(list.get(11),"땅콩","50구 / 줄판매(6개)",1000));
        em.persist(new Item(list.get(11),"작두콩","컵포트",1000));
        em.persist(new Item(list.get(11),"감자","컵포트",1000));
        em.persist(new Item(list.get(11),"들깻잎","72구 / 줄판매(6개)",1000));


        //홍길동저장
        em.persist(new User("홍길동","123", ROLE.ROLE_ADMIN,true,true));
        em.persist(new User("일길동","123", ROLE.ROLE_USER,true,true));
        em.persist(new User("이길동","123", ROLE.ROLE_USER,true,true));
        em.persist(new User("삼길동","123", ROLE.ROLE_USER,true,true));
        em.persist(new User("사길동","123", ROLE.ROLE_USER,true,true));
        em.persist(new User("오길동","123", ROLE.ROLE_USER,true,true));
        em.persist(new User("육길동","123", ROLE.ROLE_USER,true,true));
        em.persist(new User("칠길동","123", ROLE.ROLE_USER,true,true));
        em.persist(new User("팔길동","123", ROLE.ROLE_USER,true,true));
        em.persist(new User("구길동","123", ROLE.ROLE_USER,true,true));
        
        em.persist(new User("마리오","123", ROLE.ROLE_USER,false,false));
        em.persist(new User("루이지","123", ROLE.ROLE_USER,false,false));
        em.persist(new User("산리오","123", ROLE.ROLE_USER,false,false));
        em.persist(new User("모동숲","123", ROLE.ROLE_USER,false,false));
        
        
    }
}
