package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class AccountController {
@Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/accounts")
    public List<AccountDTO> getAllAccounts(){

        List<Account> accounts = accountRepository.findAll();

        Stream<Account> accountStream = accounts.stream();

        Stream<AccountDTO> accountDTOStream = accountStream.map(account -> new AccountDTO(account));

        List <AccountDTO> accountDTOS = accountDTOStream.collect(Collectors.toList());

        return accountDTOS;
    }


    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
//no se rompe la aplicacion si no se cumple
        AccountDTO foundAccount = accountRepository.findById(id).map(account -> new AccountDTO(account)).orElse(null);

        return foundAccount;
    }

}
