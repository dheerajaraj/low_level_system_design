package org.bank.command;

import org.bank.entities.BankAccount;
import org.bank.entities.Transaction;
import org.bank.exceptions.UserExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ViewTransactionCommandTest {

    @Test
    public void testExecute() {
        BankAccount fromBankAcct = new BankAccount("12345", BankAccount.AccountType.SAVINGS);
        BankAccount toBankAcct = new BankAccount("54321", BankAccount.AccountType.SAVINGS);
        DepositCommand depositCommand = new DepositCommand(fromBankAcct,2000L);
        depositCommand.execute();
        TransferCommand transferCommand = new TransferCommand(fromBankAcct, toBankAcct, 200L);
        transferCommand.execute();
        ViewTransactionCommand viewTransactionCommand = new ViewTransactionCommand(fromBankAcct);
        List<Transaction> list = viewTransactionCommand.execute();
        viewTransactionCommand = new ViewTransactionCommand(toBankAcct);
        List<Transaction> newlist = viewTransactionCommand.execute();
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(1, newlist.size());
    }

}
