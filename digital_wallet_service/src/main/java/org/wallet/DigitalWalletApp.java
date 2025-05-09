package org.wallet;

import org.wallet.biz.WalletService;
import org.wallet.biz.WalletServiceImpl;
import org.wallet.biz.command.CreateUserCommand;
import org.wallet.biz.command.PrintTransactionHistoryCommand;
import org.wallet.biz.command.TopUpCommand;
import org.wallet.biz.command.TransferCommand;
import org.wallet.biz.entities.CreditCard;
import org.wallet.biz.entities.Currency;
import org.wallet.biz.entities.PaymentMethod;
import org.wallet.biz.entities.Result;

import java.util.UUID;

/**
 * Functional Requirements:
 * 1. User should be able to create a profile and account
 * 2. user should link to a bank account and credit card
 * 3. user should be able to top up $$ to wallet
 * 4. user should pay money from wallet to external account
 * 5. Platform should support multi-currency and transactions
 * 6. Should be able to handle transaction history and generate reports.
 * <p>
 * Non-functional requirements:
 * 1. System should be consistent, inflow of money (top-ups) and outflow (payouts) should be in sync
 * 2. Amount payable should always be less than or equal to balance in wallet
 * 3. Should be scalable to large number of users.
 */
public class DigitalWalletApp {
    public static void main(String[] args) {
        WalletService walletService = WalletServiceImpl.getInstance();
        PaymentMethod paymentMethod = new CreditCard();
        CreateUserCommand command = new CreateUserCommand(walletService.getUsers(), "dheeraj1994", Currency.SINGAPORE, paymentMethod);
        Result result = walletService.executeCommand(command, null);
        UUID userId = result.getUserId();
        TopUpCommand topUpCommand = new TopUpCommand(Currency.SINGAPORE, 100.f);
        walletService.executeCommand(topUpCommand, userId);
        TransferCommand transferCommand = new TransferCommand(Currency.SINGAPORE, 20.f);
        walletService.executeCommand(transferCommand, userId);
        transferCommand = new TransferCommand(Currency.SINGAPORE, 200.f);
        walletService.executeCommand(transferCommand, userId);
        PrintTransactionHistoryCommand printTransactionHistoryCommand = new PrintTransactionHistoryCommand();
        walletService.executeCommand(printTransactionHistoryCommand, userId);
    }
}

/**
 * Entities
 * - User
 * - Payment Method for user:  Bank account, credit card, etc
 * - Transaction
 * - Currency and Currency Converter
 * - User to wallet (one to one relationship)
 */