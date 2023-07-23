package com.example.bank.assignment.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.UUID;

public class Account {

	private UUID accountNumber;

	@NotBlank(message = "Account holder name is required.")
	private String accountHolderName;

	private String gender;

	@Pattern(regexp = "\\d{10}", message = "Mobile number must be exactly 10 digits.")
	private String mobileNumber;

	private String address;

	@NotBlank(message = "Account type is required.")
	@Size(max = 10, message = "Account type cannot exceed 10 characters.")
	private String accountType;

	private double accountBalance;

	@NotBlank(message = "Password is required.")
	@Size(max = 20, message = "Password cannot exceed 20 characters.")
	private String password;

	public UUID getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(UUID accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account() {

	}

	public Account(UUID accountNumber, String accountHolderName, String gender, String mobileNumber, String address,
			String accountType, double accountBalance, String password) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.password = password;
	}

	public Account(String accountHolderName, String gender, String mobileNumber, String address, String accountType,
			double accountBalance, String password) {
		super();
		this.accountHolderName = accountHolderName;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.password = password;
	}

	public Account(UUID accountNumber,
			@NotBlank(message = "Password is required.") @Size(max = 20, message = "Password cannot exceed 20 characters.") String password) {
		super();
		this.accountNumber = accountNumber;
		this.password = password;
	}

}
