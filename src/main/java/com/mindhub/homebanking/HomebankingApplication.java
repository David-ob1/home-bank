package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
//
public class HomebankingApplication {
	//esto es el main donde se trabaja
	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository){
		return  args ->{
			Client client1 = new Client("Melba","Morel","melba@mindhub.com");

			Client client2 = new Client("Nicolas","Gonzales","superchapz@saltitoslocos.com");

			clientRepository.save(client1);
			clientRepository.save(client2);


			Account account1 = new Account("VIN001", LocalDate.now(),5000);
			Account account2 = new Account("VIN002", LocalDate.now().plusDays(1),7500);
			client1.addAccount(account1);
			client1.addAccount(account2);
			accountRepository.save(account1);
			accountRepository.save(account2);

			Account account3 = new Account("VIN003", LocalDate.now(),200);
			Account account4 = new Account("VIN004", LocalDate.now().plusDays(1),50);
			client2.addAccount(account3);
			client2.addAccount(account4);
			accountRepository.save(account3);
			accountRepository.save(account4);

		};
	}

}
