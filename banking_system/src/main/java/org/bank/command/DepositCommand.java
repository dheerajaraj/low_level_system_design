package org.bank.command;

import org.bank.entities.BankAccount;
import org.bank.entities.Transaction;

public class DepositCommand extends Command {

    private BankAccount bankAccount;
    private double amount;
    public DepositCommand(BankAccount fromBankAccount, double amount) {
        this.bankAccount = fromBankAccount;
        this.amount = amount;
    }


    @Override
    public void execute() {
        this.bankAccount.addTransaction(new Transaction(bankAccount.getId(),
                Transaction.TransactionType.CREDIT, amount));
    }
}
