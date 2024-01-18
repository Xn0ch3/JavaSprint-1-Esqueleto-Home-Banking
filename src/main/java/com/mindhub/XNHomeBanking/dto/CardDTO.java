package com.mindhub.XNHomeBanking.dto;

import com.mindhub.XNHomeBanking.models.Card;
import com.mindhub.XNHomeBanking.models.CardColor;
import com.mindhub.XNHomeBanking.models.CardType;

import java.time.LocalDate;

public class CardDTO {

    private Long id;

    private String number;

    private String cardHolder;

    private Integer cvv;

    private LocalDate fromDate;

    private LocalDate ThrueDate;

    private CardColor cardColor;

    private CardType cardType;

    private boolean cardStatus;
    //Constructor CardDTO
    public CardDTO (Card card){

        this.id = card.getId();
        this.number = card.getNumber();
        this.cardHolder = card.getCardHolder();
        this.cvv = card.getCvv();
        this.fromDate = card.getFromDate();
        this.ThrueDate = card.getThrueDate();
        this.cardColor = card.getCardColor();
        this.cardType = card.getCardType();
        this.cardStatus = card.getCardStatus();
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public Integer getCvv() {
        return cvv;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getThrueDate() {
        return ThrueDate;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public CardType getCardType() {
        return cardType;
    }

    public boolean isCardStatus() {
        return cardStatus;
    }

}//Aca termina la Class CardDTO
