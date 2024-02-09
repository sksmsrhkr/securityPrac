package com.security.boot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.security.boot.security.PrincipalDetails;

import jakarta.servlet.http.HttpSession;


@Controller
public class BoardController {

	@GetMapping("/board/list")
	public String boardList(@AuthenticationPrincipal PrincipalDetails userDetail, Model  model, HttpSession session) {
//		System.out.println("dddddddddddd dfsadfd 이메일 : " + userDetail.getUsername());
	
		if(userDetail != null) {
			String userName = userDetail.getName();
			model.addAttribute("name", userName);
			session.setAttribute("isLogin", userName);
		}
		
		
		return "/board/list";
	}
	
	@GetMapping("/board/write")
	public String writeBoard() {
		return "/board/write";
	}
}
