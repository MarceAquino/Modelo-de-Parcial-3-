import constan.EstadoVehiculo;
import exception.ErrorCodVehiculoException;
import exception.ErrorEstadoInvalido;
import model.Taller;
import model.Vehiculo;
import model.VehiculoCompacto;
import model.VehiculoSuv;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("***** 1.1 - Creación de Vehículo Compacto *****\n");

        try {
            VehiculoCompacto vehiculo1 = new VehiculoCompacto("ABC123", "Toyota Corolla", EstadoVehiculo.DISPONIBLE, 2024, 2000.0);
        } catch (ErrorCodVehiculoException e) {
            System.out.println("Excepción esperada: " + e.getMessage());
        }

        System.out.println("\n***** 1.2 - Creación y Costo de Reparación *****\n");

        try {
            VehiculoCompacto vehiculo2 = new VehiculoCompacto("XYZ5678", "Honda Civic", EstadoVehiculo.DISPONIBLE, 2023, 2200.0);
            System.out.println("Vehículo creado correctamente: " + vehiculo2);
            System.out.println("\n***** 2.1 - Cálculo de Costo de Reparación *****\n");
            double costoReparacion = vehiculo2.calcularCostoReparacion(5);
            System.out.printf("Costo de reparación (Honda Civic): %.2f%n", costoReparacion);
        } catch (ErrorCodVehiculoException e) {
            System.out.println("Excepción inesperada: " + e.getMessage());
        }

        System.out.println("\n***** 1.3 - Creación de Vehículo SUV *****\n");

        try {
            VehiculoSuv vehiculo3 = new VehiculoSuv("QRS7890", "Chevrolet Tahoe", EstadoVehiculo.DISPONIBLE, false, 4000.0);
            System.out.println("Vehículo creado correctamente: " + vehiculo3);
            System.out.println("\n***** 2.2 - Cálculo de Costo de Reparación *****\n");
            double costoReparacion = vehiculo3.calcularCostoReparacion(3);
            System.out.printf("Costo de reparación (Chevrolet Tahoe): %.2f%n", costoReparacion);
        } catch (ErrorCodVehiculoException e) {
            System.out.println("Excepción inesperada: " + e.getMessage());
        }

        System.out.println("\n***** 3.1 - Taller de Prueba *****\n");
        Taller taller = new Taller("Taller de Prueba");

        try {
            VehiculoCompacto vehiculo4 = new VehiculoCompacto("LMN1111", "Volkswagen Polo", EstadoVehiculo.DISPONIBLE, 2024, 1800.0);
            taller.agregarVehiculo(vehiculo4);
            System.out.println("Vehículo agregado al taller: " + vehiculo4);
        } catch (ErrorCodVehiculoException e) {
            System.out.println("Excepción inesperada: " + e.getMessage());
        }

        try {
            VehiculoSuv vehiculo5 = new VehiculoSuv("OPQ2222", "Toyota RAV4", EstadoVehiculo.EN_REPARACION, true, 3000.0);
            taller.agregarVehiculo(vehiculo5);
            System.out.println("Vehículo agregado al taller: " + vehiculo5);
        } catch (ErrorCodVehiculoException e) {
            System.out.println("Excepción inesperada: " + e.getMessage());
        }

        System.out.println("\n***** 3.2 - Intento de Agregar Vehículo Duplicado *****\n");
        try {
            VehiculoSuv vehiculo6 = new VehiculoSuv("LMN1111", "Ford Explorer", EstadoVehiculo.DISPONIBLE, true, 3500.0);
            taller.agregarVehiculo(vehiculo6);
        } catch (ErrorCodVehiculoException e) {
            System.out.println("Excepción esperada por código duplicado: " + e.getMessage());
        }

        System.out.println("\n***** 4 - Listado de Vehículos *****\n");
        try {
            List<Vehiculo> disponibles = taller.traerVehiculosDisponibles();
            System.out.println("Vehículos disponibles en el taller: " + disponibles);
            System.out.println("\n***** 4.1 - Vehículos en Reparación *****\n");
            List<Vehiculo> enReparacion = taller.traerVehiculos(EstadoVehiculo.EN_REPARACION);
            System.out.println("Vehículos en reparación: " + enReparacion);
        } catch (ErrorEstadoInvalido e) {
            System.out.println("Excepción inesperada: " + e.getMessage());
        }
    }
}
