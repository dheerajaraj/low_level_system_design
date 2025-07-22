package org.bank.command;

import org.bank.entities.BankAccount;
import org.bank.exceptions.WithdrawalAmountExceededException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransferCommandTest {

    @Test
    public void testTransferCommandExecute() {
        BankAccount fromBankAcct = new BankAccount("12345", BankAccount.AccountType.SAVINGS);
        BankAccount toBankAcct = new BankAccount("54321", BankAccount.AccountType.SAVINGS);
        DepositCommand depositCommand = new DepositCommand(fromBankAcct,2000L);
        depositCommand.execute();
        TransferCommand transferCommand = new TransferCommand(fromBankAcct, toBankAcct, 200L);
        transferCommand.execute();
        Assertions.assertEquals(2, fromBankAcct.getTransactions().size());
        Assertions.assertEquals(1, toBankAcct.getTransactions().size());
        Assertions.assertEquals(1800, fromBankAcct.getBalance());
        Assertions.assertEquals(200, toBankAcct.getBalance());
        transferCommand = new TransferCommand(fromBankAcct, toBankAcct, 2000L);
        Assertions.assertThrows(WithdrawalAmountExceededException.class, transferCommand::execute);
    }
}
