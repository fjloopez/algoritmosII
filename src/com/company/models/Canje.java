package models;

public class Canje {
    private Canjeable canjeable;
    private double descuento;

    private int totalMillasConsumidas;

    // La clase no dispone de setter de canjeable ni de descuento por ende se considera inmutable, y el calculo de
    // millas consumidas se realiza una única vez en el constructor.
    public Canje(Canjeable canjeable, double descuento) {
        this.canjeable = canjeable;
        this.descuento = descuento;

        totalMillasConsumidas = canjeable.getCostoMillas() - (int)Math.ceil(canjeable.getCostoMillas() * descuento);
    }

    /**
     * Devuelve el total correspondiente al consumo de millas en base al costo del canjeable menos el descuento.
     * @return
     */
    public int getMillasConsumidas() {
        return totalMillasConsumidas;
    }
}
