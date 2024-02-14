package com.security.boot.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductListDto {

	private List<ProductDto> productList = new ArrayList<>();
	
	private Long totalPages;
	
	private Long totalCount;
}
