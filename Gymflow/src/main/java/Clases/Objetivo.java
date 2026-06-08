package Clases;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Objetivo {
    private double    valorMeta;
    private double    valorActual;
    private LocalDate fechaFin;
    private String    tipo;
    private String    descripcion;
    private String    estado;

    public Objetivo() {
    }

    public Objetivo(double valorMeta, double valorActual,
                    LocalDate fechaFin, String tipo,
                    String descripcion, String estado) {
        this.valorMeta   = valorMeta;
        this.valorActual = valorActual;
        this.fechaFin    = fechaFin;
        this.tipo        = tipo;
        this.descripcion = descripcion;
        this.estado      = estado;
    }

    public double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public double getValorActual() {
        return valorActual;
    }

    public void setValorActual(double valorActual) {
        this.valorActual = valorActual;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    //  1. verificarCumplimiento
    public boolean verificarCumplimiento() {
        return valorActual >= valorMeta;
    }


    //  2. obtenerProgresoPorcentaje
    public double obtenerProgresoPorcentaje() {
        if (valorMeta <= 0) return 0;
        double pct = (valorActual / valorMeta) * 100.0;
        return Math.min(Math.round(pct * 100.0) / 100.0, 100.0);
    }

    //  3. calcularDiasRestantes
    public long calcularDiasRestantes() {
        if (fechaFin == null) return Long.MAX_VALUE;
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaFin);
    }

    //  4. actualizarProgreso
    public void actualizarProgreso(double nuevoValor) {
        this.valorActual = nuevoValor;
        if (verificarCumplimiento()) {
            this.estado = "COMPLETADO";
            System.out.println("¡Objetivo completado! (" + tipo + ")");
        }
    }

    //  6. resumen
    public String resumen() {
        long dias = calcularDiasRestantes();
        String diasStr = (dias == Long.MAX_VALUE) ? "sin límite" : dias + " días";
        return String.format("[%s] %s → %.1f / %.1f (%.1f%%) | %s | %s",
                tipo, descripcion,
                valorActual, valorMeta,
                obtenerProgresoPorcentaje(),
                diasStr, estado);
    }


}