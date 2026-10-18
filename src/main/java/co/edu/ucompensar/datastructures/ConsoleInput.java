package co.edu.ucompensar.datastructures;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.experimental.UtilityClass;

/**
 * Console reading that asks again until the value is valid, so a typo never ends the program.
 */
@UtilityClass
public class ConsoleInput {

    final PrintStream OUT = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    private final Scanner KEYBOARD = new Scanner(System.in, StandardCharsets.UTF_8);

    public int readInt(String prompt, int min, int max) {
        while (true) {
            OUT.print(prompt);
            try {
                int value = Integer.parseInt(KEYBOARD.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // falls through to the message below
            }
            OUT.println("Valor no válido. Debe ser un número entero" + range(min, max) + ".");
        }
    }

    public int readInt(String prompt) {
        return readInt(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public BigDecimal readAmount(String prompt) {
        while (true) {
            OUT.print(prompt);
            try {
                BigDecimal value = new BigDecimal(KEYBOARD.nextLine().trim().replace(",", "."));
                if (value.signum() >= 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // falls through to the message below
            }
            OUT.println("Valor no válido. Debe ser un número mayor o igual a cero.");
        }
    }

    private String range(int min, int max) {
        if (min == Integer.MIN_VALUE && max == Integer.MAX_VALUE) {
            return "";
        }
        return max == Integer.MAX_VALUE ? " mayor o igual a " + min : " entre " + min + " y " + max;
    }
}
