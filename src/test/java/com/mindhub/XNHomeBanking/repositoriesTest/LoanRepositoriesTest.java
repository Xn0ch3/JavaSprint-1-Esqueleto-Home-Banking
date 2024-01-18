package com.mindhub.XNHomeBanking.repositoriesTest;

import com.mindhub.XNHomeBanking.models.Loan;
import com.mindhub.XNHomeBanking.repositories.LoanRepositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoanRepositoriesTest {



    @Autowired
     private LoanRepositories loanRepositories;



    @Test
    public void existLoans(){

        List<Loan> loans = loanRepositories.findAll();

        assertThat(loans,is(not(empty())));

    }



    @Test

    public void existPersonalLoan(){

        List<Loan> loans = loanRepositories.findAll();

        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));

    }



}
