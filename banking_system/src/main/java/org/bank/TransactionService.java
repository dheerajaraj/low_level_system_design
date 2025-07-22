package org.bank;

import org.bank.command.DepositCommand;
import org.bank.command.TransferCommand;
import org.bank.command.ViewTransactionCommand;
import org.bank.entities.Transaction;

import java.util.List;

public interface TransactionService {
    /**
     * Transfer command
     *
     * @param command
     */
    void transfer(TransferCommand command);

    void topUp(DepositCommand command);

    List<Transaction> viewTransactions(ViewTransactionCommand command);
}
