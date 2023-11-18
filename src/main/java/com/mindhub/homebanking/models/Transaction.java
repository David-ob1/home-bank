package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    //atributos o propiedades

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO , generator = "genId")
    @GenericGenerator(name = "genId" , strategy = "native")//este ultimo asi
    private Long id;

    private TransactionType type;

    private double amount;
    private String description;
    private LocalDateTime date;

    private  Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

    private double currentBalance;

    public Transaction() {
    }

    public Transaction(TransactionType type, double amount, String description, LocalDateTime date,  double currentBalance,Boolean active) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.currentBalance = currentBalance;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
