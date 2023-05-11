/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Entities.Enfant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author gd024483
 */
public class Payement extends javax.swing.JFrame {

    /**
     * Creates new form Payement
     */
    DefaultTableModel model;
    public Payement() {
        initComponents();
    }
    public Payement(String mois, List<Enfant> enfants) {
        initComponents();
        model = (DefaultTableModel)jTable1.getModel();
        jLabel2.setText(mois);
        
        for(int i=0 ; i< enfants.size(); i++)
        { 
                Enfant e = (Enfant) enfants.get(i);
                Vector vector = new Vector<>();
                for(int j=0;j<enfants.size();j++)
                {
                    vector.add(e.getNum());
                    //vector.add(e.getNum_contrat());
                    vector.add(e.getNom());
                    vector.add(e.getPrenom());
                    vector.add(e.getPaye());
                }
                model.addRow(vector);   
        }
        model.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            Object newValue = model.getValueAt(row, column);
            
            Object[] rowData = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = model.getValueAt(row, i);
            }

            XSSFRow ligne = rechercheLigne("C:\\Temp\\mois-2023.xlsx" , Double.parseDouble(rowData[0].toString()));   
            try {
                updateLigne(ligne, Double.parseDouble(newValue.toString()));
            } catch (InterruptedException ex) {
                Logger.getLogger(Payement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
         
    }
    
    public void updateLigne(XSSFRow ligne,Double valeur) throws InterruptedException {
        int indice = 0;
        switch(jLabel2.getText()) {
            case "Janvier" :
                indice = 13;
            break;
            
            case "Février" :
               indice = 14;
            break;
            
            case "Mars" :
               indice = 15;
            break;
            
            case "Avril" :
              indice = 16;
            break;
            
            case "Mai" :
                indice = 17;
            break;
            
            case "Juin" :
               indice = 18;
            break;
            
            case "Juillet" :
                indice = 19;
            break;
            
            case "Aout" :
               indice = 20;
            break;
            
            case "Septembre" :
                indice = 21;
            break;
            
            case "Octobre" :
               indice = 22;
            break;
            
            case "Novembre" :
               indice = 23;
            break;
            
            case "Décembre" :
              indice = 24;
            break;
        }
        
        try {
            
            XSSFSheet dataSheet = null; 
            FileOutputStream output = new FileOutputStream("C:\\Temp\\mois-2023.xlsx");
            dataSheet.getRow(ligne.getRowNum()).getCell(indice).setCellValue(valeur);
            dataSheet.getWorkbook().write(output);
            output.close();
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
            
        } catch(IOException e){
       
        }
        
    }
    
    public XSSFRow rechercheLigne(String path,Double idRecherche) {
        FileInputStream fichier;
        XSSFRow ligneRecherchee = null;
         try {
                fichier = new FileInputStream(new File(path));
                XSSFWorkbook classeur = new XSSFWorkbook(fichier);
                XSSFSheet feuille = classeur.getSheetAt(0); 
                for (int i = 1; i < feuille.getPhysicalNumberOfRows(); i++) {
                    XSSFRow ligne = feuille.getRow(i);
                    if (ligne.getCell(0).getNumericCellValue() == idRecherche) {
                        ligneRecherchee = ligne;
                        break;
                    }
                }
            fichier.close();
            classeur.close();
         } catch (FileNotFoundException ex) {
             Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
         }
         System.out.println("Ligne recherché : " + ligneRecherchee.getCell(0).getNumericCellValue());
         return ligneRecherchee;
        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Paiement du mois de :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("jLabel2");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num", "Nom", "Prénom", "Payé"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Payement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
