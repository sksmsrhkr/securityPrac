package com.security.boot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.security.boot.security.PrincipalDetails;

@Controller
public class MyPageController {
	
	@GetMapping("/mypage/main")
	public String mypage(@AuthenticationPrincipal PrincipalDetails userDetail, Model  model){
		model.addAttribute("name", userDetail.getUsername());
		return "/mypage/main";
	}
	
	@GetMapping("/mypage/point")
	public String point(@AuthenticationPrincipal PrincipalDetails userDetail, Model  model){
		model.addAttribute("name", userDetail.getUsername());
		return "/mypage/point";
	}

}
