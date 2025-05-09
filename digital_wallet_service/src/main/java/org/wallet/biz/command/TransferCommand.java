package org.wallet.biz.command;

import org.wallet.biz.entities.Currency;
import org.wallet.biz.entities.Result;

public class TransferCommand extends Command {
    private Currency transferCurrency;
    private float amount;

    public TransferCommand(Currency transferCurrency, float amount) {
        this.transferCurrency = transferCurrency;
        this.amount = amount;
    }

    @Override
    public Result execute() {
        this.user.makePayment(transferCurrency, amount);
        return new Result(Boolean.TRUE);
    }
}
