package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.mindhub.homebanking.models.TransactionType.*;

@SpringBootApplication
//
public class HomebankingApplication {
	//esto es el main donde se trabaja
	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository){
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

			//transaccion cliente 1, cuenta 1
			Transaction transactionC1A = new Transaction(DEBIT, -1500,"shop",LocalDateTime.now());
			account1.addTransaction(transactionC1A);
			transactionRepository.save(transactionC1A);

			// transaccion cliente 1, cuenta 1
			Transaction transactionC1B = new Transaction(CREDIT, 900,"salary",LocalDateTime.now());
			account1.addTransaction(transactionC1B);
			transactionRepository.save(transactionC1B);

			//transaccion cliente 1,cuenta 1
			Transaction transactionC1C = new Transaction(DEBIT, -350,"AMAZON, phone",LocalDateTime.now());
			account1.addTransaction(transactionC1C);
			transactionRepository.save(transactionC1C);


			//Transaction transactionC2A = new Transaction(DEBIT, -100,"bed purchase",LocalDateTime.now());


			//			Transaction transactionC2A = new Transaction(DEBIT, -100,"bed purchase")



		};



	}

}
