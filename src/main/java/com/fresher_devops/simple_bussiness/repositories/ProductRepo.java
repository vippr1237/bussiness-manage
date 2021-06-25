package com.fresher_devops.simple_bussiness.repositories;

import com.fresher_devops.simple_bussiness.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    void deleteProductById(Long id);

    Optional<Product> findProductById(Long id);

    
}
