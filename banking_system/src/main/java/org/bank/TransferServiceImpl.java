package org.bank;

import org.bank.command.Command;

import java.util.UUID;

public class TransferServiceImpl implements TransferService {
    private static final TransferServiceImpl instance = new TransferServiceImpl();
    @Override
    public void transfer(Command command) {
        command.execute();
    }

    public static TransferService getInstance() {
        if(instance == null) {
            synchronized (TransferServiceImpl.class) {
                if(instance == null) {
                    return new TransferServiceImpl();
                }
            }
        }
        return instance;
    }
}
