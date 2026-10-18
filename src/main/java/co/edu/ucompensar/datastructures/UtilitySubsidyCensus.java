package co.edu.ucompensar.datastructures;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Locale;

/** Exercise 1: subsidy census stored in one-dimensional arrays. */
public class UtilitySubsidyCensus {

    private static final Locale CO = Locale.forLanguageTag("es-CO");

    private final Family[] families;

    public UtilitySubsidyCensus(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("La cantidad de familias debe ser mayor que cero.");
        }
        families = new Family[size];
    }

    public int size() {
        return families.length;
    }

    public void setFamily(int index, Family family) {
        families[index] = family;
    }

    /** Total to pay per utility over all families. */
    public BigDecimal[] totalsToPay() {
        BigDecimal[] totals = new BigDecimal[Utility.values().length];
        Arrays.fill(totals, BigDecimal.ZERO);
        for (Family family : families) {
            for (Utility utility : Utility.values()) {
                totals[utility.ordinal()] = totals[utility.ordinal()].add(family.toPay(utility));
            }
        }
        return totals;
    }

    public void printTotals(PrintStream out) {
        BigDecimal[] totals = totalsToPay();
        out.println();
        out.println("=== Total a pagar discriminado por servicio público ===");
        for (Utility utility : Utility.values()) {
            out.printf(CO, "%-5s $%,.2f%n", utility.getLabel(), totals[utility.ordinal()]);
        }
    }
}
