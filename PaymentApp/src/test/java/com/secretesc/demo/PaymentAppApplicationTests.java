package com.secretesc.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.secretesc.demo.controller.AccountController;

@SpringBootTest
class PaymentAppApplicationTests {

	@Autowired
	private AccountController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
