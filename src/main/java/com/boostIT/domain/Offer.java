package com.boostIT.domain;

import java.math.BigDecimal;

public class Offer {
	
	public BigDecimal last;
	public BigDecimal ask;
	public BigDecimal bid;
	
	public Offer(BigDecimal last, BigDecimal ask, BigDecimal bid) {
		this.last	= last;
		this.ask 	= ask;
		this.bid 	= bid;
	}

}
