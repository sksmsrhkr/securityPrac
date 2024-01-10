package com.security.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
//@Setter
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
	

}

