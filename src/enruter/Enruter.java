/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enruter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 57321
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
   // nomenclatura=(JOptionPane.showInputDialog(null, "Ingrese Expresion"));
    
  //JOptionPane.showMessageDialog(null,d.texting(nomenclatura)); 
  
     form_geo fr = new form_geo(); 
        
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                fr.setVisible(true);
                //new fr. .setVisible(true);
            }
        });
 }
 
    
    
    
}
