package com.ssafy.api.response;
import com.ssafy.common.model.response.BaseResponseBody;
import org.springframework.http.HttpStatus;


import lombok.Getter;
import lombok.Setter;

/**
 * 서버 요청에대한 기본 응답값(바디) 정의.
 */
@Getter
@Setter
public class RoomResponseBody extends BaseResponseBody {
    String message = null;
    Integer statusCode = null;
    String roomToken = null; //필수 값

    public RoomResponseBody() {}

    public RoomResponseBody(Integer statusCode){
        this.statusCode = statusCode;
    }

    public RoomResponseBody(Integer statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
    }

    public static RoomResponseBody of(Integer statusCode, String message, String roomToken) {
        RoomResponseBody body = new RoomResponseBody();
        body.roomToken = roomToken;
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }
}
