package com.example.tino.pregunta3.model;

import android.widget.TextView;

import java.util.ArrayList;

public class Categoria {
    private String nombre;
    private ArrayList<Pregunta> preguntas;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
