package org.bank.command;

import org.bank.entities.BankAccount;
import org.bank.entities.Transaction;

import java.util.List;

public class ViewTransactionCommand {

    private BankAccount targetBankAccount;

    public ViewTransactionCommand(BankAccount targetBankAccount) {
        this.targetBankAccount = targetBankAccount;
    }

    public List<Transaction> execute() {
        return targetBankAccount.getTransactions();
    }
}
