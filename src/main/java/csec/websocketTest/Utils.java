package csec.websocketTest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 제네릭 메서드로 JSON 문자열을 객체로 변환
    public static <T> T getObject(String json, Class<T> clazz) throws Exception {
        return objectMapper.readValue(json, clazz);
    }
}
