package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Loan;

import java.util.List;

public class LoanDTO {
    private Long id;
    private String name;
    private double maxAmount;
    private List<Integer> payments;
    private  Double interest;

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments =  loan.getPayments();
        this.interest = loan.getInterest();

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public Double getInterest() {
        return interest;
    }


}