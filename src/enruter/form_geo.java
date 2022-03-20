/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enruter;

import static enruter.dirTest.listaDirecciones;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author 57321
 */
public class form_geo extends javax.swing.JFrame implements ActionListener {
  String texto;
  static String datoGlobal;public static String pathJar;
    public form_geo() {
        initComponents();
        leerPropieties();obtenerRuta();leerPropietiesMain();
        lbProgreso.setVisible(false);//  ajustaricon();
      this.getContentPane().setBackground(Color.white);
      this.Home.setBackground(Color.white);
      anadeListenerAlModelo(tabla1);anadeListenerTablaZonas(tablaZonas);
      tablaZonas.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(generateBox()));
      tablaZonas.setColumnSelectionAllowed(true);
       addListeners();  //agrega los listeners 
    }
    private  void addListeners(){
         checkColumn2.addActionListener(this);
         btn_procesar.addActionListener(this);
         btn_open1.addActionListener(this);
         Filtrar.addActionListener(e -> { filtrar();});//Filtrar.addActionListener(this);
         //txt_Output.addActionListener(this); (e -> {      ;    });
    
}
// public static Properties config = new Properties();
  
    
     @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==btn_procesar){
             agregarTotxt(btn_procesar.getText());	
         }
        
        if (ae.getSource()==checkColumn2){
            Object[] estado= checkColumn2.getSelectedObjects();
           
          if (estado==null){   return;}  
            if (estado[0]=="Editar"){
                //tabla1.setModel(new DefaultTableModel(){ @Override public boolean isCellEditable(int row, int column) { if (column == x) { return true; } else return false; } }); 
                //Class<?> col_clas = tabla1.getColumnClass(1);
                //tabla1.getDefaultEditor(col_clas);JOptionPane.showMessageDialog(null, tabla1.getDefaultEditor(col_clas));
               
               // JOptionPane.showMessageDialog(null, tabla1.getCellEditor());
                //tabla1.setCellEditor(new DefaultCellEditor (checkColumn2));
               // tabla1.setEditingColumn(2);
               // tabla1.setCellSelectionEnabled( true );
               
              //  tabla1.setDefaultEditor(col_clas, null);
                
          
            }
             
         }
      
        if (ae.getSource()==btn_open1){
               ListaDirecciones l = new ListaDirecciones();
              try {
                  l.importar();
              } catch (IOException ex) {
                  Logger.getLogger(form_geo.class.getName()).log(Level.SEVERE, null, ex);
              }
         }
         if (ae.getSource()==tabla1){
             JOptionPane.showMessageDialog(null, "");	
         }
      }
    
    private void obtenerRuta(){////LEER ARCHIVO MAIN DE CONFIGURACION INCIAL SINO POR DEFECTO//////////////////////////////////////////////
       //String contexto;
       if ((pathJar==null)||("".equals(pathJar))){
                File f = new File(".");
                String path= f.getAbsolutePath().replace(".", "");//obtengo ruta del path
                lbInfopath.setText("Ruta de Informes:");
                txPathReports.setText(path);
                pathJar=txPathReports.getText();
       }else{
           txPathReports.setText(pathJar);
        //si no esta vacio ni nulo mantiene el dato en la variable global path
        //contexto=  pathJar;
        //      contexto =  System.getProperty("user.dir");
       }//JOptionPane.showMessageDialog(null, "E");
    } 
    public void agregarTotxt(String a){
        String valor = a;
        txt_result.setText(texto);
        
    } 
    public void procesar(){
        descomponer d = new descomponer();
        Zonal z = new Zonal();
        this.texto = txt_entrada.getText();
        if((texto.equals("")!=true)){
        txt_result.setText(d.texting(texto));
        lb_codigo.setText(d.codigo());//FUNCION QUE AGREGA EL CODIGO O ID_DIRECCION
        z.calcZona(lb_codigo.getText());//FUNCION QUE GENERA LA ZONA
        lb_zona.setText(z.getZona());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane2 = new javax.swing.JLayeredPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Home = new javax.swing.JPanel();
        lb_zona = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txt_entrada = new javax.swing.JTextField();
        txt_result = new javax.swing.JTextField();
        lb_codigo = new javax.swing.JLabel();
        lista_ciudades = new javax.swing.JComboBox<>();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        btnExcel = new javax.swing.JButton();
        btnCsv = new javax.swing.JButton();
        btn_procesar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        importarDirs = new javax.swing.JToggleButton();
        btn_list = new javax.swing.JButton();
        checklist_dirs = new javax.swing.JLabel();
        btn_open1 = new javax.swing.JButton();
        lb_zonas = new javax.swing.JLabel();
        lb_zonasHistoric = new javax.swing.JLabel();
        btn_zonalHistoric = new javax.swing.JButton();
        btn_zonal = new javax.swing.JButton();
        lbProgreso = new javax.swing.JLabel();
        txt_Output = new javax.swing.JTextField();
        panelZonas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaZonas = new javax.swing.JTable();
        btnGuardarZonas = new javax.swing.JButton();
        panelHistoric = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jbGuardar = new javax.swing.JButton();
        btRowMas = new javax.swing.JButton();
        btRowMenos = new javax.swing.JButton();
        checkColumn2 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaDirecciones = new javax.swing.JTable();
        Ordenar = new javax.swing.JButton();
        Filtrar = new javax.swing.JButton();
        txt_filtro = new javax.swing.JTextField();
        config = new javax.swing.JPanel();
        txPathReports = new javax.swing.JTextField();
        btnSelectPath = new javax.swing.JButton();
        lbInfopath = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLayeredPane2.setLayout(new java.awt.GridLayout());

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jButton1.setText("zonaP");
        jButton1.setToolTipText("Buaca la zona aproximada");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lista_ciudades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ciudad" }));
        lista_ciudades.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lista_ciudadesItemStateChanged(evt);
            }
        });

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Procesamiento"));

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/enruter/iconsexcel-48.png"))); // NOI18N
        btnExcel.setContentAreaFilled(false);
        btnExcel.setFocusPainted(false);
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        btnCsv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/CSV.png"))); // NOI18N
        btnCsv.setContentAreaFilled(false);
        btnCsv.setFocusPainted(false);
        btnCsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCsvActionPerformed(evt);
            }
        });

        btn_procesar.setText("Procesar");
        btn_procesar.setFocusPainted(false);
        btn_procesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_procesarActionPerformed(evt);
            }
        });

        btn_limpiar.setText("Limpiar");
        btn_limpiar.setFocusPainted(false);
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        importarDirs.setText("Procesar .txt ");
        importarDirs.setFocusPainted(false);
        importarDirs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importarDirsActionPerformed(evt);
            }
        });

        btn_list.setToolTipText("Funcion:procesarLista()");
        btn_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listActionPerformed(evt);
            }
        });

        checklist_dirs.setText("Listado Dirs:");

        btn_open1.setText("...");

        lb_zonas.setText("Zonas");

        lb_zonasHistoric.setText("Historic Zonas:");
        lb_zonasHistoric.setPreferredSize(new java.awt.Dimension(29, 14));

        btn_zonalHistoric.setText("jButton1");
        btn_zonalHistoric.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_zonalHistoricActionPerformed(evt);
            }
        });

        btn_zonal.setText("jButton1");
        btn_zonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_zonalActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(btnExcel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnCsv, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_procesar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_limpiar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(importarDirs, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_list, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(checklist_dirs, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_open1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lb_zonas, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lb_zonasHistoric, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_zonalHistoric, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_zonal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(importarDirs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_procesar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(0, 12, Short.MAX_VALUE)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_list, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(btnCsv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lb_zonas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checklist_dirs, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(lb_zonasHistoric, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_open1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_zonal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_zonalHistoric, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addComponent(btn_procesar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(importarDirs, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_list, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCsv, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checklist_dirs)
                    .addComponent(btn_open1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_zonas)
                    .addComponent(btn_zonal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_zonasHistoric, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                    .addComponent(btn_zonalHistoric, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        lbProgreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Procesando1.gif"))); // NOI18N
        lbProgreso.setText("Procesando");

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_entrada)
                    .addComponent(txt_result)
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_zona, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(HomeLayout.createSequentialGroup()
                                .addComponent(lb_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lista_ciudades, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(260, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_Output, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
            .addGroup(HomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lista_ciudades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_zona, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(lbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_Output, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Home", Home);

        tablaZonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre Zona", "Sector:Norte-sur-este-oeste", "Inicio Eje Calles", "Fin Eje Calles", "Inicio Eje Carreras", "Fin Eje Carreras", "A", "B"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaZonas);
        if (tablaZonas.getColumnModel().getColumnCount() > 0) {
            tablaZonas.getColumnModel().getColumn(1).setResizable(false);
        }

        btnGuardarZonas.setText("Guardar Zonas");
        btnGuardarZonas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarZonasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelZonasLayout = new javax.swing.GroupLayout(panelZonas);
        panelZonas.setLayout(panelZonasLayout);
        panelZonasLayout.setHorizontalGroup(
            panelZonasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
            .addGroup(panelZonasLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnGuardarZonas, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelZonasLayout.setVerticalGroup(
            panelZonasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelZonasLayout.createSequentialGroup()
                .addComponent(btnGuardarZonas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Zonas", panelZonas);

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Direcciones", "Inicio", "Correccion", "Resultado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla1);

        jbGuardar.setText("Guardar Cambios");
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });

        btRowMas.setText("+");
        btRowMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRowMasActionPerformed(evt);
            }
        });

        btRowMenos.setText("-");
        btRowMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRowMenosActionPerformed(evt);
            }
        });

        checkColumn2.setText("Editar");
        checkColumn2.setFocusPainted(false);

        javax.swing.GroupLayout panelHistoricLayout = new javax.swing.GroupLayout(panelHistoric);
        panelHistoric.setLayout(panelHistoricLayout);
        panelHistoricLayout.setHorizontalGroup(
            panelHistoricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHistoricLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHistoricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHistoricLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(checkColumn2)
                        .addGap(41, 41, 41)
                        .addComponent(jbGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(btRowMas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btRowMenos)
                        .addGap(29, 29, 29))
                    .addGroup(panelHistoricLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelHistoricLayout.setVerticalGroup(
            panelHistoricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHistoricLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(panelHistoricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHistoricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btRowMas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btRowMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelHistoricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbGuardar)
                        .addComponent(checkColumn2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("NoProcesadas", panelHistoric);

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentHidden(evt);
            }
        });

        tablaDirecciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cadena", "Codigo", "Direccion", "Valor Numerico"
            }
        ));
        jScrollPane3.setViewportView(tablaDirecciones);

        Ordenar.setText("Ordenar");
        Ordenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrdenarActionPerformed(evt);
            }
        });

        Filtrar.setText("Filtrar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Ordenar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(txt_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(Filtrar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ordenar)
                    .addComponent(Filtrar)
                    .addComponent(txt_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Direcciones", jPanel1);

        btnSelectPath.setText("Seleccionar Ruta");
        btnSelectPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectPathActionPerformed(evt);
            }
        });

        lbInfopath.setText("Path Reportes");

        javax.swing.GroupLayout configLayout = new javax.swing.GroupLayout(config);
        config.setLayout(configLayout);
        configLayout.setHorizontalGroup(
            configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbInfopath, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .addComponent(txPathReports))
                .addGap(18, 18, 18)
                .addComponent(btnSelectPath)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        configLayout.setVerticalGroup(
            configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInfopath, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txPathReports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelectPath))
                .addContainerGap(296, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Configuracion", null, config, "Seleccione Donde guardar sus archivos");

        jLayeredPane2.add(jTabbedPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLayeredPane2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLayeredPane2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//<editor-fold defaultstate="collapsed" desc="Generated Codigo objetos btn,lb,txt...formWindowOpened">    
    private void btn_procesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_procesarActionPerformed
        // TODO add your handling code here:
    // try {
        procesar(); // TODO add your handling code here:
      /* }catch(Exception e){ 
           JOptionPane.showMessageDialog(null, e);
       }  */ 
    }//GEN-LAST:event_btn_procesarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        // TODO add your handling code here:
        txt_entrada.setText("");
        txt_result.setText("");
        lb_codigo.setText("");lb_zona.setText("");
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void importarDirsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importarDirsActionPerformed
      try {
          // TODO add your handling code here:
          dirTest c = new dirTest();
          c.importarDirTest();
      } catch (IOException ex) {
          Logger.getLogger(form_geo.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_importarDirsActionPerformed

    private void btn_zonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_zonalActionPerformed
      try {
          // TODO add your handling code here:
          baseZonas zn = new baseZonas();
          zn.importarZonas();
      } catch (IOException ex) {
          Logger.getLogger(form_geo.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_btn_zonalActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        txt_entrada.requestFocus();
        this.checklist_dirs.setText(ListaDirecciones.checklist);//
        if (baseZonas.zonasCargadas==true){this.lb_zonas.setText("Zonas cargadas");}else{this.lb_zonas.setText("Zonas Null");}//this.lb_zonas.setText();
        llenarCombo();//llena lista de ciudades//JOptionPane.showMessageDialog(null, ruta);
    }//GEN-LAST:event_formWindowOpened

    private void btn_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listActionPerformed
      try {
          dirTest d = new dirTest();// TODO add your handling code here:
          d.procesarLista();
      } catch (IOException ex) {
          Logger.getLogger(form_geo.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_btn_listActionPerformed

    private void btn_zonalHistoricActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_zonalHistoricActionPerformed
        // TODO add your handling code here:
         try {
          // TODO add your handling code here:
          historicZonas znh = new historicZonas();
          znh.importarZonasHistoric();
      } catch (IOException ex) {
          Logger.getLogger(form_geo.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_btn_zonalHistoricActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     // String r= historicZonas.buscarZona(this.lb_codigo.getText());
      String r= historicZonas.buscarZonaProxi(this.lb_codigo.getText());
       JOptionPane.showMessageDialog(null, r);
         
    }//GEN-LAST:event_jButton1ActionPerformed
   //</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Generated Codigo jTabeed,jPanel,Tabla direcciones">     
    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
       try{
     
         if (jTabbedPane1.getSelectedIndex()==3){
           llenarTable();
        }
       }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error: "+e);
    }//tablaDirecciones.removeRowSelectionInterval(0, tablaDirecciones.getRowCount());
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentHidden
       updateTable();
    }//GEN-LAST:event_jPanel1ComponentHidden
    
    private void filtrar(){
       String filtro=   txt_filtro.getText();//Object[] idz2;
        if((dirTest.listaDirecciones==null)||(dirTest.listaDirecciones.size()<1)){}else{
            
       List<Object> dir =  dirTest.listaDirecciones.stream()//List<idDirZona> dir =  dirTest.listaDirecciones.stream()
                  .filter(p->p.getCadena().contains(filtro))
                  .sorted((e1, e2) -> e1.getDireccion().compareTo(e2.getDireccion()))
                  .map(p->idzToArray(p))//convertimos al objeto idzona en objeto generico por addrow que solo acepta objetos
                  .collect(Collectors.toList());
               
     DefaultTableModel model2 =  (DefaultTableModel) tablaDirecciones.getModel();      
      // Object[] idz = dir.toArray(Object[]::new); model2.setRowCount(0);
          model2.setRowCount(0);
     dir.forEach(ob->{model2.addRow((Object[]) ob);});
     // for (Object ob:dir){model2.addRow((Object[])ob);}    
 
        }

    }
    private Object[] idzToArray(idDirZona idz){
        String cadena=idz.getCadena();
        String codigo=idz.getCodigo();
        String dir=idz.getDireccion();
        Double valor=idz.getValorNum();
        Object vect[]={cadena,codigo,dir,valor};
      return vect;
        
    }
    private void OrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrdenarActionPerformed
        updateTable();
        llenarTable();
    }//GEN-LAST:event_OrdenarActionPerformed
        private void llenarTable(){
        
             if(tablaDirecciones.getRowCount()!=0){}else{
             
           if((dirTest.listaDirecciones==null)||(dirTest.listaDirecciones.size()<1)){}else{
           List<Object> dir =  dirTest.listaDirecciones.stream().map(p->idzToArray(p)).collect(Collectors.toList());
           
            DefaultTableModel model2 =  (DefaultTableModel) tablaDirecciones.getModel();
            dir.forEach(i -> {model2.addRow((Object[])i);});
            /*   for (Object i: dirTest.listaDirecciones )   {
               Object nuevo[]= {dirTest.listaDirecciones.get(cont).cadena,dirTest.listaDirecciones.get(cont).codigo,dirTest.listaDirecciones.get(cont).direccion,dirTest.listaDirecciones.get(cont).valorNum};
               model2.addRow(nuevo);cont++;
               }//JOptionPane.showMessageDialog(null,dirTest.listaDirecciones.get(1).X1);*/
             }
            }
    }
        private void updateTable(){//ordena la tabla
        if(tablaDirecciones.getRowCount()==0){}else{
        Collections.sort(listaDirecciones,new CompararIdZonas());//LINEA QUE ORDENA LAS DIRECCIONES POR CALLE Y CARRERA FALTA NUMERO
        DefaultTableModel model2 =  (DefaultTableModel) tablaDirecciones.getModel();// TODO add your handling code here:
        model2.setRowCount(0);
           }
        
        }
        
        
//</editor-fold >   
        
//<editor-fold defaultstate="collapsed" desc="objetos: jPanel1,btnExcel,btnCsv,listciud btn,lb,txt...">        
    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
    // lbProcesando.setVisible(true);
        try {
   
          Excel ex = new Excel();
          
        // ajustaricon();//muestra gif de procesando
         // ex.importExcel();// TODO add your handling code here:
          ex.importExcel();
          llenarTable(descomponer.DirErrColect);//LLENA LA TABLA CON LAS DIRECCIONES NO LEIDAS
           descomponer.exportar_TextBasura(descomponer.garbagColect);
           
      } catch (IOException ex1) {
          Logger.getLogger(form_geo.class.getName()).log(Level.SEVERE, null, ex1);
      }
    }//GEN-LAST:event_btnExcelActionPerformed

    private void lista_ciudadesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lista_ciudadesItemStateChanged
     
        int  item =  lista_ciudades.getSelectedIndex(),codigo;
      String cadena = (String) lista_ciudades.getSelectedItem();
     if (item<1){} else{
      if ((evt.getStateChange() == 1)) {
        String arr[] =  cadena.split("-");ciudades.ciudadGlobal = Integer.parseInt(arr[1]);
         JOptionPane.showMessageDialog(null, ciudades.ciudadGlobal);
      }
     }
    }//GEN-LAST:event_lista_ciudadesItemStateChanged
        private void llenarCombo(){
        String ciudDep, idCiud;
        if(ciudades.ciudades==null){ JOptionPane.showMessageDialog(null,"No Found Ciudades");}else{
        int cont=0;
       for (Object i: ciudades.ciudades){
          if(i==null){}else{ 
          String texto1[]=  ciudades.ciudades.get(cont).split("-");
          ciudDep=texto1[0].toLowerCase();idCiud=texto1[1];//Integer.parseInt(texto[1]);
          ciudades.mapCiud.put(ciudDep, idCiud);//(ciudDep, idCiud);
           lista_ciudades.addItem(ciudades.ciudades.get(cont));cont++;
          }
        }
      }
    }//llena el combo ciudades
    private void btnCsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsvActionPerformed
         
        final SwingWorker worker = new SwingWorker(){
 
			@Override
			protected Object doInBackground() throws Exception {
				//METODO AQUI	   
        try {
          Excel ex = new Excel();
          lbProgreso.setVisible(true);
          ex.importCsv();lbProgreso.setVisible(false);//txt_Output.setText(datoGlobal);
          llenarTable(descomponer.DirErrColect);//LLENA LA TABLA CON LAS DIRECCIONES NO LEIDAS
          
          
          
         descomponer.exportar_TextBasura(new String(descomponer.garbagColect.getBytes("ISO-8859-1"), "UTF-8"));// JOptionPane.showMessageDialog(null,descomponer.garbagColect);
      } catch (IOException ex1) {
          Logger.getLogger(form_geo.class.getName()).log(Level.SEVERE, null, ex1);
      }
     	return null;
			}	
		};
		worker.execute();   
     
                               
    }//GEN-LAST:event_btnCsvActionPerformed
    //</editor-fold > 
     //<editor-fold defaultstate="collapsed" desc="FUNCIONES Y CODIGOS JTABLE NOCORREGIDOS">
   
    private void llenarTable(String matrizDoComas){
        if("".equals(matrizDoComas)){return;}
        String segmentos[] = matrizDoComas.split(",");
        DefaultTableModel model =  (DefaultTableModel) tabla1.getModel();
        
        for(String seg:segmentos){
            String segmentos2[] = seg.split("\\.");
               if(!"".equals(seg)){ 
                Object nuevo[]={seg,segmentos2[0],"",""};
                model.addRow(nuevo);
               }
        }
        
    }
    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        // TODO add your handling code here: boton para la tabla de resultados con error XY y guargar una matriz dinamica de correccion personalizada por el usuario
        int filas = tabla1.getRowCount();int conta=0;//JOptionPane.showMessageDialog(null, );
        for (int cont=0; cont<filas;cont++){
            String err=String.valueOf(tabla1.getValueAt(cont,1));
              String dato=String.valueOf(tabla1.getValueAt(cont,2));
            if((tabla1.getValueAt(cont,3)!=null)){      
               if((!"".equals(err))&&(!"".equals(dato))){ 
                   descomponer.dirsXYnull.put(err, dato);
                     conta++;
                 }
              }else{return;}
        }
        JOptionPane.showMessageDialog(null,conta +" Elementos Guardados" );
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void btRowMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRowMasActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model2 =  (DefaultTableModel) tabla1.getModel();
            
            Object nuevo[]= {};
            model2.addRow(nuevo);
    }//GEN-LAST:event_btRowMasActionPerformed

    private void btRowMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRowMenosActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model2 =  (DefaultTableModel) tabla1.getModel();
        int fila = tabla1.getSelectedRow();
        if (fila >= 0) {
            model2.removeRow(fila);
        }
    }//GEN-LAST:event_btRowMenosActionPerformed
      private void anadeListenerAlModelo(JTable tabla) {
        tabla.getModel().addTableModelListener(this::actualizartabla1);
      }
      public void actualizartabla1(TableModelEvent evento){
           if (evento.getType() == TableModelEvent.UPDATE) {
            // Se obtiene el modelo de la tabla y la fila/columna que han cambiado.
            TableModel modelo = ((TableModel) (evento.getSource()));
            int fila = evento.getFirstRow();
            int columna = evento.getColumn();

            if (fila == 2 || columna == 3) {
                return;
            }
            
            updateCell();//JOptionPane.showMessageDialog(null,fila) ;
           }
      }
      private void updateCell(){
          int row = tabla1.getSelectedRow(); 
              String dir=String.valueOf(tabla1.getValueAt(row,0));
              String err=String.valueOf(tabla1.getValueAt(row,1));
              String dato=String.valueOf(tabla1.getValueAt(row,2));
              String mod=dir.replaceFirst(err+".", dato+".");
              tabla1.setValueAt(mod,row , 3);
      }
     
  
   //</editor-fold> 
    
//<editor-fold defaultstate="collapsed" desc="Generated Codigo archivos propiedades">               
    private void escribirPropiedades(){
        int cont=descomponer.dirsXYnull.size();String llavesXY="";
        try{
            
            //p.setProperty("user", "zambra");p.store(fOutputStream, "");
            if(cont>0){
                for (String key : descomponer.dirsXYnull.keySet()) {
                    llavesXY=llavesXY+key+","+descomponer.dirsXYnull.get(key)+ ";";
	        System.out.println(key + " = " + descomponer.dirsXYnull.get(key)); 
	        }
            Properties p = new Properties();  
                //FileOutputStream fOutputStream = new FileOutputStream("propiedades.properties");//original
                try (FileOutputStream fOutputStream = new FileOutputStream("propiedades.properties")) {
                    //FileOutputStream fOutputStream = new FileOutputStream("propiedades.properties");//original
                    p.setProperty("dirsXY", llavesXY);p.store(fOutputStream, "");   
                }
            } 
            
     
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error guardando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void leerPropieties() {
        File f = new File(".");
        String path= f.getAbsolutePath().replace(".", "");//obtengo ruta del path
        //JOptionPane.showMessageDialog(null, path);
        try{
           String llavesXY;
           Properties propiedades = new Properties();
           propiedades.load(new FileReader(path+"propiedades.properties"));
           llavesXY = propiedades.getProperty("dirsXY");
           String texto2[]=  llavesXY.split(";");
           if(texto2.length>0){
               for(String txt:texto2){
                   String seg[]=  txt.split(",");
                   descomponer.dirsXYnull.put(seg[0], seg[1]);
               }
           }
           
          }
         catch(FileNotFoundException e){
          JOptionPane.showMessageDialog(null,"No se encontro el archivo Propiedades");
       
          } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error de Lectura configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    private void leerPropietiesMain() {
        String path = System.getProperty("user.dir");
        
        try{
              
           String pathReports;
           Properties propiedades = new Properties();
           propiedades.load(new FileReader(path+"\\main.properties"));
           pathReports = propiedades.getProperty("pathReports");
              if(("".equals(pathReports))||(pathReports==null)){
                  pathJar=path;//txPathReports.setText(pathJar);
              }else{
               pathJar=pathReports;
                //txPathReports.setText(pathJar);
               }
              txPathReports.setText(pathJar);
          }
         catch(FileNotFoundException e){
          JOptionPane.showMessageDialog(null,"No se encontro el archivo main");
       
          } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error de Lectura configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    private void escribirPropiedadesMainPath(String path){
        
        try{
            Properties p = new Properties();  
            // FileOutputStream fOutputStream = new FileOutputStream("main.properties"); // original
            try (FileOutputStream fOutputStream = new FileOutputStream("main.properties")) {
                // FileOutputStream fOutputStream = new FileOutputStream("main.properties"); // original
                p.setProperty("pathReports", path);p.store(fOutputStream, "");
            }
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error guardando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//</editor-fold >     
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        escribirPropiedades();
        escribirPropiedadesMainPath(txPathReports.getText());
    }//GEN-LAST:event_formWindowClosing

    private void btnSelectPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPathActionPerformed
        // TODO add your handling code here:
        String path;
         ///////////////////////////////////////////////////////////////////////////////////////////////
         JFileChooser fc = new JFileChooser();
         fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         int respuesta = fc.showOpenDialog(this);
         if (respuesta == JFileChooser.APPROVE_OPTION) {
             path= fc.getSelectedFile().getAbsolutePath()+"\\";
               txPathReports.setText(path);
               pathJar=path;
         }
    }//GEN-LAST:event_btnSelectPathActionPerformed
/**
     * @param args the command line arguments
     */
    
//<editor-fold defaultstate="collapsed" desc="Generated Codigo CODIGOS JTABLE ZONAS">     
    private void btnGuardarZonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarZonasActionPerformed
        String acumZonas=""; // TODO add your handling code here:
          int filas = tablaZonas.getRowCount();int conta=0,contrr=0;//JOptionPane.showMessageDialog(null, );
        //  primero verificar inconsistencias
        for (int cont=0; cont<filas;cont++){
          Object control= tablaZonas.getValueAt(cont,0);
           if (control!=null){//determina las celdas no vacias de la tabla
               String control2 =String.valueOf(tablaZonas.getValueAt(cont,6));
              if((control2.contains("null"))||(control2.contains("?"))){ 
                  contrr++;//JOptionPane.showMessageDialog(null,"linea Inconsistente" );
                       }else{  
                  acumZonas=acumZonas+control2+"\n";conta++;//aqui debe guardar
                  }
            
           }
        }
        JOptionPane.showMessageDialog(null,conta +" Elementos Guardados\nLineas Con inconsistencias "+ contrr+"\n"+acumZonas );
      try {
          tools.exportarXarchivo(acumZonas,"zonasCreadas");
      } catch (IOException ex) {
         JOptionPane.showMessageDialog(null,"Error al Exportar");
      }
    }//GEN-LAST:event_btnGuardarZonasActionPerformed
     private void anadeListenerTablaZonas(JTable tabla) {
        tablaZonas.getModel().addTableModelListener(this::actualizartablaZona);
      } 
    public void actualizartablaZona(TableModelEvent evento){
           if (evento.getType() == TableModelEvent.UPDATE) {
            // Se obtiene el modelo de la tabla y la fila/columna que han cambiado.
            TableModel modelo2 = ((TableModel) (evento.getSource()));
            int fila = evento.getFirstRow();
            int columna = evento.getColumn();

            if (fila == -1 || columna == 6) {
                return;
            }
            
            updateCellZ();//JOptionPane.showMessageDialog(null,fila) ;
           }
      }
    private void updateCellZ(){
          int row = tablaZonas.getSelectedRow(); String control="Ok";
          //Z-CENTRO1|0#23.0:33.0;19.0:33.1    EJEMPLO
              String nombreZ=String.valueOf(tablaZonas.getValueAt(row,0));
              String sectorZ=configCoord(String.valueOf(tablaZonas.getValueAt(row,1)));
              String inicioX=configData(String.valueOf(tablaZonas.getValueAt(row,2)));
              String finX=configData(String.valueOf(tablaZonas.getValueAt(row,3)));
              String inicioY=configData(String.valueOf(tablaZonas.getValueAt(row,4)));
              String finY=configData(String.valueOf(tablaZonas.getValueAt(row,5)));
              String zona=nombreZ+"|"+sectorZ+"#"+inicioX+":"+finX+";"+inicioY+":"+finY;
              if(String.valueOf(tablaZonas.getValueAt(row,0)).equals("null")){zona="null";}
              tablaZonas.setValueAt(zona,row , 6);
              
              
             
      }
    private String configCoord(String coord){
        //pasar a expresion lambda
       String result="";
        if ("null".equals(coord)){
          return "null";
      }else{ 
          if("Urbano".equals(coord)){result="0";} 
          if("Norte".equals(coord)){result="1";}  
          if("Sur".equals(coord)){result="2";}  
          if("Este".equals(coord)){result="3";}
          if("Oeste".equals(coord)){result="4";}
        }
      return result;
    }
    private String configData(String dato){
      //los datos se deben transformar en numero decimal que respresente calle y subvia  
      String result="";
      if ("null".equals(dato)){
          return "null";
      }else{ 
        String tipo=  tools.isTipo(dato);
        switch(tipo){
            case "l":
                result = "N?";//JOptionPane.showMessageDialog(null,"tipo de dato incorrecto: "+ dato);
                break;
            case "n":
               //solo recibe valores enteros
                //los convertira en decimal con un punto
                result= dato+".0";
                break;

            case "d":
                    if (dato.contains(".")){
                        //aqui casos que se escribe el punto como dato decimal
                       String segmentos[] = dato.split("\\."); 
                          if((segmentos.length==2)&&("n".equals(tools.isTipo(segmentos[0])))&&("n".equals(tools.isTipo(segmentos[1])))){
                          result=dato;
                          } 
                              else{result="Error";}

                    }else{
                    //aqui casos que se puede escribir la calle o la nomenclatura tradicional
                    String segmento[] = dato.split(" ");
                         if((segmento.length==2)&&("n".equals(tools.isTipo(segmento[0])))&&("l".equals(tools.isTipo(segmento[1])))){

                             result=segmento[0]+"."+tools.letraToNum(segmento[1]);
                          } 
                              else{result="Error";}
                    }
                break;
            default:
                break;
        }  
          
        
      }
      
        
      return result;
    }
    
    //<editor-fold defaultstate="collapsed" desc="CODIGO:genera combo dentro de celda">
                private JComboBox generateBox(){
                 JComboBox bx=null;

                 try
                 {

                     bx=new JComboBox();
                     bx.addItem("Urbano");
                     bx.addItem("Norte");
                     bx.addItem("Sur");
                     bx.addItem("Este");
                     bx.addItem("Oeste");


                 }catch(Exception x)
                 {
                     System.out.println(x.getMessage());
                 }
                         return bx;

             }
   //</editor-fold>          
    
  //</editor-fold >
                
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_geo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_geo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_geo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_geo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new form_geo().setVisible(true);
        });
    }
    
     
//<editor-fold defaultstate="collapsed" desc="Variables declaration">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Filtrar;
    private javax.swing.JPanel Home;
    private javax.swing.JButton Ordenar;
    private javax.swing.JButton btRowMas;
    private javax.swing.JButton btRowMenos;
    private javax.swing.JButton btnCsv;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnGuardarZonas;
    private javax.swing.JButton btnSelectPath;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_list;
    private javax.swing.JButton btn_open1;
    private javax.swing.JButton btn_procesar;
    private javax.swing.JButton btn_zonal;
    private javax.swing.JButton btn_zonalHistoric;
    private javax.swing.JCheckBox checkColumn2;
    private javax.swing.JLabel checklist_dirs;
    private javax.swing.JPanel config;
    private javax.swing.JToggleButton importarDirs;
    private javax.swing.JButton jButton1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JLabel lbInfopath;
    private javax.swing.JLabel lbProgreso;
    private javax.swing.JLabel lb_codigo;
    private javax.swing.JLabel lb_zona;
    private javax.swing.JLabel lb_zonas;
    private javax.swing.JLabel lb_zonasHistoric;
    private javax.swing.JComboBox<String> lista_ciudades;
    private javax.swing.JPanel panelHistoric;
    private javax.swing.JPanel panelZonas;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tablaDirecciones;
    private javax.swing.JTable tablaZonas;
    private javax.swing.JTextField txPathReports;
    public static javax.swing.JTextField txt_Output;
    private javax.swing.JTextField txt_entrada;
    private javax.swing.JTextField txt_filtro;
    private javax.swing.JTextField txt_result;
    // End of variables declaration//GEN-END:variables
//</editor-fold>
}
 
