package org.wallet.biz;

import org.wallet.biz.command.Command;
import org.wallet.biz.entities.Result;
import org.wallet.biz.entities.User;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public interface WalletService {
    Result executeCommand(Command command, UUID userId);

    ConcurrentHashMap<UUID, User> getUsers();
}
