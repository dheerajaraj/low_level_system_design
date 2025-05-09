package org.wallet.biz.entities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transaction {
    private UUID transactionId;
    private LedgerType ledgerType;
    private final long createTime;
    private final float amountChanged;
    private final float balanceAmount;

    public Transaction(LedgerType ledgerType, float amountChanged, float balanceAmount) {
        this.transactionId = UUID.randomUUID();
        this.ledgerType = ledgerType;
        this.amountChanged = amountChanged;
        this.createTime = Instant.now().getEpochSecond();
        this.balanceAmount = balanceAmount;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public LedgerType getLedgerType() {
        return ledgerType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public float getAmountChanged() {
        return amountChanged;
    }

    public float getBalanceAmount() {
        return balanceAmount;
    }

    public String toString() {
        ZonedDateTime dateTime = Instant.ofEpochSecond(this.createTime)
                .atZone(ZoneId.of("Asia/Singapore"));
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return String.format("TransactionId: %s, balance: %.2f,tx type: %s, tx amount: %.2f, creation time: %s", transactionId.toString(), this.balanceAmount, ledgerType.name(), amountChanged, formattedDateTime);
    }
}
