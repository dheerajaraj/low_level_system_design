package org.bank;

import org.bank.entities.BankAccount;
import org.bank.entities.User;

public interface UserService {

    void createAccount(String firstName, String lastName, String email, BankAccount bankAccount);
    User getUser(String email);
}
