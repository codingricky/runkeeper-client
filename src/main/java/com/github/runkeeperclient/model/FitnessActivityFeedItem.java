package com.github.runkeeperclient.model;


import java.math.BigDecimal;

public class FitnessActivityFeedItem {

    private long duration;
    private BigDecimal total_distance;
    private long total_calories;
    private boolean has_path;
    private String entry_mode;
    private String source;
    private String start_time;
    private String type;
    private String uri;

    public long getDuration() {
        return duration;
    }

    public BigDecimal getTotalDistance() {
        return total_distance;
    }

    public boolean hasPath() {
        return has_path;
    }

    public String getEntryMode() {
        return entry_mode;
    }

    public String getSource() {
        return source;
    }

    public String getStartTime() {
        return start_time;
    }

    public String getType() {
        return type;
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "FitnessActivityFeedItem{" +
                "duration=" + duration +
                ", total_distance=" + total_distance +
                ", total_calories=" + total_calories +
                ", has_path=" + has_path +
                ", entry_mode='" + entry_mode + '\'' +
                ", source='" + source + '\'' +
                ", start_time='" + start_time + '\'' +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
