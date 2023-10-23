package com.mindhub.homebanking.models;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity //creamos tabla en BD con los datos de esta clase

public class Client {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO , generator = "native")
    @GenericGenerator(name = "native" , strategy = "native")//este ultimo asi
    private Long id;

    private String name,lastName, mail,password;

    @OneToMany(mappedBy = "client" , fetch = FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "client" , fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();


    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(String name,String lastName) {
        this.name = name;
        this.lastName = lastName;
    }


    public Client(String name,String lastName,String mail,String password) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
//        @OneToMany(mappedBy = "client" , fetch = FetchType.EAGER)
//        private Set<Account> accounts = new HashSet<>();

    }



    // comportamientos


    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void addAccount(Account account){
        account.setClient(this);
        this.accounts.add(account);
        //add es de colecciones
    }

    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setClient(this);
        this.clientLoans.add(clientLoan);

    }

    public void addCard(Card card) {
        card.setClient(this);
        cards.add(card);
    }

    public Long getId() {
        return id;
    }
    @JsonIgnore
    public List<Loan> getLoans() {
        return clientLoans.stream().map(loans -> loans.getLoan()).collect(Collectors.toList());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
