package Clases;

import java.util.Date;

public class Estadisticas {
    private int idEstadistica;
    private int idUsuario;
    private Date fecha;
    private double imc;
    private int totalEntrenamiento;

    public Estadisticas() {
    }

    public Estadisticas(int idEstadistica, int idUsuario, Date fecha, double imc, int totalEntrenamiento) {
        this.idEstadistica = idEstadistica;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.imc = imc;
        this.totalEntrenamiento = totalEntrenamiento;
    }

    public int getIdEstadistica() {
        return idEstadistica;
    }

    public void setIdEstadistica(int idEstadistica) {
        this.idEstadistica = idEstadistica;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public int getTotalEntrenamiento() {
        return totalEntrenamiento;
    }

    public void setTotalEntrenamiento(int totalEntrenamiento) {
        this.totalEntrenamiento = totalEntrenamiento;
    }
}
