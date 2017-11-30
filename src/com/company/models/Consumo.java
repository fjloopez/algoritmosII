package models;

public class Consumo  extends GeneradorDeMillas{
    
    // atributos
    private double costo;
    
    // constructor
    
    
    public Consumo(double costo, String nombre, EstadoGM estadoGM, int factorMillas) {
        this.costo = costo;
        this.setNombre(nombre);
        this.setEstado(estadoGM);
        this.setFactorDeMillas(factorMillas);
    }
    
    // funciones
    
    
    /**
     * Devuelve las milllas generadas por el Consumo en funcion del costo y su factor de millas.
     * @return double
     */
    @Override
    public int getMillasGeneradas() {
        return (int)Math.ceil(this.costo * this.getFactorDeMillas());
    }
}
