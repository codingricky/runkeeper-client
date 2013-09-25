package com.github.codingricky.runkeeperclient.model;

public class FitnessActivityFeed {

    private FitnessActivityFeedItem[] items;
    private int size;
    private String next;
    private String previous;

    public FitnessActivityFeedItem[] getItems() {
        return items;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }
}
