/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microreserva.utildad;

import com.microreserva.modelo.ReservaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laura
 */

public class ReservaHelper {

    private final ReservaDTO dto;

    public ReservaHelper(ReservaDTO dto) {
        this.dto = dto;
    }

    public ReservaDTO getFilteredCopy() {
        ReservaDTO nueva = new ReservaDTO();

        if (dto.getIdReserva() != null) {
            nueva.setIdReserva(dto.getIdReserva());
        }
        if (dto.getNombreParqueadero() != null) {
            nueva.setNombreParqueadero(dto.getNombreParqueadero());
        }
        if (dto.getTipoVehiculo() != null) {
            nueva.setTipoVehiculo(dto.getTipoVehiculo());
        }
        if (dto.getFechaReserva() != null) {
            nueva.setFechaReserva(dto.getFechaReserva());
        }
        if (dto.getHoraInicio() != null) {
            nueva.setHoraInicio(dto.getHoraInicio());
        }
        if (dto.getHoraFin() != null) {
            nueva.setHoraFin(dto.getHoraFin());
        }
        if (dto.getEstado() != null) {
            nueva.setEstado(dto.getEstado());
        }
        if (dto.getDisponible() != null) {
            nueva.setDisponible(dto.getDisponible());
        }
        if (dto.getValorEstimado() != null) {
            nueva.setValorEstimado(dto.getValorEstimado());
        }
        if (dto.getCreatedAt() != null) {
            nueva.setCreatedAt(dto.getCreatedAt());
        }
        if (dto.getIdConductorFk() != null) {
            nueva.setIdConductorFk(dto.getIdConductorFk());
        }
        if (dto.getIdParqueaderoFk() != null) {
            nueva.setIdParqueaderoFk(dto.getIdParqueaderoFk());
        }

        return nueva;
    }

    public List<String> getFieldsList() {
        List<String> listaCampos = new ArrayList<>();

        if (dto.getIdReserva() != null) listaCampos.add("id_reserva");
        if (dto.getNombreParqueadero() != null) listaCampos.add("nombre_parqueadero");
        if (dto.getTipoVehiculo() != null) listaCampos.add("tipo_vehiculo");
        if (dto.getFechaReserva() != null) listaCampos.add("fecha_reserva");
        if (dto.getHoraInicio() != null) listaCampos.add("hora_inicio");
        if (dto.getHoraFin() != null) listaCampos.add("hora_fin");
        if (dto.getEstado() != null) listaCampos.add("estado");
        if (dto.getDisponible() != null) listaCampos.add("disponible");
        if (dto.getValorEstimado() != null) listaCampos.add("valor_estimado");
        if (dto.getCreatedAt() != null) listaCampos.add("created_at");
        if (dto.getIdConductorFk() != null) listaCampos.add("id_conductor_fk");
        if (dto.getIdParqueaderoFk() != null) listaCampos.add("id_parqueadero_fk");

        return listaCampos;
    }

    public List<Object> getFieldValuesList() {
        List<Object> listaValores = new ArrayList<>();

        if (dto.getIdReserva() != null) listaValores.add(dto.getIdReserva());
        if (dto.getNombreParqueadero() != null) listaValores.add(dto.getNombreParqueadero());
        if (dto.getTipoVehiculo() != null) listaValores.add(dto.getTipoVehiculo());
        if (dto.getFechaReserva() != null) listaValores.add(dto.getFechaReserva());
        if (dto.getHoraInicio() != null) listaValores.add(dto.getHoraInicio());
        if (dto.getHoraFin() != null) listaValores.add(dto.getHoraFin());
        if (dto.getEstado() != null) listaValores.add(dto.getEstado());
        if (dto.getDisponible() != null) listaValores.add(dto.getDisponible());
        if (dto.getValorEstimado() != null) listaValores.add(dto.getValorEstimado());
        if (dto.getCreatedAt() != null) listaValores.add(dto.getCreatedAt());
        if (dto.getIdConductorFk() != null) listaValores.add(dto.getIdConductorFk());
        if (dto.getIdParqueaderoFk() != null) listaValores.add(dto.getIdParqueaderoFk());

        return listaValores;
    }
}
