package com.fresher_devops.simple_bussiness.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	private String firstName;
	private String lastName;
	private int age;
	private String phoneNumber;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({ "id", "date" })
	private Set<Receipt> receipts;

	public Customer() {
	}

	public Customer(String firstNameName, String lastName, int age, String phoneNumber) {
		this.firstName = firstNameName;
		this.lastName = lastName;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfirstName() {
		return this.firstName;
	}

	public void setfristName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Receipt> getReceipts() {
		return this.receipts;
	}

	public void setReceipts(Set<Receipt> receipts) {
		this.receipts = receipts;
	}
}
