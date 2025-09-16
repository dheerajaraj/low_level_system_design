package org.twitter.biz;

import org.twitter.biz.entities.Tweets;

import java.util.HashMap;
import java.util.LinkedList;

public class TweetDomain {
    private final HashMap<Integer, LinkedList<Tweets>> uidToTwtmap;
    private static TweetDomain INSTANCE;
    private TweetDomain() {
        this.uidToTwtmap = new HashMap<>();
    }

    public void post(int userId, Tweets tweet){
        this.uidToTwtmap.computeIfAbsent(userId, k -> new LinkedList<>());
        this.uidToTwtmap.get(userId).addLast(tweet);
    }

    public HashMap<Integer, LinkedList<Tweets>> getAllTweetsByUser(){
        return this.uidToTwtmap;
    }

    public static TweetDomain getInstance() {
        if (INSTANCE == null) {
            synchronized (TweetDomain.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TweetDomain();
                }
            }
        }
        return INSTANCE;
    }


}
