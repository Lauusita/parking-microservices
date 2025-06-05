/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microreserva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;
import com.microreserva.modelo.ReservaDTO;
import com.microreserva.modelo.TypesReserva;
import com.microreserva.utildad.ReservaHelper;
import java.util.ArrayList;

/**
 *
 * @author Laura
 */
public class ReservaDAO {
    private static final DatabaseConnection DB = new DatabaseConnection();
    
    public int create(ReservaDTO reserva) {
        String sql = "INSERT INTO reserva (nombre_parqueadero, tipo_vehiculo, fecha_reserva, hora_inicio, hora_fin, estado, disponible, valor_estimado, created_at, id_conductor_fk, id_parqueadero_fk, id_reserva) VALUES (?, ?::tipos_vehiculos_aceptados, ?, ?, ?, ?::estado_reserva, ?, ?, ?, ?::UUID, ?::UUID, ?)";
        int rowsAffected = 0;

        try {
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);

            UUID idReserva = UUID.randomUUID();

            java.sql.Time horaInicio = java.sql.Time.valueOf(reserva.getHoraInicio());
            java.sql.Time horaFin = java.sql.Time.valueOf(reserva.getHoraFin());

            stm.setString(1, reserva.getNombreParqueadero());
            stm.setString(2, reserva.getTipoVehiculo().name());
            stm.setDate(3, new java.sql.Date(reserva.getFechaReserva().getTime()));
            stm.setTime(4, horaInicio);
            stm.setTime(5, horaFin);
            stm.setString(6, reserva.getEstado().name());
            stm.setBoolean(7, reserva.getDisponible());
            stm.setObject(8, reserva.getValorEstimado(), java.sql.Types.NUMERIC);
            stm.setTimestamp(9, new java.sql.Timestamp(System.currentTimeMillis()));
            stm.setObject(10, UUID.fromString(reserva.getIdConductorFk().toString()), java.sql.Types.OTHER);
            stm.setObject(11, UUID.fromString(reserva.getIdParqueaderoFk().toString()), java.sql.Types.OTHER);
            stm.setObject(12, idReserva, java.sql.Types.OTHER);

            rowsAffected = stm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reserva almacenada exitosamente");
            }

        } catch (Exception e) {
            System.out.println("Error al insertar la reserva: " + e.getMessage());
            e.printStackTrace();
        }

        return rowsAffected;
    }
    
    public ReservaDTO getById(String uid) {
    ReservaDTO reserva = null;

    try {
        String sql = "SELECT * FROM reserva WHERE id_reserva = ?";
        Connection conn = DB.connect();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setObject(1, UUID.fromString(uid));

        ResultSet result = stm.executeQuery();

        if (result.next()) {
            reserva = mapResultToDTO(result);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return reserva;
}

    public List<ReservaDTO> getParqueaderoByConductor(String id_conductor) {
        List<ReservaDTO> parqueaderos = new ArrayList<>();
        String sql = "SELECT * FROM reserva WHERE id_conductor_fk = ?";
        
        try {
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setObject(1, UUID.fromString(id_conductor));
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                parqueaderos.add(mapResultToDTO(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return parqueaderos;
    }
    
    public List<ReservaDTO> getParqueaderoByParqueadero(String id_parqueadero) {
        List<ReservaDTO> parqueaderos = new ArrayList<>();
        String sql = "SELECT * FROM reserva WHERE id_parqueadero_fk = ?";
        
        try {
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setObject(1, UUID.fromString(id_parqueadero));
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                parqueaderos.add(mapResultToDTO(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return parqueaderos;
    }
    
    public int update(String id, ReservaDTO reserva) {
        int rowsAffected = 0;
        
        ReservaHelper helper = new ReservaHelper(reserva);
        
        List<String> campos = helper.getFieldsList();
        List<Object> valores = helper.getFieldValuesList();

        StringBuilder setClause = new StringBuilder();
        
        for (int i = 0; i < campos.size(); i++) {
            String campo = campos.get(i);
            setClause.append(campo).append(" = ?");
            if (i < campos.size() - 1) {
                setClause.append(", ");
            }    
        }
        
        if (setClause.length() == 0) return 0; 
        
        String sql = "UPDATE reserva SET " + setClause.toString() + " WHERE id_reserva = ?::uuid";

        try {
            Connection conn = DB.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (int i = 0; i < campos.size(); i++) {
                stmt.setObject(i + 1, valores.get(i), java.sql.Types.OTHER);
            }
            stmt.setObject(valores.size() + 1, UUID.fromString(id), java.sql.Types.OTHER);
        
            rowsAffected = stmt.executeUpdate();
            return rowsAffected;
            
        } catch (Exception e) {
        }
        return rowsAffected;
    }
    
    private ReservaDTO mapResultToDTO(ResultSet rs) throws Exception {
        ReservaDTO r = new ReservaDTO();

        r.setIdReserva(rs.getObject("id_reserva"));
        r.setNombreParqueadero(rs.getString("nombre_parqueadero"));
        r.setTipoVehiculo(TypesReserva.tipo_vehiculos_aceptados.valueOf(rs.getString("tipo_vehiculo")));
        r.setFechaReserva(rs.getDate("fecha_reserva"));
        r.setHoraInicio(rs.getTime("hora_inicio").toString());     
        r.setHoraFin(rs.getTime("hora_fin").toString());           
        r.setEstado(TypesReserva.estado_reserva.valueOf(rs.getString("estado")));
        r.setDisponible(rs.getBoolean("disponible"));
        r.setValorEstimado(rs.getBigDecimal("valor_estimado").toBigInteger());
        r.setCreatedAt(rs.getTimestamp("created_at"));  
        r.setIdConductorFk(rs.getObject("id_conductor_fk"));     
        r.setIdParqueaderoFk(rs.getObject("id_parqueadero_fk")); 
        return r;
    }
}
