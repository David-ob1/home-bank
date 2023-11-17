package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;

import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.mindhub.homebanking.utils.AccountUtils.generateNumberA;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountController accountController;

@GetMapping("/clients")
    public List<ClientDTO> getAllClients(){
    List<ClientDTO> clients = clientService.findAllClients().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    return clients;
}


    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
//no se rompe la aplicacion si no se cumple
        ClientDTO foundClient = new ClientDTO(clientService.findClientById(id));
        return foundClient;
    }


    @PostMapping("/clients")
    public ResponseEntity<Object> register(
            @RequestParam String name, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {


        boolean nameEmpty = name.isBlank();
        boolean lastNameEmpty = lastName.isBlank();
        boolean emailEmpty = email.isBlank();
        boolean passwordEmpty = password.isBlank();


            if(name.isBlank()||lastName.isBlank()||
                email.isBlank()||password.isBlank()){

                StringBuilder response = new StringBuilder();

                response.append(nameEmpty ? "the name is not valid,complete it please." : "");
                response.append(lastNameEmpty ? "the last name is not valid,complete it please." : "");
                response.append(emailEmpty ? "the email is not valid,complete it please." : "");
                response.append(passwordEmpty ? "the password is not valid,complete it please." : "");

                return new ResponseEntity<>(response.toString(), HttpStatus.FORBIDDEN);
            }



        if (clientService.existsClientByEmail(email)) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);

        }


        Client client = new Client(name, lastName, email, passwordEncoder.encode(password));
        clientService.saveClient(client);
        Account account = new Account(generateNumberA(1l,100000000l), LocalDate.now(),0);
        client.addAccount(account);
        accountService.saveAccount(account);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @GetMapping("/clients/current")
    public ClientDTO getAll(Authentication authentication){
        return new ClientDTO(clientService.findClientByEmail(authentication.getName()));
    }







//        if(name.isEmpty() || name.isBlank()){
//            return new ResponseEntity<>("the name is not valid,complete it please.", HttpStatus.FORBIDDEN);
//        }
//
//        if(lastName.isEmpty() || lastName.isBlank()){
//            return new ResponseEntity<>("the last name is not valid,complete it please.", HttpStatus.FORBIDDEN);
//        }
//
//        if(email.isEmpty() || email.isBlank()){
//            return new ResponseEntity<>("the email is not valid,complete it please.", HttpStatus.FORBIDDEN);
//        }
//
//        if(password.isEmpty() || password.isBlank()){
//            return new ResponseEntity<>("the password is not valid,complete it please.", HttpStatus.FORBIDDEN);
//        }



}
