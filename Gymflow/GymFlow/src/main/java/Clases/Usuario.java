package Clases;

public class Usuario {
    private int idUsuario;
    private String Nombre;
    private String Email;
    private double peso;
    private double Altura;
    private String Nivel;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String email, double peso, double altura, String nivel) {
        this.idUsuario = idUsuario;
        Nombre = nombre;
        Email = email;
        this.peso = peso;
        Altura = altura;
        Nivel = nivel;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return Altura;
    }

    public void setAltura(double altura) {
        Altura = altura;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }
}
