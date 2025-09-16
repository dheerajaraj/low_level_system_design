package org.twitter;

import org.twitter.biz.entities.Tweets;

import java.util.List;

public interface TweetService {
    void postTweet(int userId, int tweetId);

    List<Tweets> getNewsFeed(int userId) throws Exception;

    void follow(int followerId, int followeeId);

    void unfollow(int followerId, int followeeId);
}
