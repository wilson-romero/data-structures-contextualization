package co.edu.ucompensar.datastructures;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Public utilities billed to each family, with the label shown in the report.
 */
@Getter
@RequiredArgsConstructor
public enum Utility {
    WATER("Agua"),
    POWER("Luz"),
    GAS("Gas");

    private final String label;
}
