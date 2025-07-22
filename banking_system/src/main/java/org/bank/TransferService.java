package org.bank;

import org.bank.command.Command;

import java.util.UUID;

public interface TransferService {
    /**
     * Transfer command
     * @param command
     */
    void transfer(Command command);
}
