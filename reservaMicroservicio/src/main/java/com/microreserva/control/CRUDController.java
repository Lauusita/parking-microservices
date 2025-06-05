/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.microreserva.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microreserva.modelo.ReservaDTO;
import com.microreserva.modelo.RespuestaDTO;
import com.microreserva.servicio.ReservaServicio;
import com.microreserva.utildad.ReservaHelper;
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
            String conductor_id = request.getParameter("conductor_id");

            String parqueadero_id = request.getParameter("parqueadero_id");

            System.out.println("es el de microservicios");
            if (uid != null) {
                ReservaDTO respons = new ReservaServicio().getById(uid);
            
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
            } else if ( parqueadero_id != null) {
                List<ReservaDTO> respons = new ReservaServicio().getByParqueadero(parqueadero_id);
            
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
            } else if (conductor_id != null) {
                List<ReservaDTO> respons = new ReservaServicio().getByConductor(conductor_id);

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
                ReservaDTO reserva = json.fromJson(reader, ReservaDTO.class);

                boolean missingParameters = 
                    isNullOrEmpty(reserva.getNombreParqueadero()) ||
                    reserva.getTipoVehiculo() == null ||
                    reserva.getFechaReserva() == null ||
                    reserva.getHoraInicio() == null ||
                    reserva.getHoraFin() == null ||
                    reserva.getEstado() == null ||
                    reserva.getDisponible() == null ||
                    reserva.getValorEstimado() == null ||
                    reserva.getIdConductorFk() == null ||
                    reserva.getIdParqueaderoFk() == null;

                  
                if (missingParameters) {
                    response.setStatus(400);
                    respuesta = new RespuestaDTO("Parámetros faltantes");
                    jsonResponse = json.toJson(respuesta);
                    response.getWriter().write(jsonResponse);
                    return;
                }

                int creation = new ReservaServicio().create(reserva);

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

//    @Override
//    protected void doDelete (HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//                String jsonResponse;
//                RespuestaDTO respuesta = new RespuestaDTO("Eliminación exitosa");
//
//                String uid = request.getParameter("id");
//                
//                if (uid == null) {
//                    response.setStatus(400);
//                    respuesta = new RespuestaDTO("Se debe proveer un id para actualizar.");
//                }
//                
//                int deleted = new ReservaServicio().delete(uid);
//                
//                if (deleted == 0) {
//                    response.setStatus(500);
//                    respuesta = new RespuestaDTO("No se pudo eliminar");
//                }
//                 
//                jsonResponse = json.toJson(respuesta);
//                response.getWriter().write(jsonResponse);
//
//    }

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
            
            ReservaDTO body = json.fromJson(reader, ReservaDTO.class);
            
            ReservaHelper helper = new ReservaHelper(body);
            
            ReservaDTO newBody = helper.getFilteredCopy();
            
            int updated = new ReservaServicio().update(idPropietario, newBody);

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
            throws ServletException, IOException {;
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
