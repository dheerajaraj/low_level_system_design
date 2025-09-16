package org.twitter.biz;

import org.twitter.biz.entities.Tweets;
import org.twitter.biz.entities.User;

import java.util.*;

public class NewsFeedDomain {
    private HashMap<Integer, LinkedList<Tweets>> uidToTweets;
    private static NewsFeedDomain INSTANCE;
    private static final int NEWS_FEED_LIMIT = 10;

    private NewsFeedDomain() {
        this.uidToTweets = new HashMap<>();
    }

    public void publishTweet(HashSet<User> uids, Tweets tweet) {
        for (User usr : uids) {
            this.uidToTweets.computeIfAbsent(usr.getId(), k -> new LinkedList<>());
            this.uidToTweets.get(usr.getId()).add(tweet);
        }
    }

    public List<Tweets> getRecentTweets(int userId){
        List<Tweets> result = new ArrayList<>();
        LinkedList<Tweets> tweetsList = getUidToTweets().get(userId);
        if(tweetsList == null || tweetsList.isEmpty()){
            return result;
        }
        int lastIndex = tweetsList.size()-1;
        while(lastIndex >= 0 && result.size() < NEWS_FEED_LIMIT ){
            result.add(tweetsList.get(lastIndex));
            lastIndex--;
        }
        return result;
    }

    public HashMap<Integer, LinkedList<Tweets>> getUidToTweets() {
        return this.uidToTweets;
    }

    public static NewsFeedDomain getInstance() {
        if (INSTANCE == null) {
            synchronized (NewsFeedDomain.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NewsFeedDomain();
                }
            }
        }
        return INSTANCE;
    }
}
