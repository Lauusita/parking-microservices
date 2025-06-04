package com.microparqueadero.dao;

import com.microparqueadero.modelo.ParqueaderoDTO;
import com.microparqueadero.modelo.TypesParqueadero;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.math.BigInteger;

public class ParqueaderoDAO {
    private static final DatabaseConnection DB = new DatabaseConnection();

    public int create(ParqueaderoDTO parqueadero) {
        String sql = "INSERT INTO parqueadero (id_parqueadero, nombre, direccion, ciudad, numero_celdas, tarifa_hora, horario_atencion, estado, calificacion, departamento, tipos_vehiculos_aceptados, created_at, id_propietario_fk) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = 0;

        try {
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);

            UUID idParqueadero = UUID.randomUUID();
            parqueadero.setIdParqueadero(idParqueadero.toString());

            stm.setObject(1, idParqueadero, java.sql.Types.OTHER);
            stm.setString(2, parqueadero.getNombre());
            stm.setString(3, parqueadero.getDireccion());
            stm.setString(4, parqueadero.getCiudad());
            stm.setInt(5, parqueadero.getNumeroCeldas());
            stm.setInt(6, parqueadero.getTarifaHora());
            stm.setObject(7, parqueadero.getHorarioAtencion());
            stm.setBoolean(8, parqueadero.getEstado());
            stm.setInt(9, parqueadero.getCalificacion());
            stm.setString(10, parqueadero.getDepartamento());
            stm.setObject(11, parqueadero.getTiposVehiculosAceptados());
            stm.setDate(12, new Date(System.currentTimeMillis()));
            stm.setObject(13, UUID.fromString(parqueadero.getIdPropietarioFk().toString()), java.sql.Types.OTHER);

            rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Parqueadero creado exitosamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al crear parqueadero: " + e.getMessage());
            e.printStackTrace();
        }

        return rowsAffected;
    }

    public ParqueaderoDTO getById(String uid) {
        ParqueaderoDTO parqueadero = null;
        String sql = "SELECT * FROM parqueadero WHERE id_parqueadero = ?";

        try {
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setObject(1, UUID.fromString(uid));

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                parqueadero = mapResultToDTO(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parqueadero;
    }

    public List<ParqueaderoDTO> get() {
        List<ParqueaderoDTO> parqueaderos = new ArrayList<>();
        String sql = "SELECT * FROM parqueadero";

        try {
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                parqueaderos.add(mapResultToDTO(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parqueaderos;
    }

    public int delete(String uid) {
        int rowsAffected = 0;
        String sql = "DELETE FROM parqueadero WHERE id_parqueadero = ?";

        try {
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setObject(1, UUID.fromString(uid));
            rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Parqueadero eliminado exitosamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar parqueadero: " + e.getMessage());
            e.printStackTrace();
        }

        return rowsAffected;
    }

    public int update(String idParqueadero, ParqueaderoDTO parqueadero) {
        int rowsAffected = 0;

        String sql = "UPDATE parqueadero SET nombre = ?, direccion = ?, ciudad = ?, numero_celdas = ?, tipo_celdas = ?, tarifa_hora = ?, horario_atencion = ?, estado = ?, calificacion = ?, departamento = ?, tipos_vehiculos_aceptados = ?, id_propietario_fk = ? WHERE id_parqueadero = ?::uuid";

        try {
            Connection conn = DB.connect();
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setString(1, parqueadero.getNombre());
            stm.setString(2, parqueadero.getDireccion());
            stm.setString(3, parqueadero.getCiudad());
            stm.setInt(4, parqueadero.getNumeroCeldas());
            stm.setInt(5, parqueadero.getTarifaHora());
            stm.setObject(6, parqueadero.getHorarioAtencion());
            stm.setBoolean(7, parqueadero.getEstado());
            stm.setInt(8, parqueadero.getCalificacion());
            stm.setString(9, parqueadero.getDepartamento());
            stm.setObject(10, parqueadero.getTiposVehiculosAceptados());
            stm.setDate(11, new Date(System.currentTimeMillis()));
            stm.setObject(12, UUID.fromString(parqueadero.getIdPropietarioFk().toString()), java.sql.Types.OTHER);
            stm.setObject(13, idParqueadero, java.sql.Types.OTHER);

            rowsAffected = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar parqueadero: " + e.getMessage());
            e.printStackTrace();
        }

        return rowsAffected;
    }

    private ParqueaderoDTO mapResultToDTO(ResultSet rs) throws Exception {
        ParqueaderoDTO p = new ParqueaderoDTO();
        p.setIdParqueadero(rs.getString("id_parqueadero"));
        p.setNombre(rs.getString("nombre"));
        p.setDireccion(rs.getString("direccion"));
        p.setCiudad(rs.getString("ciudad"));
        p.setNumeroCeldas(rs.getInt("numero_celdas"));
        p.setTarifaHora(rs.getInt("tarifa_hora"));
        p.setHorarioAtencion(rs.getString("horario_atencion"));
        p.setEstado(rs.getBoolean("estado"));
        p.setCalificacion(rs.getInt("calificacion"));
        p.setDepartamento(rs.getString("departamento"));
        p.setTiposVehiculosAceptados(TypesParqueadero.tipo_vehiculo_aceptado.valueOf(rs.getString("tipos_vehiculos_aceptados"))); 
        p.setCreatedAt(rs.getDate("created_at"));
        p.setIdPropietarioFk(rs.getString("id_propietario_fk"));
        return p;
    }
}
