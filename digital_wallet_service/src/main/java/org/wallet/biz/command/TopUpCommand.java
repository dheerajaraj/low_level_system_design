package org.wallet.biz.command;

import org.wallet.biz.entities.Currency;
import org.wallet.biz.entities.Result;

public class TopUpCommand extends Command {
    private Currency transferCurrency;
    private float amount;

    public TopUpCommand(Currency transferCurrency, float amount) {
        this.transferCurrency = transferCurrency;
        this.amount = amount;
    }

    @Override
    public Result execute() {
        this.user.topUpBalance(transferCurrency, amount);
        return new Result(Boolean.TRUE);
    }
}
