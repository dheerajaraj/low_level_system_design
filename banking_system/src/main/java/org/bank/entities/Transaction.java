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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Transaction [id=").append(id.toString()).append(", accountId=").append(accountId).append(", transactionType=").append(transactionType).append(", amount=").append(amount).append("]");
        return builder.toString();
    }
}
