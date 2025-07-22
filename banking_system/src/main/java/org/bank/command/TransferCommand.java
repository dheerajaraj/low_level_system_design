package org.bank.command;

import org.bank.entities.BankAccount;
import org.bank.entities.Transaction;

import java.util.UUID;

public class TransferCommand extends Command {

    private BankAccount fromBankAccount;
    private BankAccount toBankAccount;
    private double amount;

    public TransferCommand(BankAccount fromBankAccount, BankAccount toBankAccount, double amount) {
        this.fromBankAccount = fromBankAccount;
        this.toBankAccount = toBankAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        this.fromBankAccount.addTransaction(new Transaction(fromBankAccount.getId(),
                Transaction.TransactionType.DEBIT, amount));
        this.toBankAccount.addTransaction(new Transaction(toBankAccount.getId(),
                Transaction.TransactionType.CREDIT, amount));
    }
}
