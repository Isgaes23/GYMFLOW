package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rutina{
    private String nombre;
    private String nivel;
    private int diasSemana;
    private List<Ejercicio> ejercicios = new ArrayList<>();

    public Rutina() {
    }

    public Rutina(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    // ══════════════════════════════════════════
    //  1. generarRutina
    // ══════════════════════════════════════════

    public Rutina generarRutina(String nombreUsuario, String nivelUsuario,
                                       List<Ejercicio> ejerciciosPool) {
        this.nivel = nivelUsuario;
        this.nombre = "Rutina " + nivelUsuario + " – " + nombreUsuario;
        this.diasSemana = calcularDiasRecomendados(nivelUsuario);
        System.out.println("✔ Rutina generada: " + nombre
                + " | " + diasSemana + " días/semana"
                + " | " + ejercicios.size() + " ejercicios");
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(int diasSemana) {
        this.diasSemana = diasSemana;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }


    //  2. adaptarRutina
    public void adaptarRutina(String nuevoNivel, List<Ejercicio>
            ejerciciosPool) {
        this.nivel = nuevoNivel;
        this.diasSemana = calcularDiasRecomendados(nuevoNivel);
        System.out.println("↺ Rutina adaptada a nivel: " + nuevoNivel
                + " | " + diasSemana + " días/semana");
    }

    //  4. agregarEjercicio
    public void agregarEjercicio(Ejercicio ejercicio) {
        boolean yaExiste = ejercicios.stream()
                .anyMatch(e -> e.getNombre().equalsIgnoreCase(ejercicio.getNombre()));
        if (!yaExiste) {
            ejercicios.add(ejercicio);
            System.out.println("+ Ejercicio añadido: " + ejercicio.getNombre());
        } else {
            System.out.println("⚠ El ejercicio ya está en la rutina.");
        }
    }

    //  5. eliminarEjercicio
    public void eliminarEjercicio(String nombre) {
        boolean eliminado = ejercicios.removeIf(
                e -> e.getNombre().equalsIgnoreCase(nombre)
        );
        System.out.println(eliminado
                ? "- Ejercicio eliminado: " + nombre
                : "⚠ Ejercicio no encontrado: " + nombre);
    }

    //  6. calcularDuracionEstimada
    public int calcularDuracionEstimada() {
        return ejercicios.size() * 3 * 3;
    }

    // ── calcularDiasRecomendados  (auxiliar) ──────────────────
    private int calcularDiasRecomendados(String nivel) {
        if (nivel == null) return 3;
        return switch (nivel.toUpperCase()) {
            case "PRINCIPIANTE" -> 3;
            case "MEDIO" -> 4;
            case "AVANZADO" -> 5;
            default -> 3;
        };
    }}
