package com.mindhub.XNHomeBanking.Service.Implement;

import com.mindhub.XNHomeBanking.Service.CardService;
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
}
