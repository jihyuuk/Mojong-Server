package com.example.mojong.websocket;

import com.example.mojong.model.dto.ReceiptDTO;
import com.example.mojong.model.dto.sale.SaleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private ObjectMapper om = new ObjectMapper();
    private WebSocketSession PRINTER_SESSION;

    //연결 시작
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //세션설정
        PRINTER_SESSION = session;
    }

    //메세지 수신
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }

    //연결 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //세션삭제
        PRINTER_SESSION = null;
    }

    //데이터 보내기
    public void sendData(ReceiptDTO dto) throws Exception{
        PRINTER_SESSION.sendMessage(new TextMessage(om.writeValueAsString(dto)));
    }

    public boolean isConnected(){
        return PRINTER_SESSION != null;
    }
}
