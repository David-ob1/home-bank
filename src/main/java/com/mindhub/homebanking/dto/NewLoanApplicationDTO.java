package com.mindhub.homebanking.dto;

import java.util.List;

public class NewLoanApplicationDTO {

    private String  nameLoan;
    private Double amountMax;
    private Double interest;

    private List<Integer> paymets;



    public NewLoanApplicationDTO() {
    }

    public String getNameLoan() {
        return nameLoan;
    }

    public Double getAmountMax() {
        return amountMax;
    }

    public Double getInterest() {
        return interest;
    }

    public List<Integer> getPaymets() {
        return paymets;
    }



}
