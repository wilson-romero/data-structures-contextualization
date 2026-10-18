package co.edu.ucompensar.datastructures;

import java.io.PrintStream;
import java.util.Random;
import lombok.Getter;

/** Exercise 3: LIFO stack on a fixed-size array, built from the guide's PrimeraPila. */
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

    /** Guide's generar: pushes count random numbers from 0 to 99. */
    public void fillRandom(int count, Random random) {
        int room = getCapacity() - getSize();
        if (count < 0 || count > room) {
            throw new IllegalArgumentException("No hay espacio para " + count + " elementos; quedan " + room + ".");
        }
        for (int i = 0; i < count; i++) {
            push(random.nextInt(100));
        }
    }

    /** b. Insert on the top if there is room. */
    public void push(int value) {
        if (isFull()) {
            throw new IllegalStateException("Desbordamiento: la pila está llena (" + getCapacity() + " elementos).");
        }
        elements[++top] = value;
        countParity(value, 1);
    }

    /** d. Remove and return the top. */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Subdesbordamiento: la pila está vacía.");
        }
        int value = elements[top--];
        countParity(value, -1);
        return value;
    }

    /** a. Position of a value from the base, or -1. */
    public int search(int value) {
        for (int i = top; i >= 0; i--) {
            if (elements[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /** c. Replace the value at a position; returns the old one. */
    public int update(int position, int newValue) {
        checkPosition(position);
        int old = elements[position];
        elements[position] = newValue;
        countParity(old, -1);
        countParity(newValue, 1);
        return old;
    }

    /** Guide's imprimir, from the top down and including the top. */
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

    /** Guide's mostrar: even and odd counts. */
    public void printParity(PrintStream out) {
        out.println("El número de elementos pares es: " + evenCount);
        out.println("El número de elementos impares es: " + oddCount);
    }

    private void checkPosition(int position) {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía: no hay posiciones.");
        }
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
