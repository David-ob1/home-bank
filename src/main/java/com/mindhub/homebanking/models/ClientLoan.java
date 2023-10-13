package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Entity
public class ClientLoan {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO , generator = "generator")
    @GenericGenerator(name = "generator" , strategy = "native")
    private long id;

    private double amount ;

    private int Payments ;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Loan loan;

    public ClientLoan() {
    }


    public ClientLoan(double amount, int payments) {
        this.amount = amount;
        Payments = payments;
    }



    public long getId() {
        return id;
    }



    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return Payments;
    }

    public void setPayments(int payments) {
        Payments = payments;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
