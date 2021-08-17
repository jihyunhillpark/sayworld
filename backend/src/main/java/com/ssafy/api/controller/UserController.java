package com.ssafy.api.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.ssafy.api.request.*;
import com.ssafy.api.response.*;
import com.ssafy.api.service.CultureService;
import com.ssafy.db.entity.Culture;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepositorySupport;

import springfox.documentation.annotations.ApiIgnore;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	CultureService cultureService;

	// 회원 가입
	@PostMapping("/signup")
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		User user = userService.createUser(registerInfo);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	// 유저 정보 수정
	@PutMapping("/userFix/{myEmail}")
	@ApiOperation(value = "유저정보 수정", notes = "아이디를 제외한 모든 유저정보 수정")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> userFix(
			@PathVariable String myEmail,
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserFixPutReq fixInfo) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		User user = userService.fixUser(myEmail, fixInfo);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	// 이메일로 회원정보 조회
	@GetMapping("/userInfo/{myEmail}")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserRes> getUserInfo(@PathVariable String myEmail,@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
//		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
//		String userId = userDetails.getUsername();
		User user = userService.getUserByEmail(myEmail);

		return ResponseEntity.status(200).body(UserRes.of(user));
	}


	// 회원 탈퇴
	@DeleteMapping("/userDelete/{myEmail}")
	@ApiOperation(value = "회원 탈퇴", notes = "로그인한 회원 본인이 서비스에서 탈퇴 한다.")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<? extends BaseResponseBody> userDelete(@PathVariable String myEmail){

		userService.deleteUser(myEmail);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
	}

	// 문화력 등록
	@PostMapping("/culture/{myEmail}")
	@ApiOperation(value = "회원 문화력 등록", notes = "사용자가 본 영화나 책 제목을 등록한다.")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})

	public ResponseEntity<? extends BaseResponseBody> postCulture(@PathVariable String myEmail,
			@RequestBody @ApiParam(value = "문화력 정보",required = true) Collection<CulturePostReq> culturePostReq){

		for (CulturePostReq culture: culturePostReq ) {
			cultureService.postCulture(culture, myEmail);
		}
		//System.out.println(myEmail);
		//System.out.println(culturePostReq.getCultureTitle());
		//System.out.println(culturePostReq.getCultureCategory());


		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	// 문화력 정보 목록 조회
	@GetMapping("/cultureInfo/{myEmail}")
	@ApiOperation(value = "회원 문화력 조회", notes = "사용자가 본 영화나 책정보(제목)을 조회한다")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<List<Culture>> getCulture(@PathVariable String myEmail){

		return new ResponseEntity(cultureService.getCultureInfo(myEmail) , HttpStatus.OK);
	}

	// 문화력 삭제
	@DeleteMapping("/deleteCulture/{myEmail}")
	@ApiOperation(value = "회원 문화력 삭제", notes = "문화력 카테고리와 제목을 받아와 문화력 목록에서 삭제")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<? extends BaseResponseBody> deleteCulture(@PathVariable String myEmail,
			@RequestBody @ApiParam(value = "문화력 정보",required = true) CultureDeleteReq cultureDeleteReq){

		cultureService.deleteCulture(myEmail,cultureDeleteReq.getCultureTitle(), cultureDeleteReq.getCultureCategory());

		return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
	}

	// 사용자의 친구목록 조회
	@GetMapping("/friendList/{myEmail}")
	@ApiOperation(value = "회원 친구목록 조회", notes = "사용자의 친구목록의 친구(사용자) 정보목록 조회")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<List<FriendBlackRes>> getFriendList(@PathVariable String myEmail){

		return new ResponseEntity(userService.getFriendList(myEmail), HttpStatus.OK);
	}

	// 사용자 친구 등록
	@PostMapping("/addFriend/{myEmail}")
	@ApiOperation(value = "회원 친구목록 조회", notes = "사용자의 친구목록의 친구(사용자) 정보목록 조회")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<? extends BaseResponseBody> addFriend(@PathVariable String myEmail,
			@RequestBody @ApiParam(value = "등록할 친구 이메일",required = true) String friendEmail){

		userService.addFriend(myEmail, friendEmail);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	// 사용자 친구 삭제
	@DeleteMapping("/deleteFriend/{myEmail}")
	@ApiOperation(value = "회원 친구목록 조회", notes = "사용자의 친구목록의 친구(사용자) 정보목록 조회")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<? extends BaseResponseBody> deleteFriend(@PathVariable String myEmail,
			@RequestBody @ApiParam(value = "등록할 친구 이메일",required = true) String friendEmail){

		userService.deleteFriend(myEmail, friendEmail);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	// 사용자의 차단목록 조회
	@GetMapping("/blackList/{myEmail}")
	@ApiOperation(value = "회원 차단목록 조회", notes = "사용자의 차단목록의 차단 사용자(사용자) 정보목록 조회")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<List<FriendBlackRes>> getblackList(@PathVariable String myEmail){

		return new ResponseEntity(userService.getBlackList(myEmail), HttpStatus.OK);
	}

	// 사용자 차단사용자 등록
	@PostMapping("/addBlack/{myEmail}")
	@ApiOperation(value = "회원 차단 사용자 등록", notes = "사용자의 차단목록에 새로운 차단 사용자를 추가")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<? extends BaseResponseBody> addBlack(@PathVariable String myEmail,
			@RequestBody @ApiParam(value = "차단할 사용자 이메일",required = true) String blackEmail){

		userService.addBlack(myEmail, blackEmail);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	// 사용자 친구 삭제
	@DeleteMapping("/deleteBlack/{myEmail}")
	@ApiOperation(value = "회원 차단 해제", notes = "사용자의 차단목록의 사용자(사용자) 차단 해제")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<? extends BaseResponseBody> deleteBlack(@PathVariable String myEmail,
			@RequestBody @ApiParam(value = "차단해제할 사용자 이메일",required = true) String blackEmail){

		userService.deleteBlack(myEmail, blackEmail);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	// 사용자의 관심사 목록 조회
	@GetMapping("/userCategory/{myEmail}")
	@ApiOperation(value = "사용자의 관심사 목록 조회", notes = "사용자의 관심사 목록 조회")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<List<InterestRes>> getInterest(@PathVariable String myEmail){

		return new ResponseEntity(userService.getInterest(myEmail), HttpStatus.OK);
	}

	// 사용자의 관심사 등록
	@PostMapping("/userCategory/{myEmail}")
	@ApiOperation(value = "사용자의 관심사 등록", notes = "사용자의 관심사 목록에 새로운 관심사를 추가")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<? extends BaseResponseBody> addInterest(@PathVariable String myEmail
			, @RequestBody @ApiParam(value = "추가할 관심사 목록",required = true)List<InterestReq> interestReqs){

		userService.addInterest(myEmail, interestReqs);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	// 사용자의 관심사 수정
	@PutMapping("/userCategory/{myEmail}")
	@ApiOperation(value = "사용자의 관심사 수정", notes = "사용자의 기존 관심사 목록을 수정")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<? extends BaseResponseBody> fixInterest(@PathVariable String myEmail
			, @RequestBody @ApiParam(value = "수정할 관심사 목록",required = true)List<InterestReq> interestReqs){

		userService.fixInterest(myEmail, interestReqs);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


}
