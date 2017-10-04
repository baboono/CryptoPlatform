package com.boostIT.configuration;

import java.io.IOException;

import org.jfree.data.category.DefaultCategoryDataset;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitstamp.Bitstamp;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.poloniex.Poloniex;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.boostIT.domain.MarketScannerFactory;
import com.boostIT.domain.Offer;
import com.boostIT.domain.SingleMarketScanner;


@WebAppConfiguration
@Configuration
@ComponentScan
public class GeneralBeanFactory {
	private Exchange userExchangeForMarketData;
	private final String prf = "org.knowm.xchange.";
	private final String suf = "Exchange";
	private int i = 0;
	private String stringOfPickedExchanges[] = { "bitstamp", "bitbay", "poloniex", "kraken", "hitbtc", "coinfloor",
			"empoex", "cryptonit", "cryptopia", "coinmate", "chbtc", "cexio", "bter", "campbx", "bitfinex", "bitkonan",
			"coinsetter", "cointrader", "coinbaseex", "clevercoin", "cavirtex", "btccentral", "mexbt", "taurus", "jubi",
			"okcoin", "huobi", "gemini", "luno", "vircurex", "empoex", "ccex" };
	private Offer[] ofr = new Offer[stringOfPickedExchanges.length];
	public CurrencyPair currentcyPair() {
		return CurrencyPair.BTC_USD;
	}
	
	@Bean
	public MarketScannerFactory marketScannerFactory() {	
		SingleMarketScanner[] mScanners = new SingleMarketScanner[stringOfPickedExchanges.length];
		for (String s : stringOfPickedExchanges) {
			String sl = prf + s + "." + (s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase()) + suf;
			mScanners[i++] = new SingleMarketScanner(i, currentcyPair(), sl, ofr);
		}
		return new MarketScannerFactory(stringOfPickedExchanges,ofr,mScanners);
	}
	@Bean
	public MarketDataService marketingDataService() {	
		Exchange bitstamp = null;
		try {
			bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());
		}
		catch ( Exception e) {
			System.err.println("No market: " + "poloniex");
		}
		
		return bitstamp.getMarketDataService();

	}
	@Bean
	public DefaultCategoryDataset dcd(){
		return new DefaultCategoryDataset();
	}

}