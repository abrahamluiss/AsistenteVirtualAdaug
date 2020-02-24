package pe.edu.continental.adaug.Clases;

public class Respuestas {
    private String cuestion;
    private String respuestas;

    public Respuestas(String cuestion, String respuestas) {
        this.cuestion = cuestion;
        this.respuestas = respuestas;
    }

    public String getCuestion() {
        return cuestion;
    }

    public String getRespuestas() {
        return respuestas;
    }
}