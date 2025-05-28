/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.micropropietario.servicio;

import com.micropropietario.dao.PropietarioDAO;
import com.micropropietario.modelo.PropietarioDTO;
import java.util.List;

/**
 *
 * @author Laura
 */
public class PropietarioServicio {
    private static final PropietarioDAO prop = new PropietarioDAO();
    
    public List<PropietarioDTO> get() {
        return prop.get();
    }
    
    public PropietarioDTO getById(String uid) {
        return prop.getById(uid);
    }
    
    public int create(PropietarioDTO propietario) {
        return prop.create(propietario);
    }
    
    public int delete(String uid) {
        return prop.delete(uid);
    }
    
    public int update(String id, PropietarioDTO campos){
        return prop.update(id, campos);
    }
    
    public PropietarioDTO findByMailAndPass(String correo, String password) {
        return prop.findByCorreoAndPassword(correo, password);
    }
}
