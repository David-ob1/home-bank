package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity //creamos tabla en BD con los datos de esta clase

public class Client {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO , generator = "native")
    @GenericGenerator(name = "native" , strategy = "native")//este ultimo asi
    private Long id;

    private String name,lastName, mail;


    @OneToMany(mappedBy = "client" , fetch = FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(String name,String lastName) {
        this.name = name;
        this.lastName = lastName;
    }




    public Client(String name,String lastName,String mail) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;

//        @OneToMany(mappedBy = "client" , fetch = FetchType.EAGER)
//        private Set<Account> accounts = new HashSet<>();

    }


    // comportamientos

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

    public void addAccount(Account account){
        account.setClient(this);
        this.accounts.add(account);
        //add es de colecciones
    }

    public Long getId() {
        return id;
    }

}
