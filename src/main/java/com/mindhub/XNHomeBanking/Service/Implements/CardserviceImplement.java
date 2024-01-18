package com.mindhub.XNHomeBanking.service.Implements;

import com.mindhub.XNHomeBanking.service.CardService;
import com.mindhub.XNHomeBanking.models.Card;
import com.mindhub.XNHomeBanking.repositories.CardRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardserviceImplement implements CardService {
    @Autowired
    private CardRepositories cardRepositories;

    @Override
    public void saveCard(Card card) {
        cardRepositories.save(card);
    }

    @Override
    public Card findById(Long id) {
        return cardRepositories.findById(id).orElse(null);
    }

    @Override
    public void cardDelete(Card card){
        Card cards = cardRepositories.findById(card.getId()).orElse(null);
        cards.setCardStatus(false);
        cardRepositories.save(cards);
    }

}
