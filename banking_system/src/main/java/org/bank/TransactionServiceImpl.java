package org.bank;

import org.bank.command.DepositCommand;
import org.bank.command.TransferCommand;
import org.bank.command.ViewTransactionCommand;
import org.bank.entities.Transaction;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private TransactionServiceImpl() {
    }

    private static final TransactionServiceImpl instance = new TransactionServiceImpl();

    @Override
    public void transfer(TransferCommand command) {
        command.execute();
    }

    @Override
    public void topUp(DepositCommand command) {
        command.execute();
    }

    @Override
    public List<Transaction> viewTransactions(ViewTransactionCommand command) {
        return command.execute();
    }

    public static TransactionService getInstance() {
        if (instance == null) {
            synchronized (TransactionServiceImpl.class) {
                if (instance == null) {
                    return new TransactionServiceImpl();
                }
            }
        }
        return instance;
    }
}
