package org.twitter.biz;

import org.junit.jupiter.api.*;
import org.twitter.biz.entities.User;

import java.util.HashMap;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FollowerDomainTest {

    private FollowerDomain followerDomain;

    @BeforeAll
    public void setUp() {
        this.followerDomain = FollowerDomain.getInstance();
    }

    @AfterAll
    public void tearDown() {
        this.followerDomain = null;
    }

    @Test
    public void testAddingFollowers(){
        this.followerDomain.follow(2, 1);
        HashMap<Integer, User> map = this.followerDomain.getUsers();
        User usr = map.get(1);
        Assertions.assertEquals(1,usr.getFollowers().size());
        Assertions.assertTrue(usr.getFollowers().contains(map.get(2)));
        this.followerDomain.follow(2, 12);
        Assertions.assertEquals(1,usr.getFollowers().size());
        this.followerDomain.follow(-2, 10);
        Assertions.assertEquals(1,usr.getFollowers().size());
    }

    @Test
    public void testUnfollowing(){
        this.followerDomain.follow(2, 1);
        this.followerDomain.unFollow(2, 1);
        HashMap<Integer, User> map = this.followerDomain.getUsers();
        User followee = map.get(1);
        Assertions.assertEquals(0,followee.getFollowers().size());
        this.followerDomain.unFollow(2, 1);
        Assertions.assertEquals(0,followee.getFollowers().size());
    }
}
