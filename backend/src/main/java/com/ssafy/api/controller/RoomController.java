package com.ssafy.api.controller;

import com.ssafy.api.request.RoomCreatePostRequest;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.RoomService;
import com.ssafy.db.entity.Room;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.api.response.RoomCreatePostRes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rooms")
@Api(value = "방정보 API", tags = {"Rooms "})
public class RoomController {

	@Autowired
	RoomService roomService;

	/**
	 * 방 생성 */
	@PostMapping
	@ApiOperation(value = "방 생성", notes = "openVidu서버에서 생성한 방을 저장한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> createRoom(@RequestBody RoomCreatePostRequest roomCreatePostRequest, HttpSession httpSession) throws Exception {
		Room room = null;

		if ((room = roomService.createRoom(roomCreatePostRequest)) != null){ // 새로운 방일 경우 ( 기존에 존재하는 방이 아닐 경우)
			roomService.addTags(roomCreatePostRequest.getKeywords(), room); //키워드가 있다면 db에 추가
			// 방생성 성공 응답
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		}
		return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Failed to create the room : internal error by already existing room"));
	}

	/**
	 * 방 상세 조회하기 - TO DO : history 어떻게 관리할 것이냐!?
	 */
	@GetMapping("/{sessionId}")
	@ApiOperation(value = "방 상세 조회", notes = "선택한 방의 상세 정보를 조회한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "세션방 만료", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<Room> detailRoom(@RequestParam String sessionId, HttpSession httpSession) throws Exception {
		Optional<Room> selectedRoom;
		selectedRoom = roomService.detailRoom(sessionId);
		if (selectedRoom.isPresent()){
			return ResponseEntity.status(200).body(selectedRoom.get());
		}
		return ResponseEntity.status(404).body(null);
	}

	/**
	 * 방 삭제하기 - 사실, 방이름도 unique key인데 이걸로 해도 될거 같기도 하고
	 * */
	@DeleteMapping("/")
	@ApiOperation(value = "방 삭제하기", notes = "선택한 방의 상세 정보를 조회한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "세션방 만료", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> removeRoom(@RequestParam String sessionId, HttpSession httpSession) throws Exception {
		Optional<Room> room = roomService.getRoomBySessionId(sessionId);
		if(room.isPresent()){
			roomService.deleteRoom(room.get());
			return ResponseEntity.status(200).body(RoomCreatePostRes.of(200, "Delete Success"));
		}
		return ResponseEntity.status(500).body(RoomCreatePostRes.of(500, "Failed to delete room : room requested has not been in DB"));
	}
	/**
	 * 방 리스트 조회
	 */
	@GetMapping
	@ApiOperation(value = "방 전체 조회", notes = "DB에 저장한 방리스트를 얻어온다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<List<Room>> getRoomList(){
		return ResponseEntity.status(200).body(roomService.getRoomList());
	}

	/**
	 * 방 리스트 - 검색하기
	 * */
	@GetMapping("/search")
	@ApiOperation(value = "선택하여 방 검색", notes = "검색 종류에 따라 검색 단어를 포함하는 방리스트를 얻어온다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<List<Room>> getRoomListBySearch(@RequestParam("search_type") String searchType, @RequestParam String input){
		if(searchType.equals("title"))
			return ResponseEntity.status(200).body(roomService.getRoomByRoomTitle(input));
		else if(searchType.equals("nickname")) //TO-DO : one-to-many관계테이블 만들어야함
			return ResponseEntity.status(200).body(roomService.getRoomByHostNickname(input));
		else if(searchType.equals("keyword"))
			return ResponseEntity.status(200).body(roomService.getRoomListByKeyword(input));
		else
			return ResponseEntity.status(200).body(null);
	}
}
