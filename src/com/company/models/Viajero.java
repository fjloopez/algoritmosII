package models;
import exceptions.EstadoNoValido;
import exceptions.MillasInsuficientes;

import java.util.*;


public class Viajero {
    // Constantes

    public static final int MILLAS_PARA_FRECUENTE = 10000;

    // Pasajero normal suma 1% cada mil millas sin tope
    public static final int MILLAS_SUMA_PERCENTIL_NORMAL = 1000;
    public static final double PERCENTIL_NORMAL = 0.01;
    public static final double MAX_PERCENTIL_NORMAL = 0.0;

    // Pasajero frecuente suma 2% cada mil millas con tope de 30%
    public static final int MILLAS_SUMA_PERCENTIL_FRECUENTE = 1000;
    public static final double PERCENTIL_FRECUENTE = 0.02;
    public static final double MAX_PERCENTIL_FRECUENTE = 0.3;
    
    // Estados validos para poder agregar al generador de millas
    private Collection<EstadoGM> estadosValidos = new ArrayList<EstadoGM>(){{
        add(EstadoGM.DISPONIBLE);
        add(EstadoGM.TEMPORALMENTE);
    }};
    
    // Propiedades
    private String nombre, DNI;
    private TipoViajero tipo;
    private ArrayList<GeneradorDeMillas> millasGeneradas;
    private ArrayList<Canje> millasCanjeadas;

    // Constructores

    public Viajero() {
    }

    public Viajero(String nombre, String DNI, TipoViajero tipo) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.tipo = tipo;
    }

    // Getters / Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public TipoViajero getTipo() {
        return tipo;
    }

    public void setTipo(TipoViajero tipo) {
        this.tipo = tipo;
    }
    
    // Op Complejas: Acumulación y Canjeo de millas

    /**
     * Devuelve la cantidad de millas sumadas en el tiempo, sin tomar en cuenta canjes
     * @return int
     */
    public int getTotalMillasGeneradas() {
        return millasGeneradas.stream().map(GeneradorDeMillas::getMillasGeneradas).reduce(0, Integer::sum);
    }

    /**
     * Devuelve la cantidad de millas canjeadas en el tiempo
     * @return int
     */
    public int getTotalMillasCanjeadas() {
        return millasCanjeadas.stream().map(Canje::getMillasConsumidas).reduce(0 , Integer::sum);
    }

    /**
     * Devuelve la cantidad de millas disponibles actualmente para ser canjeadas (sumadas - canjeadas)
     * @return int
     */
    public int getMillasDisponibles() {
        int millasDisponibles = getTotalMillasGeneradas() - getTotalMillasCanjeadas();

        assert(millasDisponibles > 0);

        return millasDisponibles;
    }

    /**
     * Registra la operación de suma de millas. Se conserva referencia a la operación de suma de milla, por ende
     * se espera que la misma sea inmutable.
     * @param generador El generador de millas que va a verificar que el Generador tenga un estado valido y luego sumara las millas al Viajero.
     */
    public void sumarMillas(GeneradorDeMillas generador) throws EstadoNoValido {
        if (this.estadosValidos.contains(generador.getEstado())){
            millasGeneradas.add(generador);
        } else{
            throw new EstadoNoValido("El" + generador.getDescripcion() + "no se encuentra disponible");
        }
    }

    /**
     * Registra la operación de consumo o canje de millas.
     * @param canjeable La operación de canjes que va a restar millar al Viajero.
     * @throws IllegalArgumentException Si la cantidad de millas que se pretende canjear es mayor a las disponibles.
     */
    public void canjearMillas(Canjeable canjeable) throws MillasInsuficientes {
        Canje canje = new Canje(canjeable, getDescuento());

        if (getMillasDisponibles() - canje.getMillasConsumidas() >= 0) {
            millasCanjeadas.add(canje);

            // Cuando la cantidad de millas canjeadas historicamente sobrepasan MILLAS_PARA_FRECUENTE se debe cambiar el
            // tipo para reflejar los nuevos descuentos.
            if (tipo != TipoViajero.FRECUENTE && getTotalMillasCanjeadas() >= MILLAS_PARA_FRECUENTE) {
                setTipo(TipoViajero.FRECUENTE);
            }
        }
        else {
            throw new MillasInsuficientes("Millas insuficientes para la operacion");
        }
    }

    // Op Complejas: Calculo de descuentos

    /**
     * Calcula internamente el coeficiente descuento a ser aplicado bajo los parametros del tipo de promocion
     * @param cantidadMillas La cantidad de millas sobre la cual se hace el calculo
     * @param sumaPercentil La cantidad de millas necesarias para sumar una unidad percentil
     * @param percentil El coeficiente, siendo 0.0 = 0%, 0.5 = 50% y 1.0 = 100% de millas a sumar por cada sumaPercentil
     * @param max El coeficiente limite de descuento a generar, siendo 0.0 un indicador de no limite.
     * @return double Coeficiente de descuento a aplicar
     */
    private static double calcularDescuento(int cantidadMillas, int sumaPercentil, double percentil, double max) {
        double descuento = (cantidadMillas / sumaPercentil) * percentil;
        return (max > 0.0) ? Math.min(descuento, max) : descuento;
    }

    /**
     * Obtiene el coeficiente descuento a aplicar en la próxima operación de canje de millas.
     * @return double Coeficiente de próximo descuento a aplicar en relación al tipo de Viajero
     */
    public double getDescuento() {
        switch (tipo) {
            case NORMAL:
                return calcularDescuento(getTotalMillasCanjeadas(), MILLAS_SUMA_PERCENTIL_NORMAL,
                        PERCENTIL_NORMAL, MAX_PERCENTIL_NORMAL);
            case FRECUENTE:
                return calcularDescuento(getTotalMillasCanjeadas(), MILLAS_SUMA_PERCENTIL_FRECUENTE,
                        PERCENTIL_FRECUENTE, MAX_PERCENTIL_FRECUENTE);
        }
        return 0.0;
    }
}
