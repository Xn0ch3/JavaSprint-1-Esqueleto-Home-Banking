package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum TransactionType {
    @Enumerated(EnumType.STRING)

    DEBIT,

    CREDIT,

}
