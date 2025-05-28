
package com.gateway.control;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@WebServlet(name = "Gateway", urlPatterns = {"/gateway/*"})
public class Gateway extends HttpServlet {

     private final HttpClient client = HttpClient.newHttpClient();
     private final String globalUrl = "http://localhost:8080";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        headersConfig(response);
        
        String path = request.getRequestURI().replace("/gateway", "");
        
        if (path == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            HashMap<String, Object> res = new HashMap<>(); 
            res.put("msg", "Ruta inválida");
            
            Gson json = new Gson();
            
            String resJson = json.toJson(res);
            response.getWriter().write(resJson);
            return;
        }
        
        String url = this.globalUrl + path;
        
        if (path.contains("/propietario")) {
            String id = request.getParameter("id");
            
            if (id != null) {
                url = url + "?id=" + id;
            }

            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            try {
                HttpResponse<String> res = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                
                response.setStatus(res.statusCode());
                response.setContentType("application/json"); 
                response.getWriter().write(res.body()); 

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la conexión con el microservicio.");
            }
        } else if (path.contains("/conductor")) {
            String id = request.getParameter("id");
            
            if (id != null) {
                url = url + "?id=" + id;
            }

            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            try {
                HttpResponse<String> res = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                
                response.setStatus(res.statusCode());
                response.setContentType("application/json"); 
                response.getWriter().write(res.body()); 

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la conexión con el microservicio.");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        headersConfig(response);

        String path = request.getRequestURI().replace("/gateway", "");
        
        if (path == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            HashMap<String, Object> res = new HashMap<>(); 
            res.put("msg", "Ruta inválida");
            
            Gson json = new Gson();
            
            String resJson = json.toJson(res);
            response.getWriter().write(resJson);
            return;
        }
        
        String url = this.globalUrl + path;
        
        String body = request.getReader().lines().collect(Collectors.joining());
        
        if (path.contains("/propietario")) {
            String id = request.getParameter("id");
            
            if (id != null) {
                url = url + "?id=" + id;
            }
            
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

            HttpResponse<String> res;
        
            try {
               res = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
               response.setStatus(res.statusCode());
               response.getWriter().write(res.body());
               
           } catch (InterruptedException ex) {
               Logger.getLogger(Gateway.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (path.contains("/conductor")) {
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        headersConfig(response);

        String path = request.getRequestURI().replace("/gateway", "");
        
        if (path == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            HashMap<String, Object> res = new HashMap<>(); 
            res.put("msg", "Ruta inválida");
            
            Gson json = new Gson();
            
            String resJson = json.toJson(res);
            response.getWriter().write(resJson);
            return;
        }
        String id = request.getParameter("id");
        String url = this.globalUrl + path + "?id=" + id;
        
        if (path.contains("/propietario")) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

            try {
                HttpResponse<String> res = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                
                response.setStatus(res.statusCode());
                response.setContentType("application/json"); 
                response.getWriter().write(res.body()); 

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la conexión con el microservicio.");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        headersConfig(response);

        String path = request.getRequestURI().replace("/gateway", "");
        
        if (path == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            HashMap<String, Object> res = new HashMap<>(); 
            res.put("msg", "Ruta inválida");
            
            Gson json = new Gson();
            
            String resJson = json.toJson(res);
            response.getWriter().write(resJson);
            return;
        }
        
        String id = request.getParameter("id");
        String url = this.globalUrl + path + "?id=" + id;
        
        String body = request.getReader().lines().collect(Collectors.joining());
        
        if (path.contains("/propietario")) {
            
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();

            HttpResponse<String> res;
        
            try {
               res = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
               response.setStatus(res.statusCode());
               response.getWriter().write(res.body());
               
           } catch (InterruptedException ex) {
               Logger.getLogger(Gateway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
    
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
    protected void headersConfig(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }
}
