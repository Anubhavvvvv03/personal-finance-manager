package com.example.personal_finance_manager.service;

import com.example.personal_finance_manager.entity.Account;
import com.example.personal_finance_manager.entity.Category;
import com.example.personal_finance_manager.entity.Transaction;
import com.example.personal_finance_manager.entity.TransactionType;
import com.example.personal_finance_manager.repository.AccountRepository;
import com.example.personal_finance_manager.repository.CategoryRepository;
import com.example.personal_finance_manager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Transaction createTransaction(Transaction transaction, Long categoryId, Long accountId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        transaction.setCategory(category);
        transaction.setAccount(account);

        Transaction savedTransaction = transactionRepository.save(transaction);

        // Update account balance
        BigDecimal balanceChange = transaction.getType() == TransactionType.INCOME ?
                transaction.getAmount() : transaction.getAmount().negate();
        account.setBalance(account.getBalance().add(balanceChange));
        accountRepository.save(account);

        return savedTransaction;
    }

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
