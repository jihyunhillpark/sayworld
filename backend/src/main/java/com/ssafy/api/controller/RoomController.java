package com.ssafy.api.controller;

import com.ssafy.api.request.RoomCreatePostRequest;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.RoomService;
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
import javax.xml.ws.Service;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/rooms")
@Api(value = "방정보 API", tags = {"Rooms "})
public class RoomController {

	@Autowired
	RoomService roomService;

	// OpenVidu object as entrypoint of the SDK
	private OpenVidu openVidu;

	// Collection to pair session names and OpenVidu Session objects
//	private Map<String, Session> mapSessions = new ConcurrentHashMap<>();
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

	/* Newly Added - 방 생성 : 원래 Url = rooms/get-token
	 * TO DO - 같은 방제를 허용하려면 호스트 id 를 사용해서 식별자 처리해야할까!?
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
				// 최근 생성된 connectionProperties로 룸 세션 생성하고, 해당 룸에대한 토큰 얻기
				String token = session.createConnection(connectionProperties).getToken();
				roomService.createRoom(roomCreatePostRequest, token);
				// 방생성 성공 응답
				return ResponseEntity.status(200).body(RoomCreatePostRes.of(200, "Success", token));

			} catch (Exception e) {
				// 방생성 실패 응답 - 서버쪽
				return ResponseEntity.status(500).body(BaseResponseBody.of(500, "failed to create the room : internal error"));
			}
		}
	}

	/* Newly Added - 방리스트 조회
	 * TO DO - 호스트 id에 대한 처리 필요
	 */
//	@GetMapping
//	@ApiOperation(value = "방조회", notes = "openVidu서버에서 방리스트를 얻어온다.")
//	@ApiResponses({
//			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
//			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
//			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
//			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
//	})
//	public ResponseEntity<List<Session>> getRoomList(){
//		List<Session> RoomsInServer = this.openVidu.getActiveSessions();
//		return ResponseEntity.status(200).body(RoomsInServer);
//	}

//	@RequestMapping(value = "/remove-user", method = RequestMethod.POST)
//	@ApiOperation(value = "room 삭제", notes = "회원을 삭제한다.")
//	@ApiResponses({
//			@ApiResponse(code = 200, message = "성공"),
//			@ApiResponse(code = 401, message = "인증 실패"),
//			@ApiResponse(code = 404, message = "사용자 없음"),
//			@ApiResponse(code = 500, message = "서버 오류")
//	})
//	public ResponseEntity<JSONObject> removeUser(@RequestBody String sessionNameToken, HttpSession httpSession)
//			throws Exception {
//
//		try {
//			checkUserLogged(httpSession);
//		} catch (Exception e) {
//			return getErrorResponse(e);
//		}
//		System.out.println("Removing user | {sessionName, token}=" + sessionNameToken);
//
//		// Retrieve the params from BODY
//		JSONObject sessionNameTokenJSON = (JSONObject) new JSONParser().parse(sessionNameToken);
//		String sessionName = (String) sessionNameTokenJSON.get("sessionName");
//		String token = (String) sessionNameTokenJSON.get("token");
//
//		// If the session exists
//		if (this.mapSessions.get(sessionName) != null && this.mapSessionNamesTokens.get(sessionName) != null) {
//
//			// If the token exists
//			if (this.mapSessionNamesTokens.get(sessionName).remove(token) != null) {
//				// User left the session
//				if (this.mapSessionNamesTokens.get(sessionName).isEmpty()) {
//					// Last user left: session must be removed
//					this.mapSessions.remove(sessionName);
//				}
//				return new ResponseEntity<>(HttpStatus.OK);
//			} else {
//				// The TOKEN wasn't valid
//				System.out.println("Problems in the app server: the TOKEN wasn't valid");
//				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//
//		} else {
//			// The SESSION does not exist
//			System.out.println("Problems in the app server: the SESSION does not exist");
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	private void checkUserLogged(HttpSession httpSession) throws Exception {
//		if (httpSession == null || httpSession.getAttribute("loggedUser") == null) {
//			throw new Exception("User not logged");
//		}
//	}
//
//	@PostMapping(value = "/")
//	@ApiOperation(value = "방 생성", notes = "openVidu서버에서 방을 생성한다.")
//	@ApiResponses({
//			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
//			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
//			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
//			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
//	})
//	public ResponseEntity<? extends BaseResponseBody> createRoom(@RequestBody RoomCreatePostRequest roomCreatePostRequest , HttpSession httpSession) throws Exception {
//		// 브라우저 로그인 체크
//		checkUserLogged(httpSession);
//		String roomName = roomCreatePostRequest.getRoomName();
//		OpenViduRole role = OpenViduRole.PUBLISHER;
//		String serverData = "{\"serverData\": \"" + httpSession.getAttribute("loggedUser") + "\"}";
//		ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();
//
//		if (this.mapSessions.get(roomName) == null) { //방이 있는지 없는지 검사, 똑같은 방제가 있을 경우
//			// New session
//			System.out.println("New session " + roomName);
//			try {
//
//				// Create a new OpenVidu Session
//				Session session = this.openVidu.createSession();
//				// Generate a new Connection with the recently created connectionProperties
//				String token = session.createConnection(connectionProperties).getToken();
//
//				// Store the session and the token in our collections
//				this.mapSessions.put(roomName, session);
//				this.mapSessionNamesTokens.put(roomName, new ConcurrentHashMap<>());
//				this.mapSessionNamesTokens.get(roomName).put(token, role);
//
//				// Return the response to the client
//				return ResponseEntity.status(200).body(RoomCreatePostRes.of(200, "Success", token));
//
//			} catch (Exception e) {
//				// If error generate an error message and return it to client
//				return ResponseEntity.status(500).body(BaseResponseBody.of(500, "failed to create the room : internal error"));
//			}
//		}
//		return ResponseEntity.status(400).body(BaseResponseBody.of(400, "failed to create the room : existing already"));
//	}

}
