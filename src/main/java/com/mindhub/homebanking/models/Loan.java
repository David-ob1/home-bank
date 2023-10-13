package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Loan {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO , generator = "native")
    @GenericGenerator(name = "native" , strategy = "native")//este ultimo asi
    private Long id;

    private String name;

    private double maxAmount;

    @ElementCollection
    private List<Integer> payments;

    @OneToMany(mappedBy = "loan" , fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();


    public Loan (){

    }

    public Loan(String name, double maxAmount, List<Integer> payments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
    }


    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setLoan(this);
      //  this.clientLoans.add(clientLoan);
        clientLoans.add(clientLoan);

    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public List<Client> getClient() {
        return clientLoans.stream().map(client -> client.getClient()).collect(Collectors.toList());
    }



    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxAmount=" + maxAmount +
                ", payments=" + payments +
                '}';
    }


}
