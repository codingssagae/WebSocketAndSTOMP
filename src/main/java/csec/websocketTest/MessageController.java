package csec.websocketTest;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/chatroom/{id}")
    public void message(Message message){
        simpMessageSendingOperations.convertAndSend("/sub/chatroom/"+message.getChatroomId(),message);
    } // message 에 channelId를 가지고 잇음. 이것을 꺼내서 목적 url 작성해서 매시지 보냄
    // 현재 스프링 부트 서버 하나에서만 작동
}
