package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import conexion.ConexionMySQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

@WebServlet(name = "ControllerUsuario", urlPatterns = {"/usuario"})
public class ControllerUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        
        ConexionMySQL connection = new ConexionMySQL();
        
        Connection conn =  connection.getConnection();
        
        try{
            String query = "SELECT idUsuario,user,password,email,ultimoAcceso FROM usuario;";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            List<Usuario> usuarios = new ArrayList();
            
            while(resultSet.next()){
                
                Usuario usuario = new Usuario(
                        resultSet.getInt("idUsuario"),
                        resultSet.getString("user"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getDate("ultimoAcceso")
                );
                usuarios.add(usuario);
            }
                       
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(usuarios);
            
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
        
        try{
            ObjectMapper mapper = new ObjectMapper();
            
            // Convertir el JSON de la solicitud a objeto Usuario
            Usuario usuario = mapper.readValue(request.getInputStream(), Usuario.class);
            
            String query = "INSERT INTO usuario(user, password, email) VALUES (?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1,usuario.getUser());
            statement.setString(2,usuario.getPassword());
            statement.setString(3,usuario.getEmail());
            //statement.setDate(4, (Date) usuario.getUltimoAcceso());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                Long idUsuario = rs.getLong(1);
                
                response.setContentType("application/json");
                String json = mapper.writeValueAsString(idUsuario);
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
