package com.example.bank.assignment.repository;

import com.example.bank.assignment.mapper.AccountRowMapper;
import com.example.bank.assignment.model.Account;
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
         return jdbcTemplate.update("INSERT INTO accounts (account_number,account_holder_name,mobile_number,balance, password) VALUES(?,?,?,?,?)",
                account.getAccountNumber(),account.getAccountHolderName(),account.getMobileNumber(),account.getBalance(), account.getPassword() );
    }
    @Override
    public Account findById(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        Object[] params = { accountNumber };

        try {
            return jdbcTemplate.queryForObject(sql, params, new AccountRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null; // Account not found
        }
    }
    
    
    public Account findByIdAndPassword(String accountNumber,String password) {
        String sql = "SELECT * FROM accounts WHERE account_number = ? AND password=?";
        Object[] params = { accountNumber, password };

        try {
            return jdbcTemplate.queryForObject(sql, params, new AccountRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null; // Account not found
        }
    }
    @Override
    public void delete(String accountNumber, String password) {
        String sql = "DELETE FROM accounts WHERE account_number = ? AND password=?";
        Object[] params = { accountNumber , password};

        int rowsAffected = jdbcTemplate.update(sql, params);

        if (rowsAffected <= 0) {
            // Handle the case when the delete fails
            throw new RuntimeException("Failed to delete the account.");
        }
    }
    @Override
    public Account updateMobileNumber(String accountNumber, String newMobileNumber, String password) {
        String sql = "UPDATE accounts SET mobile_number = ? WHERE account_number = ? AND password=?";
        Object[] params = { newMobileNumber, accountNumber, password };

        int rowsAffected = jdbcTemplate.update(sql, params);

        if (rowsAffected > 0) {
            return findById(accountNumber);
        } else {
            // Handle the case when the update fails
            throw new RuntimeException("Failed to update the mobile number for the account.");
        }
    }
    @Override
    public void creditAmount(String accountNumber, double amount, String password) {
        Account account = findByIdAndPassword(accountNumber,password);
        if (account != null) {
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);

            String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
            Object[] params = { account.getBalance(), accountNumber };

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
    public void debitAmount(String accountNumber, double amount, String password) {
        Account account = findByIdAndPassword(accountNumber,password);
        if (account != null) {
            double newBalance = account.getBalance() - amount;
            if (newBalance >= 0) {
                account.setBalance(newBalance);

                String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
                Object[] params = { account.getBalance(), accountNumber };

                int rowsAffected = jdbcTemplate.update(sql, params);

                if (rowsAffected <= 0) {
                    // Handle the case when the update fails
                    throw new RuntimeException("Failed to debit amount from the account.");
                }
            } else {
                // Handle the case when the account balance is insufficient
                throw new RuntimeException("Insufficient balance in the account.");
            }
        }
        else {
        	throw new RuntimeException("Authentication failed with the given accountNumber.");
        }
    }
}
