package com.ssafy.api.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ssafy.api.request.CulturePostReq;
import com.ssafy.api.request.UserFixPutReq;
import com.ssafy.api.response.CultureListRes;
import com.ssafy.api.service.CultureService;
import com.ssafy.db.entity.Culture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepositorySupport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@CrossOrigin(origins = "http://localhost:8443")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;

<<<<<<< HEAD
=======
	@Autowired
	CultureService cultureService;

>>>>>>> 5307b976779667b258714225aefb6eaf6ac763a0
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
		//User userInfo = userService.getUserByEmail(user.getEmail());
		//return new ResponseEntity(userInfo, HttpStatus.OK);
		return new ResponseEntity(user, HttpStatus.OK);

		//return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	// 유저 정보 수정
	@PostMapping("/userFix")
	@ApiOperation(value = "유저정보 수정", notes = "아이디를 제외한 모든 유저정보 수정")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> userFix(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserFixPutReq fixInfo) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		User user = userService.fixUser(fixInfo);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	// 이메일로 회원정보 조회
	@GetMapping("/userInfo")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
//		String userId = userDetails.getUsername();
		String userEmail = userDetails.getUserEmail();
		User user = userService.getUserByEmail(userEmail);
		
		
		return ResponseEntity.status(200).body(UserRes.of(user));
	}


	// 회원 탈퇴
	@DeleteMapping("/userDelete")
	@ApiOperation(value = "회원 탈퇴", notes = "로그인한 회원 본인이 서비스에서 탈퇴 한다.")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<? extends BaseResponseBody> userDelete(){

		String userEmail = "helloculsu@naver.com";

		userService.deleteUser(userEmail);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
	}

	@PostMapping("/culture")
	@ApiOperation(value = "회원 문화력 등록", notes = "사용자가 본 영화나 책 제목을 등록한다.")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<? extends BaseResponseBody> culturePost(@RequestBody @ApiParam(value = "문화력 정보",required = true) CulturePostReq culturePostReq){

		String email = "culsu@naver.com";

		cultureService.postCulture(culturePostReq, email);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	@GetMapping("/culture")
	@ApiOperation(value = "회원 문화력 조회", notes = "사용자가 본 영화나 책정보(제목)을 조회한다")
	@ApiResponses({
			@ApiResponse(code=200, message = "성공"),
			@ApiResponse(code=400, message = "인증 실패"),
			@ApiResponse(code=401, message = "사용자 없음"),
			@ApiResponse(code=500, message = "서버오류")
	})
	public ResponseEntity<List<Culture>> getCulture(){
		String email = "culsu@naver.com";

		return new ResponseEntity(cultureService.getCultureInfo(email) , HttpStatus.OK);
	}
}
