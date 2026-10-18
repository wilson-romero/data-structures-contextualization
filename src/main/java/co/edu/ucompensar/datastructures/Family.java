package co.edu.ucompensar.datastructures;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/** A census family: stratum and the three bills. */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Family {

    private static final BigDecimal RATE_STRATUM_1 = new BigDecimal("0.20");
    private static final BigDecimal RATE_STRATUM_2 = new BigDecimal("0.15");
    private static final BigDecimal RATE_STRATUM_3_OR_MORE = new BigDecimal("0.09");

    int stratum;
    BigDecimal water;
    BigDecimal power;
    BigDecimal gas;

    public static Family of(int stratum, BigDecimal water, BigDecimal power, BigDecimal gas) {
        discountRate(stratum); // rejects a stratum below 1
        if (water.signum() < 0 || power.signum() < 0 || gas.signum() < 0) {
            throw new IllegalArgumentException("Los valores de los servicios no pueden ser negativos.");
        }
        return new Family(stratum, water, power, gas);
    }

    /** Discount: stratum 1 → 20 %, 2 → 15 %, 3 or more → 9 %. */
    public static BigDecimal discountRate(int stratum) {
        if (stratum < 1) {
            throw new IllegalArgumentException("El estrato debe ser 1 o mayor.");
        }
        return switch (stratum) {
            case 1 -> RATE_STRATUM_1;
            case 2 -> RATE_STRATUM_2;
            default -> RATE_STRATUM_3_OR_MORE;
        };
    }

    /** Amount to pay for a utility after the discount. */
    public BigDecimal toPay(Utility utility) {
        BigDecimal bill = switch (utility) {
            case WATER -> water;
            case POWER -> power;
            case GAS -> gas;
        };
        return bill.multiply(BigDecimal.ONE.subtract(discountRate(stratum)))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
