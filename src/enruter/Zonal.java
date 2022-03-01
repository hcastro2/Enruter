
package enruter;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Zonal {
    //PRIPMERO DEFINIR LOS CAMPOS ESPECIFICOS QUE COMPONEN UNA ZONA CON INFORMACION COMPLETA POR POSICION:
    /**   0 = NOMBRE DE ZONA
     *    1 = COORDENADA DEL 1 AL 4 SEA NORTE,SUR,ESTE,OESTE O  SIN DEFINIR=0
     *    2 = xa inicio eje X que determina las calles
     *    3 = xb fin de eje X en calles
     *    4 = ya inicio eje Y que determina las carreras
     *    5 = yb fin eje Y en carreras
     *    6 = Reservado para relacionar la zona con un operador u otro agente-usuario
     *    7 = libre
     *    8 = libre
     *    9 = libre
    */
    static Object matrizZona[] = new Object[8];
    String nombreZ;
    String coordenada;
    String inicioR;
    String finR;
    String xa,xb,ya,yb,zn;
 ArrayList <String> zonas;int rows; 
 float equal;String zona,ciudad;
 
    public Zonal() {
        this.nombreZ = "";
        this.coordenada= "";
        this.inicioR = "";
        this.finR = "";
        this.xa = "";
        this.xb = "";
        this.ya = "";
        this.yb = "";
        this.zn = "";
        this.ciudad = "";
    }

public void calcZona(String idDireccion){
    try{
    int coord,cont=0,contz=0,numcasa=-1,dirCordenada,pointCasa = -1; float Xa=0,Xb=0,Ya=0,Yb=0,ejecalle = 0,ejekr = 0;String nomZona;
    String result = null;Object matchZona[][]= new Object[4][6];
  if (baseZonas.zonasCargadas==false){
      
  }else{
      //JOptionPane.showConfirmDialog(null, baseZonas.zonasCargadas);
      Object idDireccionX [] = idDireccion.split("\\.");
        dirCordenada = Integer.parseInt((String)idDireccionX [0]);
        ejecalle = Float.valueOf((String)idDireccionX [2]+"."+idDireccionX [3]);
        ejekr  = Float.valueOf((String)idDireccionX [5]+"."+idDireccionX [6]);
        numcasa = Integer.parseInt((String)idDireccionX [8]);
        pointCasa = Integer.parseInt((String)idDireccionX [7]);
 // JOptionPane.showMessageDialog(null, pointCasa);
    ///////////////////GUARDA EN VARIABLES LOS DATOS DE LA BASE DE ZONAS DECODIFICADAS
   do{ 
       if (baseZonas.baseZonas[0][cont]==null){
          break; 
       }else{
       nomZona = (String) baseZonas.baseZonas[0][cont];
       coord = (int) baseZonas.baseZonas[1][cont];
        Xa =  (float) baseZonas.baseZonas[2][cont];
        Xb=   (float)baseZonas.baseZonas[3][cont];
        Ya=   (float)baseZonas.baseZonas[4][cont];
        Yb=   (float)baseZonas.baseZonas[5][cont];
            
              //SI NO ESTA SOBRE UN LIMITE BUSCA SI SE ENCUENTRA DENTRO DE UNA ZONA
           if ((coord==dirCordenada)&&(ejecalle>=Xa)&&(ejecalle<=Xb)&&(ejekr>=Ya)&&(ejekr<=Yb)){
               matchZona[contz][0]=nomZona;matchZona[contz][3]=Xb;//error CRA 28 #44-35 PISO 3
               matchZona[contz][1]=coord;matchZona[contz][4]=Ya;
               matchZona[contz][2]=Xa;matchZona[contz][5]=Yb;
                //JOptionPane.showMessageDialog(null, matchZona[contz][0]);//
                  contz++;   
           } 

      cont++;}
   }  while(cont < baseZonas.zonas.size());
  }
      if (contz==1){result=(String)matchZona[0][0];}//JOptionPane.showMessageDialog(null, result);
      if (contz>2){
         for(int i = 0;i< 4;i++) {
           if (matchZona[i][0]!=null){ 
               if ((ejecalle==(float)matchZona[i][3])&&(ejekr==(float)matchZona[i][5])){
                 result=(String)matchZona[i][0];//JOptionPane.showMessageDialog(null, matchZona[i][0] );
               }
             }
         }
        }
      
    if (contz==2){ 
         int inZona1=inBorderIn(numcasa,(float)matchZona[0][2],(float)matchZona[0][3],(float)matchZona[0][4],(float)matchZona[0][5],ejecalle,ejekr); 
         int inZona2=inBorderIn(numcasa,(float)matchZona[1][2],(float)matchZona[1][3],(float)matchZona[1][4],(float)matchZona[1][5],ejecalle,ejekr);
      
             if (inZona1==0){ result=(String)matchZona[0][0];}else{result=(String)matchZona[1][0];}
                 
             
       
    }
  zona=result;//setZona(zona);
   }
            catch( Exception e ) {
        zona= "ZonaErrorCalc ";
        }         
        
}
public void calcZonaCiudad(String idDireccion,String ciudad){//SUB FUNCION QUE CALCULA LAS ZONAS TENIENDO EN CUENTA LA CIUDAD
    int coord,cont=0,contz=0,numcasa=-1,dirCordenada,pointCasa = -1,ciudadDir=0; float Xa=0,Xb=0,Ya=0,Yb=0,ejecalle = 0,ejekr = 0;String nomZona;
    String result = null;Object matchZona[][]= new Object[4][6];
  if (baseZonas.zonasCargadas==false){
      
  }else{
      //JOptionPane.showConfirmDialog(null, baseZonas.zonasCargadas);
      Object idDireccionX [] = idDireccion.split("\\.");
        dirCordenada = Integer.parseInt((String)idDireccionX [0]);
        ejecalle = Float.valueOf((String)idDireccionX [2]+"."+idDireccionX [3]);
        ejekr  = Float.valueOf((String)idDireccionX [5]+"."+idDireccionX [6]);
        numcasa = Integer.parseInt((String)idDireccionX [8]);
        pointCasa = Integer.parseInt((String)idDireccionX [7]);
        //ciudadDir=Integer.parseInt((String)idDireccionX [9]);
 // JOptionPane.showMessageDialog(null, pointCasa);
    ///////////////////GUARDA EN VARIABLES LOS DATOS DE LA BASE DE ZONAS DECODIFICADAS
   do{ 
       if (baseZonas.baseZonas[0][cont]==null){
          break; 
       }else{
       nomZona = (String) baseZonas.baseZonas[0][cont];
       coord = (int) baseZonas.baseZonas[1][cont];
        Xa =  (float) baseZonas.baseZonas[2][cont];
        Xb=   (float)baseZonas.baseZonas[3][cont];
        Ya=   (float)baseZonas.baseZonas[4][cont];
        Yb=   (float)baseZonas.baseZonas[5][cont];
            
              //SI NO ESTA SOBRE UN LIMITE BUSCA SI SE ENCUENTRA DENTRO DE UNA ZONA
           if ((coord==dirCordenada)&&(ejecalle>=Xa)&&(ejecalle<=Xb)&&(ejekr>=Ya)&&(ejekr<=Yb)){
               matchZona[contz][0]=nomZona;matchZona[contz][3]=Xb;//error CRA 28 #44-35 PISO 3
               matchZona[contz][1]=coord;matchZona[contz][4]=Ya;
               matchZona[contz][2]=Xa;matchZona[contz][5]=Yb;
                //JOptionPane.showMessageDialog(null, matchZona[contz][0]);//
                  contz++;   
           } 

      cont++;}
   }  while(cont < baseZonas.zonas.size());
  }
      if (contz==1){result=(String)matchZona[0][0];}//JOptionPane.showMessageDialog(null, result);
      if (contz>2){
         for(int i = 0;i< 4;i++) {
           if (matchZona[i][0]!=null){ 
               if ((ejecalle==(float)matchZona[i][3])&&(ejekr==(float)matchZona[i][5])){
                 result=(String)matchZona[i][0];//JOptionPane.showMessageDialog(null, matchZona[i][0] );
               }
             }
         }
        }
      
    if (contz==2){ 
         int inZona1=inBorderIn(numcasa,(float)matchZona[0][2],(float)matchZona[0][3],(float)matchZona[0][4],(float)matchZona[0][5],ejecalle,ejekr); 
         int inZona2=inBorderIn(numcasa,(float)matchZona[1][2],(float)matchZona[1][3],(float)matchZona[1][4],(float)matchZona[1][5],ejecalle,ejekr);
      
             if (inZona1==0){ result=(String)matchZona[0][0];}else{result=(String)matchZona[1][0];}
                 
             
       
    }
  zona=result;//setZona(zona);
}
 public static boolean isPar(int num){
        return num % 2 == 0;
}  
 public  int inBorderIn(int numcasa, float xa, float xb,float ya,float yb,float cl,float kr){
   // si empiesa por calle eje x pointCasa = 1 sino empiesa por eje y pointCasa = 0 
   int control=0;int result = 0;
    if (cl==xa){control=1;}//dentro de rango si # casa es par
    if (cl==xb){control=2;}//dentro de rango si # casa es impar
    if (kr==ya){control=3;}//dentro de rango si # casa es impar
    if (kr==yb){control=4;}//dentro de rango si # casa es par
    
    switch(control){
        case 0:
            result=0;
            break;
        case 1: 
            if (isPar(numcasa)==true){result=2;equal=1;}//xa
            break;
        case 2:
             if (isPar(numcasa)==false){result=3;equal=2;}//xb
            break;
        case 3: 
             if (isPar(numcasa)==false){result=4;equal=3;}//ya
            break;
        case 4:
            if (isPar(numcasa)==true){result=5;equal=4;}//yb
            break;
        default:
            break;
    }
   return result;   
}

 public static void main(String[] args) {//clase exclusiva para I/O entrada salida de datos del usuario y hacer pruebas
   String ejemplo ; 
         ejemplo = JOptionPane.showInputDialog(null, "Ingrese Expresion");
    int num = Integer.parseInt(ejemplo);
    
    JOptionPane.showMessageDialog(null, isPar(num));
    
 }
   

//////////////////////METODOS GETER Y SETER /////////////////////////////////////
    // <editor-fold defaultstate="collapsed" desc="Generated Code Getter and Setter">
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
     public String getCiudad() {
        return ciudad;
    }
    public String getZona() {
        return zona;
    }
    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getNombreZ() {
        return nombreZ;
    }
    public void setNombreZ(String nombreZ) {
        this.nombreZ = nombreZ;
    }
    public String getNombreRango() {
        return nombreZ;
    }
    public void setNombreRango(String nombreRango) {
        this.nombreZ = nombreRango;
    }  
    public String getCoordenada() {
        return coordenada;
    }
    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }
    public String getInicioR() {
        return inicioR;
    }
    public void setInicioR(String inicioR) {
        this.inicioR = inicioR;
    }
    public String getFinR() {
        return finR;
    }
    public void setFinR(String finR) {
        this.finR = finR;
    }
    public String getXa() {
        return xa;
    }
    public void setXa(String xa) {
        this.xa = xa;
    }
    public String getXb() {
        return xb;
    }
    public void setXb(String xb) {
        this.xb = xb;
    }
    public String getYa() {
        return ya;
    }
    public void setYa(String ya) {
        this.ya = ya;
    }
    public String getYb() {
        return yb;
    }
    public void setYb(String yb) {
        this.yb = yb;
    }
    public String getZn() {
        return zn;
    }
    public void setZn(String zn) {
        this.zn = zn;
    }
 // </editor-fold>    
}
/////////////////////////////////////////////SUB CLASE QUE CREA OBJETO BASE DE ZONAS 
class baseZonas{
    static ArrayList <String> zonas;
    static boolean zonasCargadas = false;
     String nombreZ,InicioR,FinR;
     int coordenada;
     float xa,xb,ya,yb,zn;
    static Object baseZonas [][] ;//= new Object[8][100];   
    public void textozonaDescomp(String texto){//TOMA UNA EXPRESION CODIFICADA DE UNA ZONA Y LA DESCOMPONE EN SUS PARTES
  String arr[] = texto.split("\\|");
    setNombreZ(arr[0]);  //////////////DEFINIMOS NOMBRE DE ZONA
  String arr2[] = arr[1].split("#");
   setCoordenada(Integer.parseInt(arr2[0]));////////////DEFINIMOS COORDENADA DE ZONA
  String arr3[] = arr2[1].split(";");
   setInicioR(arr3[0]);setFinR(arr3[1]);//DEFINIMOS RANGO DE CALLES DE INICIO DE LA ZONA Y FIN DE LA ZONA
  //////////////////////////////////////DEFINIMOS EJES X Y Y DETALLADAMENTE
  String arr4[] = arr3[0].split(":");
   setXa(Float.valueOf(arr4[0]));setXb(Float.valueOf(arr4[1]));  //////////DEFINIMOS INICIO DE CALLE =Xa Y FIN DE CALLE =Xb
  String arr5[] = arr3[1].split(":");
   setYa(Float.valueOf(arr5[0]));setYb(Float.valueOf(arr5[1])); //////////DEFINIMOS INICIO DE CALLE =Ya Y FIN DE CALLE =Yb
} 
    
    public void codificarzonas(){
    int largo = zonas.size();int rows=zonas.size();baseZonas  = new Object[8][rows];
    for (int i=0; i < largo;i++){
        if(zonas.get(i) == null){
        }else{
        textozonaDescomp(zonas.get(i));//JOptionPane.showMessageDialog(null,zonas.get(i));
        /*JOptionPane.showMessageDialog(null, getNombreZ()+"\n"+getCoordenada()+"\n"+getInicioR()+"\n"+getFinR()+"\n"+
        getXa()+"\n"+getXb()+"\n"+getYa()+"\n"+getYb());*/
        
               baseZonas[0][i]=getNombreZ();
               baseZonas[1][i]=getCoordenada(); 
               baseZonas[2][i]=getXa(); 
               baseZonas[3][i]=getXb(); 
               baseZonas[4][i]=getYa(); 
               baseZonas[5][i]=getYb(); 
               
             }
        
          }
    }
    public  void importarZonas() throws IOException {//FUNCION IMPORTAR BASE DE ZONAS
    int i = 0;String file=null; zonas = new ArrayList<>();
    ///////////////// codigo que invoca la clase para procesar las direcciones
    // descomponer d; //= new descomponer();
    
     ///////////////////////////////////////////////////////////////////////////////////////////////
    JFileChooser fc = new JFileChooser();
    FileFilter filtro = new FileNameExtensionFilter("Archivos TXT", "txt");
    fc.addChoosableFileFilter(filtro);
    int respuesta = fc.showOpenDialog(fc); 
            //Comprobar si se ha pulsado Aceptar
    if (respuesta == JFileChooser.APPROVE_OPTION) {
    FileReader myFile = null;
    try {
                //Crear un objeto File con el archivo elegido
                File archivoElegido = fc.getSelectedFile();
                //Mostrar el nombre del archvivo en un campo de texto
    // JOptionPane.showMessageDialog(rootPane, archivoElegido.getAbsoluteFile());// txtNombre.setText(archivoElegido.getName()); 
           file =archivoElegido.getAbsoluteFile().toString();      
    //Leo un Archivo de Texto
    //String file = "C:/pregeo.txt";
    myFile = new FileReader(file);
    BufferedReader InputFile = new BufferedReader(myFile);
    // Read the first line
    String currentRecord = InputFile.readLine();
     zonas.add(currentRecord);
    while(currentRecord != null) {
        //d = new descomponer();
    try {
    //Copio un valor a la celda 
    //JOptionPane.showMessageDialog(null,dirs.size());//jTable3.setValueAt(currentRecord, i, 0);
    currentRecord = InputFile.readLine();
    
    if (currentRecord != ""){ // JOptionPane.showMessageDialog(null,zonas.get(i)+"\n"+d.array[11]);
       zonas.add(currentRecord);i++;
    }

     
    }
    catch (Exception ex) {
    }
    }
    JOptionPane.showMessageDialog(null,"Se importaron "+i+" Registros");
     if (zonas.size()>0){codificarzonas();zonasCargadas=true;}//se procesa la base de zonas escrita en forma de rango    
     

    } catch (FileNotFoundException ex) {
    Logger.getLogger(javaapplication6.ventana2.class.getName()).log(Level.SEVERE, null, ex);
    } finally {

        try {
    myFile.close();

    } catch (IOException ex) {
    Logger.getLogger(javaapplication6.ventana2.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
            }//fin del if 
           else{
             JOptionPane.showMessageDialog(null,"Proceso Cancelado");
            }

    } 
    /////////////////////////////////////////////////////////////////////
    public void importarZonasfijo() throws IOException {
        int i = 0;String file=null,ruta; zonas = new ArrayList<String>();
    ///////////////// codigo que invoca la clase para procesar las direcciones
     //descomponer d; //= new descomponer();
    ruta = System.getProperty("user.dir"); //JOptionPane.showMessageDialog(null,ruta);
     ///////////////////////////////////////////////////////////////////////////////////////////////
    FileReader myFile = null;
    file = ruta+"\\zonas.txt";//file ="C:\\Users\\57321\\Documents\\zonas.txt";  
     File af = new File(file);
            //Comprobar si se ha pulsado Aceptar
    if (af.isFile()==false){//JOptionPane.showMessageDialog(null,"No se cargaron fuentes de datos");
      zonasCargadas=false;
      }else{
    
    try {
                //Crear un objeto File con el archivo elegido
        
    //Leo un Archivo de Texto
    //String file = "C:/pregeo.txt";
    myFile = new FileReader(file);
    BufferedReader InputFile = new BufferedReader(myFile);
    // Read the first line
    String currentRecord = InputFile.readLine();
     zonas.add(currentRecord);
    while(currentRecord != null) {
        //d = new descomponer();
    try {
    //Copio un valor a la celda 
    //JOptionPane.showMessageDialog(null,dirs.size());//jTable3.setValueAt(currentRecord, i, 0);
    currentRecord = InputFile.readLine();
    
    if (currentRecord != ""){ // JOptionPane.showMessageDialog(null,zonas.get(i)+"\n"+d.array[11]);
       zonas.add(currentRecord);i++;
    }

     
    }
    catch (Exception ex) {
    }
    }
    //JOptionPane.showMessageDialog(null,"Se importaron "+i+" Registros");
     if (zonas.size()>0){codificarzonas();zonasCargadas=true;}//se procesa la base de zonas escrita en forma de rango    
     

    } catch (FileNotFoundException ex) {
    Logger.getLogger(javaapplication6.ventana2.class.getName()).log(Level.SEVERE, null, ex);
    } finally {

        try {
    myFile.close();

    } catch (IOException ex) {
    Logger.getLogger(javaapplication6.ventana2.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    }        
    
    }
 //////////////////////////////////////////////////////////////////////////////
  // <editor-fold defaultstate="collapsed" desc="Generated Code Getter Setter">    
    public String getNombreZ() {
        return nombreZ;
    }
    public void setNombreZ(String nombreZ) {
        this.nombreZ = nombreZ;
    }
    public int getCoordenada() {
        return coordenada;
    }
    public void setCoordenada(int coordenada) {
        this.coordenada = coordenada;
    }
    public float getXa() {
        return xa;
    }
    public void setXa(float xa) {
        this.xa = xa;
    }
    public float getXb() {
        return xb;
    }
    public void setXb(float xb) {
        this.xb = xb;
    }
    public float getYa() {
        return ya;
    }
    public void setYa(float ya) {
        this.ya = ya;
    }
    public float getYb() {
        return yb;
    }
    public void setYb(float yb) {
        this.yb = yb;
    }
    public float getZn() {
        return zn;
    }
    public void setZn(float zn) {
        this.zn = zn;
    }
    public String getInicioR() {
        return InicioR;
    }
    public void setInicioR(String InicioR) {
        this.InicioR = InicioR;
    }
    public String getFinR() {
        return FinR;
    }
    public void setFinR(String FinR) {
        this.FinR = FinR;
    }
  // </editor-fold> 
    
}

class historicZonas{//SUB CLASE PARA BUSCAR POR HISTORICO
    static ArrayList <String> zonash;static ArrayList <String> idzonas = new ArrayList<String>();
    static boolean zonasCargadas = false;
    static String baseZonasH [][];// = new Object[2][100];
    
    public  void importarZonasHistoric() throws IOException {//FUNCION IMPORTAR BASE ZONAS HISTORICAS
    int i = 0;String file=null; zonash = new ArrayList<String>();
    ///////////////// codigo que invoca la clase para procesar las direcciones
     //descomponer d; //= new descomponer();
    
     ///////////////////////////////////////////////////////////////////////////////////////////////
    JFileChooser fc = new JFileChooser();
    FileFilter filtro = new FileNameExtensionFilter("Archivos TXT", "txt");
    fc.addChoosableFileFilter(filtro);
    int respuesta = fc.showOpenDialog(fc); 
            //Comprobar si se ha pulsado Aceptar
    if (respuesta == JFileChooser.APPROVE_OPTION) {
    FileReader myFile = null;
    try {
                //Crear un objeto File con el archivo elegido
                File archivoElegido = fc.getSelectedFile();
                //Mostrar el nombre del archvivo en un campo de texto
    // JOptionPane.showMessageDialog(rootPane, archivoElegido.getAbsoluteFile());// txtNombre.setText(archivoElegido.getName()); 
           file =archivoElegido.getAbsoluteFile().toString();      
    //Leo un Archivo de Texto
    //String file = "C:/pregeo.txt";
    myFile = new FileReader(file);
    BufferedReader InputFile = new BufferedReader(myFile);
    
    String currentRecord = InputFile.readLine();
    
     zonash.add(currentRecord);
    while(currentRecord != null) {
       // d = new descomponer();
    try {
    //Copio un valor a la celda 
    //JOptionPane.showMessageDialog(null,dirs.size());//jTable3.setValueAt(currentRecord, i, 0);
    currentRecord = InputFile.readLine();
    
    if (currentRecord != ""){ // JOptionPane.showMessageDialog(null,zonas.get(i)+"\n"+d.array[11]);
       zonash.add(currentRecord);//zonash.add(a[0]);baseZonasH [i][0]=a[0];baseZonasH [i][1]=a[1];
       i++;
    }
    }
    catch (Exception ex) {
    }
    }
    int cont=0,largo= zonash.size();baseZonasH = new String[largo][2];
      for (String zonas: zonash){
         if(zonas==null){} else{
          String[] a = zonas.split("\\|");
          idzonas.add(a[0]);baseZonasH [cont][0]=a[0];baseZonasH [cont][1]=a[1];cont++;}
          
      }
    JOptionPane.showMessageDialog(null,"Se importaron "+i+" Registros");
     //if (zonas.size()>0){codificarzonas();zonasCargadas=true;}//se procesa la base de zonas escrita en forma de rango    
     

    } catch (FileNotFoundException ex) {
    Logger.getLogger(javaapplication6.ventana2.class.getName()).log(Level.SEVERE, null, ex);
    } finally {

        try {
    myFile.close();

    } catch (IOException ex) {
    Logger.getLogger(javaapplication6.ventana2.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
            }//fin del if 
           else{
             JOptionPane.showMessageDialog(null,"Proceso Cancelado");
            }

    } 
    static String buscarZona(String codigo){
      String  result="";
       int point = idzonas.indexOf(codigo);
       if (point<0){result="N/A";}else{
       result = baseZonasH[point][1];}// zonash.get(point);
        return result;
    }
    static String buscarZonaProxi(String codigo){
      String  result="",parcialcod;int cont=0,match = -1;
      if (codigo==""){}else{
      String array[]= codigo.split("\\.");
     parcialcod= array[0]+"."+array[1]+"."+array[2]+"."+array[3]+"."+array[4]+"."+array[5];
     for (String  sub : idzonas){
        if(sub==null){}else{
         //JOptionPane.showMessageDialog(null, sub);
         if (sub.contains(parcialcod)==true){match=cont;}else{}
        cont++; 
     }
     }
     if (match==-1){result="N/A";}else{ result=baseZonasH[match][1];}// JOptionPane.showMessageDialog(null,baseZonasH[match][1] );}
      }  
       //if (cont>0){}
        return result;
    }
}