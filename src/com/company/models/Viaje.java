package com.company.models;

public class Viaje extends GeneradorDeMillas implements Canjeable {
    
    private Tramo tramo;
    
    
    
    @Override
    public int getCostoMillas() {
        return 0;
    }
    
    
    @Override
    public int getMillasGeneradas() {
        return this.tramo.getGananciaMillas();
    }
}
