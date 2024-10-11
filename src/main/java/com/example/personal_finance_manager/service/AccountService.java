package com.example.personal_finance_manager.service;

import com.example.personal_finance_manager.entity.Account;
import com.example.personal_finance_manager.entity.Transaction;
import com.example.personal_finance_manager.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private  AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id)
    {
        Optional<Account> account=accountRepository.findById(id);
        return account.orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    public List<Account> getAllAccounts()
    {
        return accountRepository.findAll();
    }

    //Implement other CRUD methods
}