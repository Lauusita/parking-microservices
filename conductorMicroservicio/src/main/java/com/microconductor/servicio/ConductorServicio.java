/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microconductor.servicio;

import com.microconductor.dao.ConductorDAOPostgres;
import com.microconductor.modelo.ConductorDTO;
import java.util.List;

/**
 *
 * @author rojas
 */
public class ConductorServicio {
    private static final ConductorDAOPostgres prop = new ConductorDAOPostgres();
    
    public List<ConductorDTO> get() {
        return prop.get();
    }
    
    public ConductorDTO getById(String uid) {
        return prop.getById(uid);
    }
    
    public int create(ConductorDTO conductor) {
        return prop.create(conductor);
    }
    
    public int delete(String uid) {
        return prop.delete(uid);
    }
    
    public int update(String id, ConductorDTO campos){
        return prop.update(id, campos);
    }
    
    public ConductorDTO findByMailAndPass(String correo, String password) {
        return prop.findByCorreoAndPassword(correo, password);
    }
}
