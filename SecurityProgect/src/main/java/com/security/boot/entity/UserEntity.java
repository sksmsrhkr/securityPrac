package com.security.boot.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.boot.dto.JoinRequestDto;
import com.security.boot.role.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "user_tb")
@ToString
@SequenceGenerator(
	name = "user_seq_gen",
	sequenceName = "user_no_seq",
	initialValue = 1, allocationSize = 1)

public class UserEntity extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "user_seq_gen")
	private Long userNo;
	
	@Column(unique = true)
	private String userEmail;
	
	@Column
	private String userPw;
	
	@Column
	private String userName;
	
	@Column
	private String userGender;
	
	@Column
	private String userBirth;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static UserEntity createUser(JoinRequestDto joinReqDto, BCryptPasswordEncoder encoder) {

		UserEntity user = new UserEntity();
		
		user.setUserEmail(joinReqDto.getUserEmail());
		user.setUserName(joinReqDto.getUserName());
		user.setUserBirth(joinReqDto.getUserBirth());
		user.setUserGender(joinReqDto.getUserGender());
		String password = encoder.encode(joinReqDto.getUserPw());
		user.setUserPw(password);
		user.setRole(Role.USER);
		return user;
	
	}

}

