package Perpustakaan.frontend;

import Perpustakaan.backend.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.*;

public class FrmKategori extends javax.swing.JFrame {

    private JTextField txtIdKategori;
    private JTextField txtNama;
    private JTextField txtKeterangan;
    private JButton btnSimpan;
    private JButton btnHapus;
    private JButton btnTambahBaru;
    private JTextField txtCari;
    private JButton btnCari;
    private JTable tblKategori;
    private JScrollPane jScrollPane1;

    private JLabel labelId;
    private JLabel labelNama;
    private JLabel labelKeterangan;

    public FrmKategori() {
        txtIdKategori = new JTextField();
        txtNama = new JTextField();
        txtKeterangan = new JTextField();
        btnSimpan = new JButton("Simpan");
        btnHapus = new JButton("Hapus");
        btnTambahBaru = new JButton("Tambah Baru");
        txtCari = new JTextField();
        btnCari = new JButton("Cari");
        tblKategori = new JTable();
        jScrollPane1 = new JScrollPane();

        labelId = new JLabel("ID Kategori");
        labelNama = new JLabel("Nama Kategori");
        labelKeterangan = new JLabel("Keterangan");

        txtIdKategori.setText("0");
        txtIdKategori.setEnabled(false);

        tblKategori.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nama", "Keterangan"}
        ));
        jScrollPane1.setViewportView(tblKategori);

        getContentPane().setLayout(null);

        labelId.setBounds(15, 15, 100, 20);
        txtIdKategori.setBounds(120, 15, 150, 20);

        labelNama.setBounds(15, 45, 100, 20);
        txtNama.setBounds(120, 45, 200, 20);

        labelKeterangan.setBounds(15, 75, 100, 20);
        txtKeterangan.setBounds(120, 75, 300, 20);

        btnSimpan.setBounds(15, 110, 80, 30);
        btnTambahBaru.setBounds(100, 110, 110, 30);
        btnHapus.setBounds(215, 110, 80, 30);

        txtCari.setBounds(350, 115, 150, 25);
        btnCari.setBounds(510, 115, 60, 25);

        jScrollPane1.setBounds(15, 155, 555, 260);

        getContentPane().add(labelId);
        getContentPane().add(txtIdKategori);
        getContentPane().add(labelNama);
        getContentPane().add(txtNama);
        getContentPane().add(labelKeterangan);
        getContentPane().add(txtKeterangan);
        getContentPane().add(btnSimpan);
        getContentPane().add(btnTambahBaru);
        getContentPane().add(btnHapus);
        getContentPane().add(txtCari);
        getContentPane().add(btnCari);
        getContentPane().add(jScrollPane1);

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

        tblKategori.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tblKategoriMouseClicked(evt);
            }
        });

        setTitle("Form Kategori");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tampilkanData();
        kosongkanForm();
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        Kategori kat = new Kategori();
        kat.setIdkategori(Integer.parseInt(txtIdKategori.getText()));
        kat.setNama(txtNama.getText());
        kat.setKeterangan(txtKeterangan.getText());
        kat.save();

        txtIdKategori.setText(Integer.toString(kat.getIdkategori()));
        tampilkanData();
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tblKategori.getModel();
        int row = tblKategori.getSelectedRow();

        Kategori kat = new Kategori().getById(Integer.parseInt(model.getValueAt(row, 0).toString()));
        kat.delete();
        kosongkanForm();
        tampilkanData();
    }

    private void btnTambahBaruActionPerformed(java.awt.event.ActionEvent evt) {
        kosongkanForm();
    }

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {
        cari(txtCari.getText());
    }

    private void tblKategoriMouseClicked(java.awt.event.MouseEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tblKategori.getModel();
        int row = tblKategori.getSelectedRow();

        txtIdKategori.setText(model.getValueAt(row, 0).toString());
        txtNama.setText(model.getValueAt(row, 1).toString());
        txtKeterangan.setText(model.getValueAt(row, 2).toString());
    }

    public void kosongkanForm() {
        txtIdKategori.setText("0");
        txtNama.setText("");
        txtKeterangan.setText("");
    }

    public void tampilkanData() {
        String[] kolom = {"ID", "Nama", "Keterangan"};
        ArrayList<Kategori> list = new Kategori().getAll();
        Object rowData[] = new Object[3];

        tblKategori.setModel(new DefaultTableModel(new Object[][] {}, kolom));

        for (Kategori kat : list) {
            rowData[0] = kat.getIdkategori();
            rowData[1] = kat.getNama();
            rowData[2] = kat.getKeterangan();

            ((DefaultTableModel) tblKategori.getModel()).addRow(rowData);
        }
    }

    public void cari(String keyword) {
        String[] kolom = {"ID", "Nama", "Keterangan"};
        ArrayList<Kategori> list = new Kategori().search(keyword);
        Object rowData[] = new Object[3];

        tblKategori.setModel(new DefaultTableModel(new Object[][] {}, kolom));

        for (Kategori kat : list) {
            rowData[0] = kat.getIdkategori();
            rowData[1] = kat.getNama();
            rowData[2] = kat.getKeterangan();

            ((DefaultTableModel) tblKategori.getModel()).addRow(rowData);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmKategori().setVisible(true);
            }
        });
    }
}
