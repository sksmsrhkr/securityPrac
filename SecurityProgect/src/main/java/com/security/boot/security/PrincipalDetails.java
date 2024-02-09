package com.security.boot.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.security.boot.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다
public class PrincipalDetails implements UserDetails, OAuth2User {
	
	
	private UserEntity userEntity; //콤포지션
	
	public PrincipalDetails(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	private Map<String, Object> attributes;

	public PrincipalDetails(UserEntity userEntity, Map<String, Object> attributes) {
	    this.userEntity = userEntity;
	    this.attributes = attributes;
	}
	
	
	//계정의 갖고있는 권한 목록을 리턴한다 (권한이 여러개 있을 수 있어서 루프를 돌아야하는데 우리는 한개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collections = new ArrayList<>();
		//ArrayList 부모 중 Collection 이 있음.
		if(userEntity != null) {
		collections.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().toString()));
		} 
//		collections.add(()->{return "ROLE_" + userEntity.getRole().toString();}); 람다식 표기법

		return collections;
	}
	
	//
	@Override
	public String getPassword() {
		return userEntity.getUserPw();
	}
	
	@Override
	public String getUsername() {
		return userEntity.getUserEmail();
	}
	
	//계정이 만료되지 않았는지 리턴한다 (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있는지 않았는지 리턴한다 (true: 만료안됨)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	//비밀번호가 완료되지 않았는지 리턴한다 (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
		
	//계정이 활성화(사용가능) 인지 리턴한다 (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		System.out.print("여기를 ㅈ니나나");
		return attributes;
	}

	@Override
	public String getName() {
		return userEntity.getUserName();
	}
	
	
	
}
