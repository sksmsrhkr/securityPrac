package com.security.boot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.security.boot.security.PrincipalDetails;

@Controller
public class PaymentController {
//	private final IamportClient iamportClient;
	
	@GetMapping("/payment")
	public String point(@AuthenticationPrincipal PrincipalDetails userDetail, Model  model){
		model.addAttribute("email", userDetail.getUsername());
		model.addAttribute("name", userDetail.getName());
		
		return "/payment";
	}
	
}
