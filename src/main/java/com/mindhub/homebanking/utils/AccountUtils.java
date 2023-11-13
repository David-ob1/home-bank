package com.mindhub.homebanking.utils;

import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadLocalRandom;

public class AccountUtils {

    @Autowired
    private static AccountService accountService;


    public static String generateNumberA(long min, long max) {

        String aux = "VIN-";
        int numberOfDigits = 8;
        String formatString = "%0" + numberOfDigits + "d";

        //expreso el numero en el formato deseado  //numeros como maximo 8 d
        long number;

        String numberCompleted;
        do{
            number = ThreadLocalRandom.current().nextLong(min, max);
            numberCompleted = aux +String.format(formatString,number);
        }
        while(accountService.existsAccountByNumber(numberCompleted));
        return numberCompleted;
    }


}


//    @Autowired
//    private AccountService accountService;
//
//    public String generateNumberA(long min, long max) {
//        String aux = "VIN-";
//        long number;
//        int numberOfDigits = 8;
//        //expreso el numero en el formato deseado  //numeros como maximo 8 d
//        String formatString = "%0" + numberOfDigits + "d";
//        String numberCompleted;
//        do{
//            number = ThreadLocalRandom.current().nextLong(min, max);
//            numberCompleted = aux +String.format(formatString,number);
//        }
//        while(accountService.existsAccountByNumber(numberCompleted));
//        return numberCompleted;
//    }


