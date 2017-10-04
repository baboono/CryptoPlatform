package com.boostIT.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boostIT.entities.ExchangeTransaction;

@Repository
public interface StoreRepository extends CrudRepository<ExchangeTransaction, Long> {

}
