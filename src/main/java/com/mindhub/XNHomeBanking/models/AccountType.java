package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum AccountType {
    @Enumerated(EnumType.STRING)
    CURRENT,
    @Enumerated(EnumType.STRING)
    SAVINGS,
}
