package org.bank.command;

import org.bank.entities.BankAccount;
import org.bank.entities.User;
import org.bank.exceptions.UserExistsException;

import java.util.HashSet;

public class CreateAccountCommand extends Command {
    HashSet<User> users;
    String firstName;
    String lastName;
    String email;
    BankAccount bankAccount;

    public CreateAccountCommand(HashSet<User> users,
                                String firstName,
                                String lastName, String email, BankAccount bankAccount) {
        this.users = users;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bankAccount = bankAccount;
    }

    @Override
    public void execute() throws UserExistsException {
        User newUser = new User(this.firstName, this.lastName, this.email);
        newUser.setAccount(this.bankAccount);
        if(this.users.contains(newUser)) {
            throw new UserExistsException(this.firstName + " "+ this.lastName);
        }
        this.users.add(newUser);
    }

}
