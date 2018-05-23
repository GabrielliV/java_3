package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionJDBC {
    
    private static Connection connection;

    public static Connection getConnection() throws Exception {
        if (connection == null) {
            try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            } catch (ClassNotFoundException ex) {
            System.out.println("Driver não localizado!");
            throw new Exception(ex);
        }
            
            // Dados de conexão
            String servidor = "192.168.232.3";
            String database = "/databases/erpcolegio.fdb";
            String user = "SYSDBA";
            String password = "masterkey";
            String url = "jdbc:firebirdsql:" + servidor + "/3050:" 
                          + database + "?encoding=WIN1252";
            
           
       try {
           connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
        return connection;
    }
    public static void main(String[] args) {
        System.out.println("Teste!");
        try {
            ConnectionJDBC.getConnection();
        } catch (Exception ex) {
            System.out.println(""+ex.getMessage());
        }
    }
}