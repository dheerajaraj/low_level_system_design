package org.bank.entities;

import java.util.UUID;

public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private BankAccount account;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = UUID.randomUUID();
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    /**
     * 1. Hashing:
     * The HashSet uses a HashMap internally. When you call contains(element), the HashSet first calculates
     * the hashCode() of the element. This hash code is used to find the appropriate bucket in the underlying HashMap.
     *
     * 2. Equality Check:
     * If the bucket is not empty, the HashSet then iterates through the elements within that bucket and uses the
     * equals() method to compare each element with the element you are searching for. This ensures that only objects
     * that are considered equal by your definition (as defined in the equals() method) are considered matches.
     *
     * Hence, you need to override both HashCode and equals methods in order to see if 2 objs are truly equal
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        User other = (User) o;
        return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName) && this.email.equals(other.email);
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return 31 * result + email.hashCode();
    }
}
