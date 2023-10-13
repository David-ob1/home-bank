package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;

    private Long loanID;
    private String name;

    private double amount;

    private int payment;

    //metodos o comportamientos

    public ClientLoanDTO(ClientLoan clientLoan) {
        id = clientLoan.getId();
        loanID = clientLoan.getLoan().getId();
        name = clientLoan.getLoan().getName();
        amount = clientLoan.getAmount();
        payment = clientLoan.getPayments();
    }


    public Long getId() {
        return id;
    }

    public Long getLoanID() {
        return loanID;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public int getPayment() {
        return payment;
    }



}
