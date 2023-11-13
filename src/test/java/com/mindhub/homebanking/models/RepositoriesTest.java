package com.mindhub.homebanking.models;

import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.LoanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

    public class RepositoriesTest {

        @Autowired
       private LoanRepository loanRepository;

        @Autowired
        private ClientRepository clientRepository;





        @Test
        public void existLoans(){
            List<Loan> loans = loanRepository.findAll();
            assertThat(loans,is(not(empty())));

        }

        @Test
        public void existPersonalLoan(){
            List<Loan> loans = loanRepository.findAll();
            assertThat(loans, hasItem(hasProperty("name", is("Personal"))));

        }



        @Test
        public void existClient(){
            List<Client> clients = clientRepository.findAll();
            assertThat(clients,is(not(empty())));
        }

//        @Test
//        public void existCLientByEmail(){
//            Client client = clientRepository.existsByEmail("melba@mindhub.com");
//            assertThat();
//        }

        @Test
        public void findClient(){
          List <Client> clients = clientRepository.findAll();
            assertThat(clients, hasItem(hasProperty("name", is("lar"))));

        }







        }




