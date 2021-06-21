package com.fresher_devops.simple_bussiness.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresher_devops.simple_bussiness.models.Receipt;

public interface ReceiptRepo extends JpaRepository<Receipt, Long> {

	void deleteReceiptById(Long id);

	Optional<Receipt> findReceiptById(Long id);

}
