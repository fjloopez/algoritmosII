package com.company.models;

public class Combustible extends GeneradorDeMillas{
    
    // atributos
    private double litrosCargados;
    
    // constructor
    public Combustible(double litrosCargados, String nombre, EstadoGM estadoGM, int factorMillas) {
        this.litrosCargados = litrosCargados;
        this.setNombre(nombre);
        this.setEstado(estadoGM);
        this.setFactorDeMillas(factorMillas);
    }
    
    // funciones

    /**
     * Devuelve las milllas generadas por el Combustible en funcion de la cantidad de litros cargados y su factor de millas.
     * @return double
     */
    @Override
    public int getMillasGeneradas() {
        return (int)Math.ceil(this.litrosCargados/getFactorDeMillas());
    }
}
