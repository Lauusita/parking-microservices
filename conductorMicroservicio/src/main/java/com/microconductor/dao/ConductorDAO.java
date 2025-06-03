/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.microconductor.dao;

import com.microconductor.modelo.ConductorDTO;
import java.util.List;

/**
 *
 * @author Laura
 */
public interface ConductorDAO {
    List<ConductorDTO> get();

    ConductorDTO getById(String uid);

    int create(ConductorDTO conductor);

    int delete(String uid);

    int update(String id, ConductorDTO campos);

    ConductorDTO findByCorreoAndPassword(String correo, String password);
}
