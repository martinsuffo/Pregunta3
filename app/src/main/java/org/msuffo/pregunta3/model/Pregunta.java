package org.msuffo.pregunta3.model;

public class Pregunta {
    private String pregunta;
    private String rCorrecta;
    private String rIncorrecta1;
    private String rIncorrecta2;
    private String rIncorrecta3;

    public Pregunta(String pregunta, String rCorrecta, String rIncorrecta1, String rIncorrecta2, String rIncorrecta3){
        this.pregunta     = pregunta;
        this.rCorrecta    = rCorrecta;
        this.rIncorrecta1 = rIncorrecta1;
        this.rIncorrecta2 = rIncorrecta2;
        this.rIncorrecta3 = rIncorrecta3;
    }

    public String getPregunta() {
        return pregunta;
    }
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    public String getrCorrecta() {
        return rCorrecta;
    }
    public void setrCorrecta(String rCorrecta) {
        this.rCorrecta = rCorrecta;
    }
    public String getrIncorrecta1() {
        return rIncorrecta1;
    }
    public void setrIncorrecta1(String rIncorrecta1) {
        this.rIncorrecta1 = rIncorrecta1;
    }
    public String getrIncorrecta2() {
        return rIncorrecta2;
    }
    public void setrIncorrecta2(String rIncorrecta2) {
        this.rIncorrecta2 = rIncorrecta2;
    }
    public String getrIncorrecta3() {
        return rIncorrecta3;
    }
    public void setrIncorrecta3(String rIncorrecta3) {
        this.rIncorrecta3 = rIncorrecta3;
    }
}
