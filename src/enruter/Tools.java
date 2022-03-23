
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
 *
 * @author Usuario
 */
public class Tools {
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
//exporta cualquier valor a cualquier archivo especificado como parametro
     try{
         String strPath2 = System.getProperty("user.dir")+"\\";
         String strPath = (form_geo.pathJar==null)?strPath2:form_geo.pathJar;//
        String f = strPath+nombreArchivo+".txt";
        File file = new File(f); 
         try ( //Writer escribe = null;//FileOutputStream fis = new FileOutputStream(f);
                 PrintWriter writer = new PrintWriter(f)) {
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
         }
         // escribe = new BufferedWriter(new OutputStreamWriter(
         //               new FileOutputStream(file), "UTF8"));
         //      escribe.write(valor);
System.gc();
    }
    catch(FileNotFoundException e){
    JOptionPane.showMessageDialog(null,"No se encontro el archivo");
    }
    catch(IOException e){
    JOptionPane.showMessageDialog(null, "error");
    }
 } 
    public static void expFileLineToLine(String valor,String nombreArchivo) throws IOException {
//exporta cualquier valor a cualquier archivo especificado como parametro
     try{
         String strPath2 = System.getProperty("user.dir")+"\\";
         String strPath = (form_geo.pathJar==null)?strPath2:form_geo.pathJar;//
        String f = strPath+nombreArchivo+".txt";
        File file = new File(f); 
          //Writer escribe = null;//FileOutputStream fis = new FileOutputStream(f);
              //   PrintWriter writer = new PrintWriter(f)) {
             if (!file.exists()) {
                 file.createNewFile();
             }
         //               new FileOutputStream(file), "UTF8"));
         //      escribe.write(valor);
         //FileWriter  dos=new FileWriter(f,true);//FileWriter  dos=new FileWriter(f,true);
         // writer.println(valor);
         // writer.println("\n");
         try (FileWriter dos = new FileWriter(f,true)) {
             //               new FileOutputStream(file), "UTF8"));
             //      escribe.write(valor);
             //FileWriter  dos=new FileWriter(f,true);//FileWriter  dos=new FileWriter(f,true);
             // writer.println(valor);
             // writer.println("\n");
             dos.write((valor==null)?"null":valor);
             dos.write("\n");
             //i=i+1;
             //}
             //JOptionPane.showMessageDialog(null, "Proceso Finalizado");
             //}
             // escribe = new BufferedWriter(new OutputStreamWriter(
             //               new FileOutputStream(file), "UTF8"));
             //      escribe.write(valor);
         }
System.gc();
    }
    catch(FileNotFoundException e){
    JOptionPane.showMessageDialog(null,"No se encontro el archivo");
    }
    catch(IOException e){
    JOptionPane.showMessageDialog(null, "error");
    }
 }  
    public static void expFileLineToLine(String valor,String nombreArchivo,Map mapa) throws IOException {
//exporta cualquier valor a cualquier archivo especificado como parametro
     try{
         String strPath2 = System.getProperty("user.dir")+"\\";
         String strPath = (form_geo.pathJar==null)?strPath2:form_geo.pathJar;//
        String f = strPath+nombreArchivo+".txt";
        File file = new File(f); 
          //Writer escribe = null;//FileOutputStream fis = new FileOutputStream(f);
              //   PrintWriter writer = new PrintWriter(f)) {
             if (!file.exists()) {
                 file.createNewFile();
             }
         //               new FileOutputStream(file), "UTF8"));
         //      escribe.write(valor);
         //FileWriter  dos=new FileWriter(f,true);//FileWriter  dos=new FileWriter(f,true);
         // writer.println(valor);
         // writer.println("\n");
         try (FileWriter dos = new FileWriter(f,true)) {
             //               new FileOutputStream(file), "UTF8"));
             //      escribe.write(valor);
             //FileWriter  dos=new FileWriter(f,true);//FileWriter  dos=new FileWriter(f,true);
             // writer.println(valor);
             for (Object key : mapa.keySet()){
                 valor=key.toString();System.out.println(key.toString());
             dos.write((valor==null)?"null":valor);
             dos.write("\n");
             }
             //}
             //JOptionPane.showMessageDialog(null, "Proceso Finalizado");
             //}
             // escribe = new BufferedWriter(new OutputStreamWriter(
             //               new FileOutputStream(file), "UTF8"));
             //      escribe.write(valor);
         }
System.gc();
    }
    catch(FileNotFoundException e){
    JOptionPane.showMessageDialog(null,"No se encontro el archivo");
    }
    catch(IOException e){
    JOptionPane.showMessageDialog(null, "error");
    }
 }
    public static void crearMallaXYZ(){
         int limite = 1,cont=0,fin=300;Map <String,Integer> mapaXY = new LinkedHashMap();//90601 puntos
      
     while(cont<=fin) { 
         
         for(int x=0;x<limite;x++){
                  
                 for(int y=0;y<limite;y++){
                     //JOptionPane.showMessageDialog(null,contA+"|"+ x+"."+y);
                     mapaXY.put(x+"."+y, x+y);//listado=listado+x+"."+y+",";
                 }
               
         }
          limite++;
         cont++;
      }
    //List<String> ListofKeys = new ArrayList(mapaXY.keySet()); 
    //List<String> Listofvalues= new ArrayList(mapaXY.values());
        try {
            expFileLineToLine("","mallaPuntosXY",mapaXY); //listado=listado+key+",";
        } catch (IOException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    // for (String key : mapaXY.keySet()){System.out.println(key);}      
     
     JOptionPane.showMessageDialog(null,"Archivo de mallas generado");
    }
}
