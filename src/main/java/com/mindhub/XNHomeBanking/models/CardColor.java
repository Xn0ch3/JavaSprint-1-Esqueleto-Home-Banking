package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum CardColor {
    @Enumerated(EnumType.STRING)

    GOLD,

    SILVER,

    TITANIUM
}
