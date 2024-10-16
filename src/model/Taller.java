package model;

import constan.EstadoVehiculo;
import exception.ErrorCodVehiculoException;
import exception.ErrorEstadoInvalido;

import java.util.ArrayList;
import java.util.List;

public class Taller {
    private String nombreTaller;
    private List<Vehiculo> inventarioVehiculos;
    private List<Vehiculo> vehiculosEnReparacion;

    public Taller(String nombreTaller) {
        this.nombreTaller = nombreTaller;
        this.inventarioVehiculos  = new ArrayList<>();
        this.vehiculosEnReparacion = new ArrayList<>();

    }

    public void agregarVehiculo(Vehiculo vehiculo) throws ErrorCodVehiculoException {
        for (Vehiculo v : this.inventarioVehiculos) {
            if (v.getCodVehiculo().equals(vehiculo.getCodVehiculo())) {
                throw new ErrorCodVehiculoException("El codigo ya se encuentra en el inventario de vehiculos");
            }
        }
        this.inventarioVehiculos.add(vehiculo);
    }

    public double calcularCostoReparacion(String codigoVehiculo, int horas) throws ErrorCodVehiculoException {
        for (Vehiculo v : this.vehiculosEnReparacion) {
            if (v.getCodVehiculo().equals(codigoVehiculo)) {
                return v.calcularCostoReparacion(horas);
            }
        }
        throw new ErrorCodVehiculoException("El vehiculo no se encuentra en reparacion");
    }

    public void iniciarReparacion(String codigoVehiculo, String descripcion) throws ErrorCodVehiculoException, ErrorEstadoInvalido {
        for (Vehiculo v : this.inventarioVehiculos) {
            if (v.getCodVehiculo().equals(codigoVehiculo)){
                if (v.getEstado() == EstadoVehiculo.DISPONIBLE){
                    v.iniciarReparaciones(codigoVehiculo, descripcion);
                    this.vehiculosEnReparacion.add(v);
                    return;
                }else {
                    throw new ErrorEstadoInvalido("El vehiculo ya se encuentra en reparacion");
                }
            }
        }
        throw new ErrorCodVehiculoException("El codigo de vehiculo no se encuentra en el inventario");
    }

    public void finalizarReparacion(String codigoVehiculo) throws ErrorCodVehiculoException, ErrorEstadoInvalido {
        for (Vehiculo v : this.vehiculosEnReparacion) {
            if (v.getCodVehiculo().equals(codigoVehiculo)) {
                v.finalizarReparacion();
                this.vehiculosEnReparacion.remove(v);
                return;
            }
        }
        throw new ErrorCodVehiculoException("El vehiculo no se encuentra en reparacion");
    }


    public List<Vehiculo> traerVehiculosDisponibles() throws ErrorEstadoInvalido {
        List<Vehiculo> disponibles = new ArrayList<>();
        for(Vehiculo v : this.inventarioVehiculos){
            if(v.getEstado() != EstadoVehiculo.EN_REPARACION && v.getEstado() == EstadoVehiculo.DISPONIBLE){
                disponibles.add(v);
            }
        }
        return disponibles;

    }

    public List<Vehiculo> traerVehiculos(EstadoVehiculo estado) {
        List<Vehiculo> listaRetorno = new ArrayList<>();
        for (Vehiculo v : inventarioVehiculos) {
            if (v.getEstado() == estado) {
                listaRetorno.add(v);
            }
        }
        return listaRetorno;
    }
    public List<String> obtenerHistorialReparaciones(String codigoVehiculo) throws ErrorCodVehiculoException {
        for(Vehiculo v : inventarioVehiculos){
            if(v.getCodVehiculo().equals(codigoVehiculo)){
                return v.obtenerHistorialReparaciones();
            }
        }
        throw new ErrorCodVehiculoException("El codigo ingresado no existe en el inventario de vehiculos.");
    }


}
