package com.example.tino.pregunta3.model;

import java.util.ArrayList;

public class Ruleta {
    private ArrayList<Categoria> categorias;

    public Ruleta(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }
    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }
}
