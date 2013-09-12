package com.github.codingricky.runkeeperclient.model;

import java.math.BigDecimal;

public class WGS84 {
    private BigDecimal timestamp;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal altitude;
    private String type;

    @Override
    public String toString() {
        return "WGS84{" +
                "timestamp=" + timestamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", type='" + type + '\'' +
                '}';
    }
}
