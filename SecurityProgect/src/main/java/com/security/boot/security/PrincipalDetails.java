package com.security.boot.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.boot.entity.UserEntity;
import com.security.boot.role.Role;

public class PrincipalDetails implements UserDetails {
	
	private UserEntity userEntity;
	
	public PrincipalDetails(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collections = new ArrayList<>();
//		if("admin".equals(userEntity.getUserEmail())) {
//			collections.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
//		} else {
//			collections.add(new SimpleGrantedAuthority(Role.USER.getValue()));			
//		}
		return collections;
	}
	
	@Override
	public String getPassword() {
		return userEntity.getUserPw();
	}
	
	@Override
	public String getUsername() {
		return userEntity.getUserEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
		
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
}
