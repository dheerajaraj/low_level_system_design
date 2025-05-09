package org.wallet.util;

import org.wallet.biz.entities.Currency;

public class CurrencyConvertor {
    public static float convert(Currency defaultCurrency, Currency toCurrency, float amount) {
        if (Float.compare(amount, 0.0f) < 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if(defaultCurrency == toCurrency) {
            return amount;
        }
        float toCurrencyInUSD = amount * toCurrency.getExchangeRateToUSD();
        return toCurrencyInUSD / defaultCurrency.getExchangeRateToUSD();
    }
}
