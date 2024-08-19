package csec.websocketTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    //ws://localhost:8080/room
    private final Map<String,WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        var sessionId = session.getId();
        sessions.put(sessionId,session);

        Message message = Message.builder().sender(sessionId).receiver("all").build();
        message.newConnect();

        sessions.values().forEach(s ->{
            try{
                if(!s.getId().equals(sessionId)){
                    s.sendMessage(new TextMessage(message.toString()));
                }
            }catch (Exception e){
            }
        });
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        String payload = textMessage.getPayload();
        for (WebSocketSession s : sessions.values()){
            s.sendMessage(textMessage);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
       var sessionId = session.getId();
       sessions.remove(sessionId);
       final Message message = new Message();
       message.closeConnect();
       message.setSender(sessionId);
       sessions.values().forEach(s ->{
           try {
               s.sendMessage(new TextMessage(message.toString()));
           } catch (IOException e) {}
       });
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }
}
