package com.github.codingricky.runkeeperclient.model;

import java.math.BigDecimal;

public class Image {
    private BigDecimal timestamp;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String uri;
    private String thumbnail_uri;

    @Override
    public String toString() {
        return "Image{" +
                "timestamp=" + timestamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", uri='" + uri + '\'' +
                ", thumbnail_uri='" + thumbnail_uri + '\'' +
                '}';
    }
}
