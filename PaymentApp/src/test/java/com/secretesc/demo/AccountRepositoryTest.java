package com.secretesc.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.secretesc.demo.entity.Account;
import com.secretesc.demo.repository.AccountRepository;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepo;

    @Test
    public void whenFindById_thenReturnAccount() {
        // given
        Account account1 = new Account();
        //account1.setAccountNumber(1234);
        account1.setBalance(new BigDecimal(123.0));
        account1.setEmailaddress("EmailAddress@testemail.com");
        account1.setName("Test Account1");
        entityManager.persist(account1);
        entityManager.flush();

        // when
        Account found = accountRepo.findById(account1.getAccountNumber()).get();

        // then
        assertThat(found.getName())
          .isEqualTo(account1.getName());
    }

}