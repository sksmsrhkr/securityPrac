package com.security.boot.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.security.boot.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Page<Product> findAll(Pageable pagealbe);
	
//	@Query(value = "SELECT * FROM (SELECT rownum rnum, p.* FROM "
//			+ "(SELECT * FROM product ORDER BY product_no DESC)u) WHERE rnu BETWEEN ? AND ?", nativeQuery = true)
//	List<Product>findBy		
}

	