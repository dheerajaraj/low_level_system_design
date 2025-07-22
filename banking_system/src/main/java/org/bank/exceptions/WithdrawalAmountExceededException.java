package org.bank.exceptions;

public class WithdrawalAmountExceededException extends RuntimeException {
    public WithdrawalAmountExceededException(double amount) {
        super("Withdrawal amount exceeded the balance: " + amount);
    }
}
