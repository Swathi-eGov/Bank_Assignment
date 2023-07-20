package com.example.bank.assignment.controller;

import com.example.bank.assignment.model.Account;
import com.example.bank.assignment.repository.JdbcAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {
	
	@Autowired
    private final JdbcAccountsRepository accountsRepository;

    public AccountsController(JdbcAccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        int rowsAffected = accountsRepository.save(account);

        if (rowsAffected > 0) {
            return new ResponseEntity<>("Account created successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create the account.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{accountNumber}/{password}")
    public ResponseEntity<?> getAccountByNumber(@PathVariable String accountNumber, @PathVariable String password) {
        Account account = accountsRepository.findByIdAndPassword(accountNumber, password);

        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{accountNumber}/{password}")
    public ResponseEntity<?> updateAccount(@PathVariable String accountNumber, @PathVariable String password, @RequestBody Account updatedAccount) {
        try {
            Account account = accountsRepository.updateMobileNumber(accountNumber, updatedAccount.getMobileNumber(), password);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{accountNumber}/{password}")
    public ResponseEntity<?> deleteAccount(@PathVariable String accountNumber, @PathVariable String password) {
        try {
            accountsRepository.delete(accountNumber, password);
            return new ResponseEntity<>("Account deleted successfully.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{accountNumber}/credit/{amount}/{password}")
    public ResponseEntity<?> creditAmount(@PathVariable String accountNumber, @PathVariable double amount, @PathVariable String password) {
        try {
            accountsRepository.creditAmount(accountNumber, amount, password);
            return new ResponseEntity<>("Amount credited successfully.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{accountNumber}/debit/{amount}/{password}")
    public ResponseEntity<?> debitAmount(@PathVariable String accountNumber, @PathVariable double amount, @PathVariable String password) {
        try {
            accountsRepository.debitAmount(accountNumber, amount, password);
            return new ResponseEntity<>("Amount debited successfully.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

