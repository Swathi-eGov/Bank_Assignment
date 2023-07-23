package com.example.bank.assignment.repository;

import java.util.UUID;

import com.example.bank.assignment.model.Account;
import com.example.bank.assignment.model.Transactions;

public interface AccountsRepository {

	int save(Account account);

	void delete(UUID accountNumber, String password);

	void update(Account account);

	void creditAmount(Transactions transaction);

	void debitAmount(Transactions transaction);

	void transferAmount(Transactions transaction);

	Account findById(UUID accountNumber);

	Account findByIdAndPassword(UUID accountNumber, String password);

}