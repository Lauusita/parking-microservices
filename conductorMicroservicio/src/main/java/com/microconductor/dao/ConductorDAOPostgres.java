/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microconductor.dao;

import com.microconductor.config.PostgresConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.microconductor.modelo.ConductorDTO;
import com.microconductor.modelo.TypesConductor;
import com.microconductor.utilidad.ConductorHelper;

/**
 *
 * @author rojas
 */
public class ConductorDAOPostgres implements ConductorDAO {
    private static final PostgresConnection DB = new PostgresConnection();
    
    @Override
    public int create(ConductorDTO conductor) {
            String sql = "INSERT INTO conductor (nombre, apellido, tipo_documento, numero_documento, telefono, correo, contrasena, createdat, id_conductor) VALUES (?, ?, ?::tipo_documento, ?, ?, ?, ?, ?, ?)";
            int rowsAffected = 0;
            try {
                Connection conn = DB.connect();
                PreparedStatement stm = conn.prepareStatement(sql);

                UUID idConductor = UUID.randomUUID();

                stm.setObject(9, idConductor, java.sql.Types.OTHER);
                stm.setString(1, conductor.getNombre());
                stm.setString(2, conductor.getApellido());
                stm.setString(3, conductor.getTipoDocumento().name());
                stm.setString(4, conductor.getNumeroDocumento());
                stm.setString(5, conductor.getTelefono());
                stm.setString(6, conductor.getCorreo());
                stm.setString(7, conductor.getContrasena());
                stm.setDate(8, new Date(System.currentTimeMillis()));

              rowsAffected = stm.executeUpdate();
              if (rowsAffected > 0) System.out.println("Conductor almacenado exitosamente"); 

            } catch (Exception e) {
                System.out.println(" "+e.getMessage());
                e.printStackTrace();
            }
           return rowsAffected;
        }
    
    @Override
    public ConductorDTO getById(String uid) {
        ConductorDTO conductor = null;
        
        try {
            String sql = "SELECT * FROM conductor WHERE id_conductor = ?";
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setObject(1, UUID.fromString(uid));
            
            ResultSet result = stm.executeQuery();
            
             if (result.next()) {
                conductor = new ConductorDTO();
                conductor.setIdConductor(result.getString("id_conductor"));
                conductor.setNombre(result.getString("nombre"));
                conductor.setApellido(result.getString("apellido"));
                conductor.setCorreo(result.getString("correo"));
                conductor.setContrasena(result.getString("contrasena"));
                conductor.setTelefono(result.getString("telefono"));
                conductor.setNumeroDocumento(result.getString("numero_documento"));
                conductor.setTipoDocumento(TypesConductor.tipo_documento.valueOf(result.getString("tipo_documento")));
            }
        } catch (Exception e) {
        }
        return conductor;
    }
    
    @Override
   public List<ConductorDTO> get() {
        List<ConductorDTO> conductores = new ArrayList<>();

         try {
             String sql = "SELECT * FROM conductor";
             Connection conn = DB.connect();
             PreparedStatement stm = conn.prepareStatement(sql);

             ResultSet result = stm.executeQuery();

             while (result.next()) {
                 ConductorDTO conductor = new ConductorDTO();
                 conductor.setIdConductor(result.getString("id_conductor"));
                 conductor.setNombre(result.getString("nombre"));
                 conductor.setApellido(result.getString("apellido"));
                 conductor.setCorreo(result.getString("correo"));
                 conductor.setContrasena(result.getString("contrasena"));
                 conductor.setTelefono(result.getString("telefono"));
                 conductor.setNumeroDocumento(result.getString("numero_documento"));
                 conductor.setTipoDocumento(TypesConductor.tipo_documento.valueOf(result.getString("tipo_documento")));

                 conductores.add(conductor);
             }

         } catch (Exception e) {
             e.printStackTrace();
         }

    return conductores;
}
    
    @Override
    public int delete(String uid) {
        int rowsAffected = 0;
        try {
            String sql = "DELETE FROM conductor WHERE id_conductor = ?";
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setObject(1, UUID.fromString(uid));
            
            rowsAffected = stm.executeUpdate();
            
            if (rowsAffected > 0) System.out.println("Condcutor eliminado exitosamente"); 

            } catch (Exception e) {
                System.out.println(" "+e.getMessage());
                e.printStackTrace();
            }
        return rowsAffected;
    }
    
    @Override
    public int update(String id, ConductorDTO conductor) {
        int rowsAffected = 0;
        
        ConductorHelper helper = new ConductorHelper(conductor);
        
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
        
        String sql = "UPDATE conductor SET " + setClause.toString() + " WHERE id_conductor = ?::uuid";

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
    
    @Override
     public ConductorDTO findByCorreoAndPassword(String correo, String contrasena) {
        ConductorDTO conductor = null;
        try {
            String sql = "SELECT * FROM conductor WHERE correo = ? AND contrasena = ?";
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, correo);
            stm.setString(2, contrasena);

            ResultSet result = stm.executeQuery();

            if (result.next()) {
                conductor = new ConductorDTO();
                conductor.setIdConductor(result.getString("id_conductor"));
                conductor.setNombre(result.getString("nombre"));
                conductor.setApellido(result.getString("apellido"));
                conductor.setCorreo(result.getString("correo"));
                conductor.setContrasena(result.getString("contrasena"));
                conductor.setTelefono(result.getString("telefono"));
                conductor.setNumeroDocumento(result.getString("numero_documento"));
                conductor.setTipoDocumento(TypesConductor.tipo_documento.valueOf(result.getString("tipo_documento")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conductor;
    }
}
