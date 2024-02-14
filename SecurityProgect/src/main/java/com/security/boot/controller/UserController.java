package com.security.boot.controller;

import java.util.UUID;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.boot.dto.JoinRequestDto;
import com.security.boot.entity.UserEntity;
import com.security.boot.oauth2.KakaoInfo;
import com.security.boot.oauth2.OAuthToken;
import com.security.boot.oauth2.PrincipalOauth2UserService;
import com.security.boot.repository.UserRepository;
import com.security.boot.role.Role;
import com.security.boot.security.PrincipalDetails;
import com.security.boot.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UserController {
	
	@Autowired
	private UserService userService;
	private PrincipalOauth2UserService principalUserService;
	private UserRepository userRepository;
	private PrincipalDetails principalDetails;

	@GetMapping("/g")
	public String main(@AuthenticationPrincipal PrincipalDetails principal, HttpSession session) {
//		System.out.println("로그인 사용자 아이디: " +principal.getUsername());
		if(principal != null) {
			String userName = principal.getName();
			System.out.println("로그인 완료");
			session.setAttribute("isLogin", userName);
		}
		return "/list";
	}
	
	@GetMapping("/user/login")
	public String login() {
		return "/user/login";
	}

	@GetMapping("/oauth2/kakao")
	public String kakaologin() {
		System.out.println("시큐리티로 로그인");
		return "/oauth2/kakao";
	}
////	@ResponseBody
//	public String KakaoLogin(@RequestParam("code") String code, HttpSession session ) {
//		
//		//POST 방식으로 key=value 데이터를 요청(카카오쪽으로)
//		//Retrofit2 라이브러리
//		//OkHttp 라이브러리
//		String REQUEST_URL = "https://kauth.kakao.com/oauth/token";
//		RestTemplate restTemplate = new RestTemplate();
//		
////		System.out.println("카카오 엑세스 토큰 : " + code);
//		
//		//HttpHeaders 오브젝트 생성
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 내가 지금 전송한 bodyhttp 타입이 key-value 라고 알려주는 것
//		
//		//HttpBody 오브젝트 생성
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type", "authorization_code");
//		params.add("client_id", "69bb66a90dc27119b9c5f446fccec146"); //값들은 변수화 시켜 사용하는 게 좋음
//		params.add("redirect_uri", "http://localhost:8999/oauth2/kakao");
//		params.add("code", code);
//		params.add("client_secret", "wu7K17V3rtWoumj1crnGZXqK3jN1iMRr");
// 		
//		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
//		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
//				new HttpEntity<>(params, headers);
//		
//		//Http 요청하기- Post방식으로 - 그리고 reponse 변수의 응답 받음
//		ResponseEntity<String> response = restTemplate.postForEntity(
//				REQUEST_URL,
//				kakaoTokenRequest,
//				String.class
//			);
//
////		ResponseEntity<String> response = restTemplate.exchange(
////				REQUEST_URL,
////				  HttpMethod.POST, 
////				kakaoTokenRequest,
////				String.class
////				);
//		
//		//Json 데이터를 처리하기 힘드니 Object에 담는 것
//		//Json 데이터를 JAVA에서 처리하기 위해 JavaObjcet 로 바꾼다
//		ObjectMapper obMapper = new ObjectMapper();
//		OAuthToken oauthToken = null;
//		try {
//			oauthToken = obMapper.readValue(response.getBody(), OAuthToken.class);
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		
////		System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token());
//		
//		RestTemplate restTemplate2 = new RestTemplate();
//		
//		//HttpHeaders 오브젝트 생성
//		HttpHeaders headers2 = new HttpHeaders();
//		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token()); // 내가 지금 전송한 bodyhttp 타입이 key-value 라고 알려주는 것
//		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 내가 지금 전송한 bodyhttp 타입이 key-value 라고 알려주는 것
//		
//		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
//		HttpEntity<MultiValueMap<String, String>> kakaoInfoRequest = 
//				new HttpEntity<>(headers2);
//		
//		//Http 요청하기- Post방식으로 - 그리고 reponse 변수의 응답 받음
//		ResponseEntity<String> response2 = restTemplate2.postForEntity(
//				"https://kapi.kakao.com/v2/user/me",
//				kakaoInfoRequest,
//				String.class
//			);
//		
//		ObjectMapper obMapper2 = new ObjectMapper();
//		KakaoInfo kakaoInfo = null;
//		try {
//			kakaoInfo = obMapper2.readValue(response2.getBody(), KakaoInfo.class);
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.print("후하하" + kakaoInfo);
//		
////		OAuth2User oauth2User = principalUserService.loadUser();
//		
//		//UserEntity : userEmail, password
//		System.out.println("이메일: " + kakaoInfo.getKakao_account().getEmail());
//		String userEmail = kakaoInfo.getKakao_account().getEmail();
//		String userName = kakaoInfo.getProperties().nickname;
//		
//		UUID tempPassword = UUID.randomUUID(); 
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		System.out.println("zz패스워드: " + tempPassword);
//		String password = encoder.encode(tempPassword.toString());
//		
//		UserEntity user = new UserEntity();
//		user.setUserEmail(userEmail);
//		user.setUserName(userName);
//		user.setUserPw(password);
//		user.setRole(Role.USER);
//		
//		UserEntity validateUser = userService.validateDuplicateUser(userEmail);
//		System.out.println("zzzzzzzzzz+: " + validateUser);
//
//		if(validateUser == null) {
//			System.out.println("zzzzzzzzzz+: " + validateUser);
//			userService.save(user);
//			
//			return "/main";
//		} 
//		
//		//로그인 처리
//		session.setAttribute("userEmail", user.getUserEmail());
//		System.out.println("zzzzzzzzzz+: ");
////		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoInfo.get, cosKey));
////		SecurityContextHolder.getContext().setAuthentication(authentication);
//		return "redirect:/";
//	}
	
	@GetMapping("/user/join")
	public String joinForm() {
		return "/user/join";
	}
	
	@PostMapping("/user/join")
	public String joinMember(JoinRequestDto joinReqDto) {
	
		userService.validateDuplicateUser(joinReqDto);

		return "/main";
		
	}
	
	@GetMapping("/user/error")
	public String error() {
		return "/user/error";
	}
	
}
