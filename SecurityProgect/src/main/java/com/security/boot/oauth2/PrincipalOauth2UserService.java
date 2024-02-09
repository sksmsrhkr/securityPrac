package com.security.boot.oauth2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.security.boot.entity.UserEntity;
import com.security.boot.repository.UserRepository;
import com.security.boot.role.Role;
import com.security.boot.security.PrincipalDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService  extends DefaultOAuth2UserService{

	private final UserRepository userRepository;
//	private final BCryptPasswordEncoder encoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("푸라라");
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("getAttributes : {}", oAuth2User.getAttributes());
		System.out.println("getAttributes : {}" + oAuth2User.getAttributes());
		
		OAuth2UserInfo oAuth2UserInfo = null;
		
		String provider = userRequest.getClientRegistration().getRegistrationId();
		 System.out.println("ㅋㅋㅋㅋㅋ : " + provider);
		 
        if(provider.equals("kakao")) {
            log.info("카카오 로그인 요청");
            System.out.println("ㅋㅋㅋㅋㅋ");
            oAuth2UserInfo = new KakaoInfo(oAuth2User.getAttributes());
            
        }
        
        String socialType = oAuth2UserInfo.socialType();
        String socialId = oAuth2UserInfo.socialId();
        String userEmail = oAuth2UserInfo.userEmail();
        String userName = oAuth2UserInfo.userName();
        
        System.out.println("확왼 : " + socialType);
        System.out.println("확왼 : " + socialId);
        System.out.println("확왼 : " + userEmail);
        System.out.println("확왼 : " + userName);
        
        UserEntity userEntity;
        userEntity = userRepository.findByUserEmail(userEmail);
        System.out.println("sdaffffff : " + userEntity);
		
        if(userEntity == null) {
        	System.out.println("확왼 : " + socialType);
        	System.out.println("확왼 : " + socialId);
        	System.out.println("확왼 : " + userEmail);
        	System.out.println("확왼 : " + userName);
        	
        	UserEntity user = new UserEntity();
        	user.setUserEmail(userEmail);
        	user.setUserName(userName);
        	user.setSocialId(socialId);
        	user.setSocialType(socialType);
        	user.setRole(Role.USER);
        	
        	System.out.println("왜!!!!!!!!" + user);
        	System.out.println("가입!!! ");
        	userRepository.save(user);
        } 
        
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
		
	}

	
	
}
