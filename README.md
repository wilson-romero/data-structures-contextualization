# Estructura de Datos: Actividad de Contextualización

Fundación Universitaria Compensar · Ciencia de Datos · Estructura de Datos 2314-2A · Wilson Romero Carrasco

Programa en Java por consola con los dos ejercicios de código de la actividad:

1. **Censo de subsidios con arreglos unidimensionales**: pide la cantidad de familias, el estrato
   y el valor del agua, la luz y el gas de cada una; descuenta 20 % al estrato 1, 15 % al 2 y
   9 % al 3 o más, y muestra el total a pagar discriminado por servicio público.
2. **Pila sobre arreglo**: el código de la guía (crear la pila, llenarla, imprimirla y contar
   pares e impares) con los métodos para buscar, insertar, actualizar y eliminar un elemento.

## Cómo correrlo

### 1. Requisitos

- **Java 21 o superior** (probado con Java 21 y Java 26). Con una versión anterior no compila: el
  código usa `switch` con flecha y bloques de texto `"""`, que Java 8 no conoce, y Maven muestra
  decenas de errores como «illegal start of expression».
- **Maven** 3.9 o superior.

### 2. Comprobar la versión de Java

```bash
java -version
mvn -v
```

Las dos deben decir **21 o superior**. Si dicen 1.8 (Java 8) u otra anterior, hay que cambiar de
Java antes de compilar:

- **Con SDKMAN** (Linux, WSL o macOS), solo para esa terminal:

  ```bash
  sdk use java 21.0.6-tem        # o la versión 21+ que tengas: sdk list java
  ```

- **Sin SDKMAN**: apuntar `JAVA_HOME` al JDK 21+ instalado.

  ```bash
  export JAVA_HOME=/ruta/al/jdk-21
  export PATH="$JAVA_HOME/bin:$PATH"
  ```

  En Windows (PowerShell): `$env:JAVA_HOME="C:\ruta\al\jdk-21"; $env:Path="$env:JAVA_HOME\bin;$env:Path"`

### 3. Compilar y correr

Desde la carpeta del proyecto (donde está el `pom.xml`):

```bash
mvn package
java -jar target/data-structures-contextualization-1.1.4.jar
```

El programa abre un menú: `1` para el censo de subsidios, `2` para la pila y `0` para salir.

### En NetBeans

*File → Open Project* sobre esta carpeta y *Run*. NetBeans 31 trae soporte para JDK 21, 25 y 27;
si el proyecto no compila, revisar en *Tools → Java Platforms* que el JDK del proyecto sea 21 o
superior.
