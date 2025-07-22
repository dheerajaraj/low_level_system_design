package org.bank.entities;

import java.util.UUID;

public class Transaction {
    public enum TransactionType {
        CREDIT,
        DEBIT;
    }

    private UUID id;
    private UUID accountId;
    private TransactionType transactionType;
    private double amount;

    public Transaction(UUID bankAccountId, TransactionType transactionType, double amount) {
        this.id = UUID.randomUUID();
        this.accountId = bankAccountId;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
    public double getAmount() {
        return amount;
    }
}
