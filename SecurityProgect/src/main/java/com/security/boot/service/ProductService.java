package com.security.boot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.security.boot.entity.Product;
import com.security.boot.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	
	public Page<Product> productPaging(Pageable pageable){
		
        int page = 0;
//        		pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 3; // 한페이지에 보여줄 글 개수
		
		return productRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Direction.DESC, "productNo")));
	}
	
//	public List<Product> productList(){
//		return productRepository.findAll();
//	}
}
