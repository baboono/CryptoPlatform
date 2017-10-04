package com.boostIT.service;



import java.util.List;

import com.boostIT.entities.ExchangeTransaction;



public interface StoreManagementService {
	
	public ExchangeTransaction addStore(ExchangeTransaction store);
	public List<ExchangeTransaction> getAllStores();
	public ExchangeTransaction getStore(Long id);
	public ExchangeTransaction updateStore(ExchangeTransaction store);
	public void deleteStore(Long id);
}
