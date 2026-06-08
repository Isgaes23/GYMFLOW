package Clases;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class Estadisticas {
    private double    pesoInicial;
    private double    pesoActual;
    private double    altura;
    private List<Double> historialPesos;
    private List<LocalDate> fechasEntrenamiento;

    public Estadisticas() {
    }

    public Estadisticas(double pesoInicial, double pesoActual, double altura,
                        List<Double> historialPesos,
                        List<LocalDate> fechasEntrenamiento) {
        this.pesoInicial          = pesoInicial;
        this.pesoActual           = pesoActual;
        this.altura               = altura;
        this.historialPesos       = historialPesos;
        this.fechasEntrenamiento  = fechasEntrenamiento;
    }

    public double getPesoInicial() {
        return pesoInicial;
    }

    public void setPesoInicial(double pesoInicial) {
        this.pesoInicial = pesoInicial;
    }

    public double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public List<Double> getHistorialPesos() {
        return historialPesos;
    }

    public void setHistorialPesos(List<Double> historialPesos) {
        this.historialPesos = historialPesos;
    }

    public List<LocalDate> getFechasEntrenamiento() {
        return fechasEntrenamiento;
    }

    public void setFechasEntrenamiento(List<LocalDate> fechasEntrenamiento) {
        this.fechasEntrenamiento = fechasEntrenamiento;
    }


    //  1. generarEstadisticas
    public void generarEstadisticas() {
        System.out.println(" ESTADÍSTICAS: ");
        System.out.printf("  IMC actual      : %.2f (%s)%n", calcularIMC(),
                categoriaIMC());
        System.out.printf("  Progreso peso   : %+.1f kg%n", calcularProgresoPeso());
        System.out.printf("  Racha actual    : %d días%n", calcularRachaActual());
        System.out.printf("  Entrenamientos  : %d%n", fechasEntrenamiento.size());

    }

    //  2. calcularProgresoPeso
    public double calcularProgresoPeso() {
        return Math.round((pesoActual - pesoInicial) * 10.0) / 10.0;
    }

    //  3. calcularRachaActual
    public int calcularRachaActual() {
        if (fechasEntrenamiento == null || fechasEntrenamiento.isEmpty()) return 0;
        List<LocalDate> ordenadas = fechasEntrenamiento.stream()
                .distinct()
                .sorted((a, b) -> b.compareTo(a))
                .toList();
        int racha    = 0;
        LocalDate esperada = LocalDate.now();
        for (LocalDate f : ordenadas) {
            if (f.equals(esperada) || f.equals(esperada.minusDays(1))) {
                racha++;
                esperada = f.minusDays(1);
            } else break;
        }
        return racha;
    }


    //  5. calcularIMC
    public double calcularIMC() {
        if (pesoActual <= 0 || altura <= 0) return 0;
        double alturaM = altura / 100.0;
        return Math.round((pesoActual / (alturaM * alturaM)) * 100.0) / 100.0;
    }


    //  6. categoriaIMC
    public String categoriaIMC() {
        double imc = calcularIMC();
        if (imc == 0)    return "Sin datos";
        if (imc < 18.5)  return "Bajo peso";
        if (imc < 25.0)  return "Peso normal";
        if (imc < 30.0)  return "Sobrepeso";
        return                   "Obesidad";
    }
}
