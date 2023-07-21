package com.example.bank.assignment.repository;

import java.util.UUID;

import com.example.bank.assignment.model.Account;

public interface AccountsRepository {

    int save(Account account);

    void delete(UUID accountNumber, String password);
    
    void update(Account account);

    void creditAmount(UUID accountNumber, double amount,String password);

    void debitAmount(UUID accountNumber, double amount,String password);

    Account findById(UUID accountNumber);
    
    Account findByIdAndPassword(UUID accountNumber, String password);
}