package org.twitter.biz.entities;

import java.util.HashSet;

public class User {
    private int id;
    private String username;
    private HashSet<User> followers;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
        this.followers = new HashSet<User>();
    }

    public int getId() {
        return id;
    }

    public HashSet<User> getFollowers() {
        return this.followers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        User other = (User) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

}
