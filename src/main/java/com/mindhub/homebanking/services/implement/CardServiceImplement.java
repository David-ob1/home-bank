package com.mindhub.homebanking.services.implement;

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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Service
public class CardServiceImplement implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ResponseEntity<Object> createCard                //si no tengo un CardType responde con 400
            (@RequestParam CardColor cardColor, @RequestParam CardType cardType, Authentication authentication) {
        //busco al cliente x mail
        Client client = clientRepository.findByEmail(authentication.getName());

//        int numberOfCardType =  // card.getType() == cardType
//                (int) client.getCards().stream().filter(card -> card.getType().equals(cardType)).count();
//
//        if (numberOfCardType == 3) {
//            return new ResponseEntity<>("You cannot have more than three cards of the same type.", HttpStatus.FORBIDDEN);
//        }


                if(cardRepository.existsByColorAndTypeAndClientAndActive(cardColor,cardType,client,true)){
                    return  new ResponseEntity<>("you already have that card", HttpStatus.FORBIDDEN);
                }




        Card card = new Card(client.getName() + " " + client.getLastName(), cardType, cardColor, generateNumberCard(), generateCvvCard(), LocalDate.now().plusYears(5), LocalDate.now(),true);

        client.addCard(card);
        cardRepository.save(card);
        clientRepository.save(client);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//@Override
    public int generateRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

@Override
    public  String generateNumberCard() {
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

@Override
    public String generateCvvCard() {

        StringBuilder cardNumber;

        cardNumber = new StringBuilder();
        for (byte i = 0; i <= 2; i++) {
            //push()
            cardNumber.append(generateRandomNumber(0, 9));
        }

        return cardNumber.toString();

    }

    @Override
    public Card findCardByNumber(String cardNumber){
       Card cardFind =  cardRepository.findCardByNumber(cardNumber);
        return cardFind;
    }

    @Override
    public Boolean existByNumber(String cardNumber) {
        return  cardRepository.existsByNumber(cardNumber);
    }

    @Override
    public void saveCard(Card card){
        cardRepository.save(card);
    };

}