package com.fresher_devops.simple_bussiness.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresher_devops.simple_bussiness.models.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

	void deleteCustomerById(Long id);

	Optional<Customer> findCustomerById(Long id);

}
