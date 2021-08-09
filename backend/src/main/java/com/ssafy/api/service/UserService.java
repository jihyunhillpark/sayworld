package com.ssafy.api.service;

import com.ssafy.api.request.InterestReq;
import com.ssafy.api.request.UserFixPutReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.FriendBlackRes;
import com.ssafy.api.response.InterestRes;
import com.ssafy.db.entity.User;

import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	// 유저 CRUD methods
	User createUser(UserRegisterPostReq userRegisterInfo);
	User fixUser(String myEmail, UserFixPutReq userFixInfo);
	User getUserByEmail(String email);
	void deleteUser(String email);


	// 친구목록, 차단목록 methods
	List<FriendBlackRes> getFriendList(String myEmail);
	void addFriend(String myEmail, String friendEmail);
	void deleteFriend(String myEmail, String friendEmail);
	List<FriendBlackRes> getBlackList(String myEmail);
	void addBlack(String myEmail, String blackEmail);
	void deleteBlack(String myEmail, String blackEmail);

	List<InterestRes> getInterest(String myEmail);
	void addInterest(String myEmail, List<InterestReq> interestReq);
	void fixInterest(String myEmail, List<InterestReq> interestReq);

}
