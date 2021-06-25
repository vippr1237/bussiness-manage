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

import com.fresher_devops.simple_bussiness.models.Receipt;
import com.fresher_devops.simple_bussiness.services.ReceiptService;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
	private final ReceiptService receiptService;

	public ReceiptController(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	@GetMapping("/")
	public ResponseEntity<List<Receipt>> getAllReceipts() {
		List<Receipt> Receipts = receiptService.findAllReceipts();
		return new ResponseEntity<>(Receipts, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Receipt> getReceiptById(@PathVariable("id") Long id) {
		Receipt Receipt = receiptService.findReceiptById(id);
		return new ResponseEntity<>(Receipt, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Receipt> addReceipt(@RequestBody Receipt Receipt) {
		Receipt newReceipt = receiptService.addReceipt(Receipt);
		return new ResponseEntity<>(newReceipt, HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<Receipt> updateReceipt(@RequestBody Receipt Receipt) {
		Receipt updateReceipt = receiptService.updateReceipt(Receipt);
		return new ResponseEntity<>(updateReceipt, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReceipt(@PathVariable("id") Long id) {
		receiptService.deleteReceipt(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
