package com.example.bank.assignment.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.example.bank.assignment.model.Transactions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TransactionRowMapper implements RowMapper<Transactions> {

	@Override
	public Transactions mapRow(ResultSet rs, int rowNum) throws SQLException {
		Transactions transaction = new Transactions();
		transaction.setId(rs.getInt("id"));
		transaction.setSenderAccountNumber(UUID.fromString(rs.getString("sender_account_number")));
		transaction.setSenderPassword(rs.getString("sender_password"));
		transaction.setReceiverAccountNumber(UUID.fromString(rs.getString("receiver_account_number")));
		transaction.setReceiverPassword(rs.getString("receiver_password"));
		transaction.setAmount(rs.getDouble("amount"));
		transaction.setTransactionType(rs.getString("transaction_type"));
		transaction.setTransactionTime(rs.getLong("transaction_time"));
		return transaction;
	}
}
