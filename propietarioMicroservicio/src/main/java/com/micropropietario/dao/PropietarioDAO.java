/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.micropropietario.dao;

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
import com.micropropietario.modelo.PropietarioDTO;
import com.micropropietario.modelo.TypesPropietario;
import com.micropropietario.utildad.PropietarioHelper;

/**
 *
 * @author Laura
 */
public class PropietarioDAO {
    private static final DatabaseConnection DB = new DatabaseConnection();
    
    public int create(PropietarioDTO propietario) {
            String sql = "INSERT INTO propietario (nombre, apellido, tipo_documento, numero_documento, telefono, correo, contrasena, createdat, id_propietario) VALUES (?, ?, ?::tipo_documento, ?, ?, ?, ?, ?, ?)";
            int rowsAffected = 0;
            try {
                Connection conn = DB.connect();
                PreparedStatement stm = conn.prepareStatement(sql);

                UUID idPropietario = UUID.randomUUID();

                stm.setObject(9, idPropietario, java.sql.Types.OTHER);
                stm.setString(1, propietario.getNombre());
                stm.setString(2, propietario.getApellido());
                stm.setString(3, propietario.getTipoDocumento().name());
                stm.setString(4, propietario.getNumeroDocumento());
                stm.setString(5, propietario.getTelefono());
                stm.setString(6, propietario.getCorreo());
                stm.setString(7, propietario.getContrasena());
                stm.setDate(8, new Date(System.currentTimeMillis()));

              rowsAffected = stm.executeUpdate();
              if (rowsAffected > 0) System.out.println("Propietario almacenado exitosamente"); 

            } catch (Exception e) {
                System.out.println(" "+e.getMessage());
                e.printStackTrace();
            }
           return rowsAffected;
        }
    
    public PropietarioDTO getById(String uid) {
        PropietarioDTO propietario = null;
        
        try {
            String sql = "SELECT * FROM propietario WHERE id_propietario = ?";
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setObject(1, UUID.fromString(uid));
            
            ResultSet result = stm.executeQuery();
            
             if (result.next()) {
                propietario = new PropietarioDTO();
                propietario.setIdPropietario(result.getString("id_propietario"));
                propietario.setNombre(result.getString("nombre"));
                propietario.setApellido(result.getString("apellido"));
                propietario.setCorreo(result.getString("correo"));
                propietario.setContrasena(result.getString("contrasena"));
                propietario.setTelefono(result.getString("telefono"));
                propietario.setNumeroDocumento(result.getString("numero_documento"));
                propietario.setTipoDocumento(TypesPropietario.tipo_documento.valueOf(result.getString("tipo_documento")));
            }
        } catch (Exception e) {
        }
        return propietario;
    }
    
   public List<PropietarioDTO> get() {
        List<PropietarioDTO> propietarios = new ArrayList<>();

         try {
             String sql = "SELECT * FROM propietario";
             Connection conn = DB.connect();
             PreparedStatement stm = conn.prepareStatement(sql);

             ResultSet result = stm.executeQuery();
             System.out.println("llegaste o no pe causa");
             while (result.next()) {
                 PropietarioDTO propietario = new PropietarioDTO();
                 propietario.setIdPropietario(result.getString("id_propietario"));
                 propietario.setNombre(result.getString("nombre"));
                 propietario.setApellido(result.getString("apellido"));
                 propietario.setCorreo(result.getString("correo"));
                 propietario.setContrasena(result.getString("contrasena"));
                 propietario.setTelefono(result.getString("telefono"));
                 propietario.setNumeroDocumento(result.getString("numero_documento"));
                 propietario.setTipoDocumento(TypesPropietario.tipo_documento.valueOf(result.getString("tipo_documento")));

                 propietarios.add(propietario);
             }

         } catch (Exception e) {
             e.printStackTrace();
         }

    return propietarios;
}
    
    public int delete(String uid) {
        int rowsAffected = 0;
        try {
            String sql = "DELETE FROM propietario WHERE id_propietario = ?";
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setObject(1, UUID.fromString(uid));
            
            rowsAffected = stm.executeUpdate();
            
            if (rowsAffected > 0) System.out.println("Propietario eliminado exitosamente"); 

            } catch (Exception e) {
                System.out.println(" "+e.getMessage());
                e.printStackTrace();
            }
        return rowsAffected;
    }
    
    public int update(String id, PropietarioDTO propietario) {
        int rowsAffected = 0;
        
        PropietarioHelper helper = new PropietarioHelper(propietario);
        
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
        
        String sql = "UPDATE propietario SET " + setClause.toString() + " WHERE id_propietario = ?::uuid";

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
    
     public PropietarioDTO findByCorreoAndPassword(String correo, String contrasena) {
        PropietarioDTO propietario = null;
        try {
            String sql = "SELECT * FROM propietario WHERE correo = ? AND contrasena = ?";
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, correo);
            stm.setString(2, contrasena);

            ResultSet result = stm.executeQuery();

            if (result.next()) {
                propietario = new PropietarioDTO();
                propietario.setIdPropietario(result.getString("id_propietario"));
                propietario.setNombre(result.getString("nombre"));
                propietario.setApellido(result.getString("apellido"));
                propietario.setCorreo(result.getString("correo"));
                propietario.setContrasena(result.getString("contrasena"));
                propietario.setTelefono(result.getString("telefono"));
                propietario.setNumeroDocumento(result.getString("numero_documento"));
                propietario.setTipoDocumento(TypesPropietario.tipo_documento.valueOf(result.getString("tipo_documento")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return propietario;
    }
}
