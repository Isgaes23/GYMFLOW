package Clases;

public class Serie {
    private int idSerie;
    private int idEntrenamiento;
    private int idEjercicio;
    private int numSerie;
    private int repeticionesFecha;
    private double pesoUsado;

    public Serie() {
    }

    public Serie(int idSerie, int idEntrenamiento, int idEjercicio, int numSerie, int repeticionesFecha, double pesoUsado) {
        this.idSerie = idSerie;
        this.idEntrenamiento = idEntrenamiento;
        this.idEjercicio = idEjercicio;
        this.numSerie = numSerie;
        this.repeticionesFecha = repeticionesFecha;
        this.pesoUsado = pesoUsado;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public int getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public void setIdEntrenamiento(int idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public int getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public int getRepeticionesFecha() {
        return repeticionesFecha;
    }

    public void setRepeticionesFecha(int repeticionesFecha) {
        this.repeticionesFecha = repeticionesFecha;
    }

    public double getPesoUsado() {
        return pesoUsado;
    }

    public void setPesoUsado(double pesoUsado) {
        this.pesoUsado = pesoUsado;
    }
}
