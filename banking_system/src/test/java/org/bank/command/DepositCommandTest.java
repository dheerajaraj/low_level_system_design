package org.bank.command;

import org.bank.entities.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepositCommandTest {

    @Test
    void testDeposit(){
        BankAccount fromBankAcct = new BankAccount("12345", BankAccount.AccountType.SAVINGS);
        DepositCommand depositCommand = new DepositCommand(fromBankAcct, 2000L);
        depositCommand.execute();
        Assertions.assertEquals(2000L, fromBankAcct.getBalance());
        Assertions.assertEquals(1, fromBankAcct.getTransactions().size());
    }
}
