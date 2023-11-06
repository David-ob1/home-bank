package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;

public class LoanApplicationDTO {

    private long idLoan;

    private double amount;

    private int payments;

    private String destinationAccount;

    public LoanApplicationDTO(long idLoan, double amount, int payments, String destinationAccount) {
        this.idLoan = idLoan;
        this.amount = amount;
        this.payments = payments;
        this.destinationAccount = destinationAccount;
    }


    public long getIdLoan() {
        return idLoan;
    }

    public double getAmount() {
        return amount;
    }

    public int getPayments() {
        return payments;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }


}
