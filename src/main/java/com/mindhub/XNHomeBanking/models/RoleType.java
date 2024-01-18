package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum RoleType {
    @Enumerated(EnumType.STRING)
    ADMIN,
    CLIENT,

}
