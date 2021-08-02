package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

import java.util.Optional;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;


	// 회원가입
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setEmail(userRegisterInfo.getEmail());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setNickname(userRegisterInfo.getNickname());
		user.setGender(userRegisterInfo.getGender());
		user.setAge(userRegisterInfo.getAge());
		user.setDefaultPage(userRegisterInfo.getDefaultPage());
		user.setTierBook(userRegisterInfo.getTierBook());
		user.setTierMovie(userRegisterInfo.getTierMovie());
		user.setProfileImg(userRegisterInfo.getProfileImg());

		return userRepository.save(user);
	}


	// 유저 정보 조회
	@Override
	public User getUserByEmail(String email) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
//		User user = userRepositorySupport.findUserByEmail(email).get();
//		return user;

		Optional<User> user = userRepository.findByEmail(email);
		return user.get();
	}
}
