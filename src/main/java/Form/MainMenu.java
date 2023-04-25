/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Entities.Enfant;
import Utile.BeanBDAccess;
import com.sun.media.sound.InvalidFormatException;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
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

    /**
     * Creates new form MainMenu
     */
     private BeanBDAccess bd;
     private ResultSet resultat;
     private DefaultTableModel model;
     String file_path = "";
     
    public MainMenu() throws IOException {
        initComponents();
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            // Windows
            file_path = "D:\\enfants.xlsx";
        } else if (os.contains("Mac")) {
            // Mac
        }
        model = (DefaultTableModel)jTable1.getModel();
        bd = new BeanBDAccess();
        bd.connectBD("jdbc:mysql://localhost:3306/bd_creche?serverTimezone=UTC","root","gaetan");
        List<Enfant> list = new ArrayList<Enfant>();
         
        resultat = bd.SelectBD("select * from enfant;");
        try {
            while(resultat.next())
            {
                Enfant enfant = new Enfant();
                enfant.setNum(resultat.getInt("num"));
                enfant.setNum_contrat(resultat.getString("num_contrat"));
                enfant.setNom(resultat.getString("nom"));
                enfant.setPrenom(resultat.getString("prenom"));
                enfant.setDate_naissance(resultat.getDate("date_naissance"));
                enfant.setDate_entree(resultat.getDate("date_entree"));
                enfant.setDate_sortie(resultat.getDate("date_sortie"));
                enfant.setLundi_am(resultat.getInt("lundi_am"));
                enfant.setLundi_pm(resultat.getInt("lundi_pm"));
                enfant.setMardi_am(resultat.getInt("mardi_am"));
                enfant.setMardi_pm(resultat.getInt("mardi_pm"));
                enfant.setMercredi_am(resultat.getInt("mercredi_am"));
                enfant.setMercredi_pm(resultat.getInt("mercredi_pm"));
                enfant.setJeudi_am(resultat.getInt("jeudi_am"));
                enfant.setJeudi_pm(resultat.getInt("jeudi_pm"));
                enfant.setVendredi_am(resultat.getInt("vendredi_am"));
                enfant.setVendredi_am(resultat.getInt("vendredi_pm"));
                enfant.setMontant_jour(resultat.getInt("montant_jour"));
                enfant.setMontant_forfait(resultat.getInt("montant_forfait"));
                enfant.setCommentaire(resultat.getString("commentaire"));
                enfant.setMere(resultat.getString("mere"));
                enfant.setEmail_mere(resultat.getString("email_mere"));
                enfant.setNum_mere(resultat.getString("num_mere"));
                enfant.setPere(resultat.getString("pere"));
                enfant.setEmail_pere(resultat.getString("email_pere"));
                enfant.setNum_pere(resultat.getString("num_pere"));
                list.add(enfant);
            }
            
            ecrireListeExcel(list,file_path);
            
            for(int i=0 ; i< list.size(); i++)
            { 
                Enfant e = (Enfant) list.get(i);
                Vector vector = new Vector<>();
                for(int j=0;j<list.size();j++)
                {
                    vector.add(e.getNum());
                    vector.add(e.getNum_contrat());
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

                }
                model.addRow(vector);   
            } 
        } catch (SQLException ex) {
            System.out.println("Exception dans findAll");
        }
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println("enfant " + list.get(i));
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1700, 900));

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel1.setText("Recherche : ");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jButton1.setText("Détails\n");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jButton2.setText("Détails\n");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1650, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(1464, Short.MAX_VALUE)
                    .addComponent(jButton2)
                    .addGap(146, 146, 146)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(10, 10, 10)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(jButton2)
                    .addContainerGap(791, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Enfants", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Entrez l'année : ");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lundi-AM", "Lundi-PM", "Mardi-AM", "Mardi-PM", "Mercredi-AM", "Mercredi-PM", "Jeudi-AM", "Jeudi-PM", "Vendredi-AM", "Vendredi-PM"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton3.setText("Recherche");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1522, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
           // TODO add your handling code here:
        search(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            int selectedColumn = jTable1.getSelectedColumn();
            
             Object selectedValue = jTable1.getValueAt(selectedRow, selectedColumn);
             
    }
        //EnfantsDétails e = new EnfantsDétails(jTable1.get);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            parseFileMonth(file_path+"mois-"+jTextField2.getText()+".xlsx");

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Fichier introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException ex) {
             Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
         }

    }//GEN-LAST:event_jButton3ActionPerformed
    
    public void parseFileMonth(String filePath) throws FileNotFoundException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        try {
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
                System.out.println("row" + row.getCell(0)); 
                if(row.getCell(2).getNumericCellValue() == 1) {
                    System.out.println("janvier ok");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en janvier");
                }
                
                if(row.getCell(3).getNumericCellValue() == 1) {
                    System.out.println("fev ok");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en fev");
                }
                
                if(row.getCell(4).getNumericCellValue() == 1) {
                    System.out.println("mars ok");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en mars");
                }
                
                if(row.getCell(5).getNumericCellValue() == 1) {
                    System.out.println("janvier avril");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en avril");
                }
                
                if(row.getCell(6).getNumericCellValue() == 1) {
                    System.out.println("mai ok");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en mai");
                }
                
                if(row.getCell(7).getNumericCellValue() == 1) {
                    System.out.println("juin ok");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en juin");
                }
                
                if(row.getCell(8).getNumericCellValue() == 1) {
                    System.out.println("juillet ok");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en juillet");
                }
                
                if(row.getCell(9).getNumericCellValue() == 1) {
                    System.out.println("aout ok");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en aout");
                }
                
                if(row.getCell(10).getNumericCellValue() == 1) {
                    System.out.println("sept ok");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en sept");
                }
                
                if(row.getCell(11).getNumericCellValue() == 1) {
                    System.out.println("oct ok");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en oct");
                }
                
                if(row.getCell(12).getNumericCellValue() == 1) {
                    System.out.println("nov ok");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en nov");
                }
                
                if(row.getCell(13).getNumericCellValue() == 1) {
                    System.out.println("dec ok");
                    //recherche dans autre fichier
                } else {
                    System.out.println("viens pas en dec");
                }
                
            }
            //Fermeture de l'objet Workbook
            workbook.close();

            //Fermeture de l'objet FileInputStream
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        trs.setRowFilter(RowFilter.regexFilter(s));
        
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
