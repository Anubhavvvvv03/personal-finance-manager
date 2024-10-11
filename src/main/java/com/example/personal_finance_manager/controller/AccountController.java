package com.example.personal_finance_manager.controller;

import com.example.personal_finance_manager.entity.Account;
import com.example.personal_finance_manager.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @QueryMapping
    public Account account(@Argument Long id) {
        return accountService.getAccountById(id);
    }

    @QueryMapping
    public List<Account> allAccounts(){
        return accountService.getAllAccounts();
    }

    @MutationMapping
    public Account createAccount(@Argument String name, @Argument BigDecimal initialBalance)
    {
        Account account=new Account();
        account.setName(name);
        account.setBalance(initialBalance);
        return accountService.createAccount(account);
    }

}
