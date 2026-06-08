package Clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {
    private String contrasena;
    private double peso;
    private double altura;
    private String nivel;

    public Usuario() {
    }

    public Usuario(String contrasena, double peso, double altura, String
            nivel) {
        this.contrasena = contrasena;
        this.peso       = peso;
        this.altura     = altura;
        this.nivel      = nivel;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /* Hascode     */
    public static String hashContrasena(String textoPlano) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(textoPlano.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 no disponible", e);
        }
    }

    //  2. verificarContrasena
    public boolean verificarContrasena(String textoPlano) {
        return this.contrasena.equals(hashContrasena(textoPlano));
    }

    //  3. calcularIMC
    public double calcularIMC() {
        if (peso <= 0 || altura <= 0) return 0;
        double alturaM = altura / 100.0;
        double imc     = peso / (alturaM * alturaM);
        return Math.round(imc * 100.0) / 100.0;
    }

    //  4. categoriaIMC
    public String categoriaIMC() {
        double imc = calcularIMC();
        if (imc == 0)    return "Sin datos";
        if (imc < 18.5)  return "Bajo peso";
        if (imc < 25.0)  return "Peso normal";
        if (imc < 30.0)  return "Sobrepeso";
        return                   "Obesidad";
    }

    //  5. obtenerNivelFitness
    public String obtenerNivelFitness() {
        return (nivel != null && !nivel.isBlank()) ? nivel : "Sin nivel";
    }

    //  6. setContrasenaHash
    public void setContrasenaHash(String textoPlano) {
        this.contrasena = hashContrasena(textoPlano);
    }

}