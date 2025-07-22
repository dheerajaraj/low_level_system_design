package org.bank;

import org.bank.command.DepositCommand;
import org.bank.command.TransferCommand;
import org.bank.command.ViewTransactionCommand;
import org.bank.entities.BankAccount;
import org.bank.entities.Transaction;

import java.util.List;

/**
 * Functional Requirements
 * - User should have a bank account
 * - User should be able to transfer money to another user through the bank
 * - Invalidator: Amount to transfer should be smaller than or equal to balance
 * - User should be able to cancel the transaction
 * - User should be able to view transaction history
 * <p>
 * Commands:
 * - CreateAccountCommand
 * - TransferCommand
 * - ViewTransactionCommand
 * <p>
 * Entities:
 * - User
 * - BankAccount
 * - Transaction
 */
public class BankingApp {

    public static void main(String[] args) {
        BankAccount fromBankAcct = new BankAccount("12345", BankAccount.AccountType.SAVINGS);
        BankAccount toBankAcct = new BankAccount("54321", BankAccount.AccountType.SAVINGS);
        UserService userService = UserServiceImpl.getInstance();
        userService.createAccount("dheeraj", "R", "dheeraj1994@hotmail.com", fromBankAcct);
        userService.createAccount("John", "Doe", "john.doe@gmail.com", toBankAcct);
        ;

        TransactionService transactionService = TransactionServiceImpl.getInstance();
        DepositCommand depositCommand = new DepositCommand(userService.getUser("dheeraj1994@hotmail.com").getAccount(), 2000L);
        transactionService.topUp(depositCommand);

        TransferCommand transferCommand = new TransferCommand(userService.getUser("dheeraj1994@hotmail.com").getAccount(), userService.getUser("john.doe@gmail.com").getAccount(), 200L);
        transactionService.transfer(transferCommand);

        ViewTransactionCommand viewTransactionCommand = new ViewTransactionCommand(userService.getUser("dheeraj1994@hotmail.com").getAccount());
        List<Transaction> list = transactionService.viewTransactions(viewTransactionCommand);
        list.stream().forEach(tx -> System.out.println(tx.toString()));

        viewTransactionCommand = new ViewTransactionCommand(userService.getUser("john.doe@gmail.com").getAccount());
        List<Transaction> newlist = transactionService.viewTransactions(viewTransactionCommand);
        newlist.stream().forEach(tx -> System.out.println(tx.toString()));
    }
}