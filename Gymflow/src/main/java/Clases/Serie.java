package Clases;

public class Serie {
    private String nombreEjercicio;
    private String tipoEjercicio;
    private int    numSerie;
    private int    repeticionesHechas;
    private double pesoUsado;
    private int    descansoSegundos;
    private double pesoUsuario;

    public Serie() {
    }

    public Serie(String nombreEjercicio, String tipoEjercicio,
                 int numSerie, int repeticionesHechas,
                 double pesoUsado, int descansoSegundos, double pesoUsuario)
    {
        this.nombreEjercicio   = nombreEjercicio;
        this.tipoEjercicio     = tipoEjercicio;
        this.numSerie          = numSerie;
        this.repeticionesHechas = repeticionesHechas;
        this.pesoUsado         = pesoUsado;
        this.descansoSegundos  = descansoSegundos;
        this.pesoUsuario       = pesoUsuario;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getTipoEjercicio() {
        return tipoEjercicio;
    }

    public void setTipoEjercicio(String tipoEjercicio) {
        this.tipoEjercicio = tipoEjercicio;
    }

    public int getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public int getRepeticionesHechas() {
        return repeticionesHechas;
    }

    public void setRepeticionesHechas(int repeticionesHechas) {
        this.repeticionesHechas = repeticionesHechas;
    }

    public double getPesoUsado() {
        return pesoUsado;
    }

    public void setPesoUsado(double pesoUsado) {
        this.pesoUsado = pesoUsado;
    }

    public int getDescansoSegundos() {
        return descansoSegundos;
    }

    public void setDescansoSegundos(int descansoSegundos) {
        this.descansoSegundos = descansoSegundos;
    }

    public double getPesoUsuario() {
        return pesoUsuario;
    }

    public void setPesoUsuario(double pesoUsuario) {
        this.pesoUsuario = pesoUsuario;
    }

    //  1. obtenerVolumen
    public double obtenerVolumen() {
        return pesoUsado * repeticionesHechas;
    }

    //  2. calcularCalorias
    public double calcularCalorias() {
        double met         = obtenerMET();
        double duracionMin = ((repeticionesHechas * 3.0) + descansoSegundos) / 60.0;
        double kcal = met * pesoUsuario * (duracionMin / 60.0);
        return Math.round(kcal * 100.0) / 100.0;
    }

    //  4. resumen
    public String resumen() {
        return String.format("Serie %d | %s | %d reps × %.1f kg = %.1f kg·reps | %.2f kcal",
                numSerie, nombreEjercicio,
                repeticionesHechas, pesoUsado,
                obtenerVolumen(), calcularCalorias());
    }

    // ── obtenerMET  (auxiliar) ─────────────────────────────────
    private double obtenerMET() {
        if (tipoEjercicio == null) return 4.0;
        return switch (tipoEjercicio.toUpperCase()) {
            case "CARDIO"       -> 8.0;
            case "FUERZA"       -> 5.0;
            case "FLEXIBILIDAD" -> 3.0;
            default             -> 4.0;
        };
    }


}