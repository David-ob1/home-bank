package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    //atributos o propiedades
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO , generator = "generator")
    @GenericGenerator(name = "generator" , strategy = "native")
        private long id;

        private String number ;
        private LocalDate creationDate;
        private double balance;
        private Boolean active;
        private AccountType accountType;

    @ManyToOne (fetch = FetchType.EAGER)
    private Client client;


    @OneToMany(mappedBy = "account" , fetch = FetchType.EAGER)
    private Set<Transaction> transaction = new HashSet<>();



    //constructores


    public Account() {
    }

    public Account(String number) {
        this.number = number;
    }



    public Account  (String number, LocalDate creationDate, double balance, Boolean active,AccountType accountType){
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.active = active;
        this.accountType = accountType;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

        public void setCreationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
        }
    
        public double getBalance() {
            return balance;
        }
    
        public void setBalance(double balance) {
            this.balance = balance;
        }
    
        public Client getClient() {
            return client;
        }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addTransaction(Transaction transaction){
        transaction.setAccount(this);
        this.transaction.add(transaction);
        //add es de colecciones
    }

    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
