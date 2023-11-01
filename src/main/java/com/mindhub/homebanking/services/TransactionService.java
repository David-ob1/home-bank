package com.mindhub.homebanking.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;

public interface TransactionService {

    public ResponseEntity<Object> transfer(
            Authentication authentication,
             double amount,  String description,
             String accountOrigin, String accountDestiny);
}
