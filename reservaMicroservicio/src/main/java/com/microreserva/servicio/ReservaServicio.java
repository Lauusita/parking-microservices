/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microreserva.servicio;

import com.microreserva.dao.ReservaDAO;
import com.microreserva.modelo.ReservaDTO;
import java.util.List;

/**
 *
 * @author Laura
 */
public class ReservaServicio {
    private static final ReservaDAO reserva = new ReservaDAO();

    public ReservaDTO getById(String uid) {
        return reserva.getById(uid);
    }
    
    public List<ReservaDTO> getByConductor(String id) {
        return reserva.getParqueaderoByConductor(id);
    }
    
    public List<ReservaDTO> getByParqueadero(String id) {
        return reserva.getParqueaderoByParqueadero(id);
    }
    
    public int create(ReservaDTO reservaietario) {
        return reserva.create(reservaietario);
    }
    
    public int update(String id, ReservaDTO campos){
        return reserva.update(id, campos);
    }
}
