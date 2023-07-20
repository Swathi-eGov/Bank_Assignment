package com.example.bank.assignment.mapper;

import com.example.bank.assignment.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setAccountNumber(rs.getString("account_number"));
        account.setAccountHolderName(rs.getString("account_holder_name"));
        account.setMobileNumber(rs.getString("mobile_number"));
        account.setBalance(rs.getDouble("balance"));
        account.setPassword(rs.getString("password"));
        return account;
    }
}
