package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;

import java.time.LocalDateTime;

public class transactionDTO {

    private Long id;

    private TransactionType type;

    private double amount;
    private String description;
    private LocalDateTime date;

    private Double balance;

    private  Boolean active;


    public transactionDTO(Transaction transaction){
        id = transaction.getId();
        type = transaction.getType();
        amount = transaction.getAmount();
        description = transaction.getDescription();
        date = transaction.getDate();
        balance = transaction.getCurrentBalance();
        active = transaction.getActive();

    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Double getBalance() {
        return balance;
    }
    public Boolean getActive() {
        return active;
    }
}
