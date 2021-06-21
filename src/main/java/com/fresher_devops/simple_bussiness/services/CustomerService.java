package com.fresher_devops.simple_bussiness.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresher_devops.simple_bussiness.exception.CustomerNotFoundException;
import com.fresher_devops.simple_bussiness.models.Customer;
import com.fresher_devops.simple_bussiness.repositories.CustomerRepo;

@Service
public class CustomerService {
	private final CustomerRepo customerRepo;

	@Autowired
	public CustomerService(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public List<Customer> findAllCustomers() {
		return customerRepo.findAll();
	}

	public Customer updateCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public Customer findCustomerById(Long id) {
		return customerRepo.findCustomerById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer by id " + id + " was not found"));
	}

	public void deleteCustomer(Long id) {
		customerRepo.deleteCustomerById(id);
	}
}
