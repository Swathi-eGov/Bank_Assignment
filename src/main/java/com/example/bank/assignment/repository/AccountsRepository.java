package com.example.bank.assignment.repository;

import com.example.bank.assignment.model.Account;

public interface AccountsRepository {

    int save(Account account);

    void delete(String accountNumber, String password);

    Account updateMobileNumber(String accountNumber, String newMobileNumber, String password);

    void creditAmount(String accountNumber, double amount,String password);

    void debitAmount(String accountNumber, double amount,String password);

    Account findById(String accountNumber);
    
    Account findByIdAndPassword(String accountNumber, String password);
}