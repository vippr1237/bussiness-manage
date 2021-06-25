package com.fresher_devops.simple_bussiness.repositories;

import com.fresher_devops.simple_bussiness.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ProductRepoTest {
    @Autowired
    private ProductRepo underTest;

    @Test
    void itShouldReturnProductById() {
        //given
        Product product = new Product("product-01", 10);
        //when
        Product expected = underTest.save(product);
        Optional<Product> test = underTest.findProductById(expected.getId());
        //then
        test.ifPresent(value -> assertThat(expected).isEqualTo(value));
    }

    @Test
    void itShouldNotReturnProductById() {
        //given
        //when
        Optional<Product> test = underTest.findProductById(1L);
        //then
        boolean expected = test.isPresent();
        assertThat(expected).isFalse();
    }

}