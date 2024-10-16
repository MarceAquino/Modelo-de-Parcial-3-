package model;

import constan.EstadoVehiculo;
import exception.ErrorCodVehiculoException;

import java.time.LocalDate;


public class VehiculoCompacto extends Vehiculo {
    private int anio;
    private static final double DESCUENTO_NUEVO_VEHICULO = 0.95; //DESCUENTO DEL 5%

    public VehiculoCompacto(String codVehiculo, String modelo, EstadoVehiculo estado, int anio , double precioBase) throws ErrorCodVehiculoException {
        super(codVehiculo, modelo, estado , precioBase);
        this.anio = anio;
    }

    @Override
    public double calcularCostoReparacion(int horas) {
        double precioFinal = this.precioBase * horas;
        if(esVehiculoNuevo()){
            precioFinal *= DESCUENTO_NUEVO_VEHICULO;
        }
        return precioFinal;
    }

    public boolean esVehiculoNuevo() {
        int anioActual = LocalDate.now().getYear();
        return this.anio == anioActual;
    }
    @Override
    public String toString() {
        return String.format("%s\nAño: %d",
                super.toString(), anio);
    }
}
