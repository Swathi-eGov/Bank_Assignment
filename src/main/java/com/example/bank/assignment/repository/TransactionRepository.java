package com.example.bank.assignment.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.bank.assignment.model.Transactions;

@Repository
public class TransactionRepository {
	private final JdbcTemplate jdbcTemplate;

	public TransactionRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void saveTransaction(Transactions transaction, String transactionType) {
		String sql = "INSERT INTO transactions (sender_account_number, sender_password, receiver_account_number, receiver_password, amount, transaction_type) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, transaction.getSenderAccountNumber(), transaction.getSenderPassword(),
				transaction.getReceiverAccountNumber() != null ? transaction.getReceiverAccountNumber()
						: Transactions.SPECIAL_RECEIVER_ACCOUNT_NUMBER,
				transaction.getReceiverPassword() != null ? transaction.getReceiverPassword() : "defaultPassword",
				transaction.getAmount(), transactionType);
	}

}
