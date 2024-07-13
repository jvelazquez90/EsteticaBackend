package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import conexion.ConexionMySQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Tratamiento;


@WebServlet("/tratamiento")
public class ControllerTratamiento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        
        ConexionMySQL connection = new ConexionMySQL();
        
        Connection conn =  connection.getConnection();
        
        try{
            String query = "SELECT * FROM tratamiento";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            List<Tratamiento> tratamientos = new ArrayList();
            
            while(resultSet.next()){
                
                Tratamiento tratamiento = new Tratamiento(
                        resultSet.getInt("idTratamiento"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("cantidadSesiones"),
                        resultSet.getString("tiempo"),
                        resultSet.getString("tiempoSuperpuesto"),
                        resultSet.getInt("precio")
                );
                tratamientos.add(tratamiento);
            }
            
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(tratamientos);
            
            response.setContentType("application/json");
            response.getWriter().write(json); 
        }catch(SQLException e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }finally {
            connection.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        
        ConexionMySQL conexion = new ConexionMySQL();
        
        Connection conn = conexion.getConnection();
        /*
        String nombre = request.getParameter("nombre");
        String cantidadSesiones =request.getParameter("cantidadSesiones");
        String tiempo = request.getParameter("tiempo");
        String precio = request.getParameter("precio");
        
        System.out.println("NOMBRE: " + nombre);
        System.out.println("SESIONES: " + cantidadSesiones);
        System.out.println("TIEMPO: " + tiempo);
        System.out.println("PRECIO: " + precio);
        */     
        try{
            ObjectMapper mapper = new ObjectMapper();
            
            // Convertir el JSON de la solicitud a objeto Usuario
            Tratamiento tratamiento = mapper.readValue(request.getInputStream(), Tratamiento.class);
            
            System.out.println(tratamiento.getNombre());
            System.out.println(tratamiento.toString());
            String query = "INSERT INTO tratamiento(nombre, cantidadSesiones, tiempo, tiempoSuperpuesto, precio) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1,tratamiento.getNombre());
            statement.setInt(2,tratamiento.getCantidadSesiones());
            statement.setString(3,tratamiento.getTiempo());
            statement.setString(4,tratamiento.getTiempoSuperpuesto());
            statement.setInt(5, tratamiento.getPrecio());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                Long idTratamiento = rs.getLong(1);
                
                response.setContentType("application/json");
                String json = mapper.writeValueAsString(idTratamiento);
                response.getWriter().write(json);
            }
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            
        }catch (SQLException | IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            conexion.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
