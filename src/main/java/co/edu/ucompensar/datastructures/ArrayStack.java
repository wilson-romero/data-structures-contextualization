package co.edu.ucompensar.datastructures;

import java.io.PrintStream;
import java.util.Random;
import lombok.Getter;

/**
 * Exercise 3: a stack stored in a fixed-size array (LIFO).
 *
 * Built from the guide's PrimeraPila (Figures 15 to 18): {@code top} starts at -1 and
 * points at the last element stored. On top of the guide's generar / imprimir / mostrar
 * it adds the four operations the activity asks for: search, push, update and pop.
 * Positions are counted from the base: 0 is the first element stored, {@code top} the last.
 */
@Getter
public class ArrayStack {

    @Getter(lombok.AccessLevel.NONE)
    private final int[] elements;
    private int top = -1;
    private int evenCount;
    private int oddCount;

    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("El tamaño de la pila debe ser mayor que cero.");
        }
        elements = new int[capacity];
    }

    public int getCapacity() {
        return elements.length;
    }

    public int getSize() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == elements.length - 1;
    }

    /**
     * Guide's {@code generar} (Figure 16): pushes {@code count} random numbers from 0 to 99,
     * after checking that they fit.
     */
    public void fillRandom(int count, Random random) {
        int room = getCapacity() - getSize();
        if (count < 0 || count > room) {
            throw new IllegalArgumentException("No hay espacio para " + count + " elementos; quedan " + room + ".");
        }
        for (int i = 0; i < count; i++) {
            push(random.nextInt(100));
        }
    }

    /** b. Inserts on the top when there is room. O(1). */
    public void push(int value) {
        if (isFull()) {
            throw new IllegalStateException("Desbordamiento: la pila está llena (" + getCapacity() + " elementos).");
        }
        elements[++top] = value;
        countParity(value, 1);
    }

    /** d. Removes and returns the element on the top. O(1). */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Subdesbordamiento: la pila está vacía.");
        }
        int value = elements[top--];
        countParity(value, -1);
        return value;
    }

    /**
     * a. Position of a value counted from the base, or -1. Walks from the top down, so with
     * repeated values it finds the most recent one. O(n).
     */
    public int search(int value) {
        for (int i = top; i >= 0; i--) {
            if (elements[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /** c. Replaces the element at a position and returns the old value. O(1). */
    public int update(int position, int newValue) {
        checkPosition(position);
        int old = elements[position];
        elements[position] = newValue;
        countParity(old, -1);
        countParity(newValue, 1);
        return old;
    }

    /**
     * Guide's {@code imprimir} (Figure 17), top-down as a stack is read. The guide's loop
     * {@code i < tope} leaves out the element on the top; this one includes it.
     */
    public void print(PrintStream out) {
        if (isEmpty()) {
            out.println("La pila está vacía.");
            return;
        }
        out.println("Elementos que están en la pila (del tope a la base):");
        for (int i = top; i >= 0; i--) {
            out.println("Posición: " + i + " con valor: " + elements[i] + (i == top ? "  <- tope" : ""));
        }
        out.println("El tope de la pila es " + top + ". El máximo de la pila es " + getCapacity() + ".");
    }

    /** Guide's {@code mostrar} (Figure 18). */
    public void printParity(PrintStream out) {
        out.println("El número de elementos pares es: " + evenCount);
        out.println("El número de elementos impares es: " + oddCount);
    }

    private void checkPosition(int position) {
        if (position < 0 || position > top) {
            throw new IndexOutOfBoundsException("La posición " + position + " no existe; las válidas van de 0 a " + top + ".");
        }
    }

    private void countParity(int value, int delta) {
        if (value % 2 == 0) {
            evenCount += delta;
        } else {
            oddCount += delta;
        }
    }
}
