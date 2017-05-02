/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.apprekammedis.view;

import com.fahmi.apprekammedis.model.RekamMedis;
import com.fahmi.apprekammedis.model.RekamMedisTableModel;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author fahmikudo
 */
public class FormRekamMedis extends javax.swing.JFrame {

    RekamMedisTableModel tabelmodel = new RekamMedisTableModel();
    String pathFile = "db.txt";
    String pathFileTemp = "dbTemp.txt";
    String readFile = "";
    
    List<RekamMedis> listRekamMedis = new ArrayList<>();
    private final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabelmodel);
    
    
    
    public FormRekamMedis() {
        initComponents();
        tbRekamMedis.setRowSorter(sorter);        
        tampilData();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void tampilData() {
        try {
            listRekamMedis.clear();
            String readLine;
            File file = new File(pathFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader bufReader;
            try (FileReader reader = new FileReader(file)) {
                bufReader = new BufferedReader(reader);
                while ((readLine = bufReader.readLine()) != null) {
                    if (readLine.equals("")) {
                        continue;
                    }
                    String[] splitData = readLine.split(";");
                    RekamMedis rm = new RekamMedis();
                    rm.setNoRM(splitData[0]);
                    rm.setNama(splitData[1]);
                    rm.setAlamat(splitData[2]);
                    rm.setKelas(Integer.parseInt(splitData[3]));
                    rm.setRuangan(splitData[4]);
                    listRekamMedis.add(rm);
                }
                tabelmodel.setList(listRekamMedis);
                tbRekamMedis.setModel(tabelmodel);
            }
            bufReader.close();
        } catch (IOException ex) {
            Logger.getLogger(FormRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void DeleteByNoRekamMedis() throws IOException {
        String ID, record;
        File tempDB = new File(pathFileTemp);
        File db = new File(pathFile);
        BufferedWriter bw;
        try (BufferedReader br = new BufferedReader(new FileReader(db))) {
            bw = new BufferedWriter(new FileWriter(tempDB));
            ID = "RM-"+txtNoRm.getText();
            while ((record = br.readLine()) != null) {
                if (record.contains(ID)) {
                    continue;
                }
                bw.write(record);
                bw.flush();
                bw.newLine();
            }
        }
        bw.close();
        db.delete();
        tempDB.renameTo(db);
    }
    
    private void clear(){
        txtNoRm.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtKelas.setText("");
        txtRuangan.setText("");
        txtCari.setText("");
    }
    
    private void dialog(String text){
        JOptionPane.showMessageDialog(rootPane, text);
    }
    
    private void updatebyNoRekamMedis() throws IOException {
        String ID, record2;
        File db = new File(pathFile);
        File tempDB = new File(pathFileTemp);
        BufferedReader br2;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB))) {
            ID = "RM-"+txtNoRm.getText();
            br2 = new BufferedReader(new FileReader(db));
            while ((record2 = br2.readLine()) != null) {
                if (record2.contains(ID)) {
                    bw.write("RM-"+txtNoRm.getText()+";" + txtNama.getText() + ";" + txtAlamat.getText() + ";" + txtKelas.getText() + ";" + txtRuangan.getText());
                } else {
                    bw.write(record2);
                }
                bw.flush();
                bw.newLine();
            }
        }
        br2.close();
        db.delete();
        tempDB.renameTo(db);
    }
    
    private void saveOrUpdate(){
        if (btnSimpan.getText().equals("Simpan")) {
            String save = "\n" + "RM-" + txtNoRm.getText() + ";" + txtNama.getText() + ";" + txtAlamat.getText() + ";" + txtKelas.getText() + ";" + txtRuangan.getText();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile, true))) {
                bw.write(save);
                bw.flush();
                bw.newLine();
                bw.close();
                clear();
                tampilData();
                dialog("Data telah tersimpan");
            } catch (IOException e) {
            }
        } else {
            try {
                updatebyNoRekamMedis();
                clear();
                tampilData();
                btnHapus.setEnabled(false);
                btnSimpan.setText("Simpan");
                dialog("Data berhasil di ubah");
            } catch (IOException e) {
            }
        }
    }
    
    private void logicKelas(){
        switch (Integer.parseInt(txtKelas.getText())) {
            case 1:
                txtRuangan.setText("BUGENVILE");
                break;
            case 2:
                txtRuangan.setText("MAWAR");
                break;
            default:
                txtRuangan.setText("RAFLESIA");
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRekamMedis = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNoRm = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtKelas = new javax.swing.JTextField();
        btnShowKelas = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtRuangan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM REKAM MEDIS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(237, 237, 237))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tbRekamMedis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No. RM", "Nama Pasien", "Alamat", "Kelas", "Ruangan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbRekamMedis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRekamMedisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbRekamMedis);

        jPanel2.setBackground(new java.awt.Color(0, 153, 0));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setText("Pencarian :");

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimpan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh)
                    .addComponent(btnHapus)
                    .addComponent(btnSimpan))
                .addGap(14, 14, 14))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("No. Rekam Medis");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setText("Nama Pasien");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Alamat Pasien");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane2.setViewportView(txtAlamat);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setText("Kelas");

        txtKelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKelasKeyTyped(evt);
            }
        });

        btnShowKelas.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnShowKelas.setText("Show Kelas");
        btnShowKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowKelasActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setText("Ruangan");

        txtRuangan.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNoRm)
                            .addComponent(txtNama)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtKelas)
                                .addGap(18, 18, 18)
                                .addComponent(btnShowKelas))
                            .addComponent(txtRuangan))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKelas, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtNoRm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(btnShowKelas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRuangan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (txtNoRm.getText().isEmpty()) {
            dialog("No Rekam Medis mohon diisi");
            txtNoRm.requestFocus();
        }else if (txtNama.getText().isEmpty()) {
            dialog("Nama Pasien mohon diisi");
            txtNama.requestFocus();
        }else if (txtAlamat.getText().isEmpty()) {
            dialog("Alamat Pasien mohon diisi");
            txtAlamat.requestFocus();
        }else if (txtKelas.getText().isEmpty()) {
            dialog("Kelas Pasien mohon diisi");
            txtKelas.requestFocus();
        }else if (txtRuangan.getText().isEmpty()) {
            dialog("Ruangan Pasien mohon diisi");
            txtRuangan.requestFocus();
        }else{
            saveOrUpdate();
        }
        
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnShowKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowKelasActionPerformed
        // TODO add your handling code here:
        logicKelas();    
    }//GEN-LAST:event_btnShowKelasActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        try {
            DeleteByNoRekamMedis();
            clear();
            tampilData();
            btnHapus.setEnabled(false);
            btnSimpan.setText("Simpan");
            dialog("Data berhasil dihapus");
        } catch (IOException e) {
        }
        
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:
        String text = txtCari.getText();
        if (text.length() == 0) {
          sorter.setRowFilter(null);
        } else {
          sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
        
        
    }//GEN-LAST:event_txtCariKeyReleased

    private void tbRekamMedisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRekamMedisMouseClicked
        // TODO add your handling code here:
        int click = tbRekamMedis.rowAtPoint(evt.getPoint());
        txtNoRm.setText(tbRekamMedis.getModel().getValueAt(click, 0).toString().replace("RM-", ""));
        txtNama.setText((String) tbRekamMedis.getModel().getValueAt(click, 1));
        txtAlamat.setText((String) tbRekamMedis.getModel().getValueAt(click, 2));
        txtKelas.setText(tbRekamMedis.getModel().getValueAt(click, 3).toString());
        txtRuangan.setText((String) tbRekamMedis.getModel().getValueAt(click, 4));
        btnSimpan.setText("Update");
        btnHapus.setEnabled(true);
    }//GEN-LAST:event_tbRekamMedisMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        clear();
        btnHapus.setEnabled(false);
        btnSimpan.setText("Simpan");
        tampilData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtKelasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKelasKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)|| (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
        
    }//GEN-LAST:event_txtKelasKeyTyped

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
            java.util.logging.Logger.getLogger(FormRekamMedis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormRekamMedis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormRekamMedis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormRekamMedis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormRekamMedis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnShowKelas;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbRekamMedis;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKelas;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoRm;
    private javax.swing.JTextField txtRuangan;
    // End of variables declaration//GEN-END:variables
}
