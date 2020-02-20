/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zapateria;

import Acciones.Consulta;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author saht
 */
public class Prueba {
    public static void main(String[] args) throws SQLException {
        Consulta a = new Consulta();
        Scanner rd = new Scanner(System.in);
        int o;
        String r="Si";
        while (r.equalsIgnoreCase("Si")){
            System.out.println("1. Inventario.\n2.Registro de Ventas.\n3.Hacer un registro."
                    + "\nIngrese una opcion.");
            o = rd.nextInt();
            try {
                switch (o) {
                case 1:
                    System.out.println(a.getValuesZapatos());
                    break;
                case 2:
                    System.out.println(a.getValuesVentas());
                    break;
                case 3:
                    a.insertDataVentas(1,7);
                    break;
                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
            } catch(Exception e) {
                System.out.println("OPCION INVALIDA");
            }

            System.out.println("¿Deseas realizar otra funcion? Si | No");
            r=rd.next();
        } 
    }
}
