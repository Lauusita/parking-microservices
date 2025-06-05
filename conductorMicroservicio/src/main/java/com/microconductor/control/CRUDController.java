/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.microconductor.control;

import com.microconductor.modelo.ConductorDTO;
import com.microconductor.modelo.TypesConductor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microconductor.dao.ConductorDAO;
import com.microconductor.dao.ConductorDAOFactory;
import com.microconductor.modelo.RespuestaDTO;
import com.microconductor.utilidad.ConductorHelper;
import java.io.BufferedReader;
import java.util.List;

/**
 *
 * @author rojas
 */
@WebServlet(name = "CRUDController", urlPatterns = {"/api"})
public class CRUDController extends HttpServlet {

    private final Gson json = new GsonBuilder().setPrettyPrinting().create();
    private final String url = "http://localhost:4200";
    
    protected ConductorDAO dao() {   
        ConductorDAO dao = ConductorDAOFactory.getDAO("firebase");
        return dao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String jsonResponse;
            
            response.setHeader("Access-Control-Allow-Origin", this.url);
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            
            String uid = request.getParameter("id");
            
            if (uid != null) {
                ConductorDTO respons = dao().getById(uid);
            
                if ( respons == null ) {
                    response.setStatus(400);
                    RespuestaDTO respuesta = new RespuestaDTO("Datos no encontrados");
                    jsonResponse = json.toJson(respuesta);
                    response.getWriter().write(jsonResponse);

                } else {
                    jsonResponse = json.toJson(respons);
                    response.setStatus(200);
                    response.getWriter().write(jsonResponse);
                }
            } else {
                List<ConductorDTO> respons = dao().get();
                if ( respons.isEmpty() ) {
                    response.setStatus(400);
                    RespuestaDTO respuesta = new RespuestaDTO("Datos no encontrados");
                    jsonResponse = json.toJson(respuesta);
                    response.getWriter().write(jsonResponse);

                } else {
                    jsonResponse = json.toJson(respons);
                    response.setStatus(200);
                    response.getWriter().write(jsonResponse);
                }
            }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String jsonResponse;
                RespuestaDTO respuesta;
                
                response.setHeader("Access-Control-Allow-Origin", this.url); // o dominio específico
                response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                
                BufferedReader reader = request.getReader();
                ConductorDTO cond = json.fromJson(reader, ConductorDTO.class);
                
                Boolean isLogin = 
                    (cond.getNombre() == null || cond.getNombre().isEmpty()) &&
                    (cond.getApellido() == null || cond.getApellido().isEmpty()) &&
                    (cond.getTipoDocumento() == null) &&
                    (cond.getNumeroDocumento() == null || cond.getNumeroDocumento().isEmpty()) &&
                    (cond.getTelefono() == null || cond.getTelefono().isEmpty()) &&
                    (cond.getCorreo() != null && !cond.getCorreo().isEmpty()) &&
                    (cond.getContrasena() != null && !cond.getContrasena().isEmpty());

                boolean missingParameters = 
                    (cond.getNombre() == null || cond.getNombre().isEmpty()) ||
                    (cond.getApellido() == null || cond.getApellido().isEmpty()) ||
                    (cond.getTipoDocumento() == null) ||
                    (cond.getNumeroDocumento() == null || cond.getNumeroDocumento().isEmpty()) ||
                    (cond.getTelefono() == null || cond.getTelefono().isEmpty()) ||
                    (cond.getCorreo() == null || cond.getCorreo().isEmpty()) ||
                    (cond.getContrasena() == null || cond.getContrasena().isEmpty());

                if (isLogin) {
                    cond.setCorreo(cond.getCorreo());
                    cond.setContrasena(cond.getContrasena());
                    
                    ConductorDTO findConductor = dao().findByCorreoAndPassword(cond.getCorreo(), cond.getContrasena());
                    
                    if ( findConductor == null) {
                        response.setStatus(404);
                        respuesta = new RespuestaDTO("datos no encontrados");
                        jsonResponse = json.toJson(respuesta);

                    } else {
                        response.setStatus(200);
                        jsonResponse = json.toJson(findConductor);
                    }
                    
                    response.getWriter().write(jsonResponse);
                    
                } else {   
                    if (missingParameters) {
                        response.setStatus(400);
                        respuesta = new RespuestaDTO("Parámetros faltantes");
                        jsonResponse = json.toJson(respuesta);
                        response.getWriter().write(jsonResponse);
                        return;
                    }
                    
                    int creation = dao().create(cond);

                    if ( creation == 0 ) {
                        response.setStatus(404);
                        respuesta = new RespuestaDTO("creación fallida");
                        jsonResponse = json.toJson(respuesta);

                    } else  {
                        respuesta = new RespuestaDTO("Creación exitosa");
                        jsonResponse = json.toJson(respuesta);
                    }
                    
                    response.getWriter().write(jsonResponse);
                }
    }

    @Override
    protected void doDelete (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String jsonResponse;
                RespuestaDTO respuesta = new RespuestaDTO("Eliminación exitosa");

                String uid = request.getParameter("id");
                
                if (uid == null) {
                    response.setStatus(400);
                    respuesta = new RespuestaDTO("Se debe proveer un id para actualizar.");
                }
                
                int deleted = dao().delete(uid);
                System.out.println(deleted);
                if (deleted == 0) {
                    response.setStatus(500);
                    respuesta = new RespuestaDTO("No se pudo eliminar");
                }
                 
                jsonResponse = json.toJson(respuesta);
                response.getWriter().write(jsonResponse);

    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String jsonResponse;
           
            RespuestaDTO respuesta = new RespuestaDTO("Actualizacion exitosa");
            
            BufferedReader reader = request.getReader();
            String idConductor = request.getParameter("id");
            
            System.out.println("ase" + idConductor);
            if (idConductor == null) {
                response.setStatus(400);
                respuesta = new RespuestaDTO("Se debe proveer un id para actualizar.");
            }
            
            ConductorDTO body = json.fromJson(reader, ConductorDTO.class);
            
            ConductorHelper helper = new ConductorHelper(body);
            
            ConductorDTO newBody = helper.getFilteredCopy();
            
            int updated = dao().update(idConductor, newBody);

            if (updated == 0) {
                response.setStatus(500);
                respuesta = new RespuestaDTO("No se pudo actualizar");
            } 
            
            jsonResponse = json.toJson(respuesta);
            response.getWriter().write(jsonResponse);
    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("RECIBIDOOOOOO LPM");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}