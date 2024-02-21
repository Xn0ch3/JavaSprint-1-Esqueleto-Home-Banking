//package com.mindhub.XNHomeBanking.repositoriesTest;
//
//import com.mindhub.XNHomeBanking.models.Account;
//import com.mindhub.XNHomeBanking.repositories.AccountRepositories;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class AccountRepositoriesTest {
//
//
//    @Autowired
//    private AccountRepositories accountRepositories;
//
//    @Test
//    public void testFindByNumber() {
//        // Crear datos de prueba
//        Account account = new Account("existing_account_number", /* Otros campos */);
//        accountRepositories.save(account);
//
//        // Ejecutar la prueba
//        Account foundAccount = accountRepositories.findByNumber("existing_account_number");
//
//        // Verificar el resultado usando Hamcrest
//        assertThat(foundAccount.getNumber(), is(equalTo("existing_account_number")));
//
//        // Ejecutar la prueba con un número de cuenta que no existe
//        foundAccount = accountRepositories.findByNumber("nonexistent_account_number");
//
//        // Verificar que devuelve null cuando la cuenta no existe
//        assertThat(foundAccount, is(nullValue()));
//    }
//}
//
