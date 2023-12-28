package com.mindhub.XNHomeBanking.controlers;

import com.mindhub.XNHomeBanking.models.*;
import com.mindhub.XNHomeBanking.repositories.CardRepositories;
import com.mindhub.XNHomeBanking.repositories.ClientsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    ClientsRepositories clientsRepositories;
    @Autowired
    CardRepositories cardRepositories;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<String> createdCard(Authentication authentication,
                                              @RequestParam CardColor cardColor,
                                              @RequestParam CardType cardType) {

        Client client = clientsRepositories.findByEmail(authentication.getName());
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
        int cvv = (int) (Math.random() * 999+100);



        Card card = new Card( numberCard, client.getFirstname()+" "+ client.getLastname(), cvv , LocalDate.now(), LocalDate.now().plusYears(5), cardColor, cardType);
        client.addCard(card);
        cardRepositories.save(card);

        return new ResponseEntity<>("Account Created", HttpStatus.CREATED);
    }

    private String getRandomCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i<4; i++){
            int seccion=(int) (Math.random()*9000+1000);
            cardNumber.append(seccion).append("-");
        }
        return cardNumber.substring(0,cardNumber.length()-1);
    }

}//Aca termina la clase de CardController
