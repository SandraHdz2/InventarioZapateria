/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Conexion.BDD;
import com.sun.jndi.ldap.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author saht
 */
public class Consulta {

    BDD a = new BDD();
    java.sql.Connection conexion=a.conectarMySQL();
    public String getValuesZapatos() {
        String retorno = "";
        try {

            String Query = "CALL consultasZapatos";
            Statement st = a.conectarMySQL().createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            while (resultSet.next()) {
                retorno += "\nID: " + resultSet.getString(1) + ", Marca:"
                        + resultSet.getString(2) + ", Modelo: " + resultSet.getString(3)
                        + ", Costo:" + resultSet.getString(4) + ", Cantidad: "
                        + resultSet.getString(5);
            }
        } catch (Exception e) {
            retorno = "\nError en la adquisicion de datos";
        }
        return retorno;
    }

    public String getValuesVentas() {
        String retorno = "";
        try {
            String Query = "CALL consultasVentas";
            Statement st = a.conectarMySQL().createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            while (resultSet.next()) {
                retorno += "\nID: " + resultSet.getString(1) + ", Fecha:"
                        + resultSet.getString(2) + ", Unidades Vendidas: " + resultSet.getString(3)
                        + ", Costo Total:" + resultSet.getString(4) + ", idZapatos: "
                        + resultSet.getString(5);
            }
        } catch (Exception e) {
            retorno = e + "\nError en la adquisicion de datos";
        }
        return retorno;
    }

    public void insertDataVentas(int unidadesVendidas, int Zapatos) throws SQLException {

        java.util.Date fecha = new java.util.Date();
        DateFormat fechaf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CallableStatement sp = a.conectarMySQL().prepareCall("CALL insertarVentas(?,?,?,?)");
        conexion.setAutoCommit(false);
        try {
            float total = 0;
            Statement st = a.conectarMySQL().createStatement();
            ResultSet rs = st.executeQuery("select Costo from Zapatos where idZapatos=" + Zapatos);
            while (rs.next()) {
                total = rs.getFloat(1);
            }
            sp.setString("Fecha1", fechaf.format(fecha));
            sp.setInt("UnidadesVendidas1", unidadesVendidas);
            sp.setFloat("CostoTotal1", total * unidadesVendidas);
            sp.setInt("idZapatos1", Zapatos);
            sp.execute();
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
            conexion.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
            conexion.rollback();
        }
    }
}
