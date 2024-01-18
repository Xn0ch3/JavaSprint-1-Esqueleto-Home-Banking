package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum CardType {
    @Enumerated(EnumType.STRING)

    CREDIT,

    DEBIT
}
