package com.fresher_devops.simple_bussiness.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresher_devops.simple_bussiness.models.ReceiptDetail;

public interface ReceiptDetailRepo extends JpaRepository<ReceiptDetail, Long> {

	void deleteReceiptDetailById(Long id);

	Optional<ReceiptDetail> findReceiptDetailById(Long id);

}
