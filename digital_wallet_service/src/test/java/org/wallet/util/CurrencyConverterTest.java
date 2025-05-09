package org.wallet.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.wallet.biz.entities.Currency;

public class CurrencyConverterTest {

    @Test
    public void testCurrencyConverter() {
        String amountToDeductInSGD = String.format("%.2f", CurrencyConvertor.convert(Currency.SINGAPORE, Currency.NEW_ZEALAND, 20));
        Assertions.assertEquals("15.32", amountToDeductInSGD);
        Assertions.assertThrows(IllegalArgumentException.class, () -> CurrencyConvertor.convert(Currency.SINGAPORE, Currency.NEW_ZEALAND, -2));
    }
}
