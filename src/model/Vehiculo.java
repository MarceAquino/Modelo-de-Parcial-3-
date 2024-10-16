package model;

import constan.EstadoVehiculo;
import exception.ErrorCodVehiculoException;
import exception.ErrorEstadoInvalido;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Vehiculo {
    protected String codVehiculo;
    protected String modelo;
    protected EstadoVehiculo estado;
    protected double precioBase;
    protected List<String> historialReparaciones = new ArrayList<>();
    protected static final Set<String> codigosVehiculos = new HashSet<>();

    protected Vehiculo(String codVehiculo, String modelo, EstadoVehiculo estado , double precioBase) throws ErrorCodVehiculoException {
        validarCodigoVehiculo(codVehiculo);
        this.modelo = modelo;
        this.estado = estado;
        this.codVehiculo = codVehiculo;
        this.precioBase = precioBase;

    }

    private void validarCodigoVehiculo(String codVehiculo) throws ErrorCodVehiculoException {
        if (codVehiculo == null || codVehiculo.length() != 7) {
            throw new ErrorCodVehiculoException("El código del vehículo debe tener 7 caracteres.");
        }
        if (!codigosVehiculos.add(codVehiculo)) {
            throw new ErrorCodVehiculoException("El código ya se encuentra registrado.");
        }
    }
    private void validarEstadoReparacion(EstadoVehiculo estadoEsperado, String mensajeError) throws ErrorEstadoInvalido {
        if (this.estado != estadoEsperado) {
            throw new ErrorEstadoInvalido(mensajeError);
        }
    }

    public void iniciarReparaciones(String nombreTaller, String descripcion) throws ErrorEstadoInvalido {
        validarEstadoReparacion(EstadoVehiculo.DISPONIBLE, "El vehiculo ya se encuentra en reparación");
        this.estado = EstadoVehiculo.EN_REPARACION;
        LocalDateTime fechaActual = LocalDateTime.now();
        String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String mensaje = fechaFormateada + "  " + nombreTaller + "  " + descripcion;
        this.historialReparaciones.add(mensaje);
        //  Borrar al final.
        System.out.println(mensaje);

    }

    public void finalizarReparacion() throws ErrorEstadoInvalido {
        validarEstadoReparacion(EstadoVehiculo.EN_REPARACION, "El vehículo no está en reparación actualmente.");
        this.estado = EstadoVehiculo.DISPONIBLE;
        LocalDateTime fechaActual = LocalDateTime.now();
        String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String mensaje = fechaFormateada + "Fin de la reparación.";
        this.historialReparaciones.add(mensaje);
        // Borrar al final.
        System.out.println(mensaje);
    }

    public List<String> obtenerHistorialReparaciones() {
        return new ArrayList<>(historialReparaciones);
    }

    public abstract double calcularCostoReparacion(int horas);

    public String getCodVehiculo() {
        return codVehiculo;
    }

    public EstadoVehiculo getEstado() {
        return estado;
    }
    @Override
    public String toString() {
        return String.format("\nModelo: %s\nEstado: %s\nPrecio Base: %.2f",
                modelo, estado, precioBase);
    }
}
