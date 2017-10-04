package com.boostIT.service;



import java.util.ArrayList;
import java.util.List;

import com.boostIT.entities.ExchangeTransaction;
import com.boostIT.repo.StoreRepository;
import com.boostIT.service.StoreManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreManagementServiceImpl implements StoreManagementService {

	@Autowired(required=false)
	StoreRepository storeRepository;

	@Override
	public ExchangeTransaction addStore(ExchangeTransaction store) {
		if (store == null)
			throw new IllegalArgumentException("Store is null");

		return storeRepository.save(store);
	}

	@Override
	public ExchangeTransaction updateStore(ExchangeTransaction store) {
		if (store == null)
			throw new IllegalArgumentException("Store is null");

		ExchangeTransaction currentStore = getStore(store.getStoreId());

		if (currentStore == null)
			throw new IllegalArgumentException(
					"Store doesnot exist with given store id");

		BeanUtils.copyProperties(store, currentStore);

		return storeRepository.save(currentStore);
	}

	@Override
	public ExchangeTransaction getStore(Long id)
	{
		if (id == null) 
			throw new IllegalArgumentException("Store Id is null");
		
		ExchangeTransaction st = storeRepository.findOne(id);
		
		if (st == null) throw new IllegalArgumentException("Store with given store id does not exist");
		
		return st;
	}

	@Override
	public List<ExchangeTransaction> getAllStores() {
		
		List<ExchangeTransaction> list = new ArrayList<>();
		
		storeRepository.findAll().forEach(list::add);
		
		return list;
	}

	@Override
	public void deleteStore(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Store Id is null");

		if (getStore(id) != null)
			storeRepository.delete(id);
	}

}
