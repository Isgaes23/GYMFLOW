package Clases;

public class Ejercicio {
    private int idEntrenamiento;
    private String Nombre;
    private String grupoMuscular;
    private String tipo;
    private String nivel;

    public Ejercicio() {
    }

    public Ejercicio(int idEntrenamiento, String nombre, String grupoMuscular, String tipo, String nivel) {
        this.idEntrenamiento = idEntrenamiento;
        Nombre = nombre;
        this.grupoMuscular = grupoMuscular;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public int getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public void setIdEntrenamiento(int idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
