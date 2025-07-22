package org.bank;

/**
 * Functional Requirements
 * - User should have a bank account
 * - User should be able to transfer money to another user through the bank
 *   - Invalidator: Amount to transfer should be smaller than or equal to balance
 * - User should be able to cancel the transaction
 * - User should be able to view transaction history
 *
 * Commands:
 * - CreateAccountCommand
 * - TransferCommand
 * - ViewTransactionCommand
 *
 * Entities:
 *  - User
 *  - BankAccount
 *  - Transaction
 *
 *
 */
public class BankingApp {

    public static void main(String[] args) {

    }
}