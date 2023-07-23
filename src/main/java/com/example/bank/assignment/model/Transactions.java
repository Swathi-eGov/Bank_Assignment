package com.example.bank.assignment.model;

import java.util.UUID;

public class Transactions {

	private int id;
	private UUID senderAccountNumber;
	private String senderPassword;
	private UUID receiverAccountNumber;
	private String receiverPassword;
	private double amount;
	private String transactionType;
	private long transactionTime;

	// Use a constant UUID for the special receiver account for credits and debits
	public static final UUID SPECIAL_RECEIVER_ACCOUNT_NUMBER = UUID.fromString("00000000-0000-0000-0000-000000000000");

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(long transactionTime) {
		this.transactionTime = transactionTime;
	}

	public UUID getSenderAccountNumber() {
		return senderAccountNumber;
	}

	public void setSenderAccountNumber(UUID senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}

	public String getSenderPassword() {
		return senderPassword;
	}

	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}

	public UUID getReceiverAccountNumber() {
		return receiverAccountNumber;
	}

	public void setReceiverAccountNumber(UUID receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}

	public String getReceiverPassword() {
		return receiverPassword;
	}

	public void setReceiverPassword(String receiverPassword) {
		this.receiverPassword = receiverPassword;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Transactions() {
	}

	public Transactions(UUID senderAccountNumber, String senderPassword, double amount) {
		this.setSenderAccountNumber(senderAccountNumber);
		this.senderPassword = senderPassword;
		this.amount = amount;
	}

	public Transactions(UUID senderAccountNumber, String senderPassword, UUID receiverAccountNumber,
			String receiverPassword, double amount) {
		this.setSenderAccountNumber(senderAccountNumber);
		this.senderPassword = senderPassword;
		this.receiverAccountNumber = receiverAccountNumber;
		this.receiverPassword = receiverPassword;
		this.amount = amount;
	}

	public Transactions(int id, UUID senderAccountNumber, String senderPassword, UUID receiverAccountNumber,
			String receiverPassword, double amount) {
		super();
		this.id = id;
		this.senderAccountNumber = senderAccountNumber;
		this.senderPassword = senderPassword;
		this.receiverAccountNumber = receiverAccountNumber;
		this.receiverPassword = receiverPassword;
		this.amount = amount;
	}

}
