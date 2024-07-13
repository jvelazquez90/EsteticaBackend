package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import conexion.ConexionMySQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Paciente;

@WebServlet(name = "ControllerPaciente", urlPatterns = {"/paciente"})
public class ControllerPaciente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        
        ConexionMySQL connection = new ConexionMySQL();
        
        Connection conn =  connection.getConnection();
        
        try{
            String query = "SELECT * FROM persona INNER JOIN paciente ON persona.idPersona = paciente.Persona_id";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            List<Paciente> pacientes = new ArrayList();
            
            while(resultSet.next()){
                
                Paciente paciente = new Paciente(
                        resultSet.getInt("idPersona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("dni"),
                        resultSet.getInt("sexo"),
                        resultSet.getInt("idPaciente"),
                        resultSet.getString("telefono"),
                        resultSet.getDate("fechaDeAlta"),
                        resultSet.getDate("fechaReal"),
                        resultSet.getDouble("totalGastos"),
                        resultSet.getDouble("saldo"),
                        resultSet.getBoolean("activo")
                );
                pacientes.add(paciente);
            }
            
            for(Paciente p : pacientes){
                System.out.println(p.getNombre());
            }
            
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(pacientes);
            
            response.setContentType("application/json");
            response.getWriter().write(json); 
        }catch(SQLException e){
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }finally {
            connection.close();
        }
    }
    /*
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
            Paciente paciente = mapper.readValue(request.getInputStream(), Paciente.class);
            
            System.out.println(paciente.toString());
            
            String query = "INSERT INTO usuario(user, password, email) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1,paciente.getIdPersona());
            statement.setString(2, usuario.getPassword());
            statement.setString(2,usuario.getEmail());
            //statement.setDate(3, (Date) usuario.getUltimoAcceso());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                Long idUsuario = rs.getLong(1);
                
                response.setContentType("application/json");
                String json = mapper.writeValueAsString(idUsuario);
                response.getWriter().write(json);
            }
            
            response.setStatus(HttpServletResponse.SC_CREATED);
        }catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            conexion.close();
        }
    }
    */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
