package com.example.mojong.websocket;

import com.example.mojong.model.dto.sale.SaleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    ObjectMapper om = new ObjectMapper();
    WebSocketSession printerSession;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("연결됨"+session.getId());
        printerSession = session;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("메세지 받음" + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        printerSession = null;
        System.out.println("종료됨");
    }

    //데이터 보내기
    public boolean sendData(SaleDTO saleDTO){

        if(printerSession != null){
            try{
                printerSession.sendMessage(new TextMessage(om.writeValueAsString(saleDTO)));
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return  false;
    }
}
