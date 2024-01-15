package com.security.boot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.boot.entity.UserEntity;
import com.security.boot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService  {
	
	@Autowired
	private UserRepository userRepository;

	public UserEntity save(UserEntity user) {
		validateDuplicateUser(user);
		
		System.out.print("생일결과 : " + user.getUserBirth());
		System.out.print("성별결과 : " + user.getUserGender());
		System.out.print("이메일결과 : " + user.getUserEmail());
		System.out.print("이름결과 : " + user.getUserName());
		System.out.print("비번결과 : " + user.getUserPw());
		
		return userRepository.save(user);
	}
	
	public void validateDuplicateUser(UserEntity user) {
		UserEntity findUser = userRepository.findByUserEmail(user.getUserEmail());
		if(findUser != null) {
			throw new IllegalStateException("이미 가입한 아이디입니다.");
		}
	}

}
