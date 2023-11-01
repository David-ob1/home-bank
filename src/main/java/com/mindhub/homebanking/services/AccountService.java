package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dto.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountService {

    public List<AccountDTO> getAllAccounts();

    public List<AccountDTO> getAll(Authentication authentication);

    public AccountDTO getAccount(@PathVariable Long id);

    public ResponseEntity<Object> createAccount( Authentication authentication);

    public String generateNumberA(long min, long max);



}
