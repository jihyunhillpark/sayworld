package com.ssafy.api.service;

import com.ssafy.api.request.InterestReq;
import com.ssafy.api.request.UserFixPutReq;
import com.ssafy.api.response.FriendBlackRes;
import com.ssafy.api.response.InterestRes;
import com.ssafy.db.entity.BookCategory;
import com.ssafy.db.entity.MovieCategory;
import com.ssafy.db.entity.Room;
import com.ssafy.db.repository.BookRepository;
import com.ssafy.db.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	BookRepository bookRepository;
	@Autowired
	MovieRepository movieRepository;
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


	// 유저 정보 수정
	@Override
	public User fixUser(String myEmail, UserFixPutReq userFixInfo) {
		User user = getUserByEmail(myEmail);
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userFixInfo.getPassword()));
		user.setNickname(userFixInfo.getNickname());
		user.setGender(userFixInfo.getGender());
		user.setAge(userFixInfo.getAge());
		user.setDefaultPage(userFixInfo.getDefaultPage());
		user.setProfileImg(userFixInfo.getProfileImg());

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


	// 회원 탈퇴
	@Override
	public void deleteUser(String email){
		User user = getUserByEmail(email);
		userRepository.delete(user);
	}



	// 친구목록 조회
	@Override
	public List<FriendBlackRes> getFriendList(String myEmail){
		User me = userRepository.findByEmail(myEmail).get();
		Set<User> friendList = me.getFriends();

		List<FriendBlackRes> list = new ArrayList<>();
		for(User friend: friendList){
			list.add(FriendBlackRes.create(friend));
		}

		return list;
	}
	// 친구추가
	@Override
	public void addFriend(String myEmail, String friendEmail){
		User me = userRepository.findByEmail(myEmail).get();
		User friend = userRepository.findByEmail(friendEmail).get();
		Set<User> friendList = me.getFriends();
		friendList.add(friend);
		userRepository.save(me);
	}
	// 친구삭제
	@Override
	public void deleteFriend(String myEmail, String friendEmail){
		User me = userRepository.findByEmail(myEmail).get();
		User friend = userRepository.findByEmail(friendEmail).get();
		Set<User> friendList = me.getFriends();
		friendList.remove(friend);
		userRepository.save(me);
	}
	// 차단목록 조회
	@Override
	public List<FriendBlackRes> getBlackList(String myEmail){
		User me = userRepository.findByEmail(myEmail).get();
		Set<User> blackList = me.getBlacks();

		List<FriendBlackRes> list = new ArrayList<>();
		for(User friend: blackList){
			list.add(FriendBlackRes.create(friend));
		}

		return list;
	}
	// 차단추가
	@Override
	public void addBlack(String myEmail, String blackEmail){
		User me = userRepository.findByEmail(myEmail).get();
		User black = userRepository.findByEmail(blackEmail).get();
		Set<User> blackList = me.getBlacks();
		blackList.add(black);
		userRepository.save(me);
	}
	// 차단해제
	@Override
	public void deleteBlack(String myEmail, String blackEmail){
		User me = userRepository.findByEmail(myEmail).get();
		User black = userRepository.findByEmail(blackEmail).get();
		Set<User> blackList = me.getBlacks();
		blackList.remove(black);
		userRepository.save(me);
	}

	@Override
	public List<InterestRes> getInterest(String myEmail){
		User me = userRepository.findByEmail(myEmail).get();
		Set<BookCategory> books = me.getBookCategories();
		Set<MovieCategory> movies = me.getMovieCategories();
		List<InterestRes> res = new ArrayList<>();

		for(BookCategory book: books){
			res.add(InterestRes.createBook(book));
		}
		for(MovieCategory movie: movies){
			res.add(InterestRes.createMovie(movie));
		}

		return res;
	}

	@Override
	public void addInterest(String myEmail, List<InterestReq> interestReqs){
		User me = userRepository.findByEmail(myEmail).get();
		Set<BookCategory> books = me.getBookCategories();
		Set<MovieCategory> movies = me.getMovieCategories();
		for(InterestReq req: interestReqs){
			// movie
			if(req.getMovieOrBook() == 0){
				MovieCategory movieC = movieRepository.findByMovieCategoryId(req.getCategoryId()).get();
				movies.add(movieC);
			}
			// book
			else {
				BookCategory bookC = bookRepository.findByBookCategoryId(req.getCategoryId()).get();
				books.add(bookC);
			}
		}
		userRepository.save(me);
	}

	@Override
	public void fixInterest(String myEmail, List<InterestReq> interestReqs){
		User me = userRepository.findByEmail(myEmail).get();
		Set<BookCategory> books = me.getBookCategories();
		Set<MovieCategory> movies = me.getMovieCategories();
		books.clear();
		movies.clear();

		for(InterestReq req: interestReqs){
			// movie
			if(req.getMovieOrBook() == 0){
				MovieCategory movieC = movieRepository.findByMovieCategoryId(req.getCategoryId()).get();
				movies.add(movieC);
			}
			// book
			else {
				BookCategory bookC = bookRepository.findByBookCategoryId(req.getCategoryId()).get();
				books.add(bookC);
			}
		}
		userRepository.save(me);
	}
	@Override
	public List<Room> getRoomHistory(String myEmail){
		List<Room> history = new ArrayList<>();
		Optional<User> user = userRepository.findByEmail(myEmail);
		if(user.isPresent()){
			User selectedUser = user.get();
			history = selectedUser.getRooms();
		}
		return history;
	}
}
