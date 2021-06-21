package com.fresher_devops.simple_bussiness.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresher_devops.simple_bussiness.models.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

	void deleteProductById(Long id);

	Optional<Product> findProductById(Long id);

}
