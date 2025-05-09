package org.wallet.biz.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BalanceTest {
    @Test
    public void addBalance() {
        Balance bal = new Balance(Currency.SINGAPORE);
        bal.topUpBalance(20.0f);
        Assertions.assertEquals(20.0f, bal.getBalance());
        Assertions.assertThrows(IllegalStateException.class, () -> bal.payFromBalance(30.0f));
    }

    @Test
    public void removeBalance() {
        Balance bal = new Balance(Currency.SINGAPORE);
        bal.topUpBalance(20.0f);
        Assertions.assertThrows(IllegalStateException.class, () -> bal.payFromBalance(30.0f));
        bal.payFromBalance(10.0f);
        Assertions.assertEquals(10.0f, bal.getBalance());
    }
}
