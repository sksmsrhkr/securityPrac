package com.security.boot.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.security.boot.entity.Product;
import com.security.boot.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	@GetMapping("/")
	public String productList(Model model, @PageableDefault(size = 3, sort = "productNo", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Product> list = productService.productPaging(pageable);
//		List<Product> list = productService.productList();
		
		System.out.print("흠냔냔 : " + list);
		
//        int blockLimit = 3;
//        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
//        int endPage = Math.min((startPage + blockLimit - 1), list.getTotalPages());
		
        model.addAttribute("postsPages", list);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
		
		return "/list";
	}
	
	
}
