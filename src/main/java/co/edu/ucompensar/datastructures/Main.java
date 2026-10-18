package co.edu.ucompensar.datastructures;

import java.io.PrintStream;
import java.util.Random;
import lombok.experimental.UtilityClass;

/** Console menu for exercises 1 and 3. */
@UtilityClass
public class Main {

    private static final PrintStream OUT = ConsoleInput.OUT;

    private static final String MAIN_MENU = """

            ===== Estructura de Datos - Contextualización =====
            1. Censo familiar: subsidios a servicios públicos (arreglos)
            2. Operaciones sobre una pila
            0. Salir""";

    private static final String STACK_MENU = """
            1. Llenar con números aleatorios (guía)
            2. Insertar un elemento (push)
            3. Eliminar el elemento del tope (pop)
            4. Buscar un elemento e indicar su posición
            5. Actualizar el elemento de una posición
            6. Imprimir la pila
            7. Contar pares e impares (guía)
            0. Volver""";

    /** Shows the main menu until the user exits. */
    public static void main(String[] args) {
        int option;
        do {
            OUT.println(MAIN_MENU);
            option = ConsoleInput.readInt("Opción: ", 0, 2);
            switch (option) {
                case 1 -> runCensus();
                case 2 -> runStack();
                default -> OUT.println("Hasta luego.");
            }
        } while (option != 0);
    }

    void runCensus() {
        int size = ConsoleInput.readInt("Cantidad de familias: ", 1, Integer.MAX_VALUE);
        UtilitySubsidyCensus census = new UtilitySubsidyCensus(size);
        for (int i = 0; i < size; i++) {
            OUT.println("--- Familia " + (i + 1) + " ---");
            int stratum = ConsoleInput.readInt("Estrato: ", 1, Integer.MAX_VALUE);
            census.setFamily(i, Family.of(stratum,
                    ConsoleInput.readAmount("Valor del agua: "), ConsoleInput.readAmount("Valor de la luz: "), ConsoleInput.readAmount("Valor del gas: ")));
        }
        census.printTotals(OUT);
    }

    void runStack() {
        ArrayStack stack = new ArrayStack(ConsoleInput.readInt("Tamaño máximo de la pila: ", 1, Integer.MAX_VALUE));
        Random random = new Random();
        int option;
        do {
            OUT.println();
            OUT.println("--- Pila (" + stack.getSize() + " de " + stack.getCapacity() + ") ---");
            OUT.println(STACK_MENU);
            option = ConsoleInput.readInt("Opción: ", 0, 7);
            try {
                switch (option) {
                    case 1 -> {
                        stack.fillRandom(ConsoleInput.readInt("¿Cuántos elementos? ", 0, Integer.MAX_VALUE), random);
                        stack.print(OUT);
                    }
                    case 2 -> {
                        if (stack.isFull()) {
                            OUT.println("No hay espacio disponible: la pila está llena.");
                        } else {
                            stack.push(ConsoleInput.readInt("Valor a insertar: "));
                            OUT.println("Insertado en la posición " + stack.getTop() + ".");
                        }
                    }
                    case 3 -> OUT.println("Se eliminó el valor " + stack.pop() + ".");
                    case 4 -> {
                        int value = ConsoleInput.readInt("Valor a buscar: ");
                        int position = stack.search(value);
                        OUT.println(position == -1
                                ? "El valor " + value + " no está en la pila."
                                : "El valor " + value + " está en la posición " + position
                                        + " (a " + (stack.getTop() - position) + " del tope).");
                    }
                    case 5 -> {
                        int position = ConsoleInput.readInt("Posición: ");
                        int value = ConsoleInput.readInt("Nuevo valor: ");
                        OUT.println("Posición " + position + ": " + stack.update(position, value) + " -> " + value);
                    }
                    case 6 -> stack.print(OUT);
                    case 7 -> stack.printParity(OUT);
                    default -> { }
                }
            } catch (IllegalStateException | IllegalArgumentException | IndexOutOfBoundsException e) {
                OUT.println(e.getMessage());
            }
        } while (option != 0);
    }
}
