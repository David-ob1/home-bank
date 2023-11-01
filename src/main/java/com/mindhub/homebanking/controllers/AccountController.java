package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import  java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @RequestMapping("/accounts")
    public List<AccountDTO> getAllAccounts(){

        return accountService.getAllAccounts();
    }

    @RequestMapping("/clients/current/accounts")
    public List<AccountDTO> getAll(Authentication authentication) {
        return accountService.getAll(authentication);
    }


    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){

        return accountService.getAccount(id);
    }

    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount( Authentication authentication){


        return accountService.createAccount(authentication);
    }



    public String generateNumberA(long min, long max) {

         return accountService.generateNumberA(min,max);
    }


//    @PostMapping("/clients/current/accounts")
//    public ResponseEntity<String> createAccount(Authentication authentication) {
//        Client currentClient = clientRepository.findByEmail(authentication.getName());
//        if (clientRepository.countAccountsByClient(currentClient) >= 3) {
//            return new ResponseEntity<>("You already have the maximum number of possible accounts (3)", HttpStatus.FORBIDDEN);
//        }
//        int accountsNumber;
//        String accountNumberString;
//        do {
//            accountsNumber = getRandomNumber(0, 99999999);
//            accountNumberString = String.valueOf(accountsNumber);
//        } while (accountRepository.existsByNumber(accountNumberString));
//
//        Random random = new Random();
//        String accountNumber = "VIN-" + (10000000 + random.nextInt(90000000));
//        //hacer la comprobacion de que el numero de cuenta existe o no para que no se repita
//        Account newAccount = new Account();
//        newAccount.setAccountNumber(accountNumber);
//        newAccount.setBalance(0);
//        newAccount.setClient(currentClient);
//        accountRepository.save(newAccount);
//        return new ResponseEntity<>("Cuenta creada con éxito", HttpStatus.CREATED);
//    }


}
