package csec.websocketTest;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Message {

    private String type;
    private String sender;
    private String receiver;
    private Object data;

    public void newConnect(){
        this.type = "new";
    }

    public void closeConnect(){
        this.type = "close";
    }

    @Override
    public String toString() {
        if(this.type=="new"){
            return ""+sender + '\'' +
                    "님이 입장 하셨습니다.";
        }
        else {
            return ""+sender + '\'' +
                    "님이 퇴장 하셨습니다.";
        }
    }
}
