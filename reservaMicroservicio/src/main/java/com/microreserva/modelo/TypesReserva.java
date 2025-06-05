/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microreserva.modelo;

/**
 *
 * @author Laura
 */
public class TypesReserva {
    
    public enum estado_reserva {
        RESERVADA, 
        CANCELADA,
        COMPLETADA
    }
    
    public enum tipo_vehiculos_aceptados {
        CARRO,
        MOTO,
        BUS,
        BICICLETA,
        CAMION
    }
}
