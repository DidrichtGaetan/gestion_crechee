/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Entities.Enfant;
import Entities.Mois;
import Utile.BeanBDAccess;
import Utile.FonctionsUtiles;
import com.sun.media.sound.InvalidFormatException;
import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Gaetan
 */
public class MainMenu extends javax.swing.JFrame {

     private BeanBDAccess bd;
     private ResultSet resultat;
     private DefaultTableModel model;
     private DefaultTableModel modelMois;
     String file_path = "";
     String path_mois = "";
     List<Enfant> list = new ArrayList<Enfant>();
     List<Enfant> Fulllist = new ArrayList<Enfant>();
     List<Enfant> enfantsJanvier = new ArrayList<Enfant>();
     List<Enfant> enfantsFevrier = new ArrayList<Enfant>();
     List<Enfant> enfantsMars = new ArrayList<Enfant>();
     List<Enfant> enfantsAvril = new ArrayList<Enfant>();
     List<Enfant> enfantsMai = new ArrayList<Enfant>();
     List<Enfant> enfantsJuin = new ArrayList<Enfant>();
     List<Enfant> enfantsJuillet = new ArrayList<Enfant>();
     List<Enfant> enfantsAout= new ArrayList<Enfant>();
     List<Enfant> enfantsSeptembre = new ArrayList<Enfant>();
     List<Enfant> enfantsOctobre = new ArrayList<Enfant>();
     List<Enfant> enfantsNovembre = new ArrayList<Enfant>();
     List<Enfant> enfantsDecembre = new ArrayList<Enfant>();
     List<Mois> listMois = new ArrayList<Mois>();
     
    public MainMenu() throws IOException {
        initComponents();
        Properties prop = FonctionsUtiles.ChargerProperties();
        System.out.println("prop : " + prop.getProperty("Path_zip"));
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            // Windows
            file_path = "C:\\Temp\\enfants.xlsx";
            path_mois = "C:\\Temp\\";
        } else if (os.contains("Mac")) {
            // Mac
        }
        jTable1.getTableHeader().setOpaque(false);
        model = (DefaultTableModel)jTable1.getModel();
        modelMois = (DefaultTableModel)jTable2.getModel();
        
        list = readFile(file_path);
        
            for(int i=0 ; i< list.size(); i++)
            { 
                Enfant e = (Enfant) list.get(i);
                Vector vector = new Vector<>();
                for(int j=0;j<list.size();j++)
                {
                    vector.add(e.getNum()); 
                    vector.add(e.getNom());
                    vector.add(e.getPrenom());
                    vector.add(e.getDate_naissance());
                    vector.add(e.getDate_entree());
                    vector.add(e.getDate_sortie());
                    vector.add(e.getLundi_am());
                    vector.add(e.getLundi_pm());
                    vector.add(e.getMardi_am());
                    vector.add(e.getMardi_pm());
                    vector.add(e.getMercredi_am());
                    vector.add(e.getMercredi_pm());
                    vector.add(e.getJeudi_am());
                    vector.add(e.getJeudi_pm());
                    vector.add(e.getVendredi_am());
                    vector.add(e.getVendredi_am());
                    vector.add(e.getMontant_jour());
                    vector.add(e.getMontant_forfait());
                    vector.add(e.getCommentaire());
                    vector.add(e.getMere());
                    vector.add(e.getEmail_mere());
                    vector.add(e.getNum_mere());
                    vector.add(e.getPere());
                    vector.add(e.getEmail_pere());
                    vector.add(e.getNum_pere());
                    vector.add(e.getNum_contrat());
                    Fulllist.add(e);
                }
                model.addRow(vector);  
                
            }
            
            jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow != -1) {
                        // Convertit l'indice de ligne de la JTable en indice de ligne dans le modèle de données
                        int modelRowIndex = jTable1.convertRowIndexToModel(selectedRow);
                        Object[] rowData = new Object[model.getColumnCount()];
                        for (int i = 0; i < model.getColumnCount(); i++) {
                            rowData[i] = model.getValueAt(modelRowIndex, i); 
                        } 
                        Enfant e = rechercheParId(Fulllist,rowData[0].toString());
                        System.out.println("enfant : " + e);
                        EnfantDetail detailsWindow = new EnfantDetail(e);
                        detailsWindow.setVisible(true);
                        detailsWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    }
                }
            }
        });
            
            jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    // Code à exécuter lorsque l'utilisateur sélectionne une ligne
                   if (!event.getValueIsAdjusting()) {
                        int selectedRow = jTable2.getSelectedRow();
                       try {
                           showPayementWindow(selectedRow);
                       } catch (InterruptedException ex) {
                           Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                       }
                     }
                }
            });
    }
    
    public void showPayementWindow(int selectedRow) throws InterruptedException {
        Payement payementWindow;
        switch(selectedRow) {
            case 0 :
                payementWindow = new Payement("Janvier",jTextField2.getText(),enfantsJanvier); 
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
            
            case 1 :
                payementWindow = new Payement("Février",jTextField2.getText(),enfantsFevrier);
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
            
            case 2 :
                payementWindow = new Payement("Mars",jTextField2.getText(),enfantsMars);
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
            
            case 3 :
                payementWindow = new Payement("Avril",jTextField2.getText(),enfantsAvril);
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
            
            case 4 :
                payementWindow = new Payement("Mai",jTextField2.getText(),enfantsMai);
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
            
            case 5 :
                payementWindow = new Payement("Juin",jTextField2.getText(),enfantsJuin);
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
            
            case 6 :
                payementWindow = new Payement("Juillet",jTextField2.getText(),enfantsJuillet);
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
            
            case 7 :
                payementWindow = new Payement("Aout",jTextField2.getText(),enfantsAout);
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
            
            case 8 :
                payementWindow = new Payement("Septembre",jTextField2.getText(),enfantsSeptembre);
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
            
            case 9 :
                payementWindow = new Payement("Octobre",jTextField2.getText(),enfantsOctobre);
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
            
            case 10 :
                payementWindow = new Payement("Novembre",jTextField2.getText(),enfantsNovembre);
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
            
            case 11 :
                payementWindow = new Payement("Décembre",jTextField2.getText(),enfantsDecembre);
                payementWindow.setVisible(true);
                payementWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            break;
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

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1700, 900));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 19)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nom", "Prénom", "Date de naissance"
            }
        ));
        jTable1.setAlignmentX(1.5F);
        jTable1.setAlignmentY(1.5F);
        jTable1.setFocusable(false);
        jTable1.setRowHeight(30);
        jTable1.setSelectionBackground(new java.awt.Color(0, 120, 211));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel1.setText("Recherche : ");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1650, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Enfants", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel2.setText("Entrez l'année : ");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mois", "Lundi-AM", "Lundi-PM", "Mardi-AM", "Mardi-PM", "Mercredi-AM", "Mercredi-PM", "Jeudi-AM", "Jeudi-PM", "Vendredi-AM", "Vendredi-PM"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(255, 255, 255));
        jTable2.setRowHeight(60);
        jTable2.setSelectionBackground(new java.awt.Color(51, 153, 255));
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jButton3.setText("Recherche");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jButton4.setText("Détails\n");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1523, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(809, 809, 809)
                    .addComponent(jButton4)
                    .addContainerGap(810, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(412, 412, 412)
                    .addComponent(jButton4)
                    .addContainerGap(565, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Mois", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
           // TODO add your handling code here:
        search(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            // TODO add your handling code here:
            parseFileMonth(path_mois+"mois-"+jTextField2.getText()+".xlsx");

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Fichier introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {
             Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
         }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    
    public void parseFileMonth(String filePath) throws FileNotFoundException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        try {
            listMois.clear();
            enfantsJanvier.clear();
            enfantsFevrier.clear();
            enfantsMars.clear();
            enfantsAvril.clear();
            enfantsMai.clear();
            enfantsJuin.clear();
            enfantsJuillet.clear();
            enfantsAout.clear();
            enfantsSeptembre.clear();
            enfantsOctobre.clear();
            enfantsNovembre.clear();
            enfantsDecembre.clear();
            Mois janvier = new Mois();
            Mois fevrier = new Mois();
            Mois mars = new Mois();
            Mois avril = new Mois();
            Mois mai = new Mois();
            Mois juin = new Mois();
            Mois juillet = new Mois();
            Mois aout = new Mois();
            Mois septembre = new Mois();
            Mois octobre = new Mois();
            Mois novembre = new Mois();
            Mois decembre = new Mois();

            //Création d'un objet FileInputStream pour lire le fichier
            FileInputStream fis = new FileInputStream(new File(filePath));
            //Création d'un objet Workbook à partir du fichier
            Workbook workbook = WorkbookFactory.create(fis);

            //Récupération de la première feuille du classeur
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            rows.next();
            //Parcours des lignes de la feuille
            while (rows.hasNext()) {
                
                Row row = rows.next();
                Double d = row.getCell(0).getNumericCellValue();
                Enfant enfant = rechercheParId(list, Double.toString(d));
                
                if(row.getCell(1) != null) {
                    
                    if(row.getCell(1).getNumericCellValue() == 1) {      
                    janvier.setMois("Janvier");
                    Cell cell = row.getCell(13);
                    if (cell != null) {
                        enfant.setPaye((int) row.getCell(13).getNumericCellValue());
                    } else {
                        enfant.setPaye(0); 
                    }
                   
                    janvier = compterJour(enfant,janvier);  
                    enfantsJanvier.add(enfant);
                    
                    } 
                }
                
                if(row.getCell(2) != null) {
                    if(row.getCell(2).getNumericCellValue() == 1) {
                        fevrier.setMois("Février");
                        Cell cell = row.getCell(14);
                        if (cell != null) {
                            enfant.setPaye((int) cell.getNumericCellValue());
                        } else {
                            enfant.setPaye(0); 
                        }
                        fevrier = compterJour(enfant,fevrier);
                        enfantsFevrier.add(enfant);
                    } 
                }
                
                if(row.getCell(3) != null) {
                    if(row.getCell(3).getNumericCellValue() == 1) {
                        mars.setMois("Mars");
                        Cell cell = row.getCell(15);
                        if (cell != null) {
                            enfant.setPaye((int) cell.getNumericCellValue());
                        } else {
                            enfant.setPaye(0); 
                        }
                        mars = compterJour(enfant,mars);
                        enfantsMars.add(enfant);
                    }
                }
                
                if(row.getCell(4) != null) {
                    if(row.getCell(4).getNumericCellValue() == 1) {
                        avril.setMois("Avril");
                        Cell cell = row.getCell(16);
                        if (cell != null) {
                            enfant.setPaye((int) cell.getNumericCellValue());
                        } else {
                            enfant.setPaye(0); 
                        }
                        avril = compterJour(enfant,avril);
                        enfantsAvril.add(enfant);
                    }
                }
                
                if(row.getCell(5) != null) {
                    if(row.getCell(5).getNumericCellValue() == 1) {
                        mai.setMois("Mai");
                        Cell cell = row.getCell(17);
                        if (cell != null) {
                            enfant.setPaye((int) cell.getNumericCellValue());
                        } else {
                            enfant.setPaye(0); 
                        }
                        mai = compterJour(enfant,mai);
                        enfantsMai.add(enfant);
                    } 
                }
                
                if(row.getCell(6) != null) {
                    if(row.getCell(6).getNumericCellValue() == 1) {
                        juin.setMois("Juin");
                        Cell cell = row.getCell(18);
                        if (cell != null) {
                            enfant.setPaye((int) cell.getNumericCellValue());
                        } else {
                            enfant.setPaye(0); 
                        }
                        juin = compterJour(enfant,juin);
                        enfantsJuin.add(enfant);
                    }
                }
                
                if(row.getCell(7) != null) {
                    if(row.getCell(7).getNumericCellValue() == 1) {
                        juillet.setMois("Juillet");
                        Cell cell = row.getCell(19);
                        if (cell != null) {
                            enfant.setPaye((int) cell.getNumericCellValue());
                        } else {
                            enfant.setPaye(0); 
                        }
                        juillet = compterJour(enfant,juillet);
                        enfantsJuillet.add(enfant);
                    } 
                }
                
                if(row.getCell(8) != null) {
                    if(row.getCell(8).getNumericCellValue() == 1) {
                        aout.setMois("Aout");
                        Cell cell = row.getCell(20);
                        if (cell != null) {
                            enfant.setPaye((int) cell.getNumericCellValue());
                        } else {
                            enfant.setPaye(0); 
                        }
                        aout = compterJour(enfant,aout);
                        enfantsAout.add(enfant);
                    } 
                }
                
                if(row.getCell(9) != null) {
                    if(row.getCell(9).getNumericCellValue() == 1) {
                        septembre.setMois("Septembre");
                        Cell cell = row.getCell(21);
                        if (cell != null) {
                            enfant.setPaye((int) cell.getNumericCellValue());
                        } else {
                            enfant.setPaye(0); 
                        }
                        septembre = compterJour(enfant,septembre);
                        enfantsSeptembre.add(enfant);
                    } 
                }
                
                if(row.getCell(10) != null) {
                    if(row.getCell(10).getNumericCellValue() == 1) {
                        octobre.setMois("Octobre");
                        Cell cell = row.getCell(22);
                        if (cell != null) {
                            enfant.setPaye((int) cell.getNumericCellValue());
                        } else {
                            enfant.setPaye(0); 
                        }
                        octobre = compterJour(enfant,octobre);
                        enfantsOctobre.add(enfant);
                    } 
                }
                
                if(row.getCell(11) != null) {
                    if(row.getCell(11).getNumericCellValue() == 1) {
                        novembre.setMois("Novembre");
                        Cell cell = row.getCell(23);
                        if (cell != null) {
                            enfant.setPaye((int) cell.getNumericCellValue());
                        } else {
                            enfant.setPaye(0); 
                        }
                        novembre = compterJour(enfant,novembre);
                        enfantsNovembre.add(enfant);
                    }
                }
                
                if(row.getCell(12) != null) {
                    if(row.getCell(12).getNumericCellValue() == 1) {
                        decembre.setMois("Decembre");
                        Cell cell = row.getCell(24);
                        if (cell != null) {
                            enfant.setPaye((int) cell.getNumericCellValue());
                        } else {
                            enfant.setPaye(0); 
                        }
                        decembre = compterJour(enfant,decembre);
                        enfantsDecembre.add(enfant);
                    } 
                }
            }
            listMois.add(janvier);
            listMois.add(fevrier); 
            listMois.add(mars);
            listMois.add(avril);
            listMois.add(mai);
            listMois.add(juin);
            listMois.add(juillet);
            listMois.add(aout);
            listMois.add(septembre);
            listMois.add(octobre);
            listMois.add(novembre);
            listMois.add(decembre);
 
            for(int i=0 ; i< listMois.size(); i++)
            { 
                Mois m = (Mois) listMois.get(i);
                System.out.println("mois : " + m);
                Vector vector = new Vector<>();
                
                vector.add(m.getMois());
                vector.add(m.getLundi_AM());
                vector.add(m.getLundi_PM());
                vector.add(m.getMardi_AM());
                vector.add(m.getMardi_PM());
                vector.add(m.getMercredi_AM());
                vector.add(m.getMercredi_PM());
                vector.add(m.getJeudi_AM());
                vector.add(m.getJeudi_PM());
                vector.add(m.getVendredi_AM());
                vector.add(m.getVendredi_PM());
                modelMois.addRow(vector);  
            } 
            //Fermeture de l'objet Workbook
            workbook.close();
            //Fermeture de l'objet FileInputStream
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public List<Enfant> readFile(String path) throws FileNotFoundException {
        
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = wb.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
            rows.next();
            while (rows.hasNext()) {
                Row row = rows.next();
                Enfant e = new Enfant();
                double d = row.getCell(0).getNumericCellValue();
                e.setNum( Double.toString(d));
                e.setNum_contrat(row.getCell(1).getStringCellValue());
                e.setNom(row.getCell(2).getStringCellValue());
                e.setPrenom(row.getCell(3).getStringCellValue());
                //e.setDate_naissance(row.getCell(4).getStringCellValue());
                /*e.setDate_entree(row.getCell(5).getStringCellValue());
                e.setDate_sortie(row.getCell(6).getStringCellValue());*/
                e.setLundi_am((int)(row.getCell(7).getNumericCellValue()));
                e.setLundi_pm((int)(row.getCell(8).getNumericCellValue()));
                e.setMardi_am((int)(row.getCell(9).getNumericCellValue()));
                e.setMardi_pm((int)(row.getCell(10).getNumericCellValue()));
                e.setMercredi_am((int)(row.getCell(11).getNumericCellValue()));
                e.setMercredi_pm((int)(row.getCell(12).getNumericCellValue()));
                e.setJeudi_am((int)(row.getCell(13).getNumericCellValue()));
                e.setJeudi_pm((int)(row.getCell(14).getNumericCellValue()));
                e.setVendredi_am((int)(row.getCell(15).getNumericCellValue()));
                e.setVendredi_pm((int)(row.getCell(16).getNumericCellValue()));
                list.add(e);
            }
            
        } catch (IOException e) {
            e.printStackTrace();

        }
        return list;
    }
    
    public static Enfant rechercheParId(List<Enfant> enfants, String id) {
        Enfant findEnfant = new Enfant();
        Optional<Enfant> e = enfants.stream()
            .filter(enfant -> enfant.getNum().equals(id))
            .findFirst();
            
            if(e.isPresent()) {
                findEnfant = e.get();
            }
        return findEnfant;
}
    
    public void ecrireListeExcel(List<Enfant> valeurs, String nomFichier) throws IOException {
        // Créer un nouveau fichier Excel
        XSSFWorkbook classeur = new XSSFWorkbook();

        // Créer une feuille dans le fichier Excel
        XSSFSheet feuille = classeur.createSheet("Enfants");

        // Créer une ligne pour les en-têtes de colonnes
        XSSFRow entetes = feuille.createRow(0);
        entetes.createCell(0).setCellValue("Num");
        entetes.createCell(1).setCellValue("Num_Contrat");
        entetes.createCell(2).setCellValue("Nom");
        entetes.createCell(3).setCellValue("Prenom");
        entetes.createCell(4).setCellValue("Date_Naissance");
        entetes.createCell(5).setCellValue("Date_Entree");
        entetes.createCell(6).setCellValue("Date_Sortie");
        entetes.createCell(7).setCellValue("Lundi_AM");
        entetes.createCell(8).setCellValue("Lundi_PM");
        entetes.createCell(9).setCellValue("Mardi_AM");
        entetes.createCell(10).setCellValue("Mardi_PM");
        entetes.createCell(11).setCellValue("Mercredi_AM");
        entetes.createCell(12).setCellValue("Mercredi_PM");
        entetes.createCell(13).setCellValue("Jeudi_AM");
        entetes.createCell(14).setCellValue("Jeudi_PM");
        entetes.createCell(15).setCellValue("Vendredi_AM");
        entetes.createCell(16).setCellValue("Vendredi_PM");

        // Écrire les informations de chaque enfant dans une ligne différente
        for (int i = 0; i < valeurs.size(); i++) {
            Enfant enfant = valeurs.get(i);
            XSSFRow ligne = feuille.createRow(i + 1);
            ligne.createCell(0).setCellValue(enfant.getNum());
            ligne.createCell(1).setCellValue(enfant.getNum_contrat());
            ligne.createCell(2).setCellValue(enfant.getNom());
            ligne.createCell(3).setCellValue(enfant.getPrenom());
            ligne.createCell(4).setCellValue(enfant.getDate_naissance());
            ligne.createCell(5).setCellValue(enfant.getDate_entree());
            ligne.createCell(6).setCellValue(enfant.getDate_sortie());
            ligne.createCell(7).setCellValue(enfant.getLundi_am());
            ligne.createCell(8).setCellValue(enfant.getLundi_pm());
            ligne.createCell(9).setCellValue(enfant.getMardi_am());
            ligne.createCell(10).setCellValue(enfant.getMardi_pm());
            ligne.createCell(11).setCellValue(enfant.getMercredi_am());
            ligne.createCell(12).setCellValue(enfant.getMercredi_pm());
            ligne.createCell(13).setCellValue(enfant.getJeudi_am());
            ligne.createCell(14).setCellValue(enfant.getJeudi_pm());
            ligne.createCell(15).setCellValue(enfant.getVendredi_am());
            ligne.createCell(16).setCellValue(enfant.getVendredi_pm());
        }

        // Enregistrer le fichier Excel
        FileOutputStream fichier = new FileOutputStream(nomFichier);
        classeur.write(fichier);
        fichier.close();
}
    
  
    public void search(String s) {
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter <>(model);
        jTable1.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter("(?i)" + s));
        
    }
 
    public Mois compterJour(Enfant enfant,Mois mois) {
         if(enfant.getLundi_am() == 1) {
             int cpt = mois.getLundi_AM();
             cpt++;
             mois.setLundi_AM(cpt);
         }
         
         if(enfant.getLundi_pm() == 1) {
             int cpt = mois.getLundi_PM();
             cpt++;
             mois.setLundi_PM(cpt);
         }
         
         if(enfant.getMardi_am() == 1) {
             int cpt = mois.getMardi_AM();
             cpt++;
             mois.setMardi_AM(cpt);
         }
         
          if(enfant.getMardi_pm() == 1) {
             int cpt = mois.getMardi_PM();
             cpt++;
             mois.setMardi_PM(cpt);
         }
          
         if(enfant.getMercredi_am() == 1) {
             int cpt = mois.getMercredi_AM();
             cpt++;
             mois.setMercredi_AM(cpt);
         }
         
          if(enfant.getMercredi_pm() == 1) {
             int cpt = mois.getMercredi_PM();
             cpt++;
             mois.setMercredi_PM(cpt);
         }
          
         if(enfant.getJeudi_am() == 1) {
             int cpt = mois.getJeudi_AM();
             cpt++;
             mois.setJeudi_AM(cpt);
         }
         
         if(enfant.getJeudi_pm() == 1) {
             int cpt = mois.getJeudi_PM();
             cpt++;
             mois.setJeudi_PM(cpt);
         }
         
         if(enfant.getVendredi_am() == 1) {
             int cpt = mois.getVendredi_AM();
             cpt++;
             mois.setVendredi_AM(cpt);
         }
         
         if(enfant.getVendredi_pm() == 1) {
             int cpt = mois.getVendredi_PM();
             cpt++;
             mois.setVendredi_PM(cpt);
         }
         
         return mois;
    }
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainMenu().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
