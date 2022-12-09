package mx.uv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String url = "jdbc:mysql://db4free.net/evenotesdb";
    private static String DriverName ="com.mysql.jdbc.Driver";
    private static String username="admin_evenotes";
    private static String password="evenotes12";
    
    private static Connection connection=null;

    public static Connection getConnection(){
        try{
            Class.forName(DriverName);
            connection = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println("no se encontró el driver");
            
        }
        return connection;
    }

}
