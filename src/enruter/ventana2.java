/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import java.util.regex.*;
import java.text.NumberFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import java.text.*;

/**
 *
 * @author Usuario
 */
public class ventana2 extends javax.swing.JInternalFrame{
String sub1,sub2,sub3,sub4,numbinario,distancia0,numdetalle,cadena,X,Y,literalX,literalY;int cuenta1=0;Object [] vect= new Object[10];
String ciudad,cardinal="",estructura,DECIMAL;int numD,num2=1; Double subX,subY;int contcardinal=0;
Double decimal=0.0; String subl,subn;
public static Pattern patern,patern2,pt,pt1;
public static Matcher matcher,matcher2,mt,mt1;
String array[] = new String[20];
Object  data[][] = {
{" av rousvelt ","kr 16"},
{" av guadalupe ","kr 70"},
{" av circunvalar ","kr 80"},
{" av de las americas ","kr 90"},
{" av las americas ","kr 90"}        
 };
Object datos[][]=new String[4][500000];
 String directorio,valor;File f;String coleccion []= new String[20];// TODO add your handling code here:
 // FileOutputStream fis; 
/**
     * Creates new form ventana2
     */
    public ventana2() {
        initComponents();
    }
    public String busc_alfanum(String y){//para extraer alfanumericos complejos del tipo A2A 2A2 etc
         int cont=0,largo=0,x=0;String result="",txt="",acumS=" ",acumN=" ";char A,B;//String coleccion[]= new String[15];//y=y.replace(".", " ");
         StringTokenizer st = new StringTokenizer(y," ");
           while (st.hasMoreTokens()) {
               txt= st.nextToken();//alfanum = new String[largo];
       if (tipo(txt)=='o'){ 
          largo=txt.length();//acumN=txt;
           String alfanum []= new String[largo];
          while (x<largo){
            A=txt.charAt(x);
               
             if (tipo(String.valueOf(A))=='l'){alfanum[x]=String.valueOf(A);}
               //
             else{alfanum[x+1]="";x=largo;}

               x++;
          } 
         x=0; //JOptionPane.showMessageDialog(null,alfanum[0]);
          while (x<largo){
          if(alfanum[x]==null){alfanum[x]="";}//JOptionPane.showMessageDialog(null,alfanum[x]);
            acumS=acumS+alfanum[x]; x++;
        } 
       }
               cont++;
                   } 
       acumS=acumS.replace(" ", "");//cont=y.indexOf(acumS);
      result=y.replaceFirst(acumS, acumS+" "); //NO ESTA REEMPLAZANDO  result=txt.replace(acumS,acumS+ " ");
      //JOptionPane.showMessageDialog(null,result);    
         //JOptionPane.showMessageDialog(null,coleccion[0]);//coleccion= y.split(".");
        return result;
     }
     public  char tipo(String y) {//determina la separacion de subcadenas segun su tipo:numero,letra, alfanumerico u otros
           //String y =  jTextField1. getText();
       char z ;
        //JOptionPane.showMessageDialog(null, y);//y = jPasswordField1.getPassword();
       if (y.isEmpty()) 
              z='v';//vacio
          else 
       if(y.matches("[a-z]*")) 
              z='l'; //letra
          else  
       if ((y.matches("[0-99999]*[0-99999]*")))//||(y.matches( "[0-9]+.[0-9999]*" ))) 
              z='n';//numeros
          else 
       
       if(((y.matches("[a-z]*[0-9999]*")) || (y.matches("[0-9999]*[a-z]*"))) || (y.matches("[0-999]+[a-z]"))) 
              z='a'; //alfanumerico
          else     
              z='o'; //otros
       
    //JOptionPane.showMessageDialog(null, z);
           return  z;   
            }
     public String preformatear(String y){//reemplaza y valida espacios y caracteres especiales y da una nueva salida organizada
   y=(y.length()>45)?y.substring(0, 46):y;
   y=y.toLowerCase();y=y.replace("-", " ");y=y.replace(".", " ");y=y.replace(" no ", " ");
   y=y.replace("ak ","cr av");y=y.replace("ac ", "cl av");y=y.replace("1ra", "1");y=y.replace("3ra", "3");
   y=y.replace("carrera","cr");y=y.replace("karrera", "cr");y=y.replace("cra", "cr");
   y=y.replace("nâ°", "#");y=y.replace("n0", "#");
   y=y.replace("manzana ", "mz ");y=y.replace("manz ", "mz ");y=y.replace("manza ", "mz ");
   y=y.replace("numero", " ");y=y.replace("/", " ");y=y.replace("manzan ", "mz ");
   y=y.replace("ª","#");y=y.replace("º","#");y=y.replace("°","#");y=y.replace("n#","");y=y.replace("*","");
   y=y.replace("manzana", "mz");y=y.replace("casa", "cs");
   y=y.replace("transversal", "tv");y=y.replace("trr ", "tv");y=y.replace("transv", "tv");
   y=y.replace("trv", "tv");y=y.replace("tranv", "tv");y=y.replace("trav", "tv");
   y=y.replace("trasversal", "tv");y=y.replace("tr ", "tv");
   y=y.replace("diagonal", "dg");y=y.replace("diag ", "dg");
   y=y.replace("calle ", "cl ");y=y.replace("cll ", "cl ");
   y=y.replace("#", " ");y=y.replace("nro", " ");y=y.replace("NRO", " ");
   y=y.replace("oeste", " oeste ");y=y.replace("norte", " norte ");y=y.replace("sur", " sur ");
    y=y.replace(","," "); y=y.replace("b/"," "); y=y.replace("avenida","av");// JOptionPane.showMessageDialog(null, y);
    y=(y.length()<11)?y=y.concat(" 0"):y;//determina si el largo es de 10
    text_inicial(y);
    y=busc_alfanum(y);//JOptionPane.showMessageDialog(null,zz);
       return y;
     }
     public String buscarstr(String y){//contiene rutinas de busqueda en bases editables para asignacion de valores calle o carrera
        
         StringTokenizer st = new StringTokenizer(y);int cont=0,result=0,tokens,ltr=0;
        String sub="",base=" cl cll calle cr kr crr krr cra kra carrera karrera km ",
                baseAC=" av1 av2 av3 av3n ",text="",
                baseG=" tv ",baseD=" dg ",
                baseAK=" av4 av5 av6",
                baseKM="km kilometro";
       try{// JOptionPane.showMessageDialog(null, y);
        String [] firs= new String [15];tokens=st.countTokens();
        while (st.hasMoreTokens()) {
                 sub=st.nextToken();firs[cont]=sub;cont++;//JOptionPane.showMessageDialog(null, sub);
                 patern= Pattern.compile("\\s"+sub+"\\s");//necesito que haga busqueda exacta
                 matcher= patern.matcher(base);//JOptionPane.showMessageDialog(null,sub.length());
                 if ((matcher.find()==true)&&(sub.length()>1)){
                    result=cont;text=sub;ltr++;//JOptionPane.showMessageDialog(null,text);
                 if(ltr==2){text="";}   
                y=y.substring(y.indexOf(text), y.length());} // y=y.replace(firs[0], "");}
                 
                 else{
          // if (((matcher.find()!=true)&&(sub.length()>0))||((matcher.find()==true)&&(sub.length()==1)))
                  }}//JOptionPane.showMessageDialog(null,y);   
       if(ltr==0){//
          patern2= Pattern.compile(firs[0]);
         matcher2= patern2.matcher(baseG);//base de avenidas
                 y=(matcher2.find()==true)?"kr"+y.replace("?", ""):"? "+y;                        
          if(y.contains("?"))
          patern2= Pattern.compile(firs[0]);
         matcher2= patern2.matcher(baseD);
                 y=(matcher2.find()==true)?"cl "+y.replace("?", ""):y;    
          if(y.contains("?"))
              sub=firs[0]+firs[1];
          patern2= Pattern.compile(sub);
         matcher2= patern2.matcher(baseAC);
                 y=(matcher2.find()==true)?"cl "+y.replace("?", ""):y;       
          if(y.contains("?"))
              sub=firs[0]+firs[1];
          patern2= Pattern.compile(sub);
         matcher2= patern2.matcher(baseAK);
                 y=(matcher2.find()==true)?"kr "+y.replace("?", ""):y;
        if(y.contains("?"))
          patern= Pattern.compile(firs[0]);
         matcher= patern.matcher(base);
          patern2= Pattern.compile(firs[1]+firs[2]);
         matcher2= patern2.matcher(baseAC);
 y=((matcher.find()==false)&&(matcher2.find()==true))?"cl "+y.replace(firs[0], ""):y;
  if(y.contains("?"))
          patern= Pattern.compile(firs[0]);
         matcher= patern.matcher(base);
          patern2= Pattern.compile(firs[1]+firs[2]);
         matcher2= patern2.matcher(baseAK);
 y=((matcher.find()==false)&&(matcher2.find()==true))?"kr "+y.replace(firs[0], ""):y; 
  /* if(y.contains("?"))//para direcciones con nombres de avenidas sin numero
              sub=firs[0]+firs[1]+firs[2]+firs[3];//JOptionPane.showMessageDialog(null, sub);
          patern2= Pattern.compile(sub);
         matcher2= patern2.matcher(baseN);
                 y=(matcher2.find()==true)?"cl "+y.replace("?", ""):y;*/              
       }      
    //JOptionPane.showMessageDialog(null, y);
                 
        return y;// JOptionPane.showMessageDialog(null,y);
         } catch (Exception ex) {
            return "NullA";
       } 
     }
     public String subtokenizar(String y){
         int cont=0,contstr=0;Object vector[]=new Object[15];String vectorStr[]=new String[15];
        String text,acum = ""; int contnum=0;
        try{
       StringTokenizer st=new StringTokenizer(y," "); 
        while (st.hasMoreTokens()){
         text = st.nextToken();//r= calleocarrera(text);//double x= Double.parseDouble(z);
          char z=tipo(text);//JOptionPane.showMessageDialog(null, text);
            switch(z){
          case 'n':
              vector[cont]=text;vectorStr[contstr]=String.valueOf(z);contnum++;//contstr++;//(text!=null)?text:"0";
              break;
          case 'l': 
              if ("n".equals(text)&&contstr==2) {
             text="";//JOptionPane.showMessageDialog(null, contstr);//contstr++;
             }//elimina la N si representa la palabra numero en tercera posicion
              vector[cont]=text;//vectorStr[contstr]="0";contstr++;/*(text.length()==1)?text:null;*/ 
             vectorStr[contstr]=String.valueOf(z);
             
             break;
          case 'a':
              boolean q;char zz;
              extrac(text);zz=text.charAt(0);q=tipo(String.valueOf(zz))=='n';
             //JOptionPane.showMessageDialog(null,q); 
        if (q==false){subl=sub2;subn=sub1;}else{subl=sub1;subn=sub2;}//sub1=subl;sub2=subn;     
           vector[cont]=subl;//obtiene el numero//vectorStr[contstr]=sub1;//vect[cuenta1]=sub2;cuenta1++;//cont++;  
           vector[cont+1]=subn;cont++;//contstr++;//acum=sub1+"."+acum;
             vectorStr[contstr]=String.valueOf(z); //cardinal=coordenada(sub1);//vector[cont]=sub1;
             //JOptionPane.showMessageDialog(null, sub1);
              break;
          case 'o':
              if ((cont==1)&&(text.length()==3)){
            sub4=text.substring(0, 1)+" "+text.substring(1, 2);  }//JOptionPane.showMessageDialog(null,text);
              extraco(text);//extraco(text);//contletra(text);
           vector[cont]=sub4;cont++;//
           //JOptionPane.showMessageDialog(null,sub4);
              //vectorStr[contstr]=String.valueOf(z);
              break;
          default:  
              ;
                    }
            
      cont++;contstr++;   
      } //JOptionPane.showMessageDialog(null, vector[0]);
 /////////////////////////////////PREFORMAR//////////////////////////////////////////////
         if((vector[0].equals(vector[1]))){vector[0]="";}//elimina inicio repetido
         if((vector[0].equals("calle"))){vector[0]="cl";}//reemplaza calle por cl en la primera palabra
 /////////////////////////////////////////////////////////////////////////////////////////    
         int x=0;//JOptionPane.showMessageDialog(null, contnum);
      while(x<cont){
          //JOptionPane.showMessageDialog(null, vector[x]);
          acum=acum+" "+vector[x];
          x++;
      }
      ////////////////////////////////////////////////////////////////////////////////////////////////////
      x=0;String acumula="",mas=y,total;//JOptionPane.showMessageDialog(null, contnum);
      while((x<contstr)&&("l".equals(vectorStr[x]))){ 
          acumula=acumula+" "+vector[x];
          mas=mas.replace((String)vector[x], "");
          x++;
      }if(x>1){acum=(!"".equals(buscarcalle(acumula)))?buscarcalle(acumula)+mas+acumula:acum;}//agregar funcion o metodo de busqueda
     //////////////////////////////////////////////////////////////////////////////////////////////////////////
      if(contnum==0){acum="0 "+acum;}//si no tiene ningun numero agrega 0 para que no de error
      acum=acum.replace("null", "");estructura=vectorStr[0]+vectorStr[1]+vectorStr[2]+vectorStr[3];
      //JOptionPane.showMessageDialog(null,acum );
      return acum;
        } catch (Exception ex) {
            return "NullB";
       }
      
     }
     public String calleocarrera(String y){//determina si la direccion comienza por calle o por carrera sino toma kr
         String z;String dir="o";//aun revisar estas lineas club=c es error etc
    if (y.isEmpty()) {
             dir=""; z="vacio";
    }else{
      //JOptionPane.showMessageDialog(null,y);  
       z=y.replace(" ","");
      z= z.trim();//JOptionPane.showMessageDialog(null,z.indexOf("c"));
      if((z.length()<4)&&(z.indexOf("c") == 0)&&((z.indexOf("l") == 1)||(z.indexOf("l") == 2)))  
      dir="c";
     // if((z.length()==5)&&(z.indexOf("c") == 0)&&((z.indexOf("l") == 2)||(z.indexOf("e") == 4)))  
         else
       if((z.length()<4)&&((z.indexOf("r") == 2)||(z.indexOf("r") == 1))&&((z.indexOf("k") == 0)||(z.indexOf("c") == 0) )) 
         dir="k";//z="k";//carrera
         else
       if((z.length()<4)&&((z.indexOf("m") == 2)||(z.indexOf("z") == 1))&&((z.indexOf("m") == 0)||(z.indexOf("z") == 0) )) 
        dir="mz";//JOptionPane.showMessageDialog(null,"");// dir="k";//z="k";//carrera
       
         else  
         dir=z;//z="no encontrado";  
         }
         
         return dir;
     }
     public String buscarcalle(String y){//BUSCA EN UNA MATRIZ Y REEMPLAZA NOMBRE DE CALLE POR EL NUMERO
         int x=0;String result="";
        while(x<data.length) {
         patern= Pattern.compile(y);
         matcher= patern.matcher(data[x][0].toString());
         if(matcher.find()==true){result=data[x][1].toString();}
         //JOptionPane.showMessageDialog(null,result);
         x++;
        }
         return result;
     }
     public void extrac(String y){//en caso alfanumerico separa literal de numero
         sub1="";sub2="";//subl="";subn="";
         int largo,num,cont=0; char x;String letra=null;
        largo= y.length();num=contnum(y);
        
        while (cont < largo){
            x=y.charAt(cont);
        letra=String.valueOf(x);    
        //JOptionPane.showMessageDialog(null,cont);
             if (letra.matches("[a-z]*")==true){
                  sub2=sub2+""+letra; //letras
             }else{
                 sub1=sub1+""+letra;//numeros
              }
        cont++;
        }
        //JOptionPane.showMessageDialog(null,sub1+"-"+sub2);
        //sub1=y.substring(num, largo);sub2=y.substring(0,num);
        //sub1="";   sub2="";
        // return sub2;
     }
     public void extraco(String y){//en caso alfanumerico separa literal de numero
         int largo,num=0,cont=0,c=0; char x,z;String letra="",letra2="",acum="";
         largo= y.length();
        char cadena[]= new char [largo];String subcadena[]=new String[largo];
        cadena=y.toCharArray();
        while (cont<largo-1){
          
         letra=String.valueOf(cadena[cont]);letra2=String.valueOf(cadena[cont+1]);
         x=tipo(letra);z=tipo(letra2);
        // if ((letra.matches("[a-z]*")==true)&&(letra2.matches("[a-z]*")==false)){num=0;cont++;}else{num=1;}
         if (x==z){num=0;subcadena[cont]=letra+letra2;cont++;}else{num=1;subcadena[cont]=letra;}
         //letra2=(subcadena[num]==null)?"":subcadena[num];
            
        //JOptionPane.showMessageDialog(null,letra+letra2);  
        //en proceso falta sub dividir
        cont++;
        }
       letra="";
        while(c<cont){
          letra=(subcadena[c]==null)?"":subcadena[c];//JOptionPane.showMessageDialog(null, vector[x]);
          acum=acum+" "+letra;
          c++;
      }
       // acum=acum.replace(null, "");
        //JOptionPane.showMessageDialog(null,acum);
        //sub4=y.substring(num, largo);sub3=y.substring(0,num);
        sub4=acum;
        // return sub2;
     }
     public int contnum(String y){//cuenta cantidad de numeros en cadena
         int largo,cantidad=0;largo=y.length();String z;
       for(int x=0;x<largo;x++){
         z=String.valueOf(y.charAt(x));//JOptionPane.showMessageDialog(null, z);
        if (z.matches("[0-9]*")) {
            cantidad++;
        }  
           
       }  
      return cantidad;   
     }
     public int contletra(String y){//cuenta cantidad de numeros en cadena
         int c,largo,cantidad=0;largo=y.length();String z; 
       c=0;z=String.valueOf(y.charAt(c));
     while (z.matches("[0-9]*")==true){
         z=String.valueOf(y.charAt(c));
        c++;  
     }
      // JOptionPane.showMessageDialog(null, c);
      return c; 
     } //trabajando...buscar posicion de primera letra
     public String valor(Object[] A,int largo){//DECIMAL//DESINTEGRA UNA DIRECCION EN PARTES SEPARANDO NUMEROS DE LETRAS Y DANDO UN VALOR FRACCIONARIO A LA DIRECCION
         String a,b,c,d,total,control,z;int num=0;Double Q1,Q2,Q3,Q4,valordecimal=0.0,result=0.0;
       vect= new Object[10];
         NumberFormat nf = NumberFormat.getInstance();nf.setMaximumFractionDigits(9);
         control=String.valueOf(A[0]); //JOptionPane.showMessageDialog(null, control);
         for(int x=0;x<largo;x++){
           z=String.valueOf(A[x]); 
           if(z.matches("[0-9999]*")==true){
               vect[num]=A[x];num++;
           }
         } 
 if (vect[3]==null)
   vect[3]=0;        
 a=String.valueOf(vect[0]);b=String.valueOf(vect[1]);c=String.valueOf(vect[2]);d=String.valueOf(vect[3]);
 numbinario=a+"."+b;numdetalle=c+"."+d;distancia0=String.valueOf(Integer.parseInt(a)+Integer.parseInt(b));
 Q1=Double.parseDouble(a);Q2=Double.parseDouble(b);Q3=Double.parseDouble(c);Q4=Double.parseDouble(d);
 
 d=(!"0".equals(d))?"#"+d:"";
 if ("c".equals(control)) { 
     valordecimal=Q1+(Q2/1000);X=String.valueOf(a);Y=String.valueOf(b);//JOptionPane.showMessageDialog(null, d);
  total=String.valueOf(valordecimal)+"#"+c+d;//"0"+d;
  DECIMAL=total.replace("#", "000");decimal=Double.parseDouble(DECIMAL);
     }
 else{
     valordecimal=Q2+(Q1/1000);X=String.valueOf(b);Y=String.valueOf(a);
  total=String.valueOf(valordecimal)+"#"+c+d;
  //////////CONVERTIR TODOS LOS NUMEROS EN UN SOLO DECIMAL = 0.00000000000000 
  DECIMAL=total.replace("#", "000");decimal=Double.parseDouble(DECIMAL);
  
  //decimal=decimal+subX+subY;
     //JOptionPane.showMessageDialog(null, total);
 //////////////////////////////////////////////////////////////////////////
  }
  
       return total; 
     }
     public int buscarzona(int x,int y){//de acuerdo a una matriz previa busca la zona a la que pertenece
      
         int zona=0,result=0,inix,finx,iniy,finy; int cont=0,filas;
       filas=jTable2.getRowCount();
      // colum=jTable2.getColumnCount();
    try{   
       while (cont< filas)
   { 
       
   zona= Integer.parseInt(jTable2.getValueAt(cont, 0).toString());
   inix= Integer.parseInt(jTable2.getValueAt(cont, 1).toString());
   finx= Integer.parseInt(jTable2.getValueAt(cont, 2).toString());
   iniy= Integer.parseInt(jTable2.getValueAt(cont, 3).toString());
   finy= Integer.parseInt(jTable2.getValueAt(cont, 4).toString());
       
   if ((x>=inix)&&(x<=finx)&&(y>=iniy)&&(y<=finy)){
       result=zona;
   }
    cont++;
   } 
       }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Uno o varios campos de la matriz de zonas se encuentra en blanco");
            
       }

       return  result; 
     }
     public String coordenada(String s){//determina ubicacion cardinal:norte,sur,oeste,oriente
      String result="",base="n nte norte sur oeste os este ";char ch='0',ch2='0';//int largo=s.length();
     // JOptionPane.showMessageDialog(null,s);
        pt= Pattern.compile(s);
       mt= pt.matcher(base);
    if ((mt.find()==true)){ //esta bandera es para contar y elegir la primera cohinsidencia y omitir cualquier otra  
    ch=s.charAt(0);//JOptionPane.showMessageDialog(null,ch);
      switch (ch)
      {
          case 'n':
              cardinal="NORTE";//JOptionPane.showMessageDialog(null, "1"+"//"+s);
               break;
          case 's':
              cardinal="SUR";
              break;
          case 'e':
              if ((s.length()>1)&&(s.charAt(1)=='s'))
              cardinal="ESTE";
              break;
         case 'm':
              if ((s.length()==2)&&(s.charAt(0)=='m')&&(s.charAt(1)=='z')){
              cardinal="MANZANA";}//JOptionPane.showMessageDialog(null,"");}
              break;
          case 'o':
              if (s.length()>1){
              ch2=s.charAt(1);//JOptionPane.showMessageDialog(null,ch2);
              if (ch2=='r')
              cardinal="ORIENTE";
              if (ch2=='c')
              cardinal="OCCIDENTE";
              if ((ch2=='e')||(ch2=='s'))
              cardinal="OESTE";//JOptionPane.showMessageDialog(null,ch2);
              break;
              }
             default:
             
               cardinal="";//"URBANO"; //JOptionPane.showMessageDialog(null, "mayor 1"+"//"+s);  } 
                 
      }
       //JOptionPane.showMessageDialog(null,cardinal);
            result=cardinal; //array[0]=result;
      }else{
        if ((s.length()==2)&&(s.charAt(1)=='n'))
              cardinal="NORTE";            ;}
      // JOptionPane.showMessageDialog(null, array[0]);
      return cardinal;
     }
     public String subcalle(String y){
       String s= y.replace(".", " "); int cont=0;
      StringTokenizer st1 = new StringTokenizer(s);
      String sub="",resulta="",c="",k="",C,K,base=" abcdefghijkl";
     while ((st1.hasMoreTokens())&&(cont<1)) {
                 sub=st1.nextToken();
          if((sub.equals(X)==true)) {cont++;c=st1.nextToken().substring(0, 1);}
     }
       if(X.equals(Y)){cont=0;}     
     else{
       st1= new  StringTokenizer(s);cont=0;}
     while ((st1.hasMoreTokens())&&(cont<1)) {
                 sub=st1.nextToken();
         
          if((sub.equals(Y)==true)) {cont++;k=st1.nextToken().substring(0, 1);}
                         }
         pt= Pattern.compile(c);pt1= Pattern.compile(k);
      mt= pt.matcher(base);mt1= pt1.matcher(base);
      C=(mt.find()==true)?c:"";K=(mt1.find()==true)?k:"";
    
    subX=Double.valueOf(base.indexOf(C))/100000;subY=Double.valueOf(base.indexOf(K))/10000;
      //decimal=decimal+subX+subY;//)+"//"+subX+"!!"+subY);
resulta=((C.equals("")==true)&&(K.equals("")==true))?"":"#("+ C+"."+ K+")"; //resulta=(((x=="")&&(y=="null"))||((x=="null")&&(y=="")))?"":"#("+x+"."+y+")";}
    
     return  resulta;
         
     }  
     public void text_inicial(String y){
         int cont=0;//String coleccion[]= new String[15];//y=y.replace(".", " ");
         StringTokenizer st = new StringTokenizer(y," ");
           while (st.hasMoreTokens()) {
                coleccion[cont]= st.nextToken();
                cont++;//JOptionPane.showMessageDialog(null,st.nextToken());
                   }                
         //JOptionPane.showMessageDialog(null,coleccion[0]);//coleccion= y.split(".");
         //return coleccion[0];
     }
     public String expresion(String y){//EXTRAE LA TOTALIDAD DE LA DIRECCION Y DEVUELVE UNA EXPRESION ORGANIZADA
      int cont=0,contstr=0;Object vector[]=new Object[15];String vectorStr[]=new String[15];
     String text,r,acum = null;String inistr[]= new String[15];// =  jTextField1.getText();//String z ; 
          try{
     array = new String[10];contcardinal=0;cardinal="";
   y=y.replace("?", "");//JOptionPane.showMessageDialog(null, y);
   StringTokenizer st=new StringTokenizer(y," "); 
        while (st.hasMoreTokens()){
         text = st.nextToken();r= calleocarrera(text);//JOptionPane.showMessageDialog(null,tipo(text));
          char z=tipo(text);
            switch(z){
          case 'n':
              vector[cont]=text;vectorStr[contstr+2]=text;contstr++;//(text!=null)?text:"0";              
              break;
          case 'l': 
              vector[cont]=r;inistr[cont]=text;//cardinal=coordenada(r);//vectorStr[contstr]="0";contstr++;/*(text.length()==1)?text:null;*/ 
             if(!"".equals(coordenada(r))){ array[contcardinal]=coordenada(r);contcardinal++;}//contstr++;
              break;
          case 'a':
              extrac(text);//JOptionPane.showMessageDialog(null,subl);
              vector[cont]=calleocarrera(sub1);//obtiene el numero//vectorStr[contstr]=sub1;//vect[cuenta1]=sub2;cuenta1++;//cont++;
             vector[cont+1]=sub2;cont++;contstr++;//acum=sub1+"."+acum;
              //cardinal=coordenada(sub1);//vector[cont]=sub1;
              if(!"".equals(coordenada(sub1))){ array[contcardinal]=coordenada(r);contcardinal++;}
              break;
          case 'o':
              vector[cont+1]=text;cont++;contstr++;//JOptionPane.showMessageDialog(null, "caso");
              break;
          default:  
              ;
                    }
           //JOptionPane.showMessageDialog(null,z+"//"+text); 
      cont++;   
      } 
      int x=0;
      while(x<cont){
          
          cadena=cadena+"."+vector[x];
          x++;
      }
 //////////CONVERTIR TODOS LOS NUMEROS EN UN SOLO DECIMAL = 0.00000000000000 
        
 ///////////////////////////////////////////////////////////////////////////
      cadena=cadena.replaceAll("null.", "");//cadena=cardinal+cadena;
      cardinal=(array[0]==null)?"URBANO":array[0];//JOptionPane.showMessageDialog(null, array[0]);
      if ((contstr<3)||(y.contains("km"))){//verifica que la cadena tenga los 3 indicadores minimos calle carrera y numero de casa
         vectorStr[0]="0";vectorStr[1]="0";
         //JOptionPane.showMessageDialog(null, coleccion[0]);
         String rural;rural="RURAL";if("mz".equals(coleccion[0])){rural="MANZANA";};
         acum=coleccion[0]+"/"+rural+"/"+valor(vectorStr,contstr+2)+"/"+cadena+"&"+(subX+subY+decimal);
      }else{
          
      valor(vector,cont);
      literalX=cadena.substring(cadena.indexOf(X)+X.length()+1,cadena.indexOf(X)+X.length()+2);
      literalY=cadena.substring(cadena.indexOf(Y)+Y.length()+1,cadena.indexOf(Y)+Y.length()+2);
      //cardinal=coordenada(y);
      vectorStr[0]=(literalX.matches("[a-z]"))?literalX:"";vectorStr[1]=(literalY.matches("[a-z]"))?literalY:"";
     String subcall=subcalle(cadena); //JOptionPane.showMessageDialog(null, subcalle(vectorStr[0],vectorStr[1]));
      //JOptionPane.showMessageDialog(null,decimal);
     //jLabel1.setText(valor(vector,cont)+subcall+"//"+cardinal+cadena+"//"+distancia0); 
     //DA FORMATO DE SALIDA AL DECIMAL
     
     acum=coleccion[0]+"/"+cardinal+"/"+valor(vector,cont)+subcall+"/"+cadena+"&"+(subX+subY+decimal);//+"/"+buscarzona(Integer.parseInt(X),Integer.parseInt(Y));
         //JOptionPane.showMessageDialog(null,acum);
      }
          // JOptionPane.showMessageDialog(null,valor);
    vector=null;vectorStr=null;vect=null;array=null;   
     }catch(Exception e){
           acum="NullE"+cadena+"&"+"0.0";// JOptionPane.showMessageDialog(null,e.getMessage());
              }
        return acum;
     }             
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     public String function(String x){
       String result="null",y; 
       y=preformatear(x);//JOptionPane.showMessageDialog(null,y); 
       y=subtokenizar(y);if (estructura.indexOf("o")!=-1){y="0";cadena=" Direccion Incorrecta";}
       //JOptionPane.showMessageDialog(null, y);
       y=buscarstr(y);//JOptionPane.showMessageDialog(null, y);
       y=expresion(y);result=y; 
       /*if (y==null){y="Herror";}else{//
       result=y; 
       }*/
         return result;
     }
     //Leo archivo Text 
public void FileTextRead() throws IOException {
int i = 0;String file=null;

JFileChooser fc = new JFileChooser();
int respuesta = fc.showOpenDialog(this);
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
while(currentRecord != null) {
currentRecord = InputFile.readLine();
try {
//Copio un valor a la celda 
//JOptionPane.showMessageDialog(null,currentRecord);//jTable3.setValueAt(currentRecord, i, 0);
   valor= function(currentRecord);
//valor=function(currentRecord);
//datos[1][i]=valor;
//JOptionPane.showMessageDialog(null,valor);
exportar_datos(valor);
cadena="";
//jTextField1.setText();//Refresco la Tabla 
//jTable3.paintImmediately(jTable3.getX(),jTable3.getY(), jTable3.getWidth(), jTable3.getHeight());
i = i +1;
}
catch (Exception ex) {
}
}
JOptionPane.showMessageDialog(null,"Proceso Finalizado");

        
} catch (FileNotFoundException ex) {
Logger.getLogger(ventana2.class.getName()).log(Level.SEVERE, null, ex);
} finally {

    try {
myFile.close();

} catch (IOException ex) {
Logger.getLogger(ventana2.class.getName()).log(Level.SEVERE, null, ex);
}
}
        }//fin del if 
       else{
         JOptionPane.showMessageDialog(rootPane,"Proceso Cancelado");
        }

}        
public void FileTextRead2() throws IOException {
int i = 0;String valor,file=null;

JFileChooser fc = new JFileChooser();
int respuesta = fc.showOpenDialog(this);
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
while(currentRecord != null) {
currentRecord = InputFile.readLine();

try {
//Copio un valor a la celda 
//JOptionPane.showMessageDialog(null,currentRecord);//jTable3.setValueAt(currentRecord, i, 0);
datos[0][i]=currentRecord;//valor=function(currentRecord);
//datos[1][i]=valor;
//JOptionPane.showMessageDialog(null,i);
cadena="";
//Refresco la Tabla 
//jTable3.paintImmediately(jTable3.getX(),jTable3.getY(), jTable3.getWidth(), jTable3.getHeight());
i = i +1;
}
catch (Exception ex) {
}
}
JOptionPane.showMessageDialog(null,"Proceso Finalizado");

        
} catch (FileNotFoundException ex) {
Logger.getLogger(ventana2.class.getName()).log(Level.SEVERE, null, ex);
} finally {

    try {
myFile.close();

} catch (IOException ex) {
Logger.getLogger(ventana2.class.getName()).log(Level.SEVERE, null, ex);
}
}
        }//fin del if 
       else{
         JOptionPane.showMessageDialog(rootPane,"Proceso Cancelado");
        }
} 
 public void exportar_datos(String valor) throws IOException {

try{
//FileOutputStream fis = new FileOutputStream(f);
FileWriter  dos=new FileWriter(f,true);
//while (datos[1][i] != null){ //Se repite 233 veces
//dos.write(valor);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jButton3.setText("direcciones");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(java.awt.SystemColor.controlHighlight);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "1", "25", "1", "25"},
                {"2", "1", "25", "26", "50"},
                {"3", "1", "25", "51", "70"},
                {"4", "1", "25", "71", "100"},
                {"5", "1", "25", "101", "150"},
                {"6", "26", "50", "1", "150"},
                {"7", "51", "70", "1", "150"},
                {"8", "71", "100", "1", "150"},
                {"9", "101", "150", "1", "75"},
                {"10", "101", "150", "75", "150"}
            },
            new String [] {
                "ZONA", "INICIO X", "FIN X", "INICIO Y", "FIN Y"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getColumn(0).setResizable(false);
        jTable2.getColumnModel().getColumn(1).setResizable(false);
        jTable2.getColumnModel().getColumn(2).setResizable(false);
        jTable2.getColumnModel().getColumn(3).setResizable(false);
        jTable2.getColumnModel().getColumn(4).setResizable(false);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jButton1.setText("GEOREF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("IMPORTAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 376, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(56, 56, 56)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                .addContainerGap(311, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         jLabel1.setText("");cadena="";// TODO add your handling code here:
      String y; 
       
    y=preformatear(jTextField1.getText());//JOptionPane.showMessageDialog(null,y); 
    busc_alfanum(y);
    y=subtokenizar(y);if (estructura.indexOf("o")!=-1){y="0";cadena=" Direccion Incorrecta";}
    //JOptionPane.showMessageDialog(null, y);
    y=buscarstr(y);//JOptionPane.showMessageDialog(null, y);
    y=expresion(y);
    
   jLabel1.setText(y);
      jTextField1.setText("");
       jTextField1.requestFocus();cardinal="URBANO"; 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       jTextField2.setText(jLabel1.getText()+"//"+buscarzona(Integer.parseInt(X),Integer.parseInt(Y))); // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String y,valor; int fila=0, filas=jTable3.getRowCount();
      directorio=System.getProperty("user.dir");
   f = new File(directorio+"/prueba.txt");  
    //RUTINA QUE IMPORTAR DATOS DE TXT    
           try {
        FileTextRead();//JOptionPane.showMessageDialog(null,filas );
    } catch (IOException ex) {
        Logger.getLogger(ventana2.class.getName()).log(Level.SEVERE, null, ex);
    }
   
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    
    }//GEN-LAST:event_jButton2ActionPerformed
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

   
}
