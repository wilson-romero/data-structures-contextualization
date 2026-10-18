package co.edu.ucompensar.datastructures;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * One family of the census: its stratum and the three bills before the subsidy.
 * Immutable; created through {@link #of} so an invalid family never exists.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Family {

    int stratum;
    double water;
    double power;
    double gas;

    public static Family of(int stratum, double water, double power, double gas) {
        discountRate(stratum); // rejects a stratum below 1
        if (water < 0 || power < 0 || gas < 0) {
            throw new IllegalArgumentException("Los valores de los servicios no pueden ser negativos.");
        }
        return new Family(stratum, water, power, gas);
    }

    /**
     * Stratum 1 gets 20 % off each utility, stratum 2 gets 15 % and stratum 3 or higher 9 %.
     */
    public static double discountRate(int stratum) {
        if (stratum < 1) {
            throw new IllegalArgumentException("El estrato debe ser 1 o mayor.");
        }
        return switch (stratum) {
            case 1 -> 0.20;
            case 2 -> 0.15;
            default -> 0.09;
        };
    }

    /** Amount to pay for one utility, after the family's discount. */
    public double toPay(Utility utility) {
        double bill = switch (utility) {
            case WATER -> water;
            case POWER -> power;
            case GAS -> gas;
        };
        return bill * (1 - discountRate(stratum));
    }
}
