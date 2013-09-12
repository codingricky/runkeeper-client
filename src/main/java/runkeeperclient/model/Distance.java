package runkeeperclient.model;

import java.math.BigDecimal;

public class Distance {

    private BigDecimal timestamp;
    private BigDecimal distance;

    @Override
    public String toString() {
        return "Distance{" +
                "timestamp=" + timestamp +
                ", distance=" + distance +
                '}';
    }
}
