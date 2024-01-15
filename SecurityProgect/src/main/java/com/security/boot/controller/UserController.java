package com.security.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.security.boot.dto.JoinRequestDto;
import com.security.boot.entity.UserEntity;
import com.security.boot.service.UserService;

@Controller
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String main() {
		return "/main";
	}
	
	@GetMapping("/user/login")
	public String login() {
		return "/user/login";
	}

	
	@GetMapping("/user/join")
	public String joinForm() {
		return "/user/join";
	}
	
	@PostMapping("/user/join")
	public String joinMember(JoinRequestDto joinReq) {
	
		System.out.print("생일 : " + joinReq.toString());
		System.out.print("성별 : " + joinReq.getUserEmail());
		System.out.print("이메일 : " + joinReq.getUserEmail());
		System.out.print("이름 : " + joinReq.getUserName());
		System.out.print("비번 : " + joinReq.getUserPw());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		UserEntity user = UserEntity.createUser(joinReq, encoder);
		
		userService.save(user);

		return "board/list";
	}
	
	@GetMapping("/user/error")
	public String error() {
		return "/user/error";
	}
	
}
