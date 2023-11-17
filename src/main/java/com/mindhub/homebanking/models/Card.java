package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGen")
    @GenericGenerator(name = "idGen", strategy = "native")
    private long id;

//    @ManyToOne(fetch = FetchType.EAGER)
//    private Client cardholder;
    private String cardholder;
    private CardType type;
    private CardColor color;
    private String number;
    private String cvv;


    private LocalDate thruDate;

    private LocalDate fromDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    private Boolean active;

    public Card() {
    }

    public Card(String s, CardType cardType, CardColor cardColor, String string, String generateCvvCard, LocalDate localDate, LocalDate now, Boolean b) {
    }

    public Card(long id, String cardholder, CardType type, CardColor color, String number, String cvv, LocalDate thruDate, LocalDate fromDate, Client client, Boolean active) {
        this.id = id;
        this.cardholder = cardholder;
        this.type = type;
        this.color = color;
        this.number = number;
        this.cvv = cvv;
        this.active = active;
        this.thruDate = thruDate;
        this.fromDate = fromDate;
        this.client = client;
    }





    public Card(String s, CardType cardType, String s1, String number, LocalDate localDate, LocalDate now) {
    }

    public long getId() {
        return id;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor cardColor) {
        this.color = cardColor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Boolean isActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }


    
    
}

