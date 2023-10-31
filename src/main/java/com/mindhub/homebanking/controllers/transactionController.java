package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequestMapping ("/api")
public class transactionController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    @PostMapping("/clients/current/transfer")
    public ResponseEntity<Object> transfer(
            Authentication authentication,
            @RequestParam double amount, @RequestParam String description,
            @RequestParam String accountOrigin, @RequestParam String accountDestiny){


        Client client = clientRepository.findByEmail(authentication.getName());
        Account accountDebit = accountRepository.findByNumber(accountOrigin);
        Account accountCredit = accountRepository.findByNumber(accountDestiny);


        if(description.isEmpty() || description.isBlank()){
            return new ResponseEntity<>("the description is not valid,complete it please.", HttpStatus.FORBIDDEN);
        }
        if(accountOrigin.equals(accountDestiny)){
            return new ResponseEntity<>("the accounts must be different", HttpStatus.FORBIDDEN);
        }

        if(accountOrigin.isEmpty() || accountOrigin.isBlank()){
            return new ResponseEntity<>("the description is not valid,complete it please.", HttpStatus.FORBIDDEN);
        }

        if(accountDestiny.isEmpty() || accountDestiny.isBlank()){
            return new ResponseEntity<>("the description is not valid,complete it please.", HttpStatus.FORBIDDEN);
        }


        if (client == null) {
            throw new UsernameNotFoundException("Unknow client " + authentication.getName());
        }

        if(accountDebit.getBalance() < amount){
          return new ResponseEntity<>("you don't have enough credit",HttpStatus. CONFLICT);
        }


        //account == Authentication

        // son 2 pasa de uno a otro
        Transaction transaction1 = new Transaction(TransactionType.DEBIT,(-amount),accountDebit.getNumber() + description, LocalDateTime.now());

        Transaction transaction2 = new Transaction(TransactionType.CREDIT,amount, accountCredit.getNumber() + description, LocalDateTime.now());

        //guardo las transaction 1 y 2 tanto en el repository como en las cuentas
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        accountDebit.addTransaction(transaction1);
        accountCredit.addTransaction(transaction2);

        //restar y sumar montos


        accountDebit.setBalance(accountDebit.getBalance() - amount);
        accountCredit.setBalance(accountCredit.getBalance() + amount);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
