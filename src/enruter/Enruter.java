/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enruter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
@SuppressWarnings("Convert2Lambda")
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
        
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                fr.setVisible(true);
                //new fr. .setVisible(true);
            }
        });
 }
 
    
}


class tools{
     public static boolean isnumeric(String text){
        boolean bool = text.matches("[0-9]*");
        return bool;
    }
    public static boolean isletra(String text){
        boolean bool = text.matches("[a-zA-Z]*");
        return bool;
    }
    public static String isTipo( String dato){
        String result="null";
             
        if ((isnumeric(dato)==false)&&(isletra(dato)==false)){
            return "d";//desconocido
        }
        if ((isnumeric(dato)==true)&&(isletra(dato)==false)){
            return "n";//numero
        }
        if ((isnumeric(dato)==false)&&(isletra(dato)==true)){
            return "l";//letra
        }
       
        return null; 
    }
    public static String letraToNum(String letra){
        int point;String result="";
        String baseabc = "#abcdefghijklmnopqrstuvwxyz";int largo= letra.length();
          if((largo>1)||("d".equals(isTipo(letra)))||("n".equals(isTipo(letra)))){ 
              
              result="N?";
          }else{
              point= baseabc.indexOf(letra);
              result= String.valueOf(point);
          }
        return result;
    }
    public static void exportarXarchivo(String valor,String nombreArchivo) throws IOException {

     try{
        // String strPath = System.getProperty("user.home");
         String strPath = form_geo.pathJar;//
        String f = strPath+nombreArchivo+".txt";
        File file = new File(f); 
     //Writer escribe = null;//FileOutputStream fis = new FileOutputStream(f);
     PrintWriter writer = new PrintWriter(f);
    if (!file.exists()) {
                file.createNewFile();
            }
   // escribe = new BufferedWriter(new OutputStreamWriter(
     //               new FileOutputStream(file), "UTF8"));
      //      escribe.write(valor);

    //FileWriter  dos=new FileWriter(f,true);//FileWriter  dos=new FileWriter(f,true);
    writer.println(valor);
    //writer.println("\n");
   // escribe.write((valor==null)?"null":valor);
    //escribe.write("\n");

    //i=i+1;
    //}
    //JOptionPane.showMessageDialog(null, "Proceso Finalizado");
    writer.close();System.gc();
    }
    catch(FileNotFoundException e){
    System.out.println("No se encontro el archivo");
    }
    catch(IOException e){
    JOptionPane.showMessageDialog(null, "error");
    }
 } 
}