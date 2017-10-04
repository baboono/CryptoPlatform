package com.boostIT.domain;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.jfree.data.category.DefaultCategoryDataset;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.springframework.stereotype.Component;

@Component
public class BitstampTickerDemoImpl implements BitstampTickerDemo {

	// static PoloniexExchange exchgn = (PoloniexExchange)
	// ExchangeFactory.INSTANCE.createExchange(PoloniexExchange.class.getName());
	// static RippleExchange exchgn = (RippleExchange)
	// ExchangeFactory.INSTANCE.createExchange(RippleExchange.class.getName());
	
	//String s  = "org.knowm.xchange.bitstamp.BitstampExchange";
	String s  = "org.knowm.xchange.bitbay.BitbayExchange";
	
	Exchange exchgn = ExchangeFactory.INSTANCE.createExchange(s);
	
	
	private BigDecimal lastPrize;
	MarketDataService marketDataService = exchgn.getMarketDataService();
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	public void runDemo() throws IOException, InterruptedException {
		MarketScan ms = new MarketScan();
		
		System.out.println("-----------------------------" + BitstampExchange.class.getName());
				
		ms.start();

		//LineChart mc = new LineChart("Test", "chart");

		// Use the factory to get Bitstamp exchange API using default settings
		// Exchange bitstamp =
		// ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());
		// loginPoloniex();
		// makeBidOnPoloniex();

		// raw((BitstampMarketDataServiceRaw) marketDataService);
	}

	public DefaultCategoryDataset createDataset() {
		dataset.addValue(0, "Value", "time");
		return dataset;
	}

	// static void loginPoloniex() throws NotAvailableFromExchangeException,
	// NotYetImplementedForExchangeException, ExchangeException, IOException {
	// //
	// poloniex.getExchangeSpecification().setUserName("baltrukiewicz@gmail.com");
	// //
	// poloniex.getExchangeSpecification().setApiKey("W6IUFE7G-TLUMWOWF-0OB3JH89-OX86VVCI");
	// // poloniex.getExchangeSpecification().setPassword("Sabinka123!@#");
	//
	// ExchangeSpecification exSpec = new
	// PoloniexExchange().getDefaultExchangeSpecification();
	// exSpec.setApiKey("W6IUFE7G-TLUMWOWF-0OB3JH89-OX86VVCI");
	// exSpec.setUserName("baltrukiewicz@gmail.com");
	// exSpec.setPassword("Sabinka123!@#");
	// exSpec.setSecretKey("900ab9bcb584e379f155cab346016a38adce9e057bb35f73b0827a1563801fa47c20c0cb4652c2c685c7c36cfa2e140c99b8eb210279e75e226c335c9851e5be");
	// Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(exSpec);
	//
	// // Get the account information
	// AccountService accountService = bitstamp.getAccountService();
	// AccountInfo accountInfo = accountService.getAccountInfo();
	//
	// System.out.println("User Name:" + accountInfo.getUsername());
	//
	// Wallet da = accountInfo.getWallet();
	// BigDecimal dza = accountInfo.getTradingFee();
	//
	// System.out.println("User Name:" + accountInfo.getUsername());
	//
	// //
	// //
	// // ExchangeSpecification es = es =
	// // poloniex.getDefaultExchangeSpecification();
	// //
	// //
	// // String as = poloniex.getExchangeSpecification().getUserName();
	// // String as2 = poloniex.getExchangeSpecification().getPassword();
	// // String as3 = poloniex.getExchangeSpecification().getApiKey();
	// // String as4 = poloniex.getExchangeSpecification().getSecretKey();
	// //
	// //
	// poloniex.getExchangeSpecification().setSecretKey("900ab9bcb584e379f155cab346016a38adce9e057bb35f73b0827a1563801fa47c20c0cb4652c2c685c7c36cfa2e140c99b8eb210279e75e226c335c9851e5be");
	// // BigDecimal dsa =
	// // poloniex.getAccountService().getAccountInfo().getTradingFee();
	// // System.err.println(dsa);
	//
	// }

	// static void makeBidOnPoloniex() throws NotAvailableFromExchangeException,
	// NotYetImplementedForExchangeException, ExchangeException, IOException {
	// exchgn.getDefaultExchangeSpecification().setUserName("baltrukiewicz@gmail.com");
	// exchgn.getDefaultExchangeSpecification().setApiKey("W6IUFE7G-TLUMWOWF-0OB3JH89-OX86VVCI");
	// exchgn.getExchangeSpecification().setSecretKey("900ab9bcb584e379f155cab346016a38adce9e057bb35f73b0827a1563801fa47c20c0cb4652c2c685c7c36cfa2e140c99b8eb210279e75e226c335c9851e5be");
	// BigDecimal dsa =
	// exchgn.getAccountService().getAccountInfo().getTradingFee();
	//
	// System.err.println(dsa);
	// }

	// private static void raw(BitstampMarketDataServiceRaw marketDataService)
	// throws IOException {
	// BitstampTicker bitstampTicker =
	// marketDataService.getBitstampTicker(CurrencyPair.BTC_USD);
	// System.out.println(bitstampTicker.toString());
	// }
	//
	// public void balanceRejectTest() throws Exception {
	// InvocationResult invocationResult = new
	// InvocationResult("{\"error\":\"Invalid API key\\/secret pair.\"}", 200);
	// Method apiMethod =
	// PoloniexAuthenticated.class.getDeclaredMethod("returnCompleteBalances",
	// String.class, ParamsDigest.class, SynchronizedValueFactory.class,
	// String.class);
	// RestMethodMetadata balances = RestMethodMetadata.create(apiMethod, "",
	// "");
	//
	// try {
	// new JacksonResponseReader(new
	// DefaultJacksonObjectMapperFactory().createObjectMapper(),
	// false).read(invocationResult, balances);
	// } catch (PoloniexException e) {
	// System.out.println(e.getMessage().equals(("Invalid API key/secret
	// pair.")));
	// throw e;
	// }
	// }

	private class MarketScan extends Thread {

		public void run() {
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
					ticker = marketDataService.getTicker(CurrencyPair.BTC_USD/* DOGE_BTC */);
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

				lastPrize = ticker.getLast();

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

	

}
