/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author saht
 */
public class BDD {
    public String driver = "com.mysql.cj.jdbc.Driver";
    // Nombre de la base de datos
    public String database = "ZapateriaBDD";
    // Host
    public String hostname = "localhost";
    // Puerto
    public String port = "3306";
    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    // Nombre de usuario
    public String username = "root";
    // Clave de usuario
    public String password = "1998Marzo16.";
    Connection conn = null;
    public Connection conectarMySQL() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void cerrarMySQL() {
        try {
            conn.close();
            System.out.println("Finalizo conexion con el servidor");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
