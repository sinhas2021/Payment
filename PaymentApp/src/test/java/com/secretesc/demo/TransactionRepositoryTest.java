package com.secretesc.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specification;

import com.secretesc.demo.entity.Account;
import com.secretesc.demo.entity.Transaction;
import com.secretesc.demo.model.Status;
import com.secretesc.demo.repository.TransactionRepository;


@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionRepository transRepo;

    @Test
    public void whenFindById_thenReturnTransaction() {
        // given
        Account account1 = new Account();
        account1.setBalance(new BigDecimal(123.0));
        account1.setEmailaddress("EmailAddress1@testemail.com");
        account1.setName("Test Account1");
        entityManager.persist(account1);
        entityManager.flush();

        Account account2 = new Account();
        account2.setBalance(new BigDecimal(12334.0));
        account2.setEmailaddress("EmailAddress2@testemail.com");
        account2.setName("Test Account2");
        entityManager.persist(account2);
        entityManager.flush();
        
        Integer fromAccountNo = account1.getAccountNumber();
        Integer toAccountNo = account2.getAccountNumber();
        
        Transaction transaction = new Transaction();
		transaction.setAmount(new BigDecimal(123.0));
		transaction.setFromAccount(fromAccountNo);
		transaction.setRemarks("This is from unit testing");
		transaction.setStatus(Status.SUCCESS.name());
		transaction.setToAccount(toAccountNo);
		transaction.setTransTimestamp(LocalDateTime.now());
		
		entityManager.persist(transaction);
        entityManager.flush();
        
        // when
        List<Transaction> found = transRepo.findAll(createSearchByAccountSpec(fromAccountNo));
        
        // then
        assertThat(found.size()==1)
          .isEqualTo(found.get(0).getAmount().equals(new BigDecimal(123)));
    }
    
    private Specification<Transaction> createSearchByAccountSpec(int accountNumber) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.equal(root.get("fromAccount"), accountNumber),
				criteriaBuilder.equal(root.get("toAccount"), accountNumber));
	}
    

}