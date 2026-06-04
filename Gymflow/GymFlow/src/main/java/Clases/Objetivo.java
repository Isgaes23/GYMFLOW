package Clases;

public class Objetivo {
    private int idObjetivo;
    private int idUsuario;
    private String tipo;
    private String valorMeta;
    private String valorActual;
    private String estado;

    public Objetivo() {
    }

    public Objetivo(int idObjetivo, int idUsuario, String tipo, String valorMeta, String valorActual, String estado) {
        this.idObjetivo = idObjetivo;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.valorMeta = valorMeta;
        this.valorActual = valorActual;
        this.estado = estado;
    }

    public int getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(int idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(String valorMeta) {
        this.valorMeta = valorMeta;
    }

    public String getValorActual() {
        return valorActual;
    }

    public void setValorActual(String valorActual) {
        this.valorActual = valorActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
