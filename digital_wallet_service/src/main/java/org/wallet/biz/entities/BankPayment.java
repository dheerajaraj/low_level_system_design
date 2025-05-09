package org.wallet.biz.entities;

public class BankPayment implements PaymentMethod {
    @Override
    public boolean pay(float amount, Currency baseCurrency, Currency toCurrency) {
        /**
         * Communicate with bank
         */
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean topUp(float amount, Currency baseCurrency) {
        /**
         * Communicate with credit card scheme
         */
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }
}
