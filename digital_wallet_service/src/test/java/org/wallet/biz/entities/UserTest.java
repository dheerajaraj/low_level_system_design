package org.wallet.biz.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testMakePayment(){
        PaymentMethod paymentMethod = new CreditCard();
        User user = new User("dheeraj1994", Currency.SINGAPORE, paymentMethod);
        user.topUpBalance(Currency.SINGAPORE, 50f);
        Assertions.assertEquals(1, user.getTransactionHistory().size());
        user.makePayment(Currency.SINGAPORE, 500f);
        Assertions.assertEquals(1, user.getTransactionHistory().size());
        Balance balance = user.getBalance();
        Assertions.assertEquals(50f, balance.getBalance());
        user.makePayment(Currency.SINGAPORE, 5f);
        Assertions.assertEquals(45f, balance.getBalance());
        Assertions.assertEquals(2, user.getTransactionHistory().size());
        user.topUpBalance(Currency.SINGAPORE, 50f);
        Assertions.assertEquals(95f, balance.getBalance());
        Assertions.assertEquals(3, user.getTransactionHistory().size());
    }
}
