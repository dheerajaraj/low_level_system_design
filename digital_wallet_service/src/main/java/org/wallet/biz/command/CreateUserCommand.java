package org.wallet.biz.command;

import org.wallet.biz.entities.Currency;
import org.wallet.biz.entities.PaymentMethod;
import org.wallet.biz.entities.Result;
import org.wallet.biz.entities.User;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CreateUserCommand extends Command {
    ConcurrentHashMap<UUID, User> users;
    String userName;
    Currency defaultCurrency;
    PaymentMethod paymentMethod;

    public CreateUserCommand(ConcurrentHashMap<UUID, User> users, String userName, Currency defaultCurrency, PaymentMethod paymentMethod) {
        this.users = users;
        this.userName = userName;
        this.defaultCurrency = defaultCurrency;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public Result execute() {
        User user = new User(this.userName, this.defaultCurrency, this.paymentMethod);
        this.users.put(user.getUserId(), user);
        Result result = new Result();
        result.setUserId(user.getUserId());
        result.setSuccess(Boolean.TRUE);
        System.out.println(String.format("Created user with id: %s and username: %s", user.getUserId().toString(), this.userName));
        return result;
    }
}
