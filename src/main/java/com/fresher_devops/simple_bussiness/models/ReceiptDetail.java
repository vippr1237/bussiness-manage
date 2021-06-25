package com.fresher_devops.simple_bussiness.models;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
public class ReceiptDetail implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	private int quantity;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "receipt_id", nullable = false)
	@JsonIncludeProperties({ "id", "date", "customer" })
	private Receipt receipt;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	@JsonIncludeProperties({ "id", "productName" })
	private Product product;

	public ReceiptDetail() {

	}

	public ReceiptDetail(Receipt receipt, Product product, int quantity) {
		this.receipt = receipt;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Receipt getReceipt() {
		return this.receipt;
	}

	public void setRecepit(Receipt receipt) {
		this.receipt = receipt;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
