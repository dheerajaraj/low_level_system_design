package org.wallet.biz.entities;

import org.wallet.util.CurrencyConvertor;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

public class User {
    private UUID userId;
    private String userName;
    private Currency defaultCurrency;
    private final Balance balance;
    private PaymentMethod paymentMethod;
    private final TreeMap<Integer, Transaction> transactionHistory;
    private int transactionCounter;

    public User(String userName, Currency defaultCurrency, PaymentMethod paymentMethod) {
        this.userId = UUID.randomUUID();
        this.userName = userName;
        this.defaultCurrency = defaultCurrency;
        this.balance = new Balance(defaultCurrency);
        this.paymentMethod = paymentMethod;
        this.transactionHistory = new TreeMap<>();
    }

    public UUID getUserId() {
        return userId;
    }

    public Balance getBalance() {
        return balance;
    }

    public TreeMap<Integer, Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void makePayment(Currency transferCurrency, float amount) {
        try {
            float amountToDeductInBaseCurrency = CurrencyConvertor.convert(this.defaultCurrency, transferCurrency, amount);
            synchronized (this.balance) {
                if (Float.compare(this.balance.getBalance(), amountToDeductInBaseCurrency) < 0) {
                    throw new IllegalStateException("Not enough balance");
                }
                boolean successful = this.paymentMethod.pay(amount, this.defaultCurrency, transferCurrency);
                if (successful) {
                    this.balance.payFromBalance(amountToDeductInBaseCurrency);
                    transactionCounter++;
                    synchronized (this.transactionHistory) {
                        this.transactionHistory.put(transactionCounter, new Transaction(LedgerType.DEBIT, amountToDeductInBaseCurrency, this.balance.getBalance()));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to make payment with error trace" + e.getMessage());
        }
    }

    public void topUpBalance(Currency transferCurrency, float amount) {
        float amountToTopUpInBaseCurrency = CurrencyConvertor.convert(this.defaultCurrency, transferCurrency, amount);
        synchronized (this.balance) {
            boolean successful = this.paymentMethod.topUp(amount, this.defaultCurrency);
            if (successful) {
                this.balance.topUpBalance(amountToTopUpInBaseCurrency);
                transactionCounter++;
                synchronized (this.transactionHistory) {
                    this.transactionHistory.put(transactionCounter, new Transaction(LedgerType.CREDIT, amountToTopUpInBaseCurrency, this.balance.getBalance()));
                }
            }
        }
    }

    public void printTransactionHistory() {
        List<String> stringToPrint = new ArrayList<>();
        synchronized (this.transactionHistory) {
            for (Transaction tx : this.transactionHistory.values()) {
                stringToPrint.add(String.format("user name: %s, %s", this.userName, tx.toString()));
            }
        }
        stringToPrint.forEach(System.out::println);
    }

}
