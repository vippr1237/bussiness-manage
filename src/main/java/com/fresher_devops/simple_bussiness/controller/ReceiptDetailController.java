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

import com.fresher_devops.simple_bussiness.models.ReceiptDetail;
import com.fresher_devops.simple_bussiness.services.ReceiptDetailService;

@RestController
@RequestMapping("/receiptdetail")
public class ReceiptDetailController {
	private final ReceiptDetailService receiptDetailService;

	public ReceiptDetailController(ReceiptDetailService receiptDetailService) {
		this.receiptDetailService = receiptDetailService;
	}

	@GetMapping("/")
	public ResponseEntity<List<ReceiptDetail>> getAllReceiptDetails() {
		List<ReceiptDetail> ReceiptDetails = receiptDetailService.findAllReceiptDetails();
		return new ResponseEntity<>(ReceiptDetails, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReceiptDetail> getReceiptDetailById(@PathVariable("id") Long id) {
		ReceiptDetail ReceiptDetail = receiptDetailService.findReceiptDetailById(id);
		return new ResponseEntity<>(ReceiptDetail, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ReceiptDetail> addReceiptDetail(@RequestBody ReceiptDetail ReceiptDetail) {
		ReceiptDetail newReceiptDetail = receiptDetailService.addReceiptDetail(ReceiptDetail);
		return new ResponseEntity<>(newReceiptDetail, HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<ReceiptDetail> updateReceiptDetail(@RequestBody ReceiptDetail ReceiptDetail) {
		ReceiptDetail updateReceiptDetail = receiptDetailService.updateReceiptDetail(ReceiptDetail);
		return new ResponseEntity<>(updateReceiptDetail, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReceiptDetail(@PathVariable("id") Long id) {
		receiptDetailService.deleteReceiptDetail(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
