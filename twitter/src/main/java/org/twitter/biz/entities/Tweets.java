package org.twitter.biz.entities;

public class Tweets {
    private int id;
    private String content;

    public Tweets(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return this.id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
