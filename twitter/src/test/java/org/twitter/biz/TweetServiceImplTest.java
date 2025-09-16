package org.twitter.biz;

import org.junit.jupiter.api.*;
import org.twitter.TweetService;
import org.twitter.TweetServiceImp;
import org.twitter.biz.entities.Tweets;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TweetServiceImplTest {

    private FollowerDomain followerDomain;
    private TweetService tweetService;
    private TweetDomain tweetDomain;
    private NewsFeedDomain newsFeedDomain;

    @BeforeAll
    public void setUp() {
        this.followerDomain = FollowerDomain.getInstance();
        this.followerDomain.follow(2, 1);
        this.followerDomain.follow(3, 1);
        this.followerDomain.follow(4, 1);
        this.followerDomain.follow(5, 1);
        this.followerDomain.follow(6, 1);
        this.followerDomain.follow(1, 2);
        this.followerDomain.follow(1, 3);
        this.followerDomain.follow(1, 4);
        this.followerDomain.follow(1, 5);
        this.followerDomain.follow(1, 6);
        this.followerDomain.follow(1, 7);
        this.followerDomain.follow(1, 8);
        this.followerDomain.follow(1, 9);
        this.followerDomain.follow(1, 10);
        this.followerDomain.follow(1, 11);
        this.followerDomain.follow(1, 12);
        this.tweetService = TweetServiceImp.getInstance();
        this.tweetDomain = TweetDomain.getInstance();
        this.newsFeedDomain = NewsFeedDomain.getInstance();
    }

    @AfterAll
    public void tearDown() {
        this.followerDomain = null;
        this.tweetService = null;
    }

    @Test
    public void testPost() {
        this.tweetService.postTweet(1, 1);
        HashMap<Integer, LinkedList<Tweets>> uidToTwtmap = this.newsFeedDomain.getUidToTweets();
        LinkedList<Tweets> list = uidToTwtmap.get(2);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(1, list.size());
        list = uidToTwtmap.get(3);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(1, list.size());
        list = uidToTwtmap.get(4);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(1, list.size());
        list = uidToTwtmap.get(5);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(1, list.size());
        list = uidToTwtmap.get(6);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(1, list.size());
        list = uidToTwtmap.get(1);
        Assertions.assertNull(list);
        list = uidToTwtmap.get(7);
        Assertions.assertNull(list);
    }

    @Test
    public void getNewsFeed() throws Exception {
        List<Tweets> nf = this.tweetService.getNewsFeed(2);
        Assertions.assertNotNull(nf);
        Assertions.assertEquals(1, nf.size());
        Assertions.assertEquals(1, nf.get(0).getId());
        Assertions.assertTrue(nf.size() <= 10 && nf.size()>=1);
        this.tweetService.postTweet(2, 3);
        this.tweetService.postTweet(3, 4);
        this.tweetService.postTweet(4, 5);
        this.tweetService.postTweet(5, 6);
        this.tweetService.postTweet(6, 7);
        this.tweetService.postTweet(7, 8);
        this.tweetService.postTweet(8, 9);
        this.tweetService.postTweet(9, 10);
        this.tweetService.postTweet(10, 11);
        this.tweetService.postTweet(11, 12);
        this.tweetService.postTweet(12, 15);
        nf = this.tweetService.getNewsFeed(1);
        Assertions.assertNotNull(nf);
        Assertions.assertEquals(10, nf.size());
        nf = this.tweetService.getNewsFeed(18);
        Assertions.assertTrue(nf.isEmpty());
    }
}
