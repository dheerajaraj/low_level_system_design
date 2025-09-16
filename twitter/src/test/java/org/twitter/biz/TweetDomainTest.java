package org.twitter.biz;

import org.junit.jupiter.api.*;
import org.twitter.biz.entities.Tweets;

import java.util.HashMap;
import java.util.LinkedList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TweetDomainTest {
    private TweetDomain tweetDomain;
    private FollowerDomain followerDomain;

    @BeforeAll
    public void setUp() {
        this.tweetDomain = TweetDomain.getInstance();
        this.followerDomain = FollowerDomain.getInstance();
        this.followerDomain.follow(2,1);
        this.followerDomain.follow(3,1);
        this.followerDomain.follow(4,1);
        this.followerDomain.follow(5,1);
        this.followerDomain.follow(6,1);
    }

    @AfterAll
    public void tearDown() {
        this.tweetDomain = null;
    }

    @Test
    public void testPost(){
        this.tweetDomain.post(1, new Tweets(1, "Hello World!"));
        HashMap<Integer, LinkedList<Tweets>> uidToTwtmap = this.tweetDomain.getAllTweetsByUser();
        LinkedList<Tweets> list = uidToTwtmap.get(1);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(1, list.size());
    }
}
