package org.wallet.biz.command;

import org.wallet.biz.entities.Result;

public class PrintTransactionHistoryCommand extends Command {

    public PrintTransactionHistoryCommand() {
    }

    @Override
    public Result execute() {
        this.user.printTransactionHistory();
        return new Result(Boolean.TRUE);
    }
}
