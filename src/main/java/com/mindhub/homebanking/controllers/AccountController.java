package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
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

    @Autowired
    private ClientService clientService;


    @RequestMapping("/accounts")
    public List<AccountDTO> getAllAccounts(){

        List<Account> accounts = accountService.findAllAccounts();

        Stream<Account> accountStream = accounts.stream();

        Stream<AccountDTO> accountDTOStream = accountStream.map(account -> new AccountDTO(account));

        List <AccountDTO> accountDTOS = accountDTOStream.collect(Collectors.toList());

        return accountDTOS;
    }

    @RequestMapping("/clients/current/accounts")
    public List<AccountDTO> getAll(Authentication authentication) {
        Client client = (clientService.findClientByEmail(authentication.getName()));
        List<AccountDTO> accounts = client.getAccounts().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
        return accounts;
    }


//    @RequestMapping("/accounts/{id}")
//    public AccountDTO getAccount(@PathVariable Long id){
//        //no se rompe la aplicacion si no se cumple
//        AccountDTO foundAccount = accountService.findAccountById(id).map(account -> new AccountDTO(account)).orElse(null);
//
//        return foundAccount;
//    }

    @RequestMapping("/accounts/{id}")
    public ResponseEntity<Object> getAccount(Authentication authentication,
                                             @PathVariable Long id) {
        Client client = (clientService.findClientByEmail(authentication.getName()));
        Set<Long> accountsId = client.getAccounts().stream().map(account -> account.getId()).collect(Collectors.toSet());
        Account account = accountService.findAccountById(id);
        if (!accountsId.contains(id)) {
            return new ResponseEntity<>("the account does not belong to the authenticated client",
                    HttpStatus.FORBIDDEN);
        }
        if (account != null) {
            return new ResponseEntity<>(new AccountDTO(account),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not found",
                    HttpStatus.NOT_FOUND);
        }
    }






    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(Authentication authentication){
        Client client = clientService.findClientByEmail(authentication.getName());


        if( client.getAccounts().size() >= 3){
            return new ResponseEntity<>("you already has 3 account" , HttpStatus.FORBIDDEN);
        }                                                      //solo el ultimo no incluye


        Account accountCAuten = new Account(generateNumberA(1l,100000000l), LocalDate.now(),0);
        client.addAccount(accountCAuten);
        accountService.saveAccount(accountCAuten);
        clientService.saveClient(client);

        //   Account account1 = new Account("VIN001", LocalDate.now(),5000);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public String generateNumberA(long min, long max) {
//       List<AccountDTO> account = getAllAccounts();
//       Set<String> accounetSet = account.stream().map(accountDTO ->
//           accountDTO.getNumber()
//
//       ).collect(Collectors.toSet());

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
        while(accountService.existsAccountByNumber(numberCompleted));
        return numberCompleted;
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
