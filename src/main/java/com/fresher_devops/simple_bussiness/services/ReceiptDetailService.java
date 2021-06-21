package com.fresher_devops.simple_bussiness.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresher_devops.simple_bussiness.exception.ReceiptDetailNotFoundException;
import com.fresher_devops.simple_bussiness.models.ReceiptDetail;
import com.fresher_devops.simple_bussiness.repositories.ReceiptDetailRepo;

@Service
public class ReceiptDetailService {
	private final ReceiptDetailRepo receiptDetailRepo;

	@Autowired
	public ReceiptDetailService(ReceiptDetailRepo receiptDetailRepo) {
		this.receiptDetailRepo = receiptDetailRepo;
	}

	public ReceiptDetail addReceiptDetail(ReceiptDetail receiptDetail) {
		return receiptDetailRepo.save(receiptDetail);
	}

	public List<ReceiptDetail> findAllReceiptDetails() {
		return receiptDetailRepo.findAll();
	}

	public ReceiptDetail updateReceiptDetail(ReceiptDetail receiptDetail) {
		return receiptDetailRepo.save(receiptDetail);
	}

	public ReceiptDetail findReceiptDetailById(Long id) {
		return receiptDetailRepo.findReceiptDetailById(id)
				.orElseThrow(() -> new ReceiptDetailNotFoundException("ReceiptDetail by id " + id + " was not found"));
	}

	public void deleteReceiptDetail(Long id) {
		receiptDetailRepo.deleteReceiptDetailById(id);
	}
}
