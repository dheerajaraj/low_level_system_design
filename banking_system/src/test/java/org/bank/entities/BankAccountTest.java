package org.bank.entities;

import org.bank.exceptions.WithdrawalAmountExceededException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankAccountTest {

    private BankAccount bankAccount;

    @BeforeAll
    public void setUp() {
        this.bankAccount = new BankAccount("B12345", BankAccount.AccountType.SAVINGS);
    }

    @AfterAll
    public void tearDown() {
        this.bankAccount = null;
    }

    @Test
    public void testAddBankAccount() {
        this.bankAccount.addTransaction(new Transaction(UUID.randomUUID(), Transaction.TransactionType.CREDIT, 20.0));
        Assertions.assertEquals(1, this.bankAccount.getTransactions().size());
        Assertions.assertEquals(20.0, this.bankAccount.getBalance());
        this.bankAccount.addTransaction(new Transaction(UUID.randomUUID(), Transaction.TransactionType.DEBIT, 10.0));
        Assertions.assertEquals(2, this.bankAccount.getTransactions().size());
        Assertions.assertEquals(10.0, this.bankAccount.getBalance());
        Assertions.assertThrows(WithdrawalAmountExceededException.class, () -> this.bankAccount.addTransaction(new Transaction(UUID.randomUUID(), Transaction.TransactionType.DEBIT, 15.0)));
        Assertions.assertEquals(2, this.bankAccount.getTransactions().size());
    }
}
