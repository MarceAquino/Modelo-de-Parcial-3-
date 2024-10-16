package model;

import constan.EstadoVehiculo;
import exception.ErrorCodVehiculoException;


public class VehiculoSuv extends Vehiculo {
    private boolean traccionIntegral;
    private static final double INCREMENTO_TRACCION = 1.10; // AUMENTO DEL 10%

    public VehiculoSuv(String codVehiculo, String modelo, EstadoVehiculo estado, boolean traccionIntegral, double precioBase) throws ErrorCodVehiculoException {
        super(codVehiculo, modelo, estado,precioBase);
        this.traccionIntegral = traccionIntegral;
    }

    @Override
    public double calcularCostoReparacion(int horas) {
        double precioFinal = this.precioBase * horas;
        if(isTraccionIntegral()){
            precioFinal *= INCREMENTO_TRACCION;
        }
        return precioFinal;
    }

    private boolean isTraccionIntegral() {
        return traccionIntegral;
    }
    @Override
    public String toString() {
        return String.format("%s\nTracción Integral: %s",
                super.toString(), (traccionIntegral ? "Sí" : "No"));
    }
}
