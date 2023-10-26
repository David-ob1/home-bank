package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
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
public class cardController {
    @Autowired
  private  ClientRepository clientRepository;
    @Autowired
    private CardRepository cardRepository;

    @PostMapping("/clients/current/cards")
    public ResponseEntity <Object> crateCard(@RequestParam CardColor cardColor, @RequestParam CardType cardType, Authentication authentication){
        //busco al cliente x mail
        Client client = clientRepository.findByEmail(authentication.getName());

        int numberOfCardType =  // card.getType() == cardType
                (int) client.getCards().stream().filter(card -> card.getType().equals(cardType)).count();


        if (numberOfCardType == 3) {
            return new ResponseEntity<>("You cannot have more than three cards of the same type.", HttpStatus.FORBIDDEN);
        }

        Card card = new Card(client.getName()+" "+ client.getLastName(),cardType,cardColor,generateNumberCard(),generateCvvCard(),LocalDate.now().plusYears(5),LocalDate.now());

        client.addCard(card);
        cardRepository.save(card);
        clientRepository.save(client);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public int generateRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String generateNumberCard() {
        StringBuilder cardNumber;
        do {
            cardNumber = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                cardNumber.append(generateRandomNumber(0, 9));
                if ((i + 1) % 4 == 0 && i != 15) cardNumber.append("-");
            }
        } while (cardRepository.existsByNumber(cardNumber.toString()));
        return cardNumber.toString();
    }
    public String generateCvvCard() {

        StringBuilder cardNumber;
        do {
            cardNumber = new StringBuilder();
            for (byte i = 0; i <= 2; i++) {
                          //push()
                cardNumber.append(generateRandomNumber(0, 9));
            }
        } while (cardRepository.existsByCvv(cardNumber.toString()));
        return cardNumber.toString();
    }




}

