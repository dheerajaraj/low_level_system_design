package org.bank;

import org.bank.command.CreateAccountCommand;
import org.bank.entities.BankAccount;
import org.bank.entities.User;
import org.bank.exceptions.UserExistsException;

import java.util.HashSet;

public class UserServiceImpl implements UserService {
    private HashSet<User> users;

    private UserServiceImpl() {
        users = new HashSet<>();
    }

    public static UserServiceImpl instance;


    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    return new UserServiceImpl();
                }
            }
        }
        return instance;
    }

    public User getUser(String email) {
        for(User user : users) {
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void createAccount(String firstName, String lastName, String email, BankAccount bankAccount) {
        try {
            CreateAccountCommand command = new CreateAccountCommand(users, firstName, lastName, email, bankAccount);
            command.execute();
        } catch (UserExistsException e) {
            System.out.println(e.getMessage());
        }
    }
}
