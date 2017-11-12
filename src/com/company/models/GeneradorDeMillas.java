package com.company.models;

public abstract class GeneradorDeMillas {
    //atributos
    private int id;
    private String Nombre;
    private String Descripcion;
    private EstadoGM estado;
    private double factorDeMillas;
    
    
    // Getters y Setters
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return Nombre;
    }
    
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    
    public String getDescripcion() {
        return Descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    
    public EstadoGM getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoGM estado) {
        this.estado = estado;
    }
    
    public double getFactorDeMillas() {
        return factorDeMillas;
    }
    
    public void setFactorDeMillas(double factorDeMillas) {
        this.factorDeMillas = factorDeMillas;
    }
    
    // operaciones
    public abstract int getMillasGeneradas();
}
