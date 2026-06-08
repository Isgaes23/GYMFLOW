package Clases;

import Clases.Serie;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ╔══════════════════════════════════════════════╗
 *  MÉTODOS PROPIOS – Entrenamiento
 * ╚══════════════════════════════════════════════╝
 */
public class Entrenamiento {

    // ── Atributos necesarios para los métodos ──────────────────
    private LocalDate fecha;
    private int       duracionMinutos;
    private double    caloriasQuemadas;
    private String    notas;
    private List<Serie> series = new ArrayList<>();

    // Control interno de sesión (no persiste en BD)
    private LocalTime horaInicio;

    public Entrenamiento() {}

    // ── Getters ────────────────────────────────────────────────
    public LocalDate           getFecha()            { return fecha; }
    public int                 getDuracionMinutos()  { return duracionMinutos; }
    public double              getCaloriasQuemadas() { return caloriasQuemadas; }
    public String              getNotas()            { return notas; }
    public List<Serie>  getSeries()           { return series; }
    public int                 totalSeries()         { return series.size(); }

    //  1. iniciarEntrenamiento
    public void iniciarEntrenamiento() {
        this.fecha      = LocalDate.now();
        this.horaInicio = LocalTime.now();
        this.series     = new ArrayList<>();
        System.out.println(" Entrenamiento iniciado: " + fecha + " " + horaInicio);
    }

    //  2. finalizarEntrenamiento
    public void finalizarEntrenamiento() {
        if (horaInicio != null) {
            long min = java.time.Duration
                    .between(horaInicio, LocalTime.now())
                    .toMinutes();
            this.duracionMinutos = (int) Math.max(min, 1);
        }
        this.caloriasQuemadas = calcularCaloriasQuemadas();
        System.out.printf("Entrenamiento finalizado: %d min | %.2f kcal%n",
                duracionMinutos, caloriasQuemadas);
    }

    //  3. registrarSerie
    public void registrarSerie(Serie serie) {
        this.series.add(serie);
        System.out.println("  + " + serie.resumen());
    }

    //  4. calcularCaloriasQuemadas
    public double calcularCaloriasQuemadas() {
        double total = series.stream()
                .mapToDouble(Serie::calcularCalorias)
                .sum();
        return Math.round(total * 100.0) / 100.0; // ← redondea el total
    }

    //  5. obtenerDuracionReal
    public int obtenerDuracionReal() {
        return duracionMinutos;
    }


}