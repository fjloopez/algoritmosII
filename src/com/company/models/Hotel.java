package com.company.models;

public class Hotel extends GeneradorDeMillas {
    
    private Lugar lugar;
    private String categoria;
    private Double precioPorNoche;
    private int cantNoches;
    
    
    public Hotel(Lugar lugar, String categoria, Double precioPorNoche, int cantNoches, String nombre, EstadoGM estadoGM, int factorMillas) {
        this.lugar = lugar;
        this.categoria = categoria;
        this.precioPorNoche = precioPorNoche;
        this.cantNoches = cantNoches;
        this.setNombre(nombre);
        this.setEstado(estadoGM);
        this.setFactorDeMillas(factorMillas);
    }
    
    
    /**
     * Devuelve las milllas generadas por el Hotel en funcion de la cantidad de noches, el precio por noche y su factor de millas.
     * @return double
     */
    @Override
    public int getMillasGeneradas() {
        return  (int)Math.ceil(this.cantNoches * this.precioPorNoche * this.getFactorDeMillas());
    }
    
    
}
