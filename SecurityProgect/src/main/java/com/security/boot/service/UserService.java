package com.security.boot.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.boot.dto.JoinRequestDto;
import com.security.boot.entity.UserEntity;
import com.security.boot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService  {
	
	final UserRepository userRepository;
//	private final BCryptPasswordEncoder encoder;
	
	public void validateDuplicateUser(JoinRequestDto dto) {
		System.out.println("++++++++++++++++++ dpd : " + dto);
		UserEntity findUser = userRepository.findByUserEmail(dto.getUserEmail());
		System.out.println("++++++++++++++++++ dpd : " + findUser);
		
		if(findUser == null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			dto.setUserPw(encoder.encode(dto.getUserPw()));
			userRepository.save(dto.toEntity());
		} 
//		else {
//			return "이미 가입함";
//		}
	}

	public UserEntity save(JoinRequestDto dto) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		UserEntity findUser = userRepository.findByUserEmail(dto.getUserEmail());
		if(findUser == null) {			
			dto.setUserPw(encoder.encode(dto.getUserPw()));
			return userRepository.save(dto.toEntity());
		} 
		return findUser;			
		
	}
		

}
