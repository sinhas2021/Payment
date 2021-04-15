package com.secretesc.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.secretesc.demo.controller.AccountController;
import com.secretesc.demo.model.AccountDTO;
import com.secretesc.demo.service.IAccountService;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IAccountService accService;

    @Test
    public void testAllAccounts()
      throws Exception {
        
        AccountDTO demoAccount = new AccountDTO();
        demoAccount.setName("Test Account Name1allEmployees");
        List<AccountDTO> allEmployees = Arrays.asList(demoAccount);

        when(accService.findAllAccounts()).thenReturn(allEmployees);

        mvc.perform(get("/api/accounts")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].name", is(demoAccount.getName())));
    }
}