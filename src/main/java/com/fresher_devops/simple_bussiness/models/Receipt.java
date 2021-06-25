package com.fresher_devops.simple_bussiness.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
public class Receipt implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", shape = Shape.STRING)

	private String date = LocalDateTime.now().toString();
	private float total;

	@OneToMany(mappedBy = "receipt", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({ "id" })
	private Set<ReceiptDetail> receiptDetail;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
	@JsonIncludeProperties({ "id", "firstName" })
	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ReceiptDetail> getRecepitDetail() {
		return receiptDetail;
	}

	public void setRecepitDetail(Set<ReceiptDetail> recepitDetail) {
		this.receiptDetail = recepitDetail;
	}

	public Receipt() {

	}

	public Receipt(String date, float total, Customer customer) {
		this.date = date;
		this.total = total;
		this.customer = customer;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getTotal() {
		return this.total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
