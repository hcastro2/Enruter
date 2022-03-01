package enruter;

import java.util.Comparator;
import javax.swing.JOptionPane;
/**
 *
 * @author 57321
 */
public class idDirZona {
     String direccion;
   String cadena;
   String codigo;
   Double valorNum;int coordenada,ejex,X1,X2,ejey,Y1,Y2,numCasa,valorX,valorY;
   Object filasExcel[];int columnas;
    public idDirZona(String direccion, String cadena, String codigo,Double valorNum) {
        this.direccion = direccion;
        this.cadena = cadena;
        this.codigo = codigo;
        this.valorNum = valorNum;
        separarCodigo(codigo);
    }
    public idDirZona(String direccion, String cadena, String codigo,Double valorNum,Object[] filasExcel,int columnas) {
        this.direccion = direccion;
        this.cadena = cadena;
        this.codigo = codigo;
        this.valorNum = valorNum;
        this.columnas = columnas;
         this.filasExcel = new Object[columnas];
        separarCodigo(codigo);
    }
 
 private void separarCodigo(String s){
     try{
     String a [] = s.split("\\.");
     coordenada=Integer.parseInt(a[0]);
     ejex=Integer.parseInt(a[1]);
     X1=Integer.parseInt(a[2]);
     X2=Integer.parseInt(a[3]);
     ejey=Integer.parseInt(a[4]);
     Y1=Integer.parseInt(a[5]);
     Y2=Integer.parseInt(a[6]);
     numCasa=Integer.parseInt(a[8]);
     valorX=Integer.parseInt(a[0]+a[1]+a[2]+a[3]);
     valorY=Integer.parseInt(a[4]+a[5]+a[6]);
     }
     catch(NumberFormatException e){
         String error = e.getLocalizedMessage();
         error = error.substring(18, error.length());coordenada=0;ejex=0;X1=0;X2=0;Y1=0;Y2=0;valorX=0;valorY=0;
         numCasa=0;
          setCadena( getCadena()+"error"+ error);//JOptionPane.showMessageDialog(null, error);
     }
 }
// <editor-fold defaultstate="collapsed" desc="Getter and Setter Codigos"> 

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    
    public Object[] getFilasExcel() {
        return filasExcel;
    }

    public void setFilasExcel(Object[] filasExcel) {
        this.filasExcel = filasExcel;
    }
   

    public int getEjex() {
        return ejex;
    }

    public int getEjey() {
        return ejey;
    }
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }


    public int getValorX() {
        return valorX;
    }

    public int getValorY() {
        return valorY;
    }
    
    public int getCoordenada() {
        return coordenada;
    }

    public int getX1() {
        return X1;
    }

    public int getX2() {
        return X2;
    }

    public int getY1() {
        return Y1;
    }

    public int getY2() {
        return Y2;
    }

    public int getNumCasa() {
        return numCasa;
    }

    public String getDireccion() {
        return direccion;
    }
    public String getCadena() {
        return cadena;
    }
    public String getCodigo() {
        return codigo;
    }
    public Double getValorNum() {
        return valorNum;
    }
// </editor-fold>  

}

  class CompararIdZonas implements Comparator<idDirZona>{
     
     @Override
    public int compare(idDirZona e1, idDirZona e2){
      
       int resultado = Integer.compare( e1.getValorX(), e2.getValorX());
        if ( resultado != 0 ) { return resultado; }
        
       resultado = Integer.compare(e1.getValorY(), (e2.getValorY())); 
        return resultado;
    } 

}
    /*public int compare3(idDirZona e1, idDirZona e2){
      
       int resultado = Integer.compare( e1.getX1(), e2.getEjex());
        if ( resultado != 0 ) { return resultado; }
        
        return resultado;
    }    */
    
         
/* public int compare(idDirZona e1, idDirZona e2){
                
        if(e1.getValorX()<e2.getValorX()){
            return -1;
        }else if(e1.getX1()<e2.getX1()){
            return 0;
        }else{
            return 1;
        }
      
    }*/