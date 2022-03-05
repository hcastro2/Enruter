
package enruter;



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import jxl.write.WritableSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*; 
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

 
public class Excel{
 List listaOrden;
  public static final String SEPARATOR=";";
   public static final String QUOTE="\"";
/*public static void main(String[] args) throws Exception {
   
    
  //importExcelXlsx();
    
}*/
public   void importExcel() throws IOException {
    
  JFileChooser fc = new JFileChooser();
    int respuesta = fc.showOpenDialog(fc); 
           //Comprobar si se ha pulsado Aceptar
    if (respuesta == JFileChooser.APPROVE_OPTION) {
        try {////////////////////////////////CONTROL DE ERRORES
    int columnas=0,filas=0;
    int column,fila,idciudad=0; descomponer d; Zonal zn;baseZonas bzn = new baseZonas();
          bzn.codificarzonas();
             // try {
    File archivoElegido = fc.getSelectedFile();
                //Mostrar el nombre del archvivo en un campo de texto
     String filename = archivoElegido.getAbsoluteFile().toString();//());// txtNombre.setText(archivoElegido.getName()); 
    //String filename = "C:\\Users\\57321\\Documents\\bases\\base2.xls";
        //List sheetData = new ArrayList();
        FileInputStream fis = null;
        
        fis = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        Iterator  rows = sheet.rowIterator();
        //Cell cell2 = sheet.getRow(0).getCell(5);
        /* Escribimos un tí­tulo en negrita en la casilla A2(Columna 0 fila 1) */ 
      /*  CellReference cellReference = new CellReference(2, 2); 
        Row rowmatch = sheet.getRow(cellReference.getRow()); 
        org.apache.poi.ss.usermodel.Cell cellda = rowmatch.getCell(cellReference.getCol());*/
/////////////////CONTROL PREVIO DE LA SINTAXIS DEL ARCHIVO REVISANDO COLUMNA 2//////////////////////////
            Object date[] = new Object[4]; 
            date=   (Object[]) verificarArchivo(filename); //date[0]=Errores [1]=registros [2]= Numeros [3]=Ciud encontrados
        if (((int)date[0]!=0)){JOptionPane.showMessageDialog(null,date[0] +" Registro Con Inconsistencias");return;}
        
/////////////////////////////////////////nuevo codigo/////////////////////////////
        filas = sheet.getPhysicalNumberOfRows();
        columnas = sheet.getRow(0).getPhysicalNumberOfCells();
        Object tabla[][] = new Object [columnas+6][filas-1];int colcont=0,filcont=0,contTitulos=0;;
        String encabezados[] = new String[columnas+6];
        
         while (rows.hasNext()) {//RECORRE POR FILAS Y COLUMNAS CADA CELDA
             HSSFRow row = (HSSFRow) rows.next();
                 Iterator cells = row.cellIterator();
                 //List data = new ArrayList();
            while (cells.hasNext()) { //PASA A LA SIGUIENTE LINEA
                  
                    HSSFCell cell = (HSSFCell) cells.next();
                    column = cell.getColumnIndex(); fila = cell.getRowIndex();
                    
                   if ((cell.getRowIndex()==0)){ encabezados[contTitulos]=cell.toString();};contTitulos++;//PARA LLENAR ENCABEZADOS 
             if(filcont>0){                                                
                   if ((cell.getCellType()==1)){tabla[colcont][filcont-1]=cell.toString();//si es texto lo guarda como texto
                   }else{
                            tabla[colcont][filcont-1]=(int)cell.getNumericCellValue();}//si es numerico lo guarda como entero
                   
                   colcont++;
                   // JOptionPane.showMessageDialog(null, cell.getCellType());
                     if ((column==0)&&(filcont>0)){//aqui aunque recorre toda la matriz solo ejecuta la accion sobre la columna 0 fila 1 ignora encabezados
                            d = new descomponer();String cadena=d.texting(cell.toString());String codigo=d.codigo();//String.valueOf(idciudad)+
                         Double valor=d.valor;//zn = new Zonal();   zn.calcZona(codigo);
                        //JOptionPane.showMessageDialog(null,cell+"#\n"+cadena+"#\n"+codigo+"#"+zn.getZona());
                           idciudad= (int)row.getCell(1).getNumericCellValue();
                            tabla[columnas][filcont-1]=idciudad+codigo;
                            tabla[columnas+1][filcont-1]=cadena;
                            tabla[columnas+2][filcont-1]=valor;
                            tabla[columnas+3][filcont-1]=filcont;
                           }
                     
            }
            }//fin while
              filcont++; colcont=0;  
              
         }  // fin while principal
        encabezados[columnas]="Codigo";//LLENA ENCABEZADO tabla[columnas][0]="Codigo";
        encabezados[columnas+1]="Cadena";//LLENA ENCABEZADO  tabla[columnas+1][0]="Cadena";
        encabezados[columnas+2]="ValorNumerico";//LLENA ENCABEZADO-Valor numerico para ordenar tabla[columnas+2][0]="ValorNumerico";
        encabezados[columnas+3]="OrdenOriginal";//LLENA ENCABEZADO-orden original  tabla[columnas+3][0]="OrdenOriginal";
        encabezados[columnas+4]="Zona";//LLENA ENCABEZADO-ESPACIO RESERVADO PARA ZONIFICAR  tabla[columnas+4][0]="Zona";
        encabezados[columnas+5]="NewOrden";// 
         
         
         ///////////////////////LINEA PARA EMPEZAR A ZONIFICAR LA BASE//////////////////////////////////
         for (int i=0;i<filas-1;i++){
             //JOptionPane.showMessageDialog(null,tabla[columnas][i]);
        zn = new Zonal(); zn.calcZona((String) tabla[columnas][i]);
        String result = zn.getZona();tabla[columnas+4][i]=result;}
         ////////////////////////////////////////FIN DE LA ZONIFICACION POR CALCULO////////////////  
       
        exportExcel(tabla,encabezados);//EXPORTA A EXCEL INFORME
        
        System.gc();tabla=null;zn=null;bzn=null;//CONTROL DE MEMORIA 
        
         JOptionPane.showMessageDialog(null,"Proceso Finalizado");
         
       } catch (FileNotFoundException ex) {
         JOptionPane.showMessageDialog(null,ex.getMessage());
      } catch (BiffException ex) {
          Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
      }   
      /*   } catch (Exception e) {
        JOptionPane.showMessageDialog(null,"Error "+e.getMessage());
    }*/
        
    }//fin del if 
           else{
             JOptionPane.showMessageDialog(null,"Proceso Cancelado");
            }
    
}
public  void importCsv() throws IOException {
    BufferedReader br = null;descomponer d; Zonal zn;baseZonas bzn = new baseZonas();
    ArrayList<llaves> l = new ArrayList();
      try {
          
          JFileChooser fc = new JFileChooser();
    int respuesta = fc.showOpenDialog(fc); 
           //Comprobar si se ha pulsado Aceptar
    if (respuesta == JFileChooser.APPROVE_OPTION) {}else{
      JOptionPane.showMessageDialog(null,"Proceso Cancelado");  return;
     }
               
    File archivoElegido = fc.getSelectedFile();
                //Mostrar el nombre del archvivo en un campo de texto
     String filename = archivoElegido.getAbsoluteFile().toString();
         int filcont=0;String encabezados = null;
         br =new BufferedReader(new FileReader(filename));
         String line = br.readLine();
         while (null!=line) {
            String [] fields = line.split(SEPARATOR);
           System.out.println(filcont+""+Arrays.toString(fields));
            if(filcont==0){encabezados=line;}//GENERAMOSENCABEZADOS
            if (filcont>0){//con encabezados procesa desde la fila 1
             //JOptionPane.showMessageDialog(null,fields[0]); //columna 1
              d = new descomponer();String cadena=d.texting(fields[0]);String codigo=d.codigo();//String.valueOf(idciudad)+
                         Double valor=d.valor;
             //l.add(filcont+";"+line+";"+cadena+";"+codigo+";"+valor);//llaves
             l.add(new llaves(line, "", cadena, codigo, filcont, valor));//l.add(new llaves(filcont, line, codigo, valor));
            }
            fields = removeTrailingQuotes(fields);
         // System.out.println(Arrays.toString(fields));
            filcont++;           //    form_geo.datoGlobal=String.valueOf(filcont);
            line = br.readLine();
         }
         d=null;br=null;zn=null;bzn=null;
//////////////////////////ORDENANDO LA LISTA////////////////////////////////////////////////////////////
        Collections.sort(l, new Comparator<llaves>(){
              public int compare(llaves o1, llaves o2) {
                        int resultado = Double.compare( o1.getValorNum(), o2.getValorNum());
                if ( resultado != 0 ) { return resultado; }
                
                  return resultado;
                    }  
                }); 
/////////////////////RECORREMOS LA LISTA ORDENADA/////////////////////////////////////////////////////////////////
          exportarCsv(encabezados+";Codigo;Cadena;Consecutivo");//escribimos encabezados
        int cont=0;
         for(llaves valores:l){
             if(valores!=null){
                 String a,b,c,e;
                 a=valores.getTexto();b= valores.getCodigo();c=valores.getCadena();
                 cont++;
                 exportarCsv(a+";"+b+";"+c+";"+cont);
                 //JOptionPane.showMessageDialog(null,valores.getCadena());
             }
         }
         
       System.gc();zn=null;bzn=null;l=null;br=null; 
///////////////////////////////////////////////////////////////////////////////////////////////
         JOptionPane.showMessageDialog(null, "Proceso Finalizado");
      } catch (Exception e) {
         if (null!=br) {
            br.close();}
      }  
}
private static String[] removeTrailingQuotes(String[] fields) {

      String result[] = new String[fields.length];

      for (int i=0;i<result.length;i++){
         result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
      }
      return result;
   }
public  void exportExcel(Object data[][], Object cabecera[]) throws BiffException {
    int columnas=data.length,matchfil;
    try
    {
    List  lista =  ordenar(data);// ORDENA LA LISTA YA PROCESADA
     
     
      String strPath = System.getProperty("user.home");//"C:\\Users\\57321\\Documents\\";
      WritableWorkbook workbook = Workbook.createWorkbook(new File(strPath+"\\Documents\\reporte.xls"));
      
      WritableSheet sheet = workbook.createSheet("Reporte", 0);
      
    
        //ENCABEZADOS//label= new Label(col, row, "Titulo");
        for(int cont=0;cont<columnas;cont++){
            
        sheet.addCell(new jxl.write.Label(cont, 0,(String) cabecera[cont]));
        }
        
        //for(int colm=0;colm<columnas;colm++){
          int contlist=0,fil=1; //match = (int)data[colmatch][fil];//AUN NO
        while(contlist<listaOrden.size()) {
            matchfil=(int)listaOrden.get(contlist)-1;
            //for(int fil=0;fil<filas;fil++){
                //////////despues de encontrar la fila falta recorrer esa fila unicamente
                for (int colr=0;colr<columnas;colr++){
                  if (columnas-1==colr){data[colr][matchfil]=contlist+1;}
                  sheet.addCell(new jxl.write.Label(colr, fil, String.valueOf(data[colr][matchfil]))); 
                 
                }
              // sheet.addCell(new jxl.write.Label(colm, fil+1, String.valueOf(data[colm][matchfil]))); 
               int c=contlist; ////////////////////////////////////////////
           // }
            contlist++;fil++;
        }  
        //}
       //sheet.addCell(label);
       ////////////////////////////////////////////////////////////
       //Escribe todos los label creados al workbook sino esta el write se crea el archivo vacio.
            workbook.write();   
            workbook.close();
            lista=null;sheet=null;workbook=null;
            System.gc(); 
            //Desktop.getDesktop().open(new File(strPath+"reporte.xls"));
    }
    catch (IOException ex)
    {
        JOptionPane.showMessageDialog(null,"Error al crear el fichero. Error: "+ex);
    }
    catch (WriteException ex)
    {
        JOptionPane.showMessageDialog(null,"Error al escribir el fichero. Error: "+ex);
    }
}
public  void exportExcelFil(Object data[][], Object cabecera[],String nomArchivo) throws BiffException {
    int columnas=cabecera.length,filas=data.length;
    try
    {
    
     
     
      String strPath = System.getProperty("user.dir");//"C:\\Users\\57321\\Documents\\";
      WritableWorkbook workbook = Workbook.createWorkbook(new File(strPath+"\\Documents\\"+nomArchivo+".xls"));
      
      WritableSheet sheet = workbook.createSheet("Reporte", 0);
      
    
        //ENCABEZADOS//label= new Label(col, row, "Titulo");
        for(int cont=0;cont<columnas;cont++){
            
        sheet.addCell(new jxl.write.Label(cont, 0,(String) cabecera[cont]));
        }
        
        //for(int colm=0;colm<columnas;colm++){
          int contlist=0,fil=1; //match = (int)data[colmatch][fil];//AUN NO
        while(contlist<filas) {
            //matchfil=(int)listaOrden.get(contlist)-1;
            //for(int fil=0;fil<filas;fil++){
                //////////despues de encontrar la fila falta recorrer esa fila unicamente
                for (int colr=0;colr<columnas;colr++){
                  int dato=Integer.parseInt((String) data[contlist][colr]);//if (columnas-1==colr){data[colr][matchfil]=contlist+1;}
                  sheet.addCell(new jxl.write.Number(colr, fil, dato));
                  // sheet.addCell(new jxl.write.Label(colr, fil, (String) data[contlist][colr]));
                }
       
            contlist++;fil++;
        }  
        //}
       //sheet.addCell(label);
       ////////////////////////////////////////////////////////////
       //Escribe todos los label creados al workbook sino esta el write se crea el archivo vacio.
            workbook.write();
            workbook.close();
            sheet=null;workbook=null;System.gc();
            //Desktop.getDesktop().open(new File(strPath+"reporte.xls"));
    }
    catch (IOException ex)
    {
        JOptionPane.showMessageDialog(null,"Error al crear el fichero. Error: "+ex);
    }
    catch (WriteException ex)
    {
        JOptionPane.showMessageDialog(null,"Error al escribir el fichero. Error: "+ex);
    }
}
 public void exportarCsv(String valor) throws IOException {

     try{
         
        String strPath = System.getProperty("user.home");  
        String f = strPath+"\\Documents\\result.csv";
        File file = new File(f); 
    //FileOutputStream fis = new FileOutputStream(f);
    if (!file.exists()) {
                file.createNewFile();
            }
    
    FileWriter  dos=new FileWriter(f,true);//FileWriter  dos=new FileWriter(f,true);
    
    
    dos.write((valor==null)?"null":valor);
    dos.write("\n");

    //i=i+1;
    //}
    //JOptionPane.showMessageDialog(null, "Proceso Finalizado");
    dos.close();
    }
    catch(FileNotFoundException e){
    System.out.println("No se encontro el archivo");
    }
    catch(IOException e){
    JOptionPane.showMessageDialog(null, "error");
    }
 }
private List ordenar(Object matriz[][]) {
    double valor;int consecutivo = 0;ArrayList <llaves> l=new ArrayList();
    String cadena,codigo,zona; 
    int filas=matriz[0].length, columnas= matriz.length, colmatch=columnas-6;
    for(int i=0;i<filas;i++){
          codigo= (String)matriz[colmatch][i];
          cadena= (String)matriz[colmatch+1][i];
          valor = (Double)matriz[colmatch+2][i];
          consecutivo = (int)matriz[colmatch+3][i];
          zona=(String)matriz[colmatch+4][i];
          l.add(new llaves(consecutivo, cadena, codigo, valor));
    }
          Collections.sort(l, new Comparator<llaves>(){
              public int compare(llaves o1, llaves o2) {
                        int resultado = Double.compare( o1.getValorNum(), o2.getValorNum());
                if ( resultado != 0 ) { return resultado; }
                
                  return resultado;
                    }  
                });   
          listaOrden = new ArrayList();
         for(int c=0;c<l.size();c++) {
          listaOrden.add(l.get(c).getConsecutivo());
            }
    l=null;//destruccion de objeto*/
      //JOptionPane.showMessageDialog(null,consecutivo);   

   // 
    return l;
}

private Object verificarArchivo(String archivo) throws IOException{
    int filas,columnas,column,fila,filcont=0,contNum=0,contMatch=0,contLtr=0;
    String cabecera[]={"Concecutivo","CodigoCiudad"};String cuerpo[][];
    int control=0;String match = null;
         FileInputStream fis = null;
        
        fis = new FileInputStream(archivo);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        Iterator  rows = sheet.rowIterator();
    filas = sheet.getPhysicalNumberOfRows();
    cuerpo= new String[filas-1][2];
    columnas = sheet.getRow(0).getPhysicalNumberOfCells();
    
     while (rows.hasNext()) {//RECORRE POR FILAS Y COLUMNAS CADA CELDA
             HSSFRow row = (HSSFRow) rows.next();
                 Iterator cells = row.cellIterator();
                 //List data = new ArrayList();
            while (cells.hasNext()) { //PASA A LA SIGUIENTE LINEA
                  
                    HSSFCell cell = (HSSFCell) cells.next();
                    column = cell.getColumnIndex(); fila = cell.getRowIndex();
                if ((column==1)&&(filcont>0)){//aqui aunque recorre toda la matriz solo ejecuta la accion sobre la columna 0 fila 1 ignora encabezados    
                  //getCellType   1=letras  0=numeros
                     if(cell.getCellType()==0){contNum++;}
                     if(cell.getCellType()==1){contLtr++;match=buscarIdCiudad(cell.toString().toLowerCase());
                     if(match!=null){
                     cuerpo[contMatch][0]=String.valueOf(contMatch+1);
                     cuerpo[contMatch][1]=match;contMatch++;}
                     }//JOptionPane.showMessageDialog(null,cell.toString()+"#"+ match);
                    
                 }//fin if
                
            } //fin while 
            filcont++;
     }// fin while
     Object informe[]=new Object[4];control=filcont-1-contNum;
     informe[0]=filcont-1-contNum;informe[1]=filcont-1;informe[2]=contNum;informe[3]=contMatch;
     if(control==0){return informe;}
     if(contMatch==filcont-1){try {
         informe[0]=filcont-1;informe[1]=filcont-1;informe[2]=contNum;informe[3]=contMatch;
      exportExcelFil(cuerpo,cabecera,"listCiudades");JOptionPane.showMessageDialog(null,"Codigos encontrados "+informe[3]+
          "\n Registros Procesados "+ informe[1]+ "\n Revisar Archivo Generado \n"+System.getProperty("user.dir")+"\\listCiudades.xls");
        } catch (BiffException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     return informe;
}

public  String buscarIdCiudad(String cadena){
    String result = "";
         //ciudades c = new ciudades();c.importCiudades();
          cadena = cadena.toLowerCase();
        //result= ciudades.ciudades.indexOf(cadena);
        String getKeysFromValue =  getMultipleKeysByValue(ciudades.mapCiud, cadena);
       
         result= getKeysFromValue;
    
     return result;
}
  public  String getMultipleKeysByValue(Map<String, String> map, Object value) {
    
      for (Entry<String, String> entry : map.entrySet()) {
          if(entry.getKey().equals(value)){
           return  entry.getValue();   
          //JOptionPane.showMessageDialog(null, entry.getValue());
          }
      }
      return null;
       
    } 

}//fin class


 class llaves implements Comparator<llaves>{
   String texto;
   String zona;
   String cadena;
   String codigo;
   int consecutivo;
   Double valorNum;

    public llaves(String texto, String zona, String cadena, String codigo, int consecutivo, Double valorNum) {
        this.texto = texto;
        this.zona = zona;
        this.cadena = cadena;
        this.codigo = codigo;
        this.consecutivo = consecutivo;
        this.valorNum = valorNum;
    }
  
    public llaves(int consecutivo,String cadena, String codigo, Double valorNum) {
        this.cadena = cadena;
        this.codigo = codigo;
        this.consecutivo = consecutivo;
        this.valorNum = valorNum;
        
    }
// <editor-fold defaultstate="collapsed" desc="Generated Code Getter and Setter">    

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
    
    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Double getValorNum() {
        return valorNum;
    }

    public void setValorNum(Double valorNum) {
        this.valorNum = valorNum;
    }
// </editor-fold>
    @Override
    public int compare(llaves t, llaves t1) {
         int resultado = Double.compare( t.getValorNum(), t1.getValorNum());
        if ( resultado != 0 ) { return resultado; };
        return resultado;
    }
     
   
}


