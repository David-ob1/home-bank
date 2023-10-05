package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {
    private long id;

    private String number ;
    private LocalDate creationDate;
    private double balance;

    private List<transactionDTO> transactions;


    public AccountDTO(Account account){

        id = account.getId();
        number = account.getNumber();
        creationDate = account.getCreationDate();
        balance = account.getBalance();
        transactions = account.getTransaction()
                .stream()
                .map(transaction -> new transactionDTO(transaction))
                .collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public List<transactionDTO> getTransactions() {
        return transactions;
    }
}
