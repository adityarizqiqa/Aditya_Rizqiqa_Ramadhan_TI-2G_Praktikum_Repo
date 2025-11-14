package Perpustakaan.frontend;

import Perpustakaan.backend.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.*;
import java.awt.event.*;

// Pastikan class MENG-EXTENDS JFrame
public class FrmBuku extends javax.swing.JFrame {

    // 1. Deklarasi semua komponen GUI secara manual
    private JTextField txtIdBuku;
    private JComboBox cmbKategori;
    private JTextField txtJudul;
    private JTextField txtPenerbit;
    private JTextField txtPenulis;
    private JButton btnSimpan;
    private JButton btnTambahBaru;
    private JButton btnHapus;
    private JTextField txtCari;
    private JButton btnCari;
    private JTable tblBuku;
    private JScrollPane jScrollPane1;
    
    // Label untuk field
    private JLabel labelIdBuku;
    private JLabel labelKategori;
    private JLabel labelJudul;
    private JLabel labelPenerbit;
    private JLabel labelPenulis;

    public FrmBuku() {
        
        // --- Inisialisasi Komponen ---
        txtIdBuku = new JTextField();
        cmbKategori = new JComboBox();
        txtJudul = new JTextField();
        txtPenerbit = new JTextField();
        txtPenulis = new JTextField();
        btnSimpan = new JButton("Simpan");
        btnTambahBaru = new JButton("Tambah Baru");
        btnHapus = new JButton("Hapus");
        txtCari = new JTextField();
        btnCari = new JButton("Cari");
        tblBuku = new JTable();
        jScrollPane1 = new JScrollPane();
        
        labelIdBuku = new JLabel("ID Buku");
        labelKategori = new JLabel("Kategori");
        labelJudul = new JLabel("Judul");
        labelPenerbit = new JLabel("Penerbit");
        labelPenulis = new JLabel("Penulis");

        // --- Atur Properti Komponen ---
        txtIdBuku.setText("0");
        txtIdBuku.setEnabled(false);

        tblBuku.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Kategori", "Judul", "Penulis", "Penerbit"}
        ));
        jScrollPane1.setViewportView(tblBuku); // Masukkan tabel ke ScrollPane

        getContentPane().setLayout(null);
        
        int labelWidth = 100;
        int fieldWidth = 250;
        
        labelIdBuku.setBounds(15, 15, labelWidth, 20);
        txtIdBuku.setBounds(120, 15, 100, 20);
        
        labelKategori.setBounds(15, 40, labelWidth, 20);
        cmbKategori.setBounds(120, 40, fieldWidth, 20);
        
        labelJudul.setBounds(15, 65, labelWidth, 20);
        txtJudul.setBounds(120, 65, fieldWidth, 20);
        
        labelPenerbit.setBounds(15, 90, labelWidth, 20);
        txtPenerbit.setBounds(120, 90, fieldWidth, 20);
        
        labelPenulis.setBounds(15, 115, labelWidth, 20);
        txtPenulis.setBounds(120, 115, fieldWidth, 20);
        
        btnSimpan.setBounds(15, 150, 85, 25);
        btnTambahBaru.setBounds(105, 150, 115, 25);
        btnHapus.setBounds(225, 150, 75, 25);
        
        txtCari.setBounds(320, 150, 150, 25);
        btnCari.setBounds(475, 150, 75, 25);
        
        jScrollPane1.setBounds(15, 185, 535, 200); // ScrollPane berisi tabel

        // --- Tambahkan Komponen ke Frame ---
        getContentPane().add(labelIdBuku);
        getContentPane().add(txtIdBuku);
        getContentPane().add(labelKategori);
        getContentPane().add(cmbKategori);
        getContentPane().add(labelJudul);
        getContentPane().add(txtJudul);
        getContentPane().add(labelPenerbit);
        getContentPane().add(txtPenerbit);
        getContentPane().add(labelPenulis);
        getContentPane().add(txtPenulis);
        getContentPane().add(btnSimpan);
        getContentPane().add(btnTambahBaru);
        getContentPane().add(btnHapus);
        getContentPane().add(txtCari);
        getContentPane().add(btnCari);
        getContentPane().add(jScrollPane1);
        
        // --- Tambahkan Event Listener (Sangat Penting!) ---
        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        
        btnTambahBaru.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnTambahBaruActionPerformed(evt);
            }
        });
        
        btnCari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        
        tblBuku.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tblBukuMouseClicked(evt);
            }
        });
        
        // --- Atur properti JFrame ---
        setTitle("Form Data Buku");
        setSize(570, 430); // Sesuaikan ukuran window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 3. Panggil method dari tutorial di akhir konstruktor
        tampilkanData();
        tampilkanCmbKategori();
        kosongkanForm();
    }
    public void kosongkanForm() {
        txtIdBuku.setText("0");
        cmbKategori.setSelectedIndex(0);
        txtJudul.setText("");
        txtPenulis.setText("");
        txtPenerbit.setText("");
    }
    
    // (Langkah 4: tampilkanData)
    public void tampilkanData() {
        String[] kolom = {"ID", "Kategori", "Judul", "Penulis", "Penerbit"};
        ArrayList<Buku> list = new Buku().getAll(); // Asumsi class Buku sudah ada
        Object rowData[] = new Object[5];
        
        tblBuku.setModel(new DefaultTableModel(new Object[][] {}, kolom));
        
        for(int i = 0; i < list.size(); i++) {
            rowData[0] = list.get(i).getIdbuku();
            rowData[1] = list.get(i).getKategori().getNama();
            rowData[2] = list.get(i).getJudul();
            rowData[3] = list.get(i).getPenulis();
            rowData[4] = list.get(i).getPenerbit();
            
            ((DefaultTableModel)tblBuku.getModel()).addRow(rowData);
        }
    }
    
    // (Langkah 5: cari)
    public void cari(String keyword) {
        String[] kolom = {"ID", "Kategori", "Judul", "Penulis", "Penerbit"};
        ArrayList<Buku> list = new Buku().search(keyword); // Asumsi class Buku sudah ada
        Object rowData[] = new Object[5];
        
        tblBuku.setModel(new DefaultTableModel(new Object[][] {}, kolom));
        
        for(Buku buku : list) {
            rowData[0] = buku.getIdbuku();
            rowData[1] = buku.getKategori().getNama();
            rowData[2] = buku.getJudul();
            rowData[3] = buku.getPenulis();
            rowData[4] = buku.getPenerbit();
            
            ((DefaultTableModel)tblBuku.getModel()).addRow(rowData);
        }
    }
    
    // (Langkah 6: tampilkanCmbKategori)
    public void tampilkanCmbKategori() {
        cmbKategori.setModel(new DefaultComboBoxModel(new Kategori().getAll().toArray()));
    }
    
    // (Langkah 8: btnSimpanActionPerformed)
    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {                                          
        Buku buku = new Buku();
        buku.setIdbuku(Integer.parseInt(txtIdBuku.getText()));
        buku.setKategori((Kategori)cmbKategori.getSelectedItem());
        buku.setJudul(txtJudul.getText());
        buku.setPenulis(txtPenulis.getText());
        buku.setPenerbit(txtPenerbit.getText());
        buku.save();
        
        txtIdBuku.setText(Integer.toString(buku.getIdbuku()));
        tampilkanData();
    }                                         

    // (Langkah 9: btnHapusActionPerformed)
    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {                                         
        DefaultTableModel model = (DefaultTableModel)tblBuku.getModel();
        int row = tblBuku.getSelectedRow();
        
        Buku buku = new Buku().getById(Integer.parseInt(model.getValueAt(row, 0).toString()));
        buku.delete();
        kosongkanForm();
        tampilkanData();
    }                                        

    // (Langkah 10: btnTambahBaruActionPerformed)
    private void btnTambahBaruActionPerformed(java.awt.event.ActionEvent evt) {                                              
        kosongkanForm();
    }                                             

    // (Langkah 11: btnCariActionPerformed)
    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {                                        
        cari(txtCari.getText());
    }                                       

    // (Langkah 12: tblBukuMouseClicked)
    private void tblBukuMouseClicked(java.awt.event.MouseEvent evt) {                                     
        DefaultTableModel model = (DefaultTableModel)tblBuku.getModel();
        int row = tblBuku.getSelectedRow();
        Buku buku = new Buku().getById(Integer.parseInt(model.getValueAt(row, 0).toString()));
        
        txtIdBuku.setText(String.valueOf(buku.getIdbuku()));
        cmbKategori.getModel().setSelectedItem(buku.getKategori());
        txtJudul.setText(buku.getJudul());
        txtPenerbit.setText(buku.getPenerbit());
        txtPenulis.setText(buku.getPenulis());
    }
    
    
    // Main method untuk menjalankan FrmBuku
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBuku().setVisible(true);
            }
        });
    }
}