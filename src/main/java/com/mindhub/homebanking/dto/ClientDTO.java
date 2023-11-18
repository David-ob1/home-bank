package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Client;

import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String name,lastName, email;

        private List<AccountDTO> accounts;

        private List<ClientLoanDTO> loans;

        private List<CardDTO> cards;


    public ClientDTO(Client client){
        id = client.getId();
        name = client.getName();
        lastName = client.getLastName();
        email = client.getEmail();
        accounts = client.getAccounts().stream().filter(account -> account.getActive()).map(accountActive -> new AccountDTO(accountActive)).collect(Collectors.toList());
        loans = client.getClientLoans().stream().map(loan -> new ClientLoanDTO(loan)).collect(Collectors.toList());
        cards = client.getCards().stream().filter(card -> card.isActive()).map(cardActive -> new CardDTO(cardActive)).collect(Collectors.toList());
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

    public String getEmail() {
        return email;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public List<ClientLoanDTO> getLoans() {
        return loans;
    }

    public List<CardDTO> getCards() {
        return cards;
    }
}
