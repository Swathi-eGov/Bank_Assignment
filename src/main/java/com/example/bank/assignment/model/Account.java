package com.example.bank.assignment.model;

import java.util.UUID;

public class Account {
	
    private UUID accountNumber;
    private String accountHolderName;
    private String gender;
    private String mobileNumber;
    private String address;
    private String accountType;
    private double accountBalance;
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
	public Account() {
	}

}
