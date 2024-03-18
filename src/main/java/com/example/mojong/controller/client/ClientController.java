package com.example.mojong.controller.client;

import com.example.mojong.model.dto.HistoryDTO;
import com.example.mojong.model.dto.InitDataDTO;
import com.example.mojong.model.dto.sale.SaleDTO;
import com.example.mojong.model.dto.sale.SaleDetailDTO;
import com.example.mojong.model.entity.Category;
import com.example.mojong.model.entity.Sale;
import com.example.mojong.service.CategoryService;
import com.example.mojong.service.ReceiptService;
import com.example.mojong.service.SaleService;
import com.example.mojong.websocket.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final SaleService saleService;
    private final CategoryService categoryService;
    private final ReceiptService receiptService;
    private final WebSocketHandler webSocketHandler;

    @GetMapping("/initData")
    public InitDataDTO init(Authentication authentication){
        List<Category> mojongs = categoryService.getCategories();
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        return new InitDataDTO(mojongs, username, role);
    }

    @GetMapping("/mojongs")
    public List<Category> items(){
        return categoryService.getCategories();
    }

//    @GetMapping("/items")
//    public String items(){
//        //return categoryService.getCategories();
//        return "[\n" +
//                "  {\n" +
//                "    \"category\": \"음식\",\n" +
//                "    \"items\": [\n" +
//                "      {\"name\": \"피자\", \"description\": \"인기 있는 이탈리아식 요리\", \"price\": 700},\n" +
//                "      {\"name\": \"파스타\", \"description\": \"신선한 재료로 만든 이탈리아 전통 요리\", \"price\": 1200},\n" +
//                "      {\"name\": \"스테이크\", \"description\": \"고품질 소고기 스테이크\", \"price\": 2000},\n" +
//                "      {\"name\": \"샐러드\", \"description\": \"건강한 채소와 신선한 드레싱\", \"price\": 800},\n" +
//                "      {\"name\": \"스시\", \"description\": \"신선한 생선과 쌀을 사용한 일본식 요리\", \"price\": 1500},\n" +
//                "      {\"name\": \"햄버거\", \"description\": \"송로버섯과 베이컨으로 만든 소고기 패티\", \"price\": 1000}\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"category\": \"영화\",\n" +
//                "    \"items\": [\n" +
//                "      {\"name\": \"액션\", \"description\": \"스릴 넘치는 액션 영화\", \"price\": 500},\n" +
//                "      {\"name\": \"코미디\", \"description\": \"유머와 재미로 가득한 코미디 영화\", \"price\": 500},\n" +
//                "      {\"name\": \"드라마\", \"description\": \"감동적인 드라마 영화\", \"price\": 500},\n" +
//                "      {\"name\": \"공상과학\", \"description\": \"미래를 상상하게 만드는 공상과학 영화\", \"price\": 500},\n" +
//                "      {\"name\": \"로맨스\", \"description\": \"사랑과 감동을 담은 로맨스 영화\", \"price\": 500},\n" +
//                "      {\"name\": \"공포\", \"description\": \"긴장감과 공포를 유발하는 공포 영화\", \"price\": 500}\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"category\": \"동물\",\n" +
//                "    \"items\": [\n" +
//                "      {\"name\": \"개\", \"description\": \"사랑스러운 반려 동물\", \"price\": 5000},\n" +
//                "      {\"name\": \"고양이\", \"description\": \"귀여운 새끼 고양이\", \"price\": 2500},\n" +
//                "      {\"name\": \"새\", \"description\": \"다양한 종류의 새\", \"price\": 1000},\n" +
//                "      {\"name\": \"토끼\", \"description\": \"귀여운 애완용 토끼\", \"price\": 2000},\n" +
//                "      {\"name\": \"뱀\", \"description\": \"멋진 비단뱀\", \"price\": 1500}\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"category\": \"여행지\",\n" +
//                "    \"items\": [\n" +
//                "      {\"name\": \"파리\", \"description\": \"로맨틱한 도시\", \"price\": 5000},\n" +
//                "      {\"name\": \"도쿄\", \"description\": \"모던하고 화려한 도시\", \"price\": 4500},\n" +
//                "      {\"name\": \"뉴욕\", \"description\": \"번화한 대도시\", \"price\": 6000},\n" +
//                "      {\"name\": \"로마\", \"description\": \"역사적인 명소가 많은 도시\", \"price\": 5500}\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"category\": \"운동\",\n" +
//                "    \"items\": [\n" +
//                "      {\"name\": \"축구\", \"description\": \"인기 있는 팀 스포츠\", \"price\": 2000},\n" +
//                "      {\"name\": \"농구\", \"description\": \"높이 뛰는 스포츠\", \"price\": 2500},\n" +
//                "      {\"name\": \"테니스\", \"description\": \"라켓을 사용하는 스포츠\", \"price\": 1500}\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"category\": \"색상\",\n" +
//                "    \"items\": [\n" +
//                "      {\"name\": \"빨강\", \"description\": \"열정적인 색상\", \"price\": 500},\n" +
//                "      {\"name\": \"파랑\", \"description\": \"차분한 색상\", \"price\": 500},\n" +
//                "      {\"name\": \"노랑\", \"description\": \"밝고 활기찬 색상\", \"price\": 500},\n" +
//                "      {\"name\": \"초록\", \"description\": \"자연스러운 색상\", \"price\": 500}\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"category\": \"사계절\",\n" +
//                "    \"items\": [\n" +
//                "      {\"name\": \"봄\", \"description\": \"꽃이 피는 따뜻한 계절\", \"price\": 300},\n" +
//                "      {\"name\": \"여름\", \"description\": \"더운 날씨와 해변의 계절\", \"price\": 300},\n" +
//                "      {\"name\": \"가을\", \"description\": \"단풍이 아름다운 계절\", \"price\": 300},\n" +
//                "      {\"name\": \"겨울\", \"description\": \"눈이 내리는 차가운 계절\", \"price\": 300}\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"category\": \"꽃\",\n" +
//                "    \"items\": [\n" +
//                "      {\"name\": \"장미\", \"description\": \"사랑을 상징하는 꽃\", \"price\": 1000},\n" +
//                "      {\"name\": \"튤립\", \"description\": \"우아함을 상징하는 꽃\", \"price\": 800},\n" +
//                "      {\"name\": \"백합\", \"description\": \"순수함을 상징하는 꽃\", \"price\": 1200}\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"category\": \"과일\",\n" +
//                "    \"items\": [\n" +
//                "      {\"name\": \"사과\", \"description\": \"신선하고 달콤한 과일\", \"price\": 300},\n" +
//                "      {\"name\": \"바나나\", \"description\": \"영양가가 높은 과일\", \"price\": 200}]}]";
//
//    }

    @PostMapping("/sale")
    public Long sale(@RequestBody SaleDTO saleDTO, Authentication authentication){

        Long saleId = saleService.sale(saleDTO,authentication.getName());

        if(saleDTO.isPrint()){
            webSocketHandler.sendData(saleDTO);
        }

        return saleId;
    }

    @GetMapping("/sale/{id}")
    public SaleDetailDTO saleDetail(@PathVariable Long id){
        return saleService.detail(id);
    }

    @GetMapping("/history")
    public HistoryDTO history(Pageable pageable, Authentication authentication){
        return saleService.history(pageable, authentication.getName());
    }

    @GetMapping("/receipt/{id}")
    public ResponseEntity<String> receipt(@PathVariable Long id){
        return receiptService.print(id);
    }



}
