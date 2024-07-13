package pruebas;

import com.fasterxml.jackson.databind.ObjectMapper;
import conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

public class Prueba {
    public static void main(String[] args){
        ConexionMySQL connection = new ConexionMySQL();
        
        Connection conn =  connection.getConnection();
        
        try{
            String query = "SELECT * FROM usuario";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            List<Usuario> usuarios = new ArrayList();
            
            while(resultSet.next()){
                
                Usuario usuario = new Usuario(
                        resultSet.getInt("idUsuario"),
                        resultSet.getString("user"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getDate("ultimoAcceso"),
                        resultSet.getBoolean("activo")
                );
                usuarios.add(usuario);
            }
            
            for(Usuario usu : usuarios){
                usu.toString();
            }
            

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
    }
}
