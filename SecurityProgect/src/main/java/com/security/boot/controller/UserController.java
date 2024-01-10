package com.security.boot.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.security.boot.dto.JoinRequestDto;
import com.security.boot.entity.UserEntity;

@Controller
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UserController {

	@GetMapping("/user/login")
	public String login() {
		return "/user/login.html";
	}
	
	@GetMapping("/user/join")
	public String joinForm() {
		return "/user/join.html";
	}
	
	@PostMapping("/join")
	public String joinMember(JoinRequestDto joinReq) {
	
		System.out.print("생일 : " + joinReq.toString());
		System.out.print("성별 : " + joinReq.getUserEmail());
//		System.out.print("이메일 : " + joinReq.getUserEmail());
//		System.out.print("이름 : " + joinReq.getUserName());
//		System.out.print("비번 : " + joinReq.getUserPw());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		UserEntity userEntity = new UserEntity();
		
//		userEntity.setUserPw(encoder.encode(joinReq.getUserPw()));
//		userEntity.setUserEmail(joinReq.getUserEmail());
//		userEntity.setUserName(joinReq.getUserName());
//		userEntity.setUserGender(joinReq.getUserGender());
//		userEntity.setUserBirth(joinReq.getUserBirth());

//		userService.save(userEntity);

		return "redirect:/";
	}
	
	
}
