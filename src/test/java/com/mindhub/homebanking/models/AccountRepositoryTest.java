package com.mindhub.homebanking.models;

import com.mindhub.homebanking.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {


    @Autowired
    private AccountRepository accountRepository;

    List<Account> accounts;
    @BeforeEach
    public void setUp(){
    accounts = accountRepository.findAll();
    }


    @Test
    public void findAccount(){
        List<Account> account = accountRepository.findAll();
        assertThat(account, hasItem(hasProperty("number", is("VIN001"))));

    }


    @Test
    public void accountsWithMoreThanOneTransaction() {
        // Primero, filtro las cuentas para obtener aquellas que tienen más de una transacción.
        // Uso un stream para procesar la lista de cuentas.
        List<Account> accountsWithMoreThanOneTransaction = accounts.stream()
                .filter(account -> account.getTransaction() != null && account.getTransaction().size() > 1)
                .collect(Collectors.toList());

        // Ahora, verifico si la lista resultante está vacía.
        // Si está vacía, significa que no hay cuentas con más de una transacción, y la prueba fallará.
        assertFalse(accountsWithMoreThanOneTransaction.isEmpty(), "There are no accounts with more than one transaction");

        // Luego, para cada cuenta en la lista filtrada, me aseguro de que realmente tenga más de una transacción.
        // Esto es una doble comprobación para garantizar la exactitud.
        for (Account account : accountsWithMoreThanOneTransaction) {
            assertTrue(account.getTransaction().size() > 1, "An account with less than two transactions was found");
        }

        // Si todas las verificaciones son correctas, imprimo un mensaje indicando el éxito de la prueba.
        System.out.println("There are accounts with more than one transaction");
    }



}
