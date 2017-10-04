package com.boostIT.domain;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.jfree.data.category.DefaultCategoryDataset;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.boostIT.configuration.GeneralBeanFactory;

@Component
@ComponentScan
public class MultiThreadMarketComparator extends Thread {

@Autowired
DefaultCategoryDataset dataset;
	

	
	
	public void run() {
		ApplicationContext context = new AnnotationConfigApplicationContext(GeneralBeanFactory.class);
		MarketDataService marketingDataService = context.getBean(MarketDataService.class);
		boolean someVar=false;
		boolean exit = false;
		int i = 1;
		BigDecimal compareValue = new BigDecimal("0.00000072");
		DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		df.setMaximumFractionDigits(340); // 340 =
											// DecimalFormat.DOUBLE_FRACTION_DIGITS
		Ticker ticker = null;

		while (!exit) {
			
			
			if (false) {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}

			try {
				ticker = marketingDataService.getTicker(CurrencyPair.BTC_USD/* DOGE_BTC */);
				// Ticker ticker =
				// marketDataService.getTicker(CurrencyPair.);

			} catch (NotAvailableFromExchangeException e1) {
				e1.printStackTrace();
			} catch (NotYetImplementedForExchangeException e1) {
				e1.printStackTrace();
			} catch (ExchangeException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			BigDecimal lastPrize = ticker.getLast();

			if (compareValue.compareTo(lastPrize) != 0) {
				
				
				BigDecimal bd = ticker.getLast();
				
				dataset.addValue(bd.intValue(), "Value", "time");
				
				compareValue = lastPrize;
			} else
				System.out.print(".");

			i++;

			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}	


