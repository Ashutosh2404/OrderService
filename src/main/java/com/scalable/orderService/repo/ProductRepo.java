package com.scalable.orderService.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scalable.orderService.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	
}