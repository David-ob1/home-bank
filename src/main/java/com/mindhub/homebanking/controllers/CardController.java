package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.CardService;
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
    private CardService cardService;

    @PostMapping("/clients/current/cards")public ResponseEntity <Object> createCard
    (@RequestParam CardColor cardColor, @RequestParam CardType cardType, Authentication authentication){

        return cardService.createCard(cardColor,cardType,authentication);
    }


//    public int generateRandomNumber(int min, int max) {
//        return (int) ((Math.random() * (max - min)) + min);
//    }
//
//    public String generateNumberCard() {
//        StringBuilder cardNumber;
//        do {
//            cardNumber = new StringBuilder();
//            for (int i = 0; i < 16; i++) {
//                cardNumber.append(generateRandomNumber(0, 9));
//                if ((i + 1) % 4 == 0 && i != 15) cardNumber.append("-");
//            }
//        } while (cardRepository.existsByNumber(cardNumber.toString()));
//        return cardNumber.toString();
//    }
//
//
//
//
//    public String generateCvvCard() {
//
//        StringBuilder cardNumber;
//        do {
//            cardNumber = new StringBuilder();
//            for (byte i = 0; i <= 2; i++) {
//                //push()
//                cardNumber.append(generateRandomNumber(0, 9));
//            }
//        } while (cardRepository.existsByCvv(cardNumber.toString()));
//        return cardNumber.toString();
//    }



}

