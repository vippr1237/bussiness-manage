package com.fresher_devops.simple_bussiness.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresher_devops.simple_bussiness.models.Customer;
import com.fresher_devops.simple_bussiness.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> Customers = customerService.findAllCustomers();
		return new ResponseEntity<>(Customers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
		Customer Customer = customerService.findCustomerById(id);
		return new ResponseEntity<>(Customer, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer Customer) {
		Customer newCustomer = customerService.addCustomer(Customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer Customer) {
		Customer updateCustomer = customerService.updateCustomer(Customer);
		return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
	}

	// Theo stackoverflow thì transaction cập nhật nên phải thêm
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
		customerService.deleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
