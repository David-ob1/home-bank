package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

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
    @ManyToOne (fetch = FetchType.EAGER)
//    @JoinColumn(name = "client_id")

    private Client client;

    //constructores


    public Account() {
    }

    public Account(String number) {
        this.number = number;
    }



    public Account  (String number, LocalDate creationDate, double balance){
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;

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
}
