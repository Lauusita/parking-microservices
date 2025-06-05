/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.microparqueadero.control;

import com.microparqueadero.modelo.ParqueaderoDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microparqueadero.modelo.RespuestaDTO;
import com.microparqueadero.servicio.ParqueaderoServicio;
import com.microparqueadero.utilidad.ParqueaderoHelper;
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
                ParqueaderoDTO respons = new ParqueaderoServicio().getById(uid);
            
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
                List<ParqueaderoDTO> respons = new ParqueaderoServicio().get();
                
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
                ParqueaderoDTO parq = json.fromJson(reader, ParqueaderoDTO.class);

                boolean missingParameters = 
                    (parq.getNombre() == null || parq.getNombre().isEmpty()) ||
                    (parq.getDireccion() == null || parq.getDireccion().isEmpty()) ||
                    (parq.getCiudad() == null || parq.getCiudad().isEmpty()) ||
                    (parq.getCalificacion() == null) ||
                    (parq.getDepartamento() == null || parq.getDepartamento().isEmpty()) ||
                    (parq.getNumeroCeldas() == null) ||
                    (parq.getTarifaHora() == null) ||
                    (parq.getHorarioAtencion() == null) ||
                    (parq.getEstado() == null) ||
                    (parq.getTiposVehiculosAceptados() == null) ||
                    (parq.getIdPropietarioFk() == null);
                
                if (missingParameters) {
                    response.setStatus(400);
                        respuesta = new RespuestaDTO("Parámetros faltantes");
                        jsonResponse = json.toJson(respuesta);
                        response.getWriter().write(jsonResponse);
                        return;
                } else {
                    int creation = new ParqueaderoServicio().create(parq);
                    
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
                
                int deleted = new ParqueaderoServicio().delete(uid);
                
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
            String idParqueadero = request.getParameter("id");
            
            if (idParqueadero == null) {
                response.setStatus(400);
                respuesta = new RespuestaDTO("Se debe proveer un id para actualizar.");
            }
            
            ParqueaderoDTO body = json.fromJson(reader, ParqueaderoDTO.class);
            
            ParqueaderoHelper helper = new ParqueaderoHelper(body);
            
            ParqueaderoDTO newBody = helper.getFilteredCopy();
            
            int updated = new ParqueaderoServicio().update(idParqueadero, newBody);

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