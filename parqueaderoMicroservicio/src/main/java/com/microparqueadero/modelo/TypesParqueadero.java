package com.microparqueadero.modelo;

public class TypesParqueadero {

    public enum tipo_vehiculo_aceptado {
        CARRO,
        MOTO,
        CAMION,
        BICICLETA
    }

    public enum estado_parqueadero {
        DISPONIBLE,
        OCUPADO,
        FUERA_SERVICIO
    }

    public enum tipo_parqueadero {
        CUBIERTO,
        DESCUBIERTO
    }

}
