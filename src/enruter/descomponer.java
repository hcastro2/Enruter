package enruter;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


public class descomponer {
// la idea es tomar el texto original y eliminar los posibles errores y particularidades del lenguaje comun    
    public int contnum=0,contLetra1digito=0,contltr=0;Object array[] = new Object[14];String finnumericos,numeric1,numeric2,numeric3;
    String letraabc []= new String[2],sintaxis,vectorabc=""; Double valor=0.0;static String textIni,garbagColect="",DirErrColect="";
    String cadenaq1=".",cadenaq2=".",cadenaq3=".",cadena3num,cadena2num,cardinal1,cardinal2;
   static Map <String ,String> dirsXYnull = new HashMap<> ();
  public void inicomponentes() {
        this.finnumericos = "";
        this.numeric1 = "";this.numeric2 = "";this.numeric3 = "";
        this.contnum=0;this.contLetra1digito=0;this.contltr=0;
        this.sintaxis = "";
        //this.array = null;this.letraabc = null;
    }   
    public String texting (String textoOrigen){//FUNCION PRINCIPAL
        //eliminar del texto original puntos y espacios dobles y caracteres especiales y otros aspectos del lengujae comun//
         textoOrigen = preformatear(textoOrigen);textIni=textoOrigen;
    
            String result="",sub;int cont=0 ;
             StringTokenizer token = new StringTokenizer(textoOrigen," ");
              while (token.hasMoreTokens()){
                  
              sub = (token.nextToken());cont++;
              result = result+"."+sub;
                                           }
              result = result.substring(1, result.length()) ; 
              //JOptionPane.showMessageDialog(null, result);
              result = alfanumeric(result);//descomponer alfanumericos
              result = textdelet(result);//eliminando texto inicesario
              result = sintax(result);//Corregir problemas de sintaxis
              expresion(result);//GENERA LA EXPRESION FINAL ANTES DE GENERAR EL CODIGO;SE REQUIERE CONTROL DE SINTAXIS MINIMA
              //JOptionPane.showMessageDialog(null, sintaxis);//define ltr=cl,cr,etc+cantidad de textos y numericos
              if (("ltr".equals(sintaxis))&&(contltr>0)&&(contnum>2)){//EJECUTA PROCESO DE CODIGO SI LA SINTAXIS MINIMA SE CUMPLE
              result=divExpresion(result);
              coordenada(result);
              cardinal(result);/*subvial(result);*/
              subVia(result);
              subcardinal(result);
              codigo();
              }else{//DE LO CONTRARIO PASAMOS A REVISAR OTROS CASOS DE SINTAXIS
                  
                result=especialcase(result);  
              }
              if ((array[9]==null)||("0".equals(String.valueOf(array[9])))){}else{result = result+array[9];}//VUELVE A COMPLETAR LA CADENA QUE SE HABIA DIVIDIDO PARA MAYOR CONTROL
            return result;
    }
//////////////////////////PREFORMATEAR LA EXPRESION//////////////////////////////////////  
    public String preformatear(String texto){
  
        if ("".equals(texto)){texto="Null";}
        texto = texto.trim();texto=texto.replace("N#","");texto=texto.replace("¥","");texto=texto.replace("NË"," ");
        texto=texto.replace("N?"," ");texto=texto.replace(" NÉ "," ");texto=texto.replace(" N` "," ");texto=texto.replace(" N§ "," ");
        texto=texto.replace(" Nø "," ");texto=texto.replace(" ?Ñ "," ");texto=texto.replace(" NÂ° "," ");
        texto=texto.replace(" S/N "," sn ");texto=texto.replace(" s/n "," sn ");texto=texto.replace("N°","");
        texto=texto.replace("N�","");texto=texto.replace("�","");
        texto=texto.replace("?","#");texto=texto.replace("æ"," ");texto=texto.replace("N§"," ");texto=texto.replace("\\"," ");
        texto = texto.replaceAll("[#-/_]", " ");//caracteres especiales que se reemplazan por espacio
        texto = texto.replaceAll("[–.,;:·]", " ");texto = texto.replaceAll("  ", " ");
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);           ///  ESTAS LINEAS
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", ""); ///  ELIMINAN TILDES 
        texto = texto.toLowerCase();//eliminar mayusculas   
        //////////////ESTANDARIZAR EXPRESIONES
    texto=texto.replace("ª","#");texto=texto.replace("º","#");texto=texto.replace("°","#");texto=texto.replace("*","");
    texto=texto.replace("½"," ");texto=texto.replace("ï"," ");texto=texto.replace("¿"," ");texto=texto.replace("\"","");
    texto=texto.replace("ak ","cr av");texto=texto.replace("ac ", "cl av");texto=texto.replace("1ra", "1");texto=texto.replace("3ra", "3 ");
    texto=texto.replace("carrera","cr");texto=texto.replace("karrera", "cr ");texto=texto.replace("cra ", "cr ");texto=texto.replace("kra", "cr ");
    texto=texto.replace("kr ", "cr ");
    texto=texto.replace("nâ°", "#");texto=texto.replace("n0", "#");
    texto=texto.replace("manzana ", "mz ");texto=texto.replace("manz ", "mz ");texto=texto.replace("manza ", "mz ");
    texto=texto.replace("manzana", "mz ");texto=texto.replace("manzan ", "mz ");texto=texto.replace("mza ", "mz ");texto=texto.replace("mzn ", "mz ");
    texto=texto.replace("lote ", "lt ");
    texto=texto.replace("numero", " ");texto=texto.replace("/", " ");
    texto=texto.replace("kilometro", " km ");
    texto=texto.replace(" Nª ","#");texto=texto.replace(" Nº ","");
    texto=texto.replace(" casa ", " cs ");
    texto=texto.replace("transversal ", "tv ");texto=texto.replace("trr ", "tv ");texto=texto.replace("transv ", "tv ");
    texto=texto.replace("trv ", "tv ");texto=texto.replace("trasv ", "tv ");texto=texto.replace("tras ", "tv ");texto=texto.replace("trasnv ", "tv ");
    texto=texto.replace("trasversal", "tv ");texto=texto.replace("trans ", "tv ");texto=texto.replace("tr ", "tv ");
    texto=texto.replace("diagonal", "dg ");texto=texto.replace("diag ", "dg ");texto=texto.replace("dig ", "dg ");
    texto=texto.replaceFirst("callejon ", "cjn ");
    texto=texto.replaceFirst("calle ", "cl ");texto=texto.replace("calle ", "cl ");texto=texto.replace("cll ", "cl ");
    texto=texto.replace("clle ", "cl ");texto=texto.replace("call ", "cl ");
    texto=texto.replace("nro ", " ");texto=texto.replace("NRO", " ");texto=texto.replace(" no ", " ");texto=texto.replace(" No ", " ");
    texto=texto.replace(" oe ", " oeste ");texto=texto.replace(" es ", " este ");
    texto=texto.replace("norte", " norte ");texto=texto.replace(" nte ", " norte ");texto=texto.replace("sur", " sur ");
    texto=texto.replace("b/"," "); texto=texto.replace("avenida","av");texto=texto.replace("avda","av");
    texto=texto.replace("aven","av ");
    texto=texto.replace("n#","#"); texto=texto.replace("#"," ");
     /////////////////////////////////////////////////////////////////////////////////////////////
                                                                               
     if ((texto.isEmpty())||(" ".equals(texto))) {texto="null:"; } 
//////////////////////////////////////////////////////////////////////////////////////////////////   
    
     return texto;   
    }
//////////////////SUBFUNCIONES PARA DESCOMPONER MAS EL TEXTO//////////////
 public String alfanumeric (String texto){//determina si es alfanumerico un texto
       String result="",sub = null,text;
       int cont = 0; ArrayList<String> cadena = new ArrayList<>();
      StringTokenizer token = new StringTokenizer(texto,".");
              while (token.hasMoreTokens()){
              sub = (token.nextToken());
              if (!"".equals(sub)){}
                if ((sub.matches("[a-zA-Z]+")==false) && (sub.matches("[0-9]+")==false)){//recorre y confirma alfanumericos
                    separar(sub);//result= texto.replace(sub,separar(sub));
                      //JOptionPane.showMessageDialog(null,separar(sub));
                      cont++;cadena.add(separar(sub));
                     }
                                          }
              for (int i =0; i < cadena.size();i++){text = cadena.get(i);text=text.replace(".", "");
                  texto= texto.replace(text,cadena.get(i)); 
                  //JOptionPane.showMessageDialog(null,text); 
              }
            result=texto;
        result=("".equals(result))?texto:result;                   
       return result;
                                        } 
    public String separar (String alfanum){
     String result,acum="";int largo = alfanum.length(); //char a,b = 0;     String result="",a,l="",n="",t="",sub,acum="";int largo = alfanum.length(); //char a,b = 0;
     
    /*    for (int i =0; i < largo;i++){
            a = alfanum.substring(i, i+1);
            
             if ((isletra(a)==true)){
               l= l+a;n=n+"."; }
             if ((isnumeric(a)==true)){
               n=n+a;l=l+"."; }                       
              t=n+"."+l;          //t=n+"."+l;  oroginal
        }   */
/////////////////////////nuevo texto///////////////////
String c1="",c2 = "";         
   for (int i =1; i < largo;i++){
            c1 = alfanum.substring(i-1, i);
            c2 = alfanum.substring(i, i+1);
        if (!isTipo(c1).equals(isTipo(c2))) {  // JOptionPane.showMessageDialog(null,isTipo(c1)+"-"+isTipo(c2));
           acum=acum+c1+"."; 
        }else{
            acum=acum+c1;
        }
                           // System.out.println(isTipo(c1)+"-"+isTipo(c2));           
        } 
   acum= acum+c2;result=acum;
////////////////ojo texto original///////////////////
  /*     StringTokenizer token = new StringTokenizer(t,".");
              while (token.hasMoreTokens()){
              sub = (token.nextToken());
             
              if (!"".equals(sub)){
                alfanum=alfanum.replace(sub,sub+".");// alfanum=alfanum.replace(sub, sub+".");original
                                   }
              }
              result=alfanum;//JOptionPane.showMessageDialog(null,alfanum);*/
     return result;
 }//SUBFUNCION DENTRO DE ALFANUMERIC
 public String textdelet (String texto){//eliminar textos inecesarios como No,etc K,etc 
       String result="";int control = -1;
       if((texto.contains(".cl.")==false)&&(texto.contains(".cr.")==false)){control=0;};
       texto = texto.replace(".No.", "."); texto = texto.replace(".nr.", ".");texto = texto.replace(".ni.", ".");
       //if ((texto.contains("av."))&&(control==0)){sintaxis="av?";}//si no contiene la expresion completa avenida mas calle o carrera asume estandar de avenida calle
      String segmentos[] = texto.split("\\.");
      //CORREGIMOS Y ELIMINAMOS AL PRINCIPIO DE LA NOMENCLATURA
      
      if ((segmentos.length>1)&&(segmentos[0].length()==1)&&("n".equals(isTipo(segmentos[1])))){result=corregirText(texto,segmentos);}//caso c=calle k=carrera
      if (segmentos[0].contains("avn")){result=corregirText(texto,segmentos);}//subfuncion corregirtext encapsuladas en funciones y utiliddes
      if (segmentos[0].contains("crr")){result=corregirText(texto,segmentos);}
      if (segmentos[0].contains("car")){result=corregirText(texto,segmentos);}
      if (segmentos[0].contains("cra")){result=corregirText(texto,segmentos);}
      if ((segmentos[0].contains("kr"))||segmentos[0].contains("krr")){result=corregirText(texto,segmentos);}
      if (segmentos[0].contains("cll")){result=corregirText(texto,segmentos);}
       if (segmentos[0].contains("calle")){result=corregirText(texto,segmentos);}
      if (segmentos[0].contains("diag")){result=corregirText(texto,segmentos);}
      if ((segmentos[0].contains("tran"))||(segmentos[0].contains("tranv"))||(segmentos[0].contains("trav"))){result=corregirText(texto,segmentos);}
         Boolean controlXY=  dirsXYnull.containsKey(segmentos[0]);    //CONTROL PERSONALIZADO CON AYUDA DE CORRECCIONES DEL USUARIO
         if((controlXY==true)&&(valor!=null)){
         String valor = dirsXYnull.get(segmentos[0].toString());result=texto.replaceFirst(segmentos[0]+".", valor+".");
         }
      
         if((buscarH("ca", segmentos)>-1)&&(texto.contains("mz"))){
         segmentos[buscarH("ca", segmentos)]="cs";   result=arrayTostring(segmentos) ;}
      if("".equals(result)){result=texto;}//si no encuentra correccion devuelve el texto completo
       return result;
                                        }
 public String sintax (String txt) { //POSICION.LARGO.CASO.SUBCADENA///////////////
    int contnumeros=0;String acumulador=".",acumulador2=".",garbagetxt="",cola=".",cola2=".";String sub="",numPrimo="";// es necesario que previamente se tome el texto hasta el final del 3 numero
    StringTokenizer token = new StringTokenizer(txt,".");
              while (token.hasMoreTokens()){
                  sub = (token.nextToken());
                  if ((isnumeric(sub)==false)&&(isletra(sub)==false)){garbagetxt=garbagetxt+sub+".";}//COLECTOR DE TEXTO BASURA
                  if (contnumeros<2){acumulador2=acumulador2+"."+sub;}else{cola2=cola2+"."+sub;}
                  if (contnumeros<3){acumulador=acumulador+"."+sub;}else{cola=cola+"."+sub;}
                  if (isnumeric(sub)==true){contnumeros++;} 
                  if((isnumeric(sub)==true)&&(contnumeros==1)){numPrimo=sub;}
              }
      //exportar_TextBasura(        
   if(garbagetxt.length()>=1){
       txt=GarbagDelet(garbagetxt,txt);acumulador=GarbagDelet(garbagetxt,acumulador);
       acumulador2=GarbagDelet(garbagetxt,acumulador2);//String s = new String(garbagetxt.getBytes("ISO-8859-1"), "UTF-8");
       garbagColect=garbagColect+garbagetxt+"\n";
        //ELIMINA  TEXTO BASURA System.out.println(textIni+"-"+garbagetxt);     
}
   
   
   if(contnumeros==2){acumulador=acumulador2;}else{}  
   cadena2num=acumulador2.replace("..", ".")+".";
   cadena3num= acumulador.replace("..", ".");//aqui guarda en memoria la cadena hasta el tercer numero
   /////////////////////////////////////FIN DEL CONTROL PREVIO ARROJA SUBCADENA HASTA EL 3 NUMERO           
    String result;txt=acumulador+".";txt = txt.replace("..", ".");
        int caso=0,cont=0,largo=0,point = 0;
        ArrayList<String> subcadena = new ArrayList<>();//NO PUEDO USAR ARRAY LIST
         token = new StringTokenizer(txt,".");
              while (token.hasMoreTokens()){
             caso=0; sub = (token.nextToken());largo=sub.length();point=txt.indexOf(sub);
             
                  if ((largo==2)&&(sub.contains("n"))&&(!"no".equals(sub))){caso=1;}//caso 1
                  if ((largo==2)&&(sub.contains("e"))){caso=2;}//caso 2 casos 3 y 4 reservados para Sur y oriente
                  if ((largo==1)&&(sub.equals("t"))){caso=5;}//caso transversales
                  if (caso==1){subcadena.add(caso+","+point+","+largo);}
                  if (caso==2){subcadena.add(caso+","+point+","+largo);}
                  if (caso==5){subcadena.add(caso+","+point+","+largo);}
                    }
 ///////////////////MODIFICAR YA QUE NO CUMPLE: CONTROL ENTRE EL PRIMER NUMERO Y EL ULTIMO////////////////
  int pointMin=-1;//punto de inicio de la correccion de sintaxis 
   if("".equals(numPrimo)){}else{
   pointMin=txt.indexOf("."+numPrimo+".");}
 
 /////////////////////////CONTROLAMOS QUE EL TEXTO ESPECIAL ESTE ENTRE EL PRIMER CARDINAL Y EL ULTIMO NUMERO///////////////////////////////////////////////////////////////////////////////
     String control = matchText(txt,"cl,dg,cr,tv,av");int bandera;//
    if((control==null)||(contnumeros<=1)){bandera=-1;}else{       // if((control==null)){bandera=-1;}else{ 
        bandera=txt.indexOf("."+control+".");}                    // 
 //////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////CORREGIR CASOS ESPECIALES DE SINTAXIS/////////////////////
       cont=0; 
        for (String subcadena1 : subcadena) {
            String original,reemplazo;
            sub=subcadena.get(cont);int w = Integer.parseInt(sub.substring(0,1));int inicio,larg;
              Object  dato[]= sub.split(",");
              if ((Integer.parseInt((String)dato[1])<bandera)||(bandera==-1)||(Integer.parseInt((String)dato[1])<pointMin)){}else{
              switch(w){
                case 0:
             
                 break;
                case 1: //PRIMER CASO CON DOS CARACTERES + n ESTANDARIZA A LA NOMENCLATURA NORTE   
                  Object  data[]= sub.split(",");
                  inicio= Integer.parseInt((String) data[1]);larg= Integer.parseInt((String) data[2]);
                  original="."+txt.substring(inicio,inicio+2)+".";
                   reemplazo=   original.replace("n", ".norte.");//reemplazo="."+txt.substring(inicio,inicio+1)+".norte.";
                  txt = txt.replace(original,reemplazo); //JOptionPane.showMessageDialog(null,txt);
                     data=null;
                 break;
                case 2:
                    Object  data1[]= sub.split(",");
                  inicio= Integer.parseInt((String) data1[1]);larg= Integer.parseInt((String) data1[2]);
                  original="."+txt.substring(inicio,inicio+2)+".";
                  reemplazo=   original.replace("e", ".este.");
                  txt = txt.replace(original,reemplazo); //JOptionPane.showMessageDialog(null,txt);
                  data1=null;
                   break;
                case 5:
                    Object  data2[]= sub.split(",");
                  inicio= Integer.parseInt((String) data2[1]);larg= Integer.parseInt((String) data2[2]);
                    original="."+txt.substring(inicio,inicio+1)+".";
                    reemplazo=".tv.";
                    if((!"".equals(cadena2num))&&(cadena2num.contains(".t."))){//REEMPLAZAR T POR TV SI SE ENCUENTRA ANTES DEL SEGUNDO NUMERO
                    txt = txt.replace(original,reemplazo);} //JOptionPane.showMessageDialog(null,txt);
                    data1=null;
                    break;
                default:  
              
                      }//end switch
                 }//end if
            cont++;
        }//end for
      cadena3num =  generarCadena3num(txt);
       if (contnumeros>=3) {txt=txt+cola;}else{txt=txt+cola2;} //COMO SEPARE LA CADENA EN DOS AHORA LA VUELVO A UNIFICAR
       txt = txt.replace("..", ".");txt = txt.replace("..", ".");txt=txt.replaceFirst(".", "");
        
       result=txt;
       return result; 
    }
////////////FUNCIONES PARA TRANFORMAR UNA EXPRESION DEL LEXICO A EXPRESION NUMERICA CARTESIANA/////////////
// <editor-fold defaultstate="collapsed" desc="Generated Code Configuracion del Codigo"> 
/////CONFIGURACION OBJETIVO 0.0.0.0.0.0.0.0.0.0.0.0.0.0 14 ESPACIOS//////////////////////////////////////
/////[0.][0.0.0.][0.0.0.][0.0.][0.0.][0.0][0]=[CARDINAL][VIA,SUBVIA EJE X,#][VIA,SUBVIA EJE Y,# ][#CASA][SUB#CASA][SUBSECTORES]
//posicion [0] COORDENADA
//POSICION 1   CALLE, DIAGONAL O UBICACION EJE X
//POSICION 2   NUMERO DE LA CALLE
//POSICION 3   SUBCALLE a,b,c,d... etc   
//POSICION 4   CARRERA, TRANSVERSAL O UBICACION EJE Y
//POSICION 5   NUMERO DE LA CARRERA
//POSICION 6   SUBCALLE a,b,c,d... etc
//POSICION 7   UBICACION DEL NUMERO DE LA CASA 1 POR LA CARRERA 0 POR LA CALLE
//POSICION 8   NUMERO DE LA CASA   
//POSICION 9   NUMERO DE SUBCASA = PISO  USAR POR EL MOMENTO PARA AGREGAR TODO EL TEXTO COMPLEMENTARIO
//POSICION 10  NUMERO DE SUBCASA = APARTAMENTO    
//POSICION 11  RESEVADO:GENERAMOS EL CODIGO DE CUADRANTE PREVIO A LA ZONOFICACION   (4.5) 
//POSICION 12  RESEVADO:CODIGO DE LA CIUDAD
// </editor-fold>     
public void expresion (String exp){ //String expresion (String exp){
    //primero convertir el texto formateado en un subcodigo o id_direccion comparable con sus similares
    //de la forma siguiente:  urbano.cl.23x.34y.45.z.k  norte.cl.23a.34b.45.1.1
    try{
    String sub;
    String base = "cl,cr,dg,tv", control = "null";
    //determinar si el texto recibido es numerico o string
   StringTokenizer token = new StringTokenizer(exp,".");
              while (token.hasMoreTokens()){
              sub = (token.nextToken());
              if ((isnumeric(sub)==true)&&(contnum==2)){array[8]=sub;}//puede ser un error
              if ((isnumeric(sub)==true)&&(contnum==0)){numeric1=sub;}//puede ser un error
              if ((isnumeric(sub)==true)&&(contnum==1)){numeric2=sub;}//puede ser un error
              if (isnumeric(sub)==true){contnum++;finnumericos=sub;}
              if ((isnumeric(sub)==true)&&(contnum==3)){numeric3=sub;}
              if (isletra(sub)==true){contltr++;}if (buscar(sub,base)==true){control="ltr";}
             // if ((isletra(sub)==true)&&(sub.length()==1)){vectorabc=vectorabc+sub+",";}//CASO CON MAS DE 2 LETRAS PARA DEFINIR SUBVIA
              if((contnum<3)){
                  
              if ((contnum>0)&&(isletra(sub)==true)&&(sub.length()==1)&&(!"n".equals(sub))){vectorabc=vectorabc+sub+",";letraabc[contLetra1digito]=sub;contLetra1digito++;}
              }
          //contnum cuenta cada subcadena con datos numericos
          if (contnum<2){cadenaq1=cadenaq1+"."+sub;}
          if ((contnum>=2)&&(contnum<3)){cadenaq2=cadenaq2+"."+sub;}       
                                           }
 
            
    //////////ES NECESARIO QUE LA SINTAXIS EL CARDINAL SEA PRIMERO QUE EL PRIMER NUMERO//////////////////////////// 
    cardinal1=matchText(cadenaq1,"cl,dg,cr,tv,av"); 
    int pointCardinal,pointnumeric1; pointnumeric1=exp.indexOf(numeric1); pointCardinal=  exp.indexOf(cardinal1); 
      if (pointnumeric1<pointCardinal){control="invertido";}//caso especial donde se invierte la estructura de la nomenclatura  
      if ("av".equals(cardinal1)){control="null";}
      if (cadenaq1.contains(".km.")){control="null";}
      

       sintaxis=control;// JOptionPane.showMessageDialog(null, contltr+"#"+contnum+"#"+control);
     }
    catch(Exception e){
        sintaxis="null";//JOptionPane.showMessageDialog(null, "error");
        String subviales[] = vectorabc.split(","); 
        if(subviales.length>=3){sintaxis="vialExtra";}//ERROR MAS DE DOS SUBVIALES
        //if(cardinal1!=null){sintaxis="invertido";}//cardinal1=matchText(cadenaq1,"cl,dg,cr,tv,av");
        String exp2="."+exp;if ((cardinal1!=null)){}else{
      if ((exp2.contains(".mz."))||(exp2.contains("mz."))){sintaxis="mz";}
      if ((exp2.contains(".km."))||(exp2.contains("km."))){sintaxis="null";}
    }
      //if (cardinal1==null){sintaxis="null";}
    }  
}   
public String divExpresion (String s){
    String textInicio,textFinal;
    int posicion_numeric;//array[0]=0;
   posicion_numeric=cadena3num.lastIndexOf(numeric3);textInicio=s.substring(0,posicion_numeric+numeric3.length());//limitamos la busqueda hasta la ultima posicion numerica evitando casos de expresiones muy largas
   textFinal = s.substring(posicion_numeric+numeric3.length(),s.length());
   //ES NECESARIO DETERMINAR MAS ESPECIFICAMENTE ESTOS CASOS YA QUE LA N SE EXCLUYE DE LAS NOMENCLATURAS COMO SUBVIA PERO SIRVE PARA DETERMINAR SI ES NORTE DE MANERA ABREVIADA PERO TAMBIEN PARA LA PALABRA NUMERO
   textInicio = textInicio.replace(".n.", ".");/////DEBE TENER UNA VALIDACION PREVIA
   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    array[9]=textFinal;
   //JOptionPane.showMessageDialog(null,textFinal);
    
    return textInicio;
}
public void coordenada(String s){//determina cardinal:norte,sur,oeste,oriente
    array[0]=0;
      //try{ 
   String base[] = {"null=urbano","norte","sur","este","oeste"};int cont=0;//,posicion_numeric;//array[0]=0;
   s=cadena3num;
 // posicion_numeric=s.lastIndexOf(numeric3)+numeric3.length();s=s.substring(0,posicion_numeric);//limitamos la busqueda hasta la ultima posicion numerica evitando casos de expresiones muy largas
    
   for (String base1 : base) {
       
       if (s.indexOf("."+base[cont]+".")>0){
           
           array[0]=cont;
                                           }
                 cont++;
                             }
      //JOptionPane.showMessageDialog(null,array[0]);
}
public void cardinal(String c){//busca los cardinales con eje X=cl,dg,etc eje Y=cr,tv,etc y los ubica en sus posiciones del id
   String match = "";
    String base[] = {"cl","dg","cr","tv","mz","ak","ac"};int cont=0;String q = null;
   c="."+c;array[1]="";array[4]="";int point = c.indexOf("."+numeric1+".");
   for (String base1 : base) {
       int z = cont; q = c.substring(0, point+1);
       //JOptionPane.showMessageDialog(null,q);
       if ((q.indexOf("."+base[cont]+".")>-1)){
             match="."+base[cont]+".";
            switch(z){
              case 0: case 1:
                  array[1]=cont;array[2]=numeric1;array[5]=numeric2;array[7]=1;//subvial(c);//caso eje x 
                  break;
              case 2: case 3:
                  array[4]=cont;array[5]=numeric1;array[2]=numeric2;array[7]=0;
                  break;
                 
              case 4: 
                  array[1]=cont;
                  break;
              case 5: 
                  array[1]=0;array[4]=cont;array[5]=numeric1;array[2]=numeric2;array[7]=0;
                  break;  
              case 6: 
                   array[1]=cont;array[2]=numeric1;array[5]=numeric2;array[7]=1;//subvial(c);//caso eje x
                  break;    
              default: 
                 
            }
           
              
        }
    cont++;  
   }  
   if((array[4]=="")&&(array[1].equals(0))){array[4]=2;};if((array[4]=="")&&(array[1].equals(1))){array[4]=2;}  
   if((array[1]=="")&&(array[4].equals(2))){array[1]=0;};if((array[1]=="")&&(array[4].equals(3))){array[1]=0;}
   if((array[1]=="")&&(array[4]=="")){array[1]=0;array[4]=0;}
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
  //HASTA AQUI BUSCA EN LOS PRIMERAS SUBACADENAS SI INICIA POR CL O CR PERO FALTA DEFINIR CUANDO SE ESCRIBE CL-TV CR-DG
  int point2,caso = 0; if (numeric1.equals(numeric2)){point2 = c.lastIndexOf("."+numeric2+".");}else{
  point2 = c.indexOf("."+numeric2+".");}
  String q2 = c.substring(point, point2+1);
  if ((q2.contains("cl"))||(q2.contains("dg"))||(q2.contains("cr"))||(q2.contains("tv"))){
   //JOptionPane.showMessageDialog(null,q2+"#"+match);//array[4]=buscarV(q2,base);
          if (buscarV(match,base)==buscarV(q2,base)){//primer caso: si son iguales omitir el segundo caso 
               if (buscarV(match,base)<2){array[7]=1;}else{array[7]=0;};  //CASO CARDINALES REPETIDOS  
          }else{
            caso=buscarV(match,base);
                     switch(caso){//AUN HACE FALTA COMPLETAR EL PROCESO SUBVIAL EN ESTOS CASOS NO COMUNES
                 case 0:
                     array[4]=buscarV(q2,base);//CASO INICIA POR CALLE
                     break;
                 case 1: 
                     //CASO INICIA POR DIAGONAL---INTERCALAR
                     if(buscarV(q2,base)==0){array[7]=1;//con calle
                     array[1]=buscarV(q2,base);array[4]=buscarV(match,base);array[2]=numeric2;array[5]=numeric1;
                      
                     }else{
                        array[4]=buscarV(q2,base);
                     }
                     break;
                 case 2: 
                     if((buscarV(q2,base)<=1)){array[1]=buscarV(q2,base);array[2]=numeric2;
                     array[4]=buscarV(match,base);array[5]=numeric1;array[7]=buscarV(q2,base);
                    } else{
                      array[1]=buscarV(match,base);array[2]=numeric1;array[4]=buscarV(q2,base);array[5]=numeric2;
                      array[7]=buscarV(q2,base);}
                      
                     break; 
                 case 3:
                     if ((buscarV(q2,base)==2)){
                       array[1]=buscarV(q2,base);array[5]=numeric1;array[4]=buscarV(match,base);array[2]=numeric2;array[7]=buscarV(q2,base);  
                         }else{
                         array[1]=buscarV(q2,base);array[2]=numeric2;
                         array[4]=buscarV(match,base);array[5]=numeric1;array[7]=buscarV(q2,base);
                     }
                     break;
                 case 4:

                     break;
                }
          }         
   }
 ///////////////////////FIN DE LOS SUBCASOS ////////////////////////////// 
}
    private void subcardinal(String s){//PARA CASOS ESPECIALES QUE CONTIENEN LA PALABRA BIS: EN ESTE CASO ADICIONA 0.5 AL CARDINAL
       int control=0,q,x; final int subc = 20;
      if (array[3]==null){array[3]=0;}  if(array[6]==null){array[6]=0;}
       q = (int)array[3];
       x=(int)array[6];
        if (s.contains(".bis.")){
          if (numeric1.equals(numeric2)){control = s.lastIndexOf("."+numeric2+".");}else{control = s.indexOf("."+numeric2+".");} 
          
          int control2 = s.indexOf(".bis.");
              //JOptionPane.showMessageDialog(null, array[7]);
             if(array[7].equals(1)){
                         if (control2<control){
                                array[3]= q + subc;// JOptionPane.showMessageDialog(null, "3");// array[3]
                               }else{
                                array[6]= x + subc; //JOptionPane.showMessageDialog(null, "6");// array[6]
                         }
               }else{
                         if (control2>control){
                                 array[3]= q + subc;//JOptionPane.showMessageDialog(null, "6");// array[6]
                               }else{
                                 array[6]= x + subc;// JOptionPane.showMessageDialog(null, "3");// array[3]
                                    
                         }
             }
        
        }
    }//
    public void subvial (String st){
    String base = "#abcdefghijklmprstuv";//debemos tener en cuenta la nomenclatura BIS que marca una diferenciacion vial SE EXCLUYE  N, Ñ, O, W
   int control,posicion_numeric;
   if(contnum>=3){
    st=cadena3num+".";
  posicion_numeric=st.lastIndexOf("."+numeric3+".");st=st.substring(0,posicion_numeric)+".";//limitamos la busqueda hasta la ultima posicion numerica evitando casos de expresiones muy largas
   }else{}
   //JOptionPane.showMessageDialog(null, st);
   if (numeric1.equals(numeric2)){control = st.lastIndexOf("."+numeric2+".");}else{control = st.indexOf("."+numeric2+".");} 
          
    switch(contLetra1digito){
        case 0://tener en cuenta aqui caso con bis ya que no debe borrar ese valor||(array[6]!=""))
           // array[3]=0;array[6]=0;
               if((array[3]==null)&&(array[6]==null)){ array[3]=0;array[6]=0;  }else{
                   array[3]=(array[3]==null)?0:array[3];
                   array[6]=(array[6]==null)?0:array[6];
                }    
          break; 
        case 1:
            if(array[7].equals(1)){
                if ((st.indexOf("."+letraabc[0]+".")<control)){//(st.indexOf("."+letraabc[0]+".")<control)
                    array[3]=base.indexOf(letraabc[0]);array[6]=0;}else{
                    array[3]=0;array[6]=base.indexOf(letraabc[0]);   
                  }
            }else{
                if ((st.indexOf("."+letraabc[0]+".")>control)){//(st.indexOf("."+letraabc[0]+".")<control)
                    array[3]=base.indexOf(letraabc[0]);array[6]=0;}else{
                    array[3]=0;array[6]=base.indexOf(letraabc[0]);    
                  }
            }
          break;
        case 2:
            if (("n".equals(letraabc[0]))){letraabc[0]="";}
            if(array[7].equals(1)){
            array[3]=base.indexOf(letraabc[0]);array[6]=base.indexOf(letraabc[1]);}else{
             array[6]=base.indexOf(letraabc[0]);array[3]=base.indexOf(letraabc[1]);
            }
          break;
        default:  
    }
    if ((int)array[3]==-1){array[3]=0;}if ((int)array[6]==-1){array[6]=0;}//SIN AL FINAL NO SE ENCONTRO SUBVIAL LO PASA DE -1 A 0
    //JOptionPane.showMessageDialog(null,array[3]+"#"+array[6]);
    //st.indexOf(numeric1);
    
}//
private void subVia(String s){
   try{ 
    int control = contLetra1digito;int point;String cadena1,cadena2;
    int match1 = -1,match2 = -1,letra1 = 0,letra2 = 0;String z= cadenaq1+cadenaq2;
    point = z.indexOf("."+numeric1+".");//buscamos descomponer la cadena en dos partes SE EXCLUYE  N, Ñ, O, W
      String base[] = {"cl","dg","cr","tv"}; String baseabc = "#abcdefghijklmprstuv"; 
        cadenaq2=cadenaq2+"."; 
     cadena1=cadenaq1;
     cadena2=z.substring(point,z.length());
    if ((cadena2.contains("cl"))||(cadena2.contains("dg"))||(cadena2.contains("cr"))||(cadena2.contains("tv"))){
    //DESDE AQUI PROCESA SI ADEMAS DE DEFINIR EL EJE X TAMBIEN CONTIENE EL EJE Y
    if (control<1){
          
        }else{
          switch(control){
            case 1:
        match1=buscarV(cadena1, base);if(cadenaq1.contains("."+letraabc[0]+".")){letra1=baseabc.indexOf(letraabc[0]);}else{letra1=0;}
        match2=buscarV(cadena2, base);if(cadenaq2.contains("."+letraabc[0]+".")){letra2=baseabc.indexOf(letraabc[0]);}else{letra2=0;}
       // JOptionPane.showMessageDialog(null, match1+"#"+letra1+"\n"+match2+"#"+letra2);
               break;
            case 2:
        match1=buscarV(cadena1, base);if(cadenaq1.contains("."+letraabc[0]+".")){letra1=baseabc.indexOf(letraabc[0]);}else{letra1=0;}
        match2=buscarV(cadena2, base);if(cadenaq2.contains("."+letraabc[1]+".")){letra2=baseabc.indexOf(letraabc[1]);}else{letra2=0;}
       // JOptionPane.showMessageDialog(null, match1+"#"+letra1+"\n"+match2+"#"+letra2);
                break;
           }//fin switch  
    }//fin if
    if (match1==match2){
        subvial(s);
    }else{
    if(match1==(int)array[1]){array[3]=letra1;}if(match2==(int)array[4]){array[6]=letra2;}
    if(match1==(int)array[4]){array[6]=letra1;}if(match2==(int)array[1]){array[3]=letra2;}
    
    }
   }else{
      //////////////////////////DESDE AQUI COMIENZA PROCESAMIENTO SI SOLO DEFINE UN EJE Y SE ASUME LA REGLA DEL EJE OPUESTO  
       subvial(s);
       
    }
    if (array[3]==null){array[3]=0;}if (array[6]==null){array[6]=0;}
    if ((int)array[3]==-1){array[3]=0;}if ((int)array[6]==-1){array[6]=0;}//SIN AL FINAL NO SE ENCONTRO SUBVIAL LO PASA DE -1 A 0
     }
   
    catch(Exception e){
      array[0]=-1;
    } 
}
public String especialcase (String e){
    int s = -1;String baseabc = "#abcdefghijklmnopqrstuvwxyz";
    if(("null".equals(sintaxis))&&(contltr>0)&&(contnum>=3)){s=0;}
    if(("ltr".equals(sintaxis))&&(contltr>=1)&&(
            contnum==0)){s=1;}
    if((e.contains("av."))&&("null".equals(sintaxis))&&(contltr>0)&&(contnum>=3)){s=2;}
    if(("null".equals(sintaxis))&&(contltr>0)&&(contnum==2)){s=3;}
    if((e.contains("km."))&&("null".equals(sintaxis))&&(contltr>0)&&(contnum>=0)){s=4;}//casos con kilometro
    if (("ltr".equals(sintaxis))&&(contltr>=1)&&(contnum==2)){s=5;}//caso con solo calle y carrera 
    if (("invertido".equals(sintaxis))&&(contltr>=1)&&(contnum>=3)){s=6;} //caso con sintaxis invertida
    if (("vialExtra".equals(sintaxis))&&(cardinal1!=null)){s=7;}//casos con mas de 2 letras para determinar subvia
    if (("mz".equals(sintaxis))&&(s==-1)){String ee="."+e+"."; 
        if((ee.contains(".mz."))){s=8;}  ;}
    ////////////espacio para otros casos
    if (("null".equals(sintaxis))&&(contltr>0)&&(contnum>0)&&(contnum<=2)&&(s==-1)){s=9;}//SI AL MENOS TIENE UN NUMERO
    if (("ltr".equals(sintaxis))&&(contltr>0)&&(contnum==1)&&(s==-1)){s=10;}//SI AL MENOS TIENE UN NUMERO
    if (("null".equals(sintaxis))&&(contnum==0)&&(s==-1)){s=11;}//dejar este como ultimo caso con cero numeros y todas
    if (("vialExtra".equals(sintaxis))&&(cardinal1==null)){s=12;}//con mas de 2 letras para determinar subvia pero sin Cardinal correspondiente
      switch(s){
        case 0://AQUI EXISTEN 3 NUMEROS MINIMOS CON UNO O MAS TEXTO PERO NO DEFINE CALLE,CARRERA O ALGO SIMILAR: 100 CODIGO CARDINAL INCOMPLETO
             //aqui el algoritmo al tener 3 numeros minimos y unos textos puede comenzar a interpretar arrojar resultados calculados
             String segmentos[] = e.split("\\.");
           Boolean control=  dirsXYnull.containsKey(segmentos[0].toString());
           String valor = dirsXYnull.get(segmentos[0].toString());
            if(valor!=null){
                e=e.replaceFirst(segmentos[0]+".", valor+".");
                coordenada(e);cardinal(e);subVia(e);subcardinal(e);codigo();
            }else{
                         
              array[0]=100;array[2]=numeric1;array[5]=numeric2;array[7]=numeric3;array[8]=0;// JOptionPane.showMessageDialog(null, "Se requiere definir calle carrera o nomenclatura similar");
              DirErrColect=DirErrColect+e+",";//guarda las direcciones con dificultad para leer
            }
              break;
          case 1:
              e="."+e+".";//AQUI EXISTEN DATOS DE CALLE O CARRERA PERO NO EXISTE NINGUN NUMERO:CODIGO 200
          array[0]=102; if (e.contains(".cl.")==true){array[2]=0;} if (e.contains(".dg.")==true){array[2]=1;}
          if (e.contains(".cr.")==true){array[5]=2;} if (e.contains(".tv.")==true){array[5]=3;} 
              break;
        case 2://casos de avenidas
            ///caso especial cuando contiene avenida y cardinal(cl,cr.dg,tv)
            if(("".equals(cardinal2))||(cardinal2==null)){//si no contiene otro cardinal procesa asi
                   if (!"null".equals(buscarList(e))){//busca en lista de direcciones equivalencias con ak o ac en los nombres de avenidas
                     e= buscarList(e)+ e;
                     inicomponentes();e=sintax(e); expresion(e);
                     coordenada(e);
                     cardinal(e);
                     subVia(e);subcardinal(e);
                     codigo();
                   }else{
                         array[2]=numeric1;array[5]=numeric2;array[7]=1;
                         e=sintax(cadena3num);
                         coordenada(e);
                         subvial(e);
                       }
            } else  {//entonces if si contiene otro cardinal
                inicomponentes();
               if(("cl".equals(cardinal2))||("dg".equals(cardinal2))){e="cr."+e;}//{t=e.replaceFirst("av.", "cr.");}
               if(("cr".equals(cardinal2))||("tv".equals(cardinal2))){e="cl."+e;}//{t=e.replaceFirst("av.", "cl.");}
                e=sintax(e); expresion(e);
                     coordenada(e);
                     cardinal(e);
                     subVia(e);subcardinal(e);
                     codigo();
            }      
              break; 
        case 3: //if(("null".equals(sintaxis))&&(contltr>0)&&(contnum==2)){s=3;}
               if (!"null".equals(buscarList(e))){//busca en lista de direcciones equivalencias con ak o ac en los nombres de avenidas
                 e= buscarList(e)+ e;inicomponentes();//JOptionPane.showMessageDialog(null,sintax(e));// coordenada(e);cardinal(e);subvial(e);codigo();
                 e = alfanumeric(e);e = sintax(e); expresion(e);
                  coordenada(e);cardinal(e);subVia(e);subcardinal(e);
              codigo();
                }else{
                   //si solo tiene dos numeros y no encontro lista de direcciones nominales entonces o es rural o es un error
                     String segment[] = textIni.split(" ");
                     e="ErrXY: "+arrayTostring(segment);array[0]=503;
              
                }
              break;
        case 4://AQUI COTEJAMOS LOS CASOS QUE LLEVAN EL INDICADOR KM KILOMETRO CODIGO 400
             array[0]=400;array[2]=numeric1;array[3]=numeric2;array[4]=numeric3;array[8]=0; //JOptionPane.showMessageDialog(null, "si");
              break;
        case 5://AQUI COTEJAMOS LOS CASOS con referencia de calle y/o carrera pero solo 2 numeros: casos que definen esquinas
             
            cardinal(e);array[0]=105;
              break; 
        case 6://AQUI COTEJAMOS LOS CASOS CON NOMENCLATURA INVERTIDA
             String nuevoText=invertir(e);inicomponentes();
             texting(nuevoText);
              break; 
        case 7://CASOS CON MAS DE UNA LETRA PARA SUBVIAL O CORDENADA ERROR 700
            
            String newE =  corregir3abc(e);//
                
              if((!newE.equals(e))){
               inicomponentes();
               newE = sintax(newE); 
               expresion(newE);  
               coordenada(newE);
               cardinal(newE);
               subVia(newE);
               subcardinal(newE);
               codigo(); e=e;
             }else{
                    String segment[] = textIni.split(" ");
                   e="Errabc: "+arrayTostring(segment);array[0]=507;
             }
                           
              break;
        case 8: //CASO DE MANZANAS ERROR 800
            e=mzSintaxis(e);//es necesario agregar un control de sintaxis y evluar casos lotes
            int pointMz=-1,pointCs=-1;String numMz,numCs;  
              String segmentos2[] = e.split("\\."); //String baseabc = "#abcdefghijklmnopqrstuvwxyz";
              //////////////PRIMERO BUSCAMOS NUMERO DE CASA////////////////////////////
              pointMz = Arrays.asList(segmentos2).indexOf("mz");
              if((pointMz>-1)&&(segmentos2[pointMz].length()==2)&&("mz".equals(segmentos2[pointMz]))&&(pointMz+1<segmentos2.length)){
                   numMz= (pointMz==-1)?"":segmentos2[pointMz+1];
                   if(isnumeric(numMz)==true){array[5]=numMz;}else{array[5]=baseabc.indexOf(numMz); }
                    if( "-1".equals(array[5].toString())){array[5]=0;}
              }else{array[5]=0;}
              //////////////SEGUNDO NUMERO DE CASA//////////////////////////////////////
              //AGREGAR SUBFUNCION DE SINTAXISMZ
              if ((e.contains(".cs."))||(e.contains("cs."))){
              pointCs= Arrays.asList(segmentos2).indexOf("cs");
                    if((pointCs>-1)&&(segmentos2[pointCs].length()==2)&&("cs".equals(segmentos2[pointCs]))&&(pointCs+1<segmentos2.length)){
                      numCs= (pointCs==-1)?"": segmentos2[pointCs+1];
                      if(isnumeric(numCs)==true){array[8]=numCs;}else{array[8]="0"; }
                    }else{array[8]="0";}
              }else{//hasta aqui busca casa y busca despues lote
                          if ((e.contains(".lt."))||(e.contains("lt."))){
                            pointCs= Arrays.asList(segmentos2).indexOf("lt");
                            if((pointCs>-1)&&(segmentos2[pointCs].length()==2)&&("lt".equals(segmentos2[pointCs]))&&(pointCs+1<segmentos2.length)){
                              numCs= (pointCs==-1)?"": segmentos2[pointCs+1];
                              if(isnumeric(numCs)==true){array[8]=numCs;}else{array[8]="0"; }
                            }else{array[8]="0";}
                  }else{
                 array[8]="0";}//si al final no encuentra nada asigna cero a la posicion 8 del array 
                }
             int matchMz = Integer.parseInt(array[5].toString()) , matchCs=Integer.parseInt(array[8].toString());  
             if((matchMz<1)||(matchCs<1)){
              e="ErrMz: "+arrayTostring(segmentos2);array[0]=508;}
              
              
              break;
         case 9://IDEA:ORGANIZAR EN ORDEN ALFABETICO
              String letra1= e.substring(0,1);int posicion=0;
              if (isletra(letra1)==true){posicion=baseabc.indexOf(letra1);}
              e=e;array[7]=contnum;array[8]=posicion;//numeric1;
              
              break;
         case 10:
             e="ErrXY: "+e;array[7]=numeric1;
             break;
          case 11:
              String segment[] = textIni.split(" ");
              if("null:".equals(segment[0])){
                 e="Error:"+e; 
              }else{
              e="Rural: "+arrayTostring(segment);array[0]=510;
              valorNumerico(segment);
              }
              break; 
          case 12:
              /////sin subvia sin manzana y sin cardinal pero con mas de 3 letras de subvia, entonces,ignorar 3abc y ordenar por alfabetico 
             String letra11= e.substring(0,1);int posicion1=0;
              if (isletra(letra11)==true){posicion1=baseabc.indexOf(letra11);}
              e=e;array[7]=contnum;array[8]=posicion1;//numeric1; 
              break;
  /////////////////////DEFAULT//////////////////////////////            
          default:
            
              e="Error:";
              break;
          }
      return e;
}
////////////////////////FUNCIONES DE VALIDACION Y OTRAS UTILIDADES//////////////////////////////////////////////////////////////////
public String codigo (){
        String codigo = "";
    for (int i=0;i<10;i++){
        if((array[i]==null)){array[i]=0;}else{}
    } 
//ANTES DE ARROJAR EL CODIGO SE COTEJAN MODIFICACIONES ESPECIALES COMO EL CASO DE NOMENCLATURAS(CL X DG Z) DONDE SE REQUIERE HACER AJUSTES        

    

//GENERAMOS EL ID DIRECCION COMPUESTO DE 9 SECCIONES QUE REPRESENTAN COMPONENTES DE UBICACION CARTESIANA
        codigo = array[0].toString()+"."+ //REPRESENTA COORDENADA NORTE SUR ESTE OESTE
                 array[1].toString()+"."+array[2].toString()+"."+array[3]+"."+//REPRESENTA EJE X CALLE 
                 array[4].toString()+"."+array[5].toString()+"."+array[6]+"."+//REPRESENTA EJE Y CARRERA
                 array[7].toString()+"."+array[8].toString();//+""+//REPRESENTA NUMERO DE CASA Y UBICACION SOBRE EJE X O EJE Y + NUMERO DE PISO O APARTAMENTO
                 //array[9];
        //GENERAMOS EL CODIGO DE CUADRANTE PREVIO A LA ZONOFICACION
             array[11]=array[2]+"."+array[5];
//GENERAMOS EL VALOR NUMERICO PARA FACILITAR EL ORDENAMIENTO
        valor=generarValor();
        //valor= Double.parseDouble(array[0]+"."+array[1]+array[2]+array[3]+array[4]+array[5]+array[6]+array[7]+array[8]);
            return codigo;
    }//GENERA EL ID DIRECCION PREVIO A PROCESAR LAS ZONAS
  public double generarValor(){
      double result;String mascalle,maskr,masnumcasa;
      
      mascalle= llenarceros( array[2].toString(),3);
      
      if (array[5].equals(0)){maskr="0";}{
         maskr=llenarceros((String) array[5].toString(),3);}
      
      if (array[8].equals(0)){masnumcasa="0";}else{
          masnumcasa=llenarceros((String) array[8].toString(),3);}
      //valorentero = Double.parseDouble(array[0]+"."+array[1]);
      //String r = array[0]+"."+array[1]+mascalle+array[3]+array[4]+maskr+array[6]+array[7]+masnumcasa;//mascalle=Double.parseDouble(array[2]+"."+array[3])*0.001;
     result=Double.parseDouble(array[0]+"."+array[1]+mascalle+array[3]+array[4]+maskr+array[6]+array[7]+masnumcasa);//mascalle+array[3]+array[4]+maskr+array[6]+array[7]+masnumcasa);
      
        return result;
      
  }
 //<editor-fold defaultstate="collapsed" desc="Generated Code Funciones y Utilidades">
   private String mzSintaxis(String txt){
       String result="",newt; newt="."+txt+".";
       if (newt.contains(".csa.")){newt=newt.replace(".csa.", ".cs.");}
       if (newt.contains(".cas.")){newt=newt.replace(".cas.", ".cs.");}
       if (newt.contains(".casa.")){newt=newt.replace(".casa.", ".cs.");}
       if (newt.contains(".lot.")){newt=newt.replace(".lot.", ".lt.");}
       if (newt.contains(".c."+numeric2+".")){newt=newt.replace(".c."+numeric2+".", ".cs."+numeric2+".");}
       newt=newt.replace("..", ".");
       result=newt.substring(1, newt.length()-1);
        return result;
    }
    private String corregir3abc(String txt){//CASOS CON MAS DE UNA LETRA PARA SUBVIAL O CORDENADA ERROR
        //String cadena1=vectorabc.substring(1, vectorabc.length());
       String caso="",result = "";String subviales1[] = vectorabc.split(",");
       String segmentos1[] = txt.split("\\.");
        ArrayList results = toNumericLimite(segmentos1); //devuelve [numero][posicion][cantidad.numeros.contados]
       int ponintControl= (int) results.get(1);
        if(vectorabc.contains("e")){caso="e";} 
        if(vectorabc.contains("d")){caso="d";}
        if(vectorabc.contains("t")){caso="t";}
        if(ponintControl<txt.indexOf(subviales1[0])){caso="out";}//no determina bien el ultimo numero en sintaxis txt.indexOf(numericq)
       // if(cardinal1==""){abc3="cardnull";}//pendiente
         switch(caso){
                     
                     case "e":
                        txt=txt.replace(".e.", ".este.");result= txt; 
                         break;
                     case "d":
             //VAMOS A BUSCAR UN CASO ESPECIFICO EJEMPLO 59.B.D.28.A     SI LA EXPRESION ES DE ESA FORMA LA D ES DIAGONA SINO SE IGNORA
                         String serch= numeric1+"."+subviales1[0]+".d."+numeric2+"."+subviales1[2];
                         String mod=numeric1+"."+subviales1[0]+".dg."+numeric2+"."+subviales1[2];;
                         if (txt.contains(serch)){result=txt.replace(serch, mod);
                         }else{
                            result=txt.replace(".d.", ".");//segmentos1[ponintControl-1]="." ; 
                         }
                        //txt=txt.replace(".e.", ".este.");result= txt; 
                         break;   
                     case "t":
                        //txt=txt.replace(".e.", ".este.");result= txt; 
                         break;    
                     case "out":
                        for(String abc:subviales1){ 
                        txt=txt.replace("."+abc+".", ".");
                            }
                        result= txt; 
                         break;
                         
                     case ""://por default simplemente determinar posicion si es correcta se tomara la letra sino se ignorara
                         int pointA=-1,pointB=-1,pointC=-1,pointD=-1,pointN1=-1,pointN2=-1;
                         String abc1 = null,abc2,abc3 = "",textabc=vectorabc;boolean matchA=false,matchB=false;
                              //String cadena=vectorabc.substring(1, vectorabc.length());
                              String subviales[] = vectorabc.split(",");
                              String segmentos[] = txt.split("\\.");
                             if(subviales.length==3) {
                                //primero determinar primer numero y al lado su letraabc 
                                pointN1=Arrays.asList(segmentos).indexOf(numeric1);
                                //determinar la letra que le corresponde y este dentro del largo de la cadena
                                if (pointN1+1<segmentos.length){ abc1=segmentos[pointN1+1];}else{abc1="null";}
                                //si esta dentro de la cadena y esta entre las letras abc entonces es match correcto y la elimina de la lista                               
                                if(vectorabc.contains(abc1)){textabc=vectorabc.replaceFirst(abc1+",", "");matchA=true;}else{matchA=false;}
                                //segundo determinar segundo numero y al lado su letraabc
                                pointN2=Arrays.asList(segmentos).indexOf(numeric2);
                                //determinar la letra que le corresponde y este dentro del largo de la cadena
                                if (pointN2+1<segmentos.length){ abc2=segmentos[pointN2+1];}else{abc2="null";}
                                //si esta dentro de la cadena y esta entre las letras abc entonces es match correcto y la elimina de la lista                               
                                if(vectorabc.contains(abc2)){textabc=textabc.replaceFirst(abc2+",", "");matchB=true;}else{matchB=false;}
                                
                             if ((matchA==true)&&(matchB==true)) {
                                 abc3=textabc.replace(",", "");
                                 //aqui //la tercera letra sobrante es el caso a resolver ignorandolo o interpretando su valor
                                int cont=0;//pointC=Arrays.asList(segmentos).indexOf(abc3);
                                     for(String seg: segmentos){
                                         if ((seg.equals(abc3))&&(cont!=pointN1+1)&&(cont!=pointN2+1))//
                                         {segmentos[cont]="." ; }//JOptionPane.showMessageDialog(null,cont)
                                         cont++;
                                     }
                                result=arrayTostring(segmentos);
                              }else{  
                              //si no hay match en los dos casos   se determina casos para ignorar las letras
                              
                              //opcion final eliminar las tres letras
                                       for(String sub: subviales){
                                         result= txt.replace("."+sub+".", ".");txt=result;
                                       } 
                                }//fin else 
                               }//fin if(subviales.length==3) {
                           
                         break;

                     default:
                         break;
                 }
                 
        if("".equals(result)){result=txt;}  //si despues de procesar no encuentra caso retorna la cadena original    
                 
        return result;
        
    }
    private String GarbagDelet(String txt,String textOrig){
        String result=textOrig;//String abc="abcdefghijklmnñopqrstuvwxyz",numeros="0123456789";
    ////////////PRIMERO DETERMINAMOS CUANTOS TEXTOS BASURA ENCONTRO EN LA CADENA Y LOS RECORREMOS    
    try {
       String segmentos[] = txt.split("\\."); int casos=segmentos.length;
    if(casos==1){
        if(segmentos[0].length()==1){  
          result=result.replaceAll(segmentos[0], "");
         }else{   
            result=result.replaceAll("[–,;:·}{]", "");
            for (int index = 0; index < segmentos[0].length(); index++) {
             String check = String.valueOf(segmentos[0].charAt(index));
             if ((isletra(check)==false)&&(isnumeric(check)==false)){
                 result=result.replaceAll(check, "");//JOptionPane.showMessageDialog(null,check);
                }
            
            }//fin for
        
        }//fin else
    }//fin if   
  ///////////////////////////PARA MAS DE UN CASO DE TEXTO BASURA  
  if(casos>1){
      for(String date: segmentos){
        if(date.length()==1){  
            result=result.replaceAll(date, "");
         }else{
               result=result.replaceAll("[–,;:·}{]", "");
               for (int index = 0; index < date.length(); index++) {
               String check = String.valueOf(date.charAt(index));
                if ((isletra(check)==false)&&(isnumeric(check)==false)){
                   result=result.replaceAll(check, "");//JOptionPane.showMessageDialog(null,check);
                   }
            
            }//fin for 
        }//fin else
      } //fin for de segmentos
  }//fin if         
          
    } catch(Exception e){
       
            try {
                exportar_TextBasura(textOrig+"-"+txt);return textOrig; //JOptionPane.showMessageDialog(null,"error procesando texto");
            } catch (IOException ex) {
                Logger.getLogger(descomponer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }  
        return result;
        
 }  
    private String arrayTostring(String array[]){
       String result="",acum="";
       for(String sub:array) {
           acum=acum+"."+sub;
       }
        result= acum.substring(1, acum.length())+".";result=result.replace("..", ".");
        return result;
    }
    private String llenarceros(String text,int numCeros){
        String result;int largo=0;
        largo = numCeros-text.length();
        for(int cont=0;cont<largo;cont++){
            text="0"+text;
        }
        result=text;
        return result;
    } 
    private String corregirText(String exp, String segmentos[]){
       String result="";int largo=0;
      ///comenzamos por el inicio de las cadenas casos posicion cero
      largo = segmentos[0].length();
       switch(segmentos[0]){
          case "k":
             result=  exp.replaceFirst("k.", "cr.");
               break;
           case "c":
            result=  exp.replaceFirst("c.", "cl.");   
               break;
           case "avn":
            result=  exp.replaceFirst("avn.", "av.");   
               break;
           case "tranv":
            result=  exp.replaceFirst("tranv.", "tv.");   
               break;
          case "tran":
            result=  exp.replaceFirst("tran.", "tv.");   
               break; 
          case "trav":
            result=  exp.replaceFirst("trav.", "tv.");   
               break;     
           case "crr":
            result=  exp.replaceFirst("crr.", "cr.");   
               break;
           case "car":
            result=  exp.replaceFirst("car.", "cr.");   
               break; 
           case "cra":
            result=  exp.replaceFirst("cra.", "cr.");   
               break;    
           case "carr":
            result=  exp.replaceFirst("carr.", "cr.");
               break;
           case "kr":
            result=  exp.replaceFirst("kr.", "cr.");
               break; 
           case "krr":
            result=  exp.replaceFirst("krr.", "cr.");
               break;    
            case "cll":
            result=  exp.replaceFirst("cll.", "cl.");
               break;
           case "calle":
            result=  exp.replaceFirst("calle.", "cl.");
               break;    
            case "diag":
            result=  exp.replaceFirst("diag", "dg.");
               break;
           default:
               break;
       
       }
       //JOptionPane.showMessageDialog(null, segmentos[0]);
       return result;
   } //se ejecuta en textdelet
    public boolean isnumeric(String text){
        boolean bool = text.matches("[0-9]*");
        return bool;
    }
    public boolean isletra(String text){
        boolean bool = text.matches("[a-zA-Z]*");
        return bool;
    }
    private String isTipo( String dato){
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
    private boolean buscar(String text, String matriz){
        boolean bool = false;int cont=0;String base [] = matriz.split(",");
        for (String base1 : base) {
         if (text.equals(base[cont])){
           bool=true;//JOptionPane.showMessageDialog(null, "");
           
                                           }
                 cont++;
                             }
        return bool;
    }
    private int buscarV(String text, String matriz[]){
       int result=0;int cont=0;//String base [] = matriz[];
        for (String base1 : matriz) {
            int match=text.indexOf("."+matriz[cont]+".");
            
         if ((match>-1)){
           result=cont;//JOptionPane.showMessageDialog(null, "");
           
                                           }
                 cont++;
                             }
        return result;
    }
    private int buscarH(String text, String matriz[]){
       int result=-1;int cont=0;//String base [] = matriz[];
        for (String base1 : matriz) {
                        
         if ((base1.equals(text))){
           return cont;//JOptionPane.showMessageDialog(null, "");
             }
                 cont++;
       }
        return result;
    }
    private String buscarList(String text){
      String result="null"; String texto;
        Iterator<String> it= ListaDirecciones.dirs.iterator();
        while(it.hasNext()) {//for (int i=0; i<=largo;i++) {
          texto = it.next();
          if (texto != null){
             String array[] = texto.split(",");
               if (text.contains(array[0])){result=array[1];}
     
                                       }
                 
                             }
        return result;
    }
    private String matchText(String cadena,String lista){
       String acum="";//subfuncion usada en la funcion expresion()
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(lista.split(",")));
           StringTokenizer token = new StringTokenizer(cadena,".");String sub,valor = null;int match=0,cont=0;
              while (token.hasMoreTokens()){
                  sub = (token.nextToken());
                  match=myList.indexOf(sub);
                  if ((match>-1)&&(cont<=2)){
                    if(sub.equals(myList.get(match))){ acum=acum+"."+sub;}//{ result[cont]=sub;cont++; }modificado
                  }
              }
        if (acum==""){}else{     
        String result[]=acum.split("\\.");
        if ("".equals(result[1])){cardinal1="";}else{cardinal1=result[1];}if((result.length<3)){}else{
        if (result[2].isEmpty()){cardinal2="";}else{cardinal2=result[2];}}//JOptionPane.showMessageDialog(null,result[1]);
        valor= result[1];
        }
        return valor;
    } 
    private String invertir(String i){//INVIERTE LA CADENA SI LA NOMENCALTURA EMPIEZA POR EL COMPLEMENTO Y DESPUES LOS CARDINALES
        String cardinal,subcadena,subcadena2,result;int point = 0,fin= i.length();//int numero1,numero2,numero3;
       // numero1=Integer.parseInt(numeric1);numero2=Integer.parseInt(numeric2);numero3=Integer.parseInt(numeric3);
        cardinal=cardinal1;point = i.indexOf("."+cardinal+".");
        subcadena=i.substring(0,point);subcadena2=i.substring(point, fin);result=subcadena2+"."+subcadena;
        //JOptionPane.showMessageDialog(null,subcadena);
        return result;
    }
    private String generarCadena3num(String txt){
        int contnumeros=0;String acumulador=".",cola=".";String sub="";
        StringTokenizer token = new StringTokenizer(txt,".");
              while (token.hasMoreTokens()){
                  sub = (token.nextToken());
                  if (contnumeros<3){acumulador=acumulador+"."+sub;}else{cola=cola+"."+sub;}
                  if (isnumeric(sub)==true){contnumeros++;}    
              }
      acumulador= acumulador.replace("..", ".");       
       return acumulador;       
    }
    private ArrayList toNumericLimite(String array[]){
       ArrayList<Object> retorno = new ArrayList(); //devuelve [numero][posicion][cantidad.numeros.contados]
       String result="",numero="";int contN=0,cont=0,point=-1;
       for(String sub:array) {
           if ((isnumeric(sub)==true)){numero=sub;contN++;}//puede ser un error
           if ((isnumeric(sub)==true)&&(contN==2)){numero=sub;point=cont;}
           if ((isnumeric(sub)==true)&&(contN==3)){numero=sub;point=cont;}
           cont++;
       }
       retorno.add(numero);retorno.add(point);retorno.add(contN);
      // result=numero;//if(contN==2){result=numero;}
        return retorno;
    }
    private void valorNumerico(String text[]){
        String acum="";
        String baseabc = "#abcdefghijklmnopqrstuvwxyz"; String[] str_arr;
        int v=text.length,c=2;
        for(int cont=0;cont<v;cont++){
                   
          for (String ch : str_arr=text[0].split("")) {
            if(("l".equals(isTipo(ch)))||("n".equals(isTipo(ch)))){   
               if(c<8){   
            array[c]=baseabc.indexOf(ch); acum=String.valueOf(baseabc.indexOf(ch))+acum;
               c++;}
             }
          }
              
        }
        System.out.println(text[0]);
        
    }
  
 // </editor-fold>   
static void exportar_TextBasura(String valor) throws IOException {

     try{
        // String strPath = System.getProperty("user.home");
         String strPath = form_geo.pathJar;//
        String f = strPath+"TextBasura.txt";
        File file = new File(f); 
     //Writer escribe = null;//FileOutputStream fis = new FileOutputStream(f);
     PrintWriter writer = new PrintWriter(f, "UTF-8");
    if (!file.exists()) {
                file.createNewFile();
            }
   // escribe = new BufferedWriter(new OutputStreamWriter(
     //               new FileOutputStream(file), "UTF8"));
      //      escribe.write(valor);

    //FileWriter  dos=new FileWriter(f,true);//FileWriter  dos=new FileWriter(f,true);
    writer.println(valor);
    writer.println("\n");
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
///////////////FUNCIONES CON MANEJO DE FICHEROS EXPORTACION E IMPORTACION
class ListaDirecciones{
    static String dir;
    static ArrayList<String> dirs = new ArrayList<>() ; 
    static ArrayList<String> dirsPrevias = new ArrayList<>();
    static ArrayList<String> expresionResult = new ArrayList<>();
    static String checklist;
    public  void FileTextRead() throws IOException {//FUNCION IMPORTAR BASE DE VIAS CON NOMBRES PARTICULARES
    String file=null; 

    String ruta = System.getProperty("user.dir");   
    FileReader myFile = null;
    file =ruta+"\\direcciones.txt";  
     File af = new File(file);            
      if (af.isFile()==false){ListaDirecciones.checklist = "Archivo no Encontrado";//JOptionPane.showMessageDialog(null,"No se cargaron fuentes de datos");
      
      }else{
    try {
                //Crear un objeto File con el archivo elegido
               // File archivoElegido = fc.getSelectedFile();
                //Mostrar el nombre del archvivo en un campo de texto
      
           
    myFile = new FileReader(file);
      //JOptionPane.showMessageDialog(null,myFile);
    BufferedReader InputFile = new BufferedReader(myFile);
    // Read the first line
    String currentRecord = InputFile.readLine();
     dirs.add(currentRecord);
    while(currentRecord != null) {

    try {
    //Copio un valor a la celda 
    //JOptionPane.showMessageDialog(null,dirs.size());//jTable3.setValueAt(currentRecord, i, 0);
    currentRecord = InputFile.readLine();
    if (!"".equals(currentRecord)){
       dirs.add(currentRecord);
    }

    //jTextField1.setText();//Refresco la Tabla 
    //jTable3.paintImmediately(jTable3.getX(),jTable3.getY(), jTable3.getWidth(), jTable3.getHeight());

    }
    catch (IOException ex) {
    }
    }
    //JOptionPane.showMessageDialog(null,"Base de direcciones cargada");
      ListaDirecciones.checklist = "Base de direcciones Cargadas";
     

    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(null," Error en Busqueda de archivo");
    } finally {

        try {
    myFile.close();

    } catch (IOException ex) {
       JOptionPane.showMessageDialog(null," Error en Lectura de Archivo");
    }
    }
            }//fin del if
          /*  }//fin del if 
           else{
             JOptionPane.showMessageDialog(null,"Proceso Cancelado");
            }*/

    }
    //FUNCION PARA IMPORTAR LISTA DE DIRECCIONES A PROCESAR
    public  void importar() throws IOException {//FUNCION IMPORTAR BASE DE VIAS CON NOMBRES PARTICULARES
    int i = 0;String file=null;
    ///////////////// codigo que invoca la clase para procesar las direcciones
     descomponer d; //= new descomponer();
    
     ///////////////////////////////////////////////////////////////////////////////////////////////
    JFileChooser fc = new JFileChooser();
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
     dirsPrevias.add(currentRecord);
    while(currentRecord != null) {
        d = new descomponer();
    try {
    //Copio un valor a la celda 
    //JOptionPane.showMessageDialog(null,dirs.size());//jTable3.setValueAt(currentRecord, i, 0);
    currentRecord = InputFile.readLine();
    expresionResult.add(d.texting(currentRecord)+"|"+d.codigo());
    if (!"".equals(currentRecord)){ // JOptionPane.showMessageDialog(null,expresionResult.get(i)+"\n"+d.array[11]);
       dirsPrevias.add(currentRecord);i++;
    }

    //jTextField1.setText();//Refresco la Tabla 
    //jTable3.paintImmediately(jTable3.getX(),jTable3.getY(), jTable3.getWidth(), jTable3.getHeight());

    }
    catch (IOException ex) {
    }
    }
    JOptionPane.showMessageDialog(null,"Se importaron "+i+" Registros");
    //checklist = " "+i+" Registros";

    } catch (FileNotFoundException ex) {
    JOptionPane.showMessageDialog(null," Error en Busqueda de archivo");
    } finally {

        try {
    myFile.close();

    } catch (IOException ex) {
    JOptionPane.showMessageDialog(null," Error en Lectura de Archivo");
    }
    }
            }//fin del if 
           else{
             JOptionPane.showMessageDialog(null,"Proceso Cancelado");
            }

    }
}

class dirTest{
    static ArrayList<String> direcciones;// = new ArrayList<String>() ;
    static List<idDirZona> listaDirecciones ;//= new ArrayList<idDirZona>();
public void importarDirTest() throws IOException{
     int i = 0;String file=null;
    ///////////////// codigo que invoca la clase para procesar las direcciones
     clearFile();//limpiamos el archivo donde se guardan los resultados si existe
    direcciones = new ArrayList<>() ;
    listaDirecciones = new ArrayList<idDirZona>();
     ///////////////////////////////////////////////////////////////////////////////////////////////
    JFileChooser fc = new JFileChooser();
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
      
     direcciones.add(currentRecord);
    while(currentRecord != null) {
        //d = new descomponer();
    try {
    //Copio un valor a la celda 
    //JOptionPane.showMessageDialog(null,dirs.size());//jTable3.setValueAt(currentRecord, i, 0);
    currentRecord = InputFile.readLine();
   
    if (!"".equals(currentRecord)){ // JOptionPane.showMessageDialog(null,expresionResult.get(i)+"\n"+d.array[11]);
       // descomponer d = new descomponer();String result=d.texting(currentRecord);String value=d.codigo();
       direcciones.add(currentRecord);i++;//exportar_datos(String.valueOf(i)+"#"+currentRecord);
    }

    //jTextField1.setText();//Refresco la Tabla 
    //jTable3.paintImmediately(jTable3.getX(),jTable3.getY(), jTable3.getWidth(), jTable3.getHeight());

    }
    catch (IOException ex) {
    }
    }
    JOptionPane.showMessageDialog(null,"Se importaron "+i+" Registros");
    //checklist = " "+i+" Registros";

    } catch (FileNotFoundException ex) {
    JOptionPane.showMessageDialog(null," Error en Busqueda de archivo");
    } finally {

        try {
    myFile.close();

    } catch (IOException ex) {
    JOptionPane.showMessageDialog(null," Error en Lectura de Archivo");
    }
    }
            }//fin del if 
           else{
             JOptionPane.showMessageDialog(null,"Proceso Cancelado");
            }

    }
public void procesarLista( ) throws IOException{
    ArrayList<String> l = (ArrayList<String>) direcciones.clone();
    String cadena = null,codigo = null;descomponer d;
    
 try{   
     if (l.isEmpty()) { 
           JOptionPane.showMessageDialog(null, "No Data Test");
       }else{
       // int size = l.size();//COMIENZA A EXPORTAR DATOS
        for (String sub : l){//for (int i=0;i<= size;i++){
           if (sub==null){ }else{
             d = new descomponer();  cadena=d.texting(sub);codigo=d.codigo();
            //JOptionPane.showMessageDialog(null, sub+"#\n"+cadena+"#\n"+codigo);i++;
           exportar_datos(sub+"|"+cadena+"|"+codigo+"|"+d.valor);//LINEA QUE EXPORTA LOS DATOS PROCESADOS
           listaDirecciones.add(new idDirZona(sub, cadena, codigo,d.valor)); } //CREA UN OBJETO Y GUARDA LOS ELEMENTOS EN MEMORIA PARA USO EN OTRAS CLASES
           //Collections.sort(listaDirecciones,new CompararIdZonas());//LINEA QUE ORDENA LAS DIRECCIONES POR CALLE Y CARRERA FALTA NUMERO
           
         }
        
        JOptionPane.showMessageDialog(null,"Proceso Finalizado");
      }//end if
 }
    catch(IOException e){
    JOptionPane.showMessageDialog(null, "error");
    } 
 
}
 public void exportar_datos(String valor) throws IOException {

     try{
         //String strPath = System.getProperty("user.home");
         String strPath = form_geo.pathJar;//
        String f = strPath+"\\result.txt";
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
    dos.close();System.gc();
    }
    catch(FileNotFoundException e){
    System.out.println("No se encontro el archivo");
    }
    catch(IOException e){
    JOptionPane.showMessageDialog(null, "error");
    }
 }
 private void clearFile() throws IOException{
     String strPath = System.getProperty("user.home");
        String f = strPath+"\\Documents\\result.txt";
    // String f = "C:\\Users\\57321\\Documents\\bases\\result.txt";
     File file = new File(f);
     //FileOutputStream fis = new FileOutputStream(f);
     if (file.exists()) {
         PrintWriter  dos=new PrintWriter(f);
         dos.print("");dos.close();
     }else{}
 }
}

class ciudades{
    static ArrayList<String> ciudades;
    static Map <String ,String> mapCiud = new HashMap<> ();
    static int ciudadGlobal;
     public  void importCiudades() throws IOException {//FUNCION IMPORTAR BASE DE VIAS CON NOMBRES PARTICULARES
    String file=null; 
    ciudades = new ArrayList<>();
    String ruta = System.getProperty("user.dir");   
    FileReader myFile = null;
    file =ruta+"\\ciudades.txt";  
     File af = new File(file);            
      if (af.isFile()==false){ListaDirecciones.checklist = "Archivo no Encontrado";//JOptionPane.showMessageDialog(null,"No se cargaron fuentes de datos");
      
      }else{
    try {
                //Crear un objeto File con el archivo elegido
               // File archivoElegido = fc.getSelectedFile();
                //Mostrar el nombre del archvivo en un campo de texto
      
           
    myFile = new FileReader(file);
      //JOptionPane.showMessageDialog(null,myFile);
    BufferedReader InputFile = new BufferedReader(myFile);
    // Read the first line
    String currentRecord = InputFile.readLine();
     ciudades.add(currentRecord);
    while(currentRecord != null) {

    try {
    //Copio un valor a la celda 
    //JOptionPane.showMessageDialog(null,dirs.size());//jTable3.setValueAt(currentRecord, i, 0);
    currentRecord = InputFile.readLine();
    if (!"".equals(currentRecord)){
       ciudades.add(currentRecord);
    }

    //jTextField1.setText();//Refresco la Tabla 
    //jTable3.paintImmediately(jTable3.getX(),jTable3.getY(), jTable3.getWidth(), jTable3.getHeight());

    }
    catch (IOException ex) {
    }
    }
    //JOptionPane.showMessageDialog(null,"Base de direcciones cargada");
      ListaDirecciones.checklist = "Base de direcciones Cargadas";
     

    } catch (FileNotFoundException ex) {
    JOptionPane.showMessageDialog(null," Error en Busqueda de archivo");
    } finally {

        try {
    myFile.close();

    } catch (IOException ex) {
    JOptionPane.showMessageDialog(null," Error en Lectura de Archivo");
    }
    }
            }//fin del if
          /*  }//fin del if 
           else{
             JOptionPane.showMessageDialog(null,"Proceso Cancelado");
            }*/

    }
 
}