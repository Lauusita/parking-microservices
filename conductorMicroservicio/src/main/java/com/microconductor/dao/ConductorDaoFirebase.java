/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microconductor.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.microconductor.modelo.ConductorDTO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Laura
 */
public class ConductorDaoFirebase implements ConductorDAO {
    public static final Firestore db = FirestoreClient.getFirestore();
    
    @Override
    public int create(ConductorDTO con) {
        try {
            CollectionReference collection = db.collection("Conductor");
            
            String idConductor = UUID.randomUUID().toString();
            
            con.setIdConductor(idConductor);
          
            String createdAt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());
            con.setCreatedat(createdAt);
            
            collection.document(idConductor).set(con);
            
            System.out.println("Conductor guardado correctamente en Firebase");
            return 1;
        } catch (Exception e) {
            System.err.println("Error al guardar conductor: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ConductorDTO> get() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ConductorDTO getById(String uid) {
        ConductorDTO conductor = null;
        try {
            CollectionReference collection = db.collection("Conductor");
            
            DocumentSnapshot snapshot = collection.document(uid).get().get();
            
            if (snapshot.exists()) {
                conductor = snapshot.toObject(ConductorDTO.class);
            }
        } catch (Exception e) {
        }
        return conductor;
    }

    @Override
    public int delete(String uid) {
        int deleted = 0;
        try {
            CollectionReference collection = db.collection("Conductor");
            
            DocumentSnapshot snapshot = (DocumentSnapshot) collection.document(uid).get();
            
            if (snapshot.exists()) {
                ApiFuture<WriteResult> future = collection.document(uid).delete();
                Boolean result = future.isDone();
                if ( result == true ) deleted = 1;
            }
        } catch (Exception e) {
        }
        return deleted;
    }

    @Override
    public int update(String uid, ConductorDTO campos) {
        int updated = 0;
        try {
            CollectionReference collection = db.collection("Conductor");
            
            DocumentSnapshot snapshot = collection.document(uid).get().get();
            
            if (!snapshot.exists()) {
                return updated;
            }
            
            Map<String, Object> updates = new HashMap<>();
            // Solo agregamos los campos que NO son null
            if (campos.getNombre() != null) updates.put("nombre", campos.getNombre());
            if (campos.getApellido() != null) updates.put("apellido", campos.getApellido());
            if (campos.getTipoDocumento() != null) updates.put("tipoDocumento", campos.getTipoDocumento());
            if (campos.getNumeroDocumento() != null) updates.put("numeroDocumento", campos.getNumeroDocumento());
            if (campos.getTelefono() != null) updates.put("telefono", campos.getTelefono());
            if (campos.getCorreo() != null) updates.put("correo", campos.getCorreo());
            if (campos.getContrasena() != null) updates.put("contrasena", campos.getContrasena());

            if (updates.isEmpty()) {
                System.out.println("No hay campos para actualizar.");
                return updated;
            }

            ApiFuture<?> future = db.collection("Conductor").document(uid).update(updates);
            future.get(); // espera a que la operación se complete
        } catch (Exception e) {
        }
        return updated;
    }

    @Override
    public ConductorDTO findByCorreoAndPassword(String correo, String password) {
        ConductorDTO conductor = null;
        
        CollectionReference collection = db.collection("Conductor");
        
        Query query = collection
                .whereEqualTo("correo", correo)
                .whereEqualTo("contrasena", password);
        
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documents;
        
        try {
            documents = future.get().getDocuments();
            
            if (!documents.isEmpty()) {
                conductor = (ConductorDTO) documents.get(0).toObject(ConductorDTO.class);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ConductorDaoFirebase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ConductorDaoFirebase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conductor;
    }
}
