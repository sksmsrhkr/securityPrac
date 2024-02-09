package com.security.boot.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.boot.entity.UserEntity;
import com.security.boot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	//스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
	//password 부분 처리는 알아서 함
	//username 이 DB에 있는지만 확인해주면 됨.
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException{
		UserEntity userEntity = userRepository.findByUserEmail(userEmail);
		
		System.out.println("푸하하하핳하하하");
		System.out.println("유저 정보 : " +userEntity);
		
		if(userEntity == null) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
		}

		return new PrincipalDetails(userEntity); //시큐리티의 세션에 유저 정보가 저장 됨.
	}
}
