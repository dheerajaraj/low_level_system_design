package org.wallet.biz.entities;


public class Balance {
    private float balance;
    private Currency currency;

    public Balance(Currency defaultCurrency) {
        this.balance = 0.0f;
        this.currency = defaultCurrency;
    }

    public float getBalance() {
        return balance;
    }

    public void topUpBalance(float topUpAmount) {
        this.balance = this.balance + topUpAmount;
    }

    public void payFromBalance(float withdrawAmount) {
        if (this.balance < withdrawAmount) {
            throw new IllegalStateException("Not enough balance to pay");
        }
        this.balance = this.balance - withdrawAmount;
    }
}
