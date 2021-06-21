package com.fresher_devops.simple_bussiness.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresher_devops.simple_bussiness.exception.ReceiptNotFoundException;
import com.fresher_devops.simple_bussiness.models.Receipt;
import com.fresher_devops.simple_bussiness.repositories.ReceiptRepo;

@Service
public class ReceiptService {
	private final ReceiptRepo receiptRepo;

	@Autowired
	public ReceiptService(ReceiptRepo receiptRepo) {
		this.receiptRepo = receiptRepo;
	}

	public Receipt addReceipt(Receipt receipt) {
		return receiptRepo.save(receipt);
	}

	public List<Receipt> findAllReceipts() {
		return receiptRepo.findAll();
	}

	public Receipt updateReceipt(Receipt receipt) {
		return receiptRepo.save(receipt);
	}

	public Receipt findReceiptById(Long id) {
		return receiptRepo.findReceiptById(id)
				.orElseThrow(() -> new ReceiptNotFoundException("Receipt by id " + id + " was not found"));
	}

	public void deleteReceipt(Long id) {
		receiptRepo.deleteReceiptById(id);
	}
}
