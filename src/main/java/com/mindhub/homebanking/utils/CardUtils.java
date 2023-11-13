package com.mindhub.homebanking.utils;

import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadLocalRandom;

public class CardUtils {



        @Autowired
        private static CardRepository cardRepository;


    public static int generateRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    public static String generateNumberCard() {
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



    public static String generateCvvCard() {
        StringBuilder cardNumber;

        cardNumber = new StringBuilder();
        for (byte i = 0; i <= 2; i++) {
            //push()
            cardNumber.append(generateRandomNumber(0, 9));
        }
        return cardNumber.toString();


}

}