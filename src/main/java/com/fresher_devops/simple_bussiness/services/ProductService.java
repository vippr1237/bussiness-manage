package com.fresher_devops.simple_bussiness.services;

import com.fresher_devops.simple_bussiness.exception.ProductNotFoundException;
import com.fresher_devops.simple_bussiness.models.Product;
import com.fresher_devops.simple_bussiness.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    public Product findProductById(Long id) {
        return productRepo.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product by id " + id + " was not found"));
    }
 
    public void deleteProduct(Long id) {
        if (productRepo.existsById(id)) {
            throw new ProductNotFoundException("Product by id" + id + "does not exits");
        }
        productRepo.deleteProductById(id);
    }
}
