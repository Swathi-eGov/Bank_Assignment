package com.example.bank.assignment.mapper;

import com.example.bank.assignment.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.UUID;

public class AccountRowMapper implements RowMapper<Account> {
	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account();
		account.setAccountNumber(UUID.fromString(rs.getString("account_number")));
		account.setAccountHolderName(rs.getString("account_holder_name"));
		account.setGender(rs.getString("gender"));
		account.setMobileNumber(rs.getString("mobile_number"));
		account.setAddress(rs.getString("address"));
		account.setAccountType(rs.getString("account_type"));
		account.setAccountBalance(rs.getDouble("account_balance"));
		account.setPassword(rs.getString("password"));
		return account;
	}
}
