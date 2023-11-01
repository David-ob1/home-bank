package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.homebanking.models.TransactionType.*;

@SpringBootApplication
//
public class HomebankingApplication {
	//esto es el main donde se trabaja
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);

	}


	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository,CardRepository cardRepository ){
		return  args ->{
				Client client1 = new Client("Melba","Morel","melba@mindhub.com",passwordEncoder.encode("1234"));

			Client client2 = new Client("Nicolas","Gonzales","superchapz@saltitoslocos.com",passwordEncoder.encode("Ediciones1"));

			Client clientMain = new Client ("David", "Vazquez","david@mindbro.com",passwordEncoder.encode("david"));
			Client dolar = new Client("lar", "eldo","dolar5000@blue.com",passwordEncoder.encode("dolar"));


			clientRepository.save(client1);
			clientRepository.save(client2);

			clientRepository.save(clientMain);
			clientRepository.save(dolar);


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


			List<Integer> paymentMortgage = List.of(12,24,36,48,60);
			List<Integer> paymentPersonal = List.of(6,12,24);
			List<Integer> paymentAutomotive = List.of(6,12,24,36);

			Loan loanA = new Loan("Mortgage",500000, paymentMortgage);
			Loan loanB = new Loan("Personal",100000, paymentPersonal);
			Loan loanC = new Loan("Automotive",300000, paymentAutomotive);

			loanRepository.save(loanA);
			loanRepository.save(loanB);
			loanRepository.save(loanC);

			ClientLoan client1LoanA = new ClientLoan(400000, 60);
			ClientLoan client1LoanB = new ClientLoan(50000, 12);

			ClientLoan client2LoanA = new ClientLoan(100000,24);
			ClientLoan client2LoanB = new ClientLoan(200000, 36);

			client1.addClientLoan(client1LoanA);
			loanA.addClientLoan(client1LoanA);
			clientLoanRepository.save(client1LoanA);


			client1.addClientLoan(client1LoanB);
			loanC.addClientLoan(client1LoanB);
			clientLoanRepository.save(client1LoanB);

			client2.addClientLoan(client2LoanA);
			loanC.addClientLoan(client2LoanA);
			clientLoanRepository.save(client2LoanA);

			client2.addClientLoan(client2LoanB);
			loanB.addClientLoan(client2LoanB);
			clientLoanRepository.save(client2LoanB);

			Card client1CardDebit = new Card("MELBA MOREL",CardType.DEBIT, CardColor.GOLD,"2000 0000 0000 0001","007",LocalDate.now(),LocalDate.now().plusYears(5));
			Card client1CardCredit1 = new Card("MELBA MOREL",CardType.CREDIT, CardColor.TITANIUM,"3000 0000 0000 0001","014",LocalDate.now(),LocalDate.now().plusYears(5));

			client1.addCard(client1CardDebit);
			cardRepository.save(client1CardDebit);

			client1.addCard(client1CardCredit1);
			cardRepository.save(client1CardCredit1);


			Card client2CardCredit1 = new Card("NICOLAS GONZALES",CardType.DEBIT,CardColor.SILVER,"3000 0000 0000 0002","007",LocalDate.now(),LocalDate.now().plusYears(5));
			client2.addCard(client2CardCredit1);
			cardRepository.save(client2CardCredit1);


		};



	}

}
