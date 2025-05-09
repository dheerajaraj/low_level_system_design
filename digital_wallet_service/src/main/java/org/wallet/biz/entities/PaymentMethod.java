package org.wallet.biz.entities;

public interface PaymentMethod {

    /**
     * Communicates with the Bank to deduct.
     * @param amount
     * @param baseCurrency
     * @param toCurrency
     * @return true/false to see if tx was successful
     * @throws InterruptedException
     */
    boolean pay(float amount, Currency baseCurrency, Currency toCurrency);

    /**
     * Top-up Amount
     * @param amount
     * @param baseCurrency
     * @return
     */
    boolean topUp(float amount, Currency baseCurrency);
}
