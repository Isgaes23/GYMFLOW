package Clases;

public class Rutina {
    private int idRutina;
    private int idUsuario;
    private String Nombre;
    private String Nivel;
    private int diasSemana;
    private String ejercicio;

    public Rutina() {
    }

    public Rutina(int idRutina, int idUsuario, String nombre, String nivel, int diasSemana, String ejercicio) {
        this.idRutina = idRutina;
        this.idUsuario = idUsuario;
        Nombre = nombre;
        Nivel = nivel;
        this.diasSemana = diasSemana;
        this.ejercicio = ejercicio;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }

    public int getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(int diasSemana) {
        this.diasSemana = diasSemana;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }
}
