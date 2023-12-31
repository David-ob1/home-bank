package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Client;

import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String name,lastName, mail;

    //puede ser List o set
        private List<AccountDTO> accounts;


    public ClientDTO(Client client){
        id = client.getId();
        name = client.getName();
        lastName = client.getLastName();
        mail = client.getMail();
        accounts = client.getAccounts().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }
}
