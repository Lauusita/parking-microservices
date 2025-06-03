/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.micropropietario.control;

import com.micropropietario.modelo.PropietarioDTO;
import com.micropropietario.modelo.TypesPropietario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.micropropietario.modelo.RespuestaDTO;
import com.micropropietario.servicio.PropietarioServicio;
import com.micropropietario.utildad.PropietarioHelper;
import java.io.BufferedReader;
import java.util.List;

/**
 *
 * @author Laura
 */
@WebServlet(name = "CRUDController", urlPatterns = {"/api"})
public class CRUDController extends HttpServlet {

    private final Gson json = new GsonBuilder().setPrettyPrinting().create();
    private final String url = "http://localhost:4200";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String jsonResponse;
            
            response.setHeader("Access-Control-Allow-Origin", this.url);
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            
            String uid = request.getParameter("id");
            System.out.println("es el de microservicios");
            if (uid != null) {
                PropietarioDTO respons = new PropietarioServicio().getById(uid);
            
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
                List<PropietarioDTO> respons = new PropietarioServicio().get();
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
                PropietarioDTO prop = json.fromJson(reader, PropietarioDTO.class);
                
                Boolean isLogin = 
                    (prop.getNombre() == null || prop.getNombre().isEmpty()) &&
                    (prop.getApellido() == null || prop.getApellido().isEmpty()) &&
                    (prop.getTipoDocumento() == null) &&
                    (prop.getNumeroDocumento() == null || prop.getNumeroDocumento().isEmpty()) &&
                    (prop.getTelefono() == null || prop.getTelefono().isEmpty()) &&
                    (prop.getCorreo() != null && !prop.getCorreo().isEmpty()) &&
                    (prop.getContrasena() != null && !prop.getContrasena().isEmpty());

                boolean missingParameters = 
                    (prop.getNombre() == null || prop.getNombre().isEmpty()) ||
                    (prop.getApellido() == null || prop.getApellido().isEmpty()) ||
                    (prop.getTipoDocumento() == null) ||
                    (prop.getNumeroDocumento() == null || prop.getNumeroDocumento().isEmpty()) ||
                    (prop.getTelefono() == null || prop.getTelefono().isEmpty()) ||
                    (prop.getCorreo() == null || prop.getCorreo().isEmpty()) ||
                    (prop.getContrasena() == null || prop.getContrasena().isEmpty());

                if (isLogin) {
                    prop.setCorreo(prop.getCorreo());
                    prop.setContrasena(prop.getContrasena());
                    
                    PropietarioDTO findPropietario = new PropietarioServicio().findByMailAndPass(prop.getCorreo(), prop.getContrasena());
                    
                    if ( findPropietario == null) {
                        response.setStatus(404);
                        respuesta = new RespuestaDTO("datos no encontrados");
                        jsonResponse = json.toJson(respuesta);

                    } else {
                        response.setStatus(200);
                        jsonResponse = json.toJson(findPropietario);
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
                    
                    int creation = new PropietarioServicio().create(prop);

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
                
                int deleted = new PropietarioServicio().delete(uid);
                
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
            String idPropietario = request.getParameter("id");
            
            System.out.println("ase" + idPropietario);
            if (idPropietario == null) {
                response.setStatus(400);
                respuesta = new RespuestaDTO("Se debe proveer un id para actualizar.");
            }
            
            PropietarioDTO body = json.fromJson(reader, PropietarioDTO.class);
            
            PropietarioHelper helper = new PropietarioHelper(body);
            
            PropietarioDTO newBody = helper.getFilteredCopy();
            
            int updated = new PropietarioServicio().update(idPropietario, newBody);

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
