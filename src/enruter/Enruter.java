/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enruter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *Hector Castro
 * hcastro2@misena.edu.co
 */
public class Enruter {
public static String nomenclatura;

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {//clase exclusiva para I/O entrada salida de datos del usuario y hacer pruebas
        // TODO code application logic here
        
        ListaDirecciones l = new ListaDirecciones();
        baseZonas z = new baseZonas();
        ciudades c = new ciudades();
         try {
                        l.FileTextRead();z.importarZonasfijo();c.importCiudades();
                    } catch (IOException ex) {
                        Logger.getLogger(descomponer.class.getName()).log(Level.SEVERE, null, ex);
                    }
   
  
     form_geo fr = new form_geo(); 
        
         java.awt.EventQueue.invokeLater(() -> {
             fr.setVisible(true);
             //new fr. .setVisible(true);
        });
 }
 
    
}

