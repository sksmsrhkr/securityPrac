package com.security.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data	
@NoArgsConstructor
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class JoinRequestDto {

	private String userEmail;
	private String userPw;
	private String userName;
	private String userGender;
	private String userBirth;	
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public JoinRequestDto(String userEmail, String userPw, String userName, String userGender, String userBirth) {
		this.userEmail = userEmail;
		this.userPw = userPw;
		this.userName = userName;
		this.userGender = userGender;
		this.userBirth = userBirth;
	}

}
