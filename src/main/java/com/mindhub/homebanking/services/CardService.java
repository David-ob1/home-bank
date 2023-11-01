package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;

public interface CardService  {

    public ResponseEntity<Object> createCard(@RequestParam CardColor cardColor,
                                            @RequestParam CardType cardType,
                                            Authentication authentication);


    public int generateRandomNumber(int min, int max);

    public String generateNumberCard();

    public String generateCvvCard();

}
