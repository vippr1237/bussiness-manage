package com.fresher_devops.simple_bussiness.services;

import com.fresher_devops.simple_bussiness.exception.ProductNotFoundException;
import com.fresher_devops.simple_bussiness.models.Product;
import com.fresher_devops.simple_bussiness.repositories.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepo productRepo;
    private ProductService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ProductService(productRepo);
    }

    @Test
    void canAddProduct() {
        Product product = new Product("product-01", 10);
        //when
        underTest.addProduct(product);
        //then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepo).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(capturedProduct).isEqualTo(product);
    }

    @Test
    void canFindAllProducts() {
        //when
        underTest.findAllProducts();
        //then
        verify(productRepo).findAll();
    }

    @Test
    void canUpdateProduct() {
        Product product = new Product(2L, "product-01", 10);
        //when
        underTest.updateProduct(product);
        //then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepo).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(capturedProduct).isEqualTo(product);
    }

    @Test
    void canFindProductById() {
        Long id = 1L;
        //when
        Product product = new Product(id, null, 10);
        when(productRepo.findProductById(id)).thenReturn(Optional.of(product));
        underTest.findProductById(id);
        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(productRepo).findProductById(idArgumentCaptor.capture());
        Long captureId = idArgumentCaptor.getValue();
        assertThat(captureId).isEqualTo(id);
    }

    @Test
    void willThrowIfIdDoesNotExits() {
        Long id = 1L;
        //then
        assertThatThrownBy(() -> underTest.findProductById(id))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product by id " + id + " was not found");
    }

    @Test
    void canDeleteProduct() {
        Long id = 1L;
        //when
        underTest.deleteProduct(id);
        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(productRepo).deleteProductById(
                idArgumentCaptor.capture());
        Long captureId = idArgumentCaptor.getValue();
        assertThat(captureId).isEqualTo(id);
    }
}