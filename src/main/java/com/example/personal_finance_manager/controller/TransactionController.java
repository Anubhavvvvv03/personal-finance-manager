package com.example.personal_finance_manager.controller;

import com.example.personal_finance_manager.entity.Transaction;
import com.example.personal_finance_manager.entity.TransactionType;
import com.example.personal_finance_manager.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @QueryMapping
    public Transaction transaction(@Argument Long id) {
        return transactionService.getTransactionById(id);
    }

    @QueryMapping
    public List<Transaction> allTransactions() {
        return transactionService.getAllTransactions();
    }

    @MutationMapping
    public Transaction createTransaction(@Argument BigDecimal amount,
                                         @Argument String description,
                                         @Argument LocalDate date,
                                         @Argument TransactionType type,
                                         @Argument Long categoryId,
                                         @Argument Long accountId) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setDate(date);
        transaction.setType(type);
        return transactionService.createTransaction(transaction, categoryId, accountId);
    }

    // Implement other mutation methods
}