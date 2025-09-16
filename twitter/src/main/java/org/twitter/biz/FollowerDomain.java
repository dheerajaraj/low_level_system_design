package org.twitter.biz;

import org.twitter.biz.entities.User;

import java.util.HashMap;

public class FollowerDomain {

    private static HashMap<Integer, User> users;
    private static FollowerDomain INSTANCE;

    private FollowerDomain() {
        users = new HashMap<>();
    }

    public void follow(int followerId, int followeeId) {
        if(!users.containsKey(followeeId) || !users.containsKey(followerId)) {
            return;
        }

        User followee = users.get(followeeId);
        User follower = users.get(followerId);
        followee.getFollowers().add(follower);
    }

    public void unFollow(int followerId, int followeeId) {
        if(!users.containsKey(followeeId) || !users.containsKey(followerId)) {
            return;
        }
        User followee = users.get(followeeId);
        User follower = users.get(followerId);
        followee.getFollowers().remove(follower);
    }

    public static FollowerDomain getInstance() {
        if (INSTANCE == null) {
            synchronized (FollowerDomain.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FollowerDomain();
                    instantiateUsers();
                }
            }
        }
        return INSTANCE;
    }

    private static void instantiateUsers() {
        for(int i = 1; i <= 20; i++) {
            User usr = new User(i, "Dheeraj"+i);
            users.put(usr.getId(), usr);
        }
    }

    public HashMap<Integer, User> getUsers(){
       return users;
    }
}
