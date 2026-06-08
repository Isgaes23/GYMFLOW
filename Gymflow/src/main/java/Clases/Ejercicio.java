package Clases;

import java.util.List;

public class Ejercicio extends Entrenamiento {
    private String nombre;
    private String tipo;

    public Ejercicio() {}

    public Ejercicio(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    // ── Getters y setters ─────────────────────
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    //  1. calcularVolumenTotal
    public double calcularVolumenTotal() {
        return getSeries().stream()
                .mapToDouble(Serie::obtenerVolumen)
                .sum();
    }

    //  2. resumenEjercicio
    public String resumenEjercicio() {
        return String.format(
                "Ejercicio: %s (%s)\nSeries: %d\nVolumen total: %.1f kg·reps\nCalorías: %.2f kcal",
                nombre,
                tipo,
                totalSeries(),
                calcularVolumenTotal(),
                calcularCaloriasQuemadas()
        );
    }


}

