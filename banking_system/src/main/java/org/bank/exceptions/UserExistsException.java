package org.bank.exceptions;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String userName) {
        super(String.format("User %s already exists", userName));
    }
}
