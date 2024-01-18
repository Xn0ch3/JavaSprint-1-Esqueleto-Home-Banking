package com.mindhub.XNHomeBanking.service;

import com.mindhub.XNHomeBanking.models.Card;

import java.util.Optional;

public interface CardService {
    void saveCard(Card card);


    Card findById(Long id);

    void cardDelete(Card card);

}
