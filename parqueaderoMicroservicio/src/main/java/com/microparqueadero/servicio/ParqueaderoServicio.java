/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microparqueadero.servicio;

import com.microparqueadero.dao.ParqueaderoDAO;
import com.microparqueadero.modelo.ParqueaderoDTO;
import java.util.List;

/**
 *
 * @author rojas
 */
public class ParqueaderoServicio {
     private static final ParqueaderoDAO parq = new ParqueaderoDAO();
    
    public List<ParqueaderoDTO> get() {
        return parq.get();
    }
    
    public ParqueaderoDTO getById(String uid) {
        return parq.getById(uid);
    }
    
    public int create(ParqueaderoDTO parqueadero) {
        return parq.create(parqueadero);
    }
    
    public int delete(String uid) {
        return parq.delete(uid);
    }
    
    public int update(String id, ParqueaderoDTO campos){
        return parq.update(id, campos);
    }
}
