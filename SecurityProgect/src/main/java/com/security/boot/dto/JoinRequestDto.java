package com.security.boot.dto;

import com.security.boot.entity.UserEntity;
import com.security.boot.role.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString
public class JoinRequestDto {

	private String userEmail;
	private String userPw;
	private String userName;
	private String userGender;
	private String userBirth;	
	private String userPhone;	
	
//	public String getUserEmail() {
//		return userEmail;
//	}
	
	public UserEntity toEntity() {
		UserEntity user = UserEntity.builder()
				.userEmail(userEmail)
				.userName(userName)
				.userBirth(userBirth)
				.userGender(userGender)
				.userPhone(userPhone)
				.userPw(userPw)
				.role(Role.USER)
				.build();
		return user;
				
	}

//	public JoinRequestDto(String userEmail, String userPw, String userName, String userGender, String userBirth, String userPhone) {
//		this.userEmail = userEmail;
//		this.userPw = userPw;
//		this.userName = userName;
//		this.userGender = userGender;
//		this.userBirth = userBirth;
//		this.userPhone = userPhone;
//	}

}
