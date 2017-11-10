package com.company.models;

public class Tramo {
    private Lugar origen, destino;
    private int costoMillas, gananciaMillas;

    public Tramo(Lugar origen, Lugar destino, int costoMillas, int gananciaMillas) {
        this.origen = origen;
        this.destino = destino;
        this.costoMillas = costoMillas;
        this.gananciaMillas = gananciaMillas;
    }

    public Lugar getOrigen() {
        return origen;
    }

    public void setOrigen(Lugar origen) {
        this.origen = origen;
    }

    public Lugar getDestino() {
        return destino;
    }

    public void setDestino(Lugar destino) {
        this.destino = destino;
    }

    public int getCostoMillas() {
        return costoMillas;
    }

    public void setCostoMillas(int costoMillas) {
        this.costoMillas = costoMillas;
    }

    public int getGananciaMillas() {
        return gananciaMillas;
    }

    public void setGananciaMillas(int gananciaMillas) {
        this.gananciaMillas = gananciaMillas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tramo)) return false;

        Tramo that = (Tramo) o;

        if (!origen.equals(that.origen)) return false;
        return destino.equals(that.destino);
    }

    @Override
    public int hashCode() {
        int result = origen.hashCode();
        result = 31 * result + destino.hashCode();
        return result;
    }
}
