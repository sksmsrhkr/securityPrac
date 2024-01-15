package com.security.boot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.security.boot.security.PrincipalDetails;


@Controller
public class BoardController {

	@GetMapping("/board/list")
	public String boardList(@AuthenticationPrincipal PrincipalDetails userDetail, Model  model) {
		System.out.println("이메일 : " + userDetail.getUsername());
		String userEmail = userDetail.getUsername();
		model.addAttribute("name", userEmail);
		
		return "/board/list";
	}
	
	@GetMapping("/board/write")
	public String writeBoard() {
		return "/board/write";
	}
}
