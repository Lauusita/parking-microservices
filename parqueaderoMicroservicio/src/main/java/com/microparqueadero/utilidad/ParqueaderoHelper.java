package com.microparqueadero.utilidad;

import com.microparqueadero.modelo.ParqueaderoDTO;
import java.util.ArrayList;
import java.util.List;

public class ParqueaderoHelper {
    private final ParqueaderoDTO dto;

    public ParqueaderoHelper(ParqueaderoDTO dto) {
        this.dto = dto;
    }

    public ParqueaderoDTO getFilteredCopy() {
        ParqueaderoDTO nuevo = new ParqueaderoDTO();

        if (dto.getNombre() != null) {
            nuevo.setNombre(dto.getNombre());
        }
        if (dto.getDireccion() != null) {
            nuevo.setDireccion(dto.getDireccion());
        }
        if (dto.getCiudad() != null) {
            nuevo.setCiudad(dto.getCiudad());
        }
        if (dto.getNumeroCeldas() != null) {
            nuevo.setNumeroCeldas(dto.getNumeroCeldas());
        }
        if (dto.getTarifaHora() != null) {
            nuevo.setTarifaHora(dto.getTarifaHora());
        }
        if (dto.getEstado() != null) {
            nuevo.setEstado(dto.getEstado());
        }
        if (dto.getCalificacion() != null) {
            nuevo.setCalificacion(dto.getCalificacion());
        }
        if (dto.getDepartamento() != null) {
            nuevo.setDepartamento(dto.getDepartamento());
        }
        if (dto.getTiposVehiculosAceptados() != null) {
            nuevo.setTiposVehiculosAceptados(dto.getTiposVehiculosAceptados());
        }
        if (dto.getCreatedAt() != null) {
            nuevo.setCreatedAt(dto.getCreatedAt());
        }

        return nuevo;
    }

    public List<String> getFieldsList() {
        List<String> listaCampos = new ArrayList<>();

        if (dto.getNombre() != null) {
            listaCampos.add("nombre");
        }
        if (dto.getDireccion() != null) {
            listaCampos.add("direccion");
        }
        if (dto.getCiudad() != null) {
            listaCampos.add("ciudad");
        }
        if (dto.getNumeroCeldas() != null) {
            listaCampos.add("numeroCeldas");
        }
        if (dto.getTarifaHora() != null) {
            listaCampos.add("tarifaHora");
        }
        if (dto.getEstado() != null) {
            listaCampos.add("estado");
        }
        if (dto.getCalificacion() != null) {
            listaCampos.add("calificacion");
        }
        if (dto.getDepartamento() != null) {
            listaCampos.add("departamento");
        }
        if (dto.getTiposVehiculosAceptados() != null) {
            listaCampos.add("tiposVehiculosAceptados");
        }
        if (dto.getCreatedAt() != null) {
            listaCampos.add("createdAt");
        }

        return listaCampos;
    }

    public List<Object> getFieldValuesList() {
        List<Object> listaCampos = new ArrayList<>();

        if (dto.getNombre() != null) {
            listaCampos.add(dto.getNombre());
        }
        if (dto.getDireccion() != null) {
            listaCampos.add(dto.getDireccion());
        }
        if (dto.getCiudad() != null) {
            listaCampos.add(dto.getCiudad());
        }
        if (dto.getNumeroCeldas() != null) {
            listaCampos.add(dto.getNumeroCeldas());
        }
        if (dto.getTarifaHora() != null) {
            listaCampos.add(dto.getTarifaHora());
        }
        if (dto.getEstado() != null) {
            listaCampos.add(dto.getEstado());
        }
        if (dto.getCalificacion() != null) {
            listaCampos.add(dto.getCalificacion());
        }
        if (dto.getDepartamento() != null) {
            listaCampos.add(dto.getDepartamento());
        }
        if (dto.getTiposVehiculosAceptados() != null) {
            listaCampos.add(dto.getTiposVehiculosAceptados());
        }
        if (dto.getCreatedAt() != null) {
            listaCampos.add(dto.getCreatedAt());
        }

        return listaCampos;
    }
}