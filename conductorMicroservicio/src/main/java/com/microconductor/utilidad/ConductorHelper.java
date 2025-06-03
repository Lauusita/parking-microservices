/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microconductor.utilidad;

import com.microconductor.modelo.ConductorDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rojas
 */
public class ConductorHelper {
    private final ConductorDTO dto;

    public ConductorHelper(ConductorDTO dto) {
        this.dto = dto;
    }
    
    public ConductorDTO getFilteredCopy() {
        ConductorDTO nuevo = new ConductorDTO();
        
        if (dto.getNombre() != null) {
            nuevo.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null) {
            nuevo.setApellido(dto.getApellido());
        }
        if (dto.getCorreo() != null) {
            nuevo.setCorreo(dto.getCorreo());
        }
        if (dto.getTelefono() != null) {
            nuevo.setTelefono(dto.getTelefono());
        }
        if (dto.getTipoDocumento() != null) {
            nuevo.setTipoDocumento(dto.getTipoDocumento());
        }
        if (dto.getNumeroDocumento() != null) {
            nuevo.setNumeroDocumento(dto.getNumeroDocumento());
        }
        if (dto.getContrasena() != null) {
            nuevo.setContrasena(dto.getContrasena());
        }
        if (dto.getCreatedat() != null) {
            nuevo.setCreatedat(dto.getCreatedat());
        }
        
        return nuevo;
    }

    public List<String> getFieldsList() {
        List<String> listaCampos = new ArrayList<>();
        
        if (dto.getNombre() != null) {
            listaCampos.add("nombre");
        }
        if (dto.getApellido() != null) {
            listaCampos.add("apellido");
        }
        if (dto.getCorreo() != null) {
            listaCampos.add("correo");
        }
        if (dto.getTelefono() != null) {
            listaCampos.add("telefono");
        }
        if (dto.getTipoDocumento() != null) {
            listaCampos.add("tipoDocumento");
        }
        if (dto.getNumeroDocumento() != null) {
            listaCampos.add("numeroDocumento");
        }
        if (dto.getContrasena() != null) {
            listaCampos.add("contrasena");
        }

        return listaCampos;
    }

    public List<Object> getFieldValuesList() {
        List<Object> listaCampos = new ArrayList<>();
        
        if (dto.getNombre() != null) {
            listaCampos.add(dto.getNombre());
        }
        if (dto.getApellido() != null) {
            listaCampos.add(dto.getApellido());
        }
        if (dto.getCorreo() != null) {
            listaCampos.add(dto.getCorreo());
        }
        if (dto.getTelefono() != null) {
            listaCampos.add(dto.getTelefono());
        }
        if (dto.getTipoDocumento() != null) {
            listaCampos.add(dto.getTipoDocumento());
        }
        if (dto.getNumeroDocumento() != null) {
            listaCampos.add(dto.getNumeroDocumento());
        }
        if (dto.getContrasena() != null) {
            listaCampos.add(dto.getContrasena());
        }

        return listaCampos;
    }
}
