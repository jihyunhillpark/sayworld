package com.ssafy.api.controller;

import com.ssafy.api.request.RoomCreatePostRequest;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.RoomService;
import com.ssafy.db.entity.Room;
import com.ssafy.db.entity.Tag;
import io.openvidu.java.client.*;
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
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rooms")
@Api(value = "방정보 API", tags = {"Rooms "})
public class RoomController {

	@Autowired
	RoomService roomService;

	// OpenVidu object as entrypoint of the SDK
	private OpenVidu openVidu;

	// Collection to pair session names and OpenVidu Session objects
	// private Map<String, Session> mapSessions = new ConcurrentHashMap<>();
	// Collection to pair session names and tokens (the inner Map pairs tokens and
	// role associated)
//	private Map<String, Map<String, OpenViduRole>> mapSessionNamesTokens = new ConcurrentHashMap<>();

	// OpenVidu server가 리스닝하는 URL
	private String OPENVIDU_URL;
	// OpenVidu server에 접속할때 필요한 SECRET
	private String SECRET;

	public RoomController(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openviduUrl) {
		this.SECRET = secret;
		this.OPENVIDU_URL = openviduUrl;
		this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
	}
	/* Newly Added - 방리스트 조회
	 * TO DO - 호스트 id에 대한 처리 필요, 지금은 OpenVidu 서버에서 바로 얻어오고 있지만, DB에서 얻어와야할까!?
	 */
	@GetMapping
	@ApiOperation(value = "방조회", notes = "openVidu서버에서 방리스트를 얻어온다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<List<Session>> getRoomList(){
		List<Session> RoomsInServer = this.openVidu.getActiveSessions();
		return ResponseEntity.status(200).body(RoomsInServer);
	}

	/*
	 * TO DO - 같은 방제를 허용하려면 호스트 id 를 사용해서 식별자 처리해야할까 -> No! 그냥 방제 구분하자...
	 */
	@PostMapping
	@ApiOperation(value = "방 생성", notes = "openVidu서버에서 방을 생성한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> createRoom(@RequestBody RoomCreatePostRequest roomCreatePostRequest , HttpSession httpSession) throws Exception {
		// 브라우저 로그인 체크
		// checkUserLogged(httpSession);
		String roomName = roomCreatePostRequest.getRoomName();
		OpenViduRole role = OpenViduRole.PUBLISHER;
		String serverData = "{\"serverData\": \"" + httpSession.getAttribute("loggedUser") + "\"}";
		ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();


		if (roomService.getRoomByRoomTitle(roomName).isPresent()) // 이미 방이 있는 경우 실패 응답 - Bad Request
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "failed to create the room : existing already"));
		else { //방이 없는 경우 - 방생성
			System.out.println("New Room session " + roomName);
			try{
				Session session = this.openVidu.createSession(); // OpenVidu 룸 세션 생성
				// 최근 생성된 connectionProperties로 룸 세션 생성하고, 해당 룸+커넥션에대한 토큰 얻기
				String token = session.createConnection(connectionProperties).getToken();

				Room room = roomService.createRoom(roomCreatePostRequest, session.getSessionId());
				List<Tag> mappedKeywords = roomService.addTags(roomCreatePostRequest.getKeywords(), room.getRoomId());
				if(mappedKeywords.size() > 0 ) room.getTags().addAll(mappedKeywords);
				// 방생성 성공 응답
				return ResponseEntity.status(200).body(RoomCreatePostRes.of(200, "Success", token));

			} catch (Exception e) {
				// 방생성 실패 응답 - 서버쪽
				return ResponseEntity.status(500).body(BaseResponseBody.of(500, "failed to create the room : internal error"));
			}
		}
	}

	/*
	 * 방상세 조회하기 - 좀 더 효율적인 방법 필요
	 */
	@GetMapping("/{sessionId}")
	@ApiOperation(value = "방 상세 조회", notes = "선택한 방의 상세 정보를 조회한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "세션방 만료", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> detailRoom(@RequestParam String sessionId, HttpSession httpSession) throws Exception {
		OpenViduRole role = OpenViduRole.PUBLISHER;
		String serverData = "{\"serverData\": \"" + httpSession.getAttribute("loggedUser") + "\"}";
		ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();
		List<Session> RoomsInServer = this.openVidu.getActiveSessions();
		Optional<Room> selectedRoom;

		// openVidu서버로 부터 받아온 세션 리스트를 Map에 저장 - 클라이언트쪽에서는 room
		Map<String, Session> activeSessions;
		activeSessions = RoomsInServer
				.stream()
				.collect(Collectors.toMap(
						Session::getSessionId,
						Function.identity()
						)
				);
		System.out.println(sessionId);
		if( (selectedRoom = roomService.detailRoom(sessionId)).isPresent() ) {
			System.out.println(sessionId);
			try{
				Session sessionInServer = activeSessions.get(sessionId);
				if(sessionInServer != null) {
					String token = sessionInServer.createConnection(connectionProperties).getToken();
					return ResponseEntity.status(200).body(RoomCreatePostRes.of(200, "Success", token));
				}
				else{
					// 실제로 유효하지 않은데 DB에 남아있는 경우
					roomService.deleteRoom(selectedRoom.get());
					return ResponseEntity.status(404).body(RoomCreatePostRes.of(404, "failed to enter the room : It was in DB but not available on OpenVidu server"));
				}
			} catch (OpenViduJavaClientException e1){
				return ResponseEntity.status(500).body(BaseResponseBody.of(500, "failed to enter the room : internal error"));
			} catch (OpenViduHttpException e2) {
				if (404 == e2.getStatus()) {
					// session이 유효하지 않을 때 DB에서 해당 방을 삭제한다.
					roomService.deleteRoom(selectedRoom.get());
					return ResponseEntity.status(404).body(BaseResponseBody.of(404, "failed to enter the room : room close by unexpected behavior"));
				}
			}

		}
		return ResponseEntity.status(500).body(BaseResponseBody.of(500, "failed to enter the room : internal error"));
	}

}
