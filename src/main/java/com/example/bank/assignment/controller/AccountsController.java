package com.example.bank.assignment.controller;

import com.example.bank.assignment.error.CustomError;
import com.example.bank.assignment.model.Account;
import com.example.bank.assignment.model.Transactions;
import com.example.bank.assignment.repository.JdbcAccountsRepository;
import com.example.bank.assignment.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

	@Autowired
	private final JdbcAccountsRepository accountsRepository;
	@Autowired
	private final TransactionRepository transactionRepository;

	public AccountsController(JdbcAccountsRepository accountsRepository, TransactionRepository transactionRepository) {
		this.accountsRepository = accountsRepository;
		this.transactionRepository = transactionRepository;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createAccount(@RequestBody @Valid Account account, BindingResult result) {
		if (result.hasErrors()) {
			// Validation failed in Java
			List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());

			CustomError customError = new CustomError("Failed to create the account. Data validation error.", errors);
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		} else {
			try {
				int rowsAffected = accountsRepository.save(account);

				if (rowsAffected > 0) {
					return new ResponseEntity<>("Account created successfully.", HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>("Failed to create the account.", HttpStatus.BAD_REQUEST);
				}
			} catch (DataIntegrityViolationException ex) {
				// Validation failed in the database
				List<String> errors = new ArrayList<>();
				errors.add(ex.getMessage()); // Add the specific database error message

				CustomError customError = new CustomError("Failed to create the account. Data validation error.",
						errors);
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
		}
	}

	@GetMapping("/search")
	public ResponseEntity<?> getAccountByNumber(@RequestBody Account account) {
		Account existingaccount = accountsRepository.findByIdAndPassword(account.getAccountNumber(),
				account.getPassword());

		if (existingaccount != null) {
			return new ResponseEntity<>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateAccount(@RequestBody Account accountToUpdate) {
		Account existingAccount = accountsRepository.findByIdAndPassword(accountToUpdate.getAccountNumber(),
				accountToUpdate.getPassword());

		if (existingAccount == null) {
			return new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
		}

		// Update the fields based on the provided values
		if (accountToUpdate.getAccountHolderName() != null) {
			existingAccount.setAccountHolderName(accountToUpdate.getAccountHolderName());
		}

		if (accountToUpdate.getMobileNumber() != null) {
			existingAccount.setMobileNumber(accountToUpdate.getMobileNumber());
		}

		if (accountToUpdate.getAddress() != null) {
			existingAccount.setAddress(accountToUpdate.getAddress());
		}

		// Save the updated account in the repository
		accountsRepository.update(existingAccount);

		return ResponseEntity.ok("Account updated successfully.");
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteAccount(@RequestBody Account account) {
		try {
			accountsRepository.delete(account.getAccountNumber(), account.getPassword());
			return new ResponseEntity<>("Account deleted successfully.", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/credit")
	public ResponseEntity<String> credit(@RequestBody Transactions creditTransaction) {
		try {
			accountsRepository.creditAmount(creditTransaction);
			transactionRepository.saveTransaction(creditTransaction, "credit");
			return new ResponseEntity<>("Amount credited successfully.", HttpStatus.OK);
		} catch (DataAccessException ex) {
			return new ResponseEntity<>("Failed to credit amount.", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/debit")
	public ResponseEntity<String> debit(@RequestBody Transactions debitTransaction) {
		try {
			accountsRepository.debitAmount(debitTransaction);
			transactionRepository.saveTransaction(debitTransaction, "debit");
			return new ResponseEntity<>("Amount debited successfully.", HttpStatus.OK);
		} catch (DataAccessException ex) {
			return new ResponseEntity<>("Failed to debit amount.", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestBody Transactions transferTransaction) {
		try {
			accountsRepository.transferAmount(transferTransaction);
			transactionRepository.saveTransaction(transferTransaction, "transfer");

			return new ResponseEntity<>("Transfer successful.", HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
