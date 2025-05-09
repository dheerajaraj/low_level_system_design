package org.wallet.biz.command;

import org.wallet.biz.entities.Result;
import org.wallet.biz.entities.User;

public abstract class Command {
    protected User user;

    public void setUser(User user) {
        this.user = user;
    }
    public abstract Result execute();
}
