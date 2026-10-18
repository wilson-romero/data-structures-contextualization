package co.edu.ucompensar.datastructures;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Public utilities and their label. */
@Getter
@RequiredArgsConstructor
public enum Utility {
    WATER("Agua"),
    POWER("Luz"),
    GAS("Gas");

    private final String label;
}
