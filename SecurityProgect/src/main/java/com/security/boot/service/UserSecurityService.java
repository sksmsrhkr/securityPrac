package com.security.boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.boot.entity.UserEntity;
import com.security.boot.repository.UserRepository;
import com.security.boot.role.Role;
import com.security.boot.security.PrincipalDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException{
		UserEntity userEntity = userRepository.findByUserEmail(userEmail);
		
		if(userEntity == null) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
		}
		
//		UserEntity _userEntity = userEntity.get();
//		List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();
//		if("admin".equals(userEmail)) {
//			autorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
//		} else {
//			autorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));			
//		}
		return new PrincipalDetails(userEntity);
	}
}
