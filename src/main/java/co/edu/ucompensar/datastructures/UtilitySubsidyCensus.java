package co.edu.ucompensar.datastructures;

import java.io.PrintStream;
import java.util.Locale;

/**
 * Exercise 1: family census with subsidies on public utilities.
 *
 * The families live in a one-dimensional array sized by how many families the user
 * declares; the totals are another one-dimensional array indexed by {@link Utility#ordinal()}.
 */
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
    public double[] totalsToPay() {
        double[] totals = new double[Utility.values().length];
        for (Family family : families) {
            for (Utility utility : Utility.values()) {
                totals[utility.ordinal()] += family.toPay(utility);
            }
        }
        return totals;
    }

    public void printTotals(PrintStream out) {
        double[] totals = totalsToPay();
        out.println();
        out.println("=== Total a pagar discriminado por servicio público ===");
        for (Utility utility : Utility.values()) {
            out.printf(CO, "%-5s $%,.2f%n", utility.getLabel(), totals[utility.ordinal()]);
        }
    }
}
