package com.security.boot.oauth2;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.security.boot.dto.LoginRequestDto;

import jakarta.servlet.http.HttpServletRequest;
import net.minidev.json.JSONObject;

@Controller
public class OauthController {

//	@GetMapping("/oauth2/kakao")
//	@ResponseBody
//	public String KakaoLogin(@RequestParam("code") String code) {
//		
//		System.out.print("코드 : " + code);
//		
////		KakaoTokenResponse kakaoTokenResponse = 
//		
////        JSONObject tokenInfo = generateToken(code);
////        String accessToken = String.valueOf(tokenInfo.get("access_token"));
////        int refreshTokenExpiresIn = (int) tokenInfo.get("refresh_token_expires_in");
////        String refreshToken = String.valueOf(tokenInfo.get("refresh_token"));
////        String scope = String.valueOf(tokenInfo.get("scope"));
//		
//		return "okay";
//	}
}
