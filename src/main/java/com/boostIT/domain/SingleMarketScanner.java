package com.boostIT.domain;



import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.marketdata.MarketDataService;


//Needs spring factory
public class SingleMarketScanner {

	private BigDecimal lastPrize;
	private CurrencyPair currencyPair;
	private MarketDataService marketDataService;
	private String name;
	private int pos;
	private Offer[] ofr;
	private ScanningThread st;

	public SingleMarketScanner(int pos, CurrencyPair currencyPair, String name, Offer[] ofr) {
		
		Exchange exchgn;
		
		this.pos = pos;
		this.currencyPair = currencyPair;
		this.name = name;
		this.ofr = ofr;
		
		try {
			exchgn = ExchangeFactory.INSTANCE.createExchange(name);
		}
		catch ( Exception e) {
			System.out.println("No market: " + name);
			return;
		}
		
		this.marketDataService = exchgn.getMarketDataService();
		st = new ScanningThread();	
		st.start();
	}
	
	private class ScanningThread extends Thread {

		private DecimalFormat df 		= new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		private BigDecimal compareValue = new BigDecimal("0.00000072");
		private Ticker ticker 			= null;
		private OrderBook ordBook		= null;

		public void run() {
			boolean exit = false;
			int i = 1;

			df.setMaximumFractionDigits(340);

			while (!exit) {



				try {
					ordBook	= marketDataService.getOrderBook(currencyPair);
					ticker 	= marketDataService.getTicker(currencyPair);
				} catch (NotAvailableFromExchangeException e) {
					//win.setTextForPane("\nError :" + name + " does not support: " + currencyPair + "\n");
					return;
				} catch (NotYetImplementedForExchangeException e) {
					//win.setTextForPane(name + "...not yet implemented for exchange Exception\n");
					return;
				} catch (ExchangeException e) {
					//win.setTextForPane("\nError :" + name + " probably does not support: " + currencyPair + "\n");
					return;					
				} catch (IOException e) {
					//win.setTextForPane("Error 4\n");					
				} catch (Exception e) {

					return;
				}
				
				if (ticker == null)
					continue;
						
				lastPrize = ticker.getLast();
				
				List<LimitOrder> asks = ordBook.getAsks();
				List<LimitOrder> bids = ordBook.getBids();				
				for (LimitOrder ask : asks) {
					
				}
				

				if (compareValue.compareTo(lastPrize) != 0) {
					//win.setTextForPane(i + ") " + name + " " + currencyPair.toString() + ".\n");
					BigDecimal bd = ticker.getLast();

					//win.setTextForPane("LAST....: " + bd.toPlainString() + "\n");
					//win.setTextForPane("ASK.....: " + ticker.getAsk().toPlainString() + "\n");
					//win.setTextForPane("BID.....: " + ticker.getBid().toPlainString() + "\n");
					ofr[pos] = new Offer(bd, ticker.getAsk(), ticker.getBid());

					compareValue = lastPrize;
				} 

				i++;
				_sleep(500);

			}

		}

	
		private void _sleep(int ms) {
			try {
				TimeUnit.MILLISECONDS.sleep(ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
