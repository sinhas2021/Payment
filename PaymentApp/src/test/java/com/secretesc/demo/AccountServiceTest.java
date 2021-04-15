package com.secretesc.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.secretesc.demo.entity.Account;
import com.secretesc.demo.repository.AccountRepository;
import com.secretesc.demo.service.IAccountService;

@SpringBootTest
public class AccountServiceTest {
	@Autowired
	private IAccountService accountService;
	
	@MockBean
	private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        Optional<Account> account1 = Optional.ofNullable(new Account());
        account1.get().setAccountNumber(1234);
        account1.get().setEmailaddress("testacco@testemail.com");
        account1.get().setBalance(new BigDecimal(123456));
        account1.get().setName("Test account name");
        Mockito.when(accountRepository.findById(account1.get().getAccountNumber()))
          .thenReturn(account1);
    }
    
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        Optional<Account> found = accountService.findAccountById(1234);
     
         assertThat(found.get()!=null)
          .isEqualTo(found.get().getAccountNumber() == 1234)
          .isEqualTo(found.get().getBalance().equals(new BigDecimal(123456)));
     }

}