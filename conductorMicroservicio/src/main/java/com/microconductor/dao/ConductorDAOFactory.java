/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microconductor.dao;

/**
 *
 * @author Laura
 */
public class ConductorDAOFactory {

    public static ConductorDAO getDAO(String tipo) {
        if (tipo.equalsIgnoreCase("postgres")) {
            return new ConductorDAOPostgres();
        } else if (tipo.equalsIgnoreCase("firebase")) {
            System.out.println("llegas?");
            return new ConductorDaoFirebase();
        } else {
            throw new IllegalArgumentException("Tipo de DAO no soportado: " + tipo);
        }
    }
}
