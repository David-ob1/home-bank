package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
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
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;


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

    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount( Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());


        if( client.getAccounts().size() == 3){
            return new ResponseEntity<>("you already has 3 account" , HttpStatus.FORBIDDEN);
        }                                                      //solo el ultimo no incluye


            Account accountCAuten = new Account(generateNumberA(1l,100000000l),LocalDate.now(),0);
            client.addAccount(accountCAuten);
            accountRepository.save(accountCAuten);
            clientRepository.save(client);

         //   Account account1 = new Account("VIN001", LocalDate.now(),5000);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public String generateNumberA(long min, long max) {
       List<AccountDTO> account = getAllAccounts();
       Set<String> accounetSet = account.stream().map(accountDTO ->
           accountDTO.getNumber()

       ).collect(Collectors.toSet());

        String aux = "VIN-";
        long number;
        int numberOfDigits = 8;
        //expreso el numero en el formato deseado  //numeros como maximo 8 d
        String formatString = "%0" + numberOfDigits + "d";
        String numberCompleted;
        do{
        number = ThreadLocalRandom.current().nextLong(min, max);
        numberCompleted = aux +String.format(formatString,number);
        }
        while (accounetSet.contains(numberCompleted));
         return numberCompleted;
    }


}
