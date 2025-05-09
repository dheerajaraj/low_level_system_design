package org.wallet.biz;

import org.wallet.biz.command.Command;
import org.wallet.biz.command.CreateUserCommand;
import org.wallet.biz.entities.Result;
import org.wallet.biz.entities.User;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WalletServiceImpl implements WalletService {
    public ConcurrentHashMap<UUID, User> users;
    private static WalletServiceImpl instance;

    private WalletServiceImpl() {
        this.users = new ConcurrentHashMap<>();
    }

    @Override
    public Result executeCommand(Command command, UUID userId) {
        if (!(command instanceof CreateUserCommand)) {
            User user = this.users.getOrDefault(userId, null);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            command.setUser(user);
        }
        return command.execute();
    }

    @Override
    public ConcurrentHashMap<UUID, User> getUsers() {
        return this.users;
    }

    public static WalletServiceImpl getInstance() {
        if (instance == null) {
            synchronized (WalletServiceImpl.class) {
                if (instance == null) {
                    instance = new WalletServiceImpl();
                }
            }
        }
        return instance;
    }
}
