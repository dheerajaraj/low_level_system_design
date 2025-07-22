package org.bank.entities;

import org.bank.exceptions.WithdrawalAmountExceededException;

import java.util.LinkedList;
import java.util.UUID;

public class BankAccount {

    public enum AccountType {
        SAVINGS,
        CURRENT;
    }

    private UUID id;
    private String accountNumber;
    private AccountType accountType;
    private LinkedList<Transaction> transactions;
    private double balance;

    public BankAccount(String accountNumber, AccountType accountType) {
        this.id = UUID.randomUUID();
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.transactions = new LinkedList<>();
        this.balance = 0L;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) throws WithdrawalAmountExceededException {
        if (transaction.getTransactionType() == Transaction.TransactionType.CREDIT) {
            this.balance += transaction.getAmount();
        } else if (transaction.getTransactionType() == Transaction.TransactionType.DEBIT) {
            if (this.balance < transaction.getAmount()) {
                throw new WithdrawalAmountExceededException(transaction.getAmount());
            }
            this.balance -= transaction.getAmount();
        }
        this.transactions.add(transaction);
    }
}

