package runkeeperclient.model;

import java.math.BigDecimal;

public class HeartRate {
    private BigDecimal timestamp;
    private int heart_rate;

    @Override
    public String toString() {
        return "HeartRate{" +
                "timestamp=" + timestamp +
                ", heart_rate=" + heart_rate +
                '}';
    }
}
