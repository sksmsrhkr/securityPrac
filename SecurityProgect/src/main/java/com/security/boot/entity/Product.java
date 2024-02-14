package com.security.boot.entity;



import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
@ToString
@SequenceGenerator(
	name = "product_seq_gen",
	sequenceName = "product_no_seq",
	initialValue = 1, allocationSize = 1)

public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "product_seq_gen")
	private Long productNo;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	@Column
	private String publisher;
	
	@Column
	private int price;
	
	@Column
	private int volume;
	
	@Column
	private Date publishedDate;
	
	@Column
	private int genre;


}