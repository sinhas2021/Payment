package com.secretesc.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.secretesc.demo.entity.Account;
import com.secretesc.demo.model.PaymentRequest;
import com.secretesc.demo.model.PaymentResponse;
import com.secretesc.demo.service.IAccountService;
import com.secretesc.demo.service.ITransactionService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {

	@RegisterExtension
	  static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
	    .withConfiguration(GreenMailConfiguration.aConfig().withUser("username", "password"))
	    .withPerMethodLifecycle(false);
    
    @MockBean
    private IAccountService accService;
    
    @MockBean
    private ITransactionService transactionService;
    
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testAllAccounts()
      throws Exception {
    	// given
        Optional<Account> account1 = Optional.ofNullable(new Account());
        account1.get().setBalance(new BigDecimal(123.0));
        account1.get().setEmailaddress("EmailAddress1@testemail.com");
        account1.get().setName("Test Account1");
        account1.get().setAccountNumber(123456);

        Optional<Account> account2 = Optional.ofNullable(new Account());
        account2.get().setBalance(new BigDecimal(12334.0));
        account2.get().setEmailaddress("EmailAddress2@testemail.com");
        account2.get().setName("Test Account2");
        account2.get().setAccountNumber(4567);
        
    	PaymentRequest pr = new PaymentRequest();
    	pr.setFromAccount(account2.get().getAccountNumber());
    	pr.setToAccount(account1.get().getAccountNumber());
    	pr.setAmount(new BigDecimal(100));
    	pr.setRemarks("First transaction");
    	when(accService.findAccountById(account1.get().getAccountNumber())).thenReturn(account1);
    	when(accService.findAccountById(account2.get().getAccountNumber())).thenReturn(account2);
    	
    	when(accService.findEmailByAccountId(account1.get().getAccountNumber())).thenReturn(account1.get().getEmailaddress());
    	when(accService.findEmailByAccountId(account2.get().getAccountNumber())).thenReturn(account2.get().getEmailaddress());
    	
    	ResponseEntity<PaymentResponse> response = this.testRestTemplate.postForEntity("/api/payment", pr, PaymentResponse.class);
    	assertEquals(200, response.getStatusCodeValue());
    	assertNotNull(response.getBody().getTransactionRefNo());
    	assertTrue(response.getBody().getTransactionRefNo().startsWith("REF_"));
    	
    	MimeMessage receivedMessage = greenMail.getReceivedMessages()[0];
        assertEquals("Your transaction is successful, pls check your account balance", GreenMailUtil.getBody(receivedMessage));
        assertEquals(1, receivedMessage.getAllRecipients().length);
        assertEquals(account1.get().getEmailaddress(), receivedMessage.getAllRecipients()[0].toString());
    }
}