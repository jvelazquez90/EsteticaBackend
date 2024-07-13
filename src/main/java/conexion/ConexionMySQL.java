package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private Connection connection;
    
    public ConexionMySQL(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/estetica",
                    "root",
                    ""
            );
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        return this.connection;
    }
    
    public void close(){
        try{
            if(this.connection != null && !this.connection.isClosed()){
                this.connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
