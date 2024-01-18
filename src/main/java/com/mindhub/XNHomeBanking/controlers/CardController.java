package com.mindhub.XNHomeBanking.controlers;

import com.mindhub.XNHomeBanking.service.CardService;
import com.mindhub.XNHomeBanking.service.ClientService;
import com.mindhub.XNHomeBanking.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.mindhub.XNHomeBanking.utils.Utils.generateCvv;
import static com.mindhub.XNHomeBanking.utils.Utils.getRandomCardNumber;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    ClientService clientService;
    @Autowired
    CardService cardService;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<String> createdCard(Authentication authentication,
                                              @RequestParam CardColor cardColor,
                                              @RequestParam CardType cardType) {

        Client client = clientService.findByEmail(authentication.getName());
        //Creamos
        long colorType = client.getCards().stream()
                .filter(card -> card.getCardColor() == cardColor && card.getCardType() == cardType)
                .count();
        long color = client.getCards().stream().filter(card -> card.getCardColor() == cardColor).count();
        long types = client.getCards().stream().filter(card -> card.getCardType() == cardType).count();
        if(types >= 3){
            return new ResponseEntity<>("It cannot have more than 3 cards."+ cardType, HttpStatus.FORBIDDEN);
        }
        if (color >=2){
            return new ResponseEntity<>("It cannot have more cards of this color."+ cardColor,HttpStatus.FORBIDDEN);
        }
        if (colorType >= 1){
            return new ResponseEntity<>("It cannot have more cards of this color"+ cardColor + "and this type"+ cardType, HttpStatus.FORBIDDEN);
        }
        if (client.getCards().size()>=6){
            return new ResponseEntity<>(
                    "Exceeded the number of cards it can have; its limit is 6.", HttpStatus.FORBIDDEN);
        }
        //Creamos un String para posteriormente asignarle un número aleatorio.
        String numberCard = getRandomCardNumber();
        int cvv = generateCvv();



        Card card = new Card( numberCard, client.getFirstname()+" "+ client.getLastname(), cvv , LocalDate.now(), LocalDate.now().plusYears(5), cardColor, cardType, true);
        client.addCard(card);
        cardService.saveCard(card);

        return new ResponseEntity<>("Card Created", HttpStatus.CREATED);

    }

    @PatchMapping("/clients/current/cards/delete")
    public ResponseEntity<String> deleteCard(@RequestParam Long id, Authentication authentication) {
        Client client = clientService.getAutenticatedClient(authentication.getName());
        Card cards = cardService.findById(id);

        if (cards.getCardStatus() && cards.getClient().getEmail().equals(authentication.getName())) {
            cardService.cardDelete(cards);
            return ResponseEntity.ok("Card successfully canceled.");
        }
        return new  ResponseEntity<>("Your Card is Delete", HttpStatus.BAD_REQUEST);
    }

}//Aca termina la clase de CardController
