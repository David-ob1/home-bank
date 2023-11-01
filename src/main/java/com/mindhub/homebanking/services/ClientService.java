package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dto.ClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ClientService {

    public List<ClientDTO> getAllClients();

    public ClientDTO getClient(@PathVariable Long id);

    public ResponseEntity<Object> register(
            @RequestParam String name, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password);


    public ClientDTO getAll(Authentication authentication);
}
