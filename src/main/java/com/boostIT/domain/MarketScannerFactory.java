package com.boostIT.domain;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.stereotype.Component;
@Component
public class MarketScannerFactory {

	private volatile boolean stp;
	private ComparisonThread st;

	private final String prf = "org.knowm.xchange.";
	private final String suf = "Exchange";
	String exchangesList[] ;
	Offer [] offer;	
	private SingleMarketScanner[] mScanners;

	public MarketScannerFactory(String [] exchangesList, Offer[] offer, SingleMarketScanner[] mScanners) {
		this.exchangesList = exchangesList;
		this.offer= offer;
		this.mScanners= mScanners;
	}

	public String[] getMarketNames() {
		return exchangesList;
	}
	public void start() {
		st = new ComparisonThread();
		st.start();
	}

	private class ComparisonThread extends Thread {
		public void run() {
			boolean exit = false;
			int num = 1;
			// BigDecimal min = new BigDecimal(Long.MAX_VALUE);
			// BigDecimal max = new BigDecimal(0);
			// int max_i = 0, min_i = 0;

			while (!exit) {

				// min = new BigDecimal(Long.MAX_VALUE);
				// max = new BigDecimal(0);

				if (stp) {
					_sleep();
					continue;
				}

				System.out.println("Starting scan");


				for (int i = 0; i < offer.length; i++) {
					for (int j = i + 1; j < offer.length; j++) {

						if (offer[i] != null && offer[j] != null && offer[i].bid.compareTo(offer[j].ask) > 0) {

							BigDecimal b = offer[i].bid;
							BigDecimal a = offer[i].ask;

							BigDecimal dif = a.subtract(b);
							BigDecimal res = dif.divide(a, 2, BigDecimal.ROUND_HALF_UP);
							res = res.multiply(new BigDecimal(100));
							String d = dif + "  (" + res + "%) ";
						}
					}

				}

				System.out.println("End of scan scan");
				_sleep();

			}
		}
	}

	public void setStop(boolean b) {
		System.out.println("Set stop");
		stp = b;
	}

	public boolean getStop() {
		return stp;
	}

	private void _sleep() {
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
