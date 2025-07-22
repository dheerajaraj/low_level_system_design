package org.bank.command;

import org.bank.entities.BankAccount;
import org.bank.entities.User;
import org.bank.exceptions.UserExistsException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashSet;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateAccountCommandTest {
    HashSet<User> users;

    @BeforeAll
    public void setUp() {
        this.users = new HashSet<>();
    }

    @AfterAll
    public void tearDown() {
        this.users.clear();
        this.users = null;
    }

    @Test
    public void testExecute() {
        BankAccount accnt = new BankAccount("12345", BankAccount.AccountType.SAVINGS);
        CreateAccountCommand command = new CreateAccountCommand(users, "dheeraj", "R", "dheeraj1994@hotmail.com", accnt);
        command.execute();
        Assertions.assertEquals(1, this.users.size());
        CreateAccountCommand duplicateCommand = new CreateAccountCommand(users, "dheeraj", "R", "dheeraj1994@hotmail" +
                ".com", accnt);
        User usrA = new User("dheeraj", "R", "dheeraj1994@hotmail.com");
        User usrB = new User("dheeraj", "R", "dheeraj1994@hotmail.com");
        HashSet<User> duplicateUsers = new HashSet<>();
        duplicateUsers.add(usrA);
        System.out.println("contains: "+duplicateUsers.contains(usrB));
        Assertions.assertThrows(UserExistsException.class, () -> duplicateCommand.execute());
    }
}
