package com.mindhub.homebanking.services.implement;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class TransactionServiceImplement implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<Object> transfer(
            Authentication authentication,
            @RequestParam double amount, @RequestParam String description,
            @RequestParam String accountOrigin, @RequestParam String accountDestiny){

        Client client = clientRepository.findByEmail(authentication.getName());
        Account accountDebit = accountRepository.findByNumber(accountOrigin);
        Account accountCredit = accountRepository.findByNumber(accountDestiny);


//        if(description.isEmpty() || description.isBlank()){
//            return new ResponseEntity<>("the description is not valid,complete it please.", HttpStatus.FORBIDDEN);
//        }
//        if(accountOrigin.equals(accountDebit)){
//            return new ResponseEntity<>("the accounts must be different", HttpStatus.FORBIDDEN);
//        }

        if(accountDebit.getNumber() == accountCredit.getNumber()){
            return new ResponseEntity<>("the accounts must be different", HttpStatus.FORBIDDEN);
        }


//        if(accountOrigin.isEmpty() || accountOrigin.isBlank()){
//            return new ResponseEntity<>("the description is not valid,complete it please.", HttpStatus.FORBIDDEN);
//        }

//        if(accountDestiny.isEmpty() || accountDestiny.isBlank()){
//            return new ResponseEntity<>("the description is not valid,complete it please.", HttpStatus.FORBIDDEN);
//        }


        if(accountDebit.getBalance() < amount){
            return new ResponseEntity<>("you don't have enough credit",HttpStatus.FORBIDDEN);
        }

        if(accountDebit.getClient()!= client){
            return new ResponseEntity<>("the origin account doesn't belong to the authenticated client",HttpStatus.FORBIDDEN);
        }

        if (accountDebit == null){
            return new ResponseEntity<>("the origin account doesn't exist", HttpStatus.FORBIDDEN);
        }

        if (accountCredit == null){
            return new ResponseEntity<>("the destiny account doesn't exist", HttpStatus.FORBIDDEN);
        }


        //account == Authentication

        // son 2 pasa de uno a otro
        Transaction transaction1 = new Transaction(TransactionType.DEBIT,(-amount),accountDebit.getNumber() + description, LocalDateTime.now());
        Transaction transaction2 = new Transaction(TransactionType.CREDIT,amount, accountCredit.getNumber() + description, LocalDateTime.now());

        //guardo las transaction 1 y 2 tanto en el repository como en las cuentas
        transactionRepository.save(transaction1);
        accountDebit.addTransaction(transaction1);
        transactionRepository.save(transaction2);
        accountCredit.addTransaction(transaction2);

        //restar y sumar montos

        accountDebit.setBalance(accountDebit.getBalance() - amount);
        accountCredit.setBalance(accountCredit.getBalance() + amount);

        return new ResponseEntity<>("the transaction was successful",HttpStatus.CREATED);
    }






}
