package org.wallet.biz.entities;

public enum Currency {
    SINGAPORE("SGD", 0.77f),
    AUSTRALIA("AUD", 0.64f),
    NEW_ZEALAND("NZD", 0.59f),
    /**
     * this is the default exchange rate
     */
    AMERICA("USD", 1.0f),
    INDIA("INR", 0.012f);

    private String currencyCode;
    private float exchangeRateToUSD;

    Currency(String currency, float exchangeRateToUSD) {
        this.currencyCode = currency;
        this.exchangeRateToUSD = exchangeRateToUSD;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public float getExchangeRateToUSD() {
        return exchangeRateToUSD;
    }
}
