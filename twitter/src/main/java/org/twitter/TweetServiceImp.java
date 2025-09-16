package org.twitter;

import org.twitter.biz.FollowerDomain;
import org.twitter.biz.NewsFeedDomain;
import org.twitter.biz.TweetDomain;
import org.twitter.biz.entities.Tweets;
import org.twitter.biz.entities.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TweetServiceImp implements TweetService {

    private static TweetServiceImp INSTANCE;
    private final FollowerDomain followerDomain;
    private final TweetDomain tweetDomain;
    private final NewsFeedDomain newsFeedDomain;

    private TweetServiceImp() {
        this.followerDomain = FollowerDomain.getInstance();
        this.tweetDomain = TweetDomain.getInstance();
        this.newsFeedDomain = NewsFeedDomain.getInstance();
    }


    @Override
    public void postTweet(int userId, int tweetId) {
        if (!this.followerDomain.getUsers().containsKey(userId)) {
            return;
        }
        User usr = this.followerDomain.getUsers().get(userId);
        Tweets newTwt = new Tweets(1, "Hello World!");
        this.tweetDomain.post(userId, newTwt);
        this.newsFeedDomain.publishTweet(usr.getFollowers(), newTwt);

    }

    @Override
    public List<Tweets> getNewsFeed(int userId) throws Exception {
        User usr = this.followerDomain.getUsers().get(userId);
        if (usr == null) {
            throw new Exception("User not found");
        }
        return this.newsFeedDomain.getRecentTweets(userId);
    }

    @Override
    public void follow(int followerId, int followeeId) {

    }

    @Override
    public void unfollow(int followerId, int followeeId) {

    }

    public static TweetServiceImp getInstance() {
        if (INSTANCE == null) {
            synchronized (TweetServiceImp.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TweetServiceImp();
                }
            }
        }
        return INSTANCE;
    }
}
