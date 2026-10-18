package co.edu.ucompensar.datastructures;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * One family of the census: its stratum and the three bills before the subsidy.
 * Immutable; created through {@link #of} so an invalid family never exists.
 * Money is {@link BigDecimal}, so no cents are lost when amounts are added up.
 */
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

    /**
     * Stratum 1 gets 20 % off each utility, stratum 2 gets 15 % and stratum 3 or higher 9 %.
     */
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

    /** Amount to pay for one utility after the family's discount, rounded to cents. */
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
