package com.mindhub.homebanking.services.implement;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountServiceImplement implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public boolean existsAccountByNumber(String number) {
        return accountRepository.existsByNumber(number);
    }

    @Override
    public Account findAccountByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

}






















    //
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//
//    @Override
//    public ResponseEntity<Object> createAccount(Authentication authentication){
//        Client client = clientRepository.findByEmail(authentication.getName());
//
//
//        if( client.getAccounts().size() == 3){
//            return new ResponseEntity<>("you already has 3 account" , HttpStatus.FORBIDDEN);
//        }                                                      //solo el ultimo no incluye
//
//
//        Account accountCAuten = new Account(generateNumberA(1l,100000000l), LocalDate.now(),0);
//        client.addAccount(accountCAuten);
//        accountRepository.save(accountCAuten);
//        clientRepository.save(client);
//
//        //   Account account1 = new Account("VIN001", LocalDate.now(),5000);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//
//@Override
//    public String generateNumberA(long min, long max) {
////       List<AccountDTO> account = getAllAccounts();
////       Set<String> accounetSet = account.stream().map(accountDTO ->
////           accountDTO.getNumber()
////
////       ).collect(Collectors.toSet());
//
//        String aux = "VIN-";
//        long number;
//        int numberOfDigits = 8;
//        //expreso el numero en el formato deseado  //numeros como maximo 8 d
//        String formatString = "%0" + numberOfDigits + "d";
//        String numberCompleted;
//        do{
//            number = ThreadLocalRandom.current().nextLong(min, max);
//            numberCompleted = aux +String.format(formatString,number);
//        }
//        while (accountRepository.existsByNumber(numberCompleted));
//        return numberCompleted;
//    }


//}