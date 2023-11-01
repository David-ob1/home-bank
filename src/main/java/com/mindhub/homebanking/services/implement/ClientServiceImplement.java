package com.mindhub.homebanking.services.implement;

import com.mindhub.homebanking.controllers.AccountController;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountController accountController;

    @Override
    public List<ClientDTO> getAllClients(){
        List<ClientDTO> clients = clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
        return clients;
    }

    @Override
    public ClientDTO getClient(@PathVariable Long id){
//no se rompe la aplicacion si no se cumple
        ClientDTO foundClient = clientRepository.findById(id).map(client -> new ClientDTO(client)).orElse(null);
        return foundClient;
    }

    @Override
    public ResponseEntity<Object> register(
            @RequestParam String name, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {


        if(name.isEmpty() || name.isBlank()){
            return new ResponseEntity<>("the name is not valid,complete it please.", HttpStatus.FORBIDDEN);
        }

        if(lastName.isEmpty() || lastName.isBlank()){
            return new ResponseEntity<>("the last name is not valid,complete it please.", HttpStatus.FORBIDDEN);
        }

        if(email.isEmpty() || email.isBlank()){
            return new ResponseEntity<>("the email is not valid,complete it please.", HttpStatus.FORBIDDEN);
        }

        if(password.isEmpty() || password.isBlank()){
            return new ResponseEntity<>("the name is not valid,complete it please.", HttpStatus.FORBIDDEN);
        }


        if (clientRepository.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }

        Client client = new Client(name, lastName, email, passwordEncoder.encode(password));
        clientRepository.save(client);
        Account account = new Account(accountController.generateNumberA(1l,100000000l), LocalDate.now(),0);
        client.addAccount(account);
        accountRepository.save(account);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ClientDTO getAll(Authentication authentication){
        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
    }





}
