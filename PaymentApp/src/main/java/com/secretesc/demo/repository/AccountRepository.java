package com.secretesc.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.secretesc.demo.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
