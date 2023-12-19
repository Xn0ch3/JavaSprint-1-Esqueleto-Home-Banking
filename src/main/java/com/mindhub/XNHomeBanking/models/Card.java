package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Card {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String cardHolder;

    private Integer cvv;

    private LocalDate fromDate;

    private LocalDate ThrueDate;

    private CardColor cardColor;

    private  CardType cardType;

    //Relacionamos ManyToOne varias tarjetas a un cliente.
    @ManyToOne
    private Client client;

    //Contructor Vació.

    public Card() {
    }

    //Constructor Parametrizado.

    public Card(String number, String cardHolder, Integer cvv, LocalDate fromDate, LocalDate thrueDate, CardColor cardColor, CardType cardType) {
        this.number = number;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
        this.fromDate = fromDate;
        ThrueDate = thrueDate;
        this.cardColor = cardColor;
        this.cardType = cardType;
    }


    //Getters & Setters

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getThrueDate() {
        return ThrueDate;
    }

    public void setThrueDate(LocalDate thrueDate) {
        ThrueDate = thrueDate;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    //ToString


    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", cvv=" + cvv +
                ", fromDate=" + fromDate +
                ", ThrueDate=" + ThrueDate +
                ", cardColor=" + cardColor +
                ", cardType=" + cardType +
                ", client=" + client +
                '}';
    }
}//Aca Termina la Class Card

