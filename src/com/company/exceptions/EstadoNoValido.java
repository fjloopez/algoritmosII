package com.company.exceptions;

public class EstadoNoValido extends Exception{
    
    public EstadoNoValido() {
    }
    
    public EstadoNoValido(String message) {
        super(message);
    }
}
