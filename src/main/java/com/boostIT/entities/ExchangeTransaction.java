package com.boostIT.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class ExchangeTransaction {

	@Id
	@GeneratedValue(generator="STORE_SEQ")
	@SequenceGenerator(name="STORE_SEQ",sequenceName="STORE_SEQ", allocationSize=1)
	Long storeId;
	

	String valuePair;
	
	String date;
	
	String exchange;

	String someOtherData;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	
	public String getStoreName() {
		return valuePair;
	}

	public void setStoreName(String storeName) {
		this.valuePair = storeName;
	}

	public String getStoreStreetAddress() {
		return date;
	}

	public void setStoreStreetAddress(String storeStreetAddress) {
		this.date = storeStreetAddress;
	}

	
	public String getStoreSuburb() {
		return exchange;
	}

	public void setStoreSuburb(String storeSuburb) {
		this.exchange = storeSuburb;
	}

	public String getStorePostcode() {
		return someOtherData;
	}

	public void setStorePostcode(String storePostcode) {
		this.someOtherData = storePostcode;
	}

}
