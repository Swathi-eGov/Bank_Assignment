package com.example.bank.assignment.repository;

import com.example.bank.assignment.mapper.AccountRowMapper;
import com.example.bank.assignment.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class JdbcAccountsRepository implements AccountsRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Account account) {
         return jdbcTemplate.update("INSERT INTO useraccount (account_holder_name,gender,mobile_number,address,account_type,account_balance, password) VALUES(?,?,?,?,?,?,?)",
                account.getAccountHolderName(),account.getGender(),account.getMobileNumber(),account.getAddress(),account.getAccountType(),account.getAccountBalance(), account.getPassword() );
    }
    @Override
    public Account findById(UUID accountNumber) {
        String sql = "SELECT * FROM useraccount WHERE account_number = ?";
        Object[] params = { accountNumber };

        try {
            return jdbcTemplate.queryForObject(sql, params, new AccountRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null; // Account not found
        }
    }
    
    
    public Account findByIdAndPassword(UUID accountNumber,String password) {
        String sql = "SELECT * FROM useraccount WHERE account_number = ? AND password=?";
        Object[] params = { accountNumber, password };

        try {
            return jdbcTemplate.queryForObject(sql, params, new AccountRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null; // Account not found
        }
    }
    @Override
    public void delete(UUID accountNumber, String password) {
        String sql = "DELETE FROM useraccount WHERE account_number = ? AND password=?";
        Object[] params = { accountNumber , password};

        int rowsAffected = jdbcTemplate.update(sql, params);

        if (rowsAffected <= 0) {
            // Handle the case when the delete fails
            throw new RuntimeException("Failed to delete the account.");
        }
    }
    
    public void update(Account account) {
        UUID accountNumber = account.getAccountNumber();
        String accountHolderName = account.getAccountHolderName();
        String mobileNumber = account.getMobileNumber();
        String address = account.getAddress();

        StringBuilder query = new StringBuilder("UPDATE useraccount SET ");
        List<Object> params = new ArrayList<>();

        if (accountHolderName != null) {
            query.append("account_holder_name = ?, ");
            params.add(accountHolderName);
        }

        if (mobileNumber != null) {
            query.append("mobile_number = ?, ");
            params.add(mobileNumber);
        }

        if (address != null) {
            query.append("address = ?, ");
            params.add(address);
        }

        // Remove the last comma and space if there are columns to update
        if (query.charAt(query.length() - 2) == ',') {
            query.delete(query.length() - 2, query.length());
        }

        query.append(" WHERE account_number = ?");
        params.add(accountNumber);

        String sql = query.toString();

        jdbcTemplate.update(sql, params.toArray());
    }

    
    
    @Override
    public void creditAmount(UUID accountNumber, double amount, String password) {
        Account account = findByIdAndPassword(accountNumber,password);
        if (account != null) {
            double newaccountBalance = account.getAccountBalance() + amount;
            account.setAccountBalance(newaccountBalance);

            String sql = "UPDATE useraccount SET account_balance = ? WHERE account_number = ?";
            Object[] params = { account.getAccountBalance(), accountNumber };

            int rowsAffected = jdbcTemplate.update(sql, params);

            if (rowsAffected <= 0) {
                // Handle the case when the update fails
                throw new RuntimeException("Failed to credit amount to the account.");
            }
        }
        else {
        	throw new RuntimeException("Authentication failed with the given accountNumber.");
        }
    }
    @Override
    public void debitAmount(UUID accountNumber, double amount, String password) {
        Account account = findByIdAndPassword(accountNumber,password);
        if (account != null) {
            double newaccountBalance = account.getAccountBalance() - amount;
            if (newaccountBalance >= 0) {
                account.setAccountBalance(newaccountBalance);

                String sql = "UPDATE useraccount SET account_balance = ? WHERE account_number = ?";
                Object[] params = { account.getAccountBalance(), accountNumber };

                int rowsAffected = jdbcTemplate.update(sql, params);

                if (rowsAffected <= 0) {
                    // Handle the case when the update fails
                    throw new RuntimeException("Failed to debit amount from the account.");
                }
            } else {
                // Handle the case when the account accountBalance is insufficient
                throw new RuntimeException("Insufficient accountBalance in the account.");
            }
        }
        else {
        	throw new RuntimeException("Authentication failed with the given accountNumber.");
        }
    }
}
