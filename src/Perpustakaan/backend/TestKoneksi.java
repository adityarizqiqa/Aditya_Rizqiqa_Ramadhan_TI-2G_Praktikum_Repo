package Perpustakaan.backend;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestKoneksi {

    public static void main(String[] args) {
        System.out.println("--- TES 1: Menjalankan INSERT ---");
        
        String namaTes = "Kategori Tes " + System.currentTimeMillis();
        String ketTes = "Deskripsi untuk tes.";
        
        String sqlInsert = "INSERT INTO kategori (nama, keterangan) VALUES ('" 
                           + namaTes + "', '" + ketTes + "')";
        
        boolean insertSukses = DBHelper.executeQuery(sqlInsert);
        
        if (insertSukses) {
            System.out.println("SUKSES: Data tes berhasil dimasukkan ke tabel 'kategori'.");
        } else {
            System.out.println("GAGAL: Tidak bisa memasukkan data.");
            return; 
        }
        System.out.println("\n--- TES 2: Menjalankan SELECT ---");
        System.out.println("Membaca semua data dari tabel 'kategori':");
        
        String sqlSelect = "SELECT * FROM kategori";

        ResultSet rs = DBHelper.selectQuery(sqlSelect);
        
        int jumlahBaris = 0;
        try {
            // Loop selama masih ada data di ResultSet
            while (rs.next()) {
                // Ambil data dari setiap kolom
                int id = rs.getInt("idkategori");
                String nama = rs.getString("nama");
                String keterangan = rs.getString("keterangan");
                
                // Cetak ke konsol
                System.out.println("  > ID: " + id + ", Nama: " + nama + ", Ket: " + keterangan);
                jumlahBaris++;
            }
            
            if (jumlahBaris > 0) {
                System.out.println("SUKSES: Berhasil membaca " + jumlahBaris + " baris data.");
            } else {
                System.out.println("PERINGATAN: Tidak ada data yang dibaca (tabel mungkin kosong?).");
            }

        } catch (SQLException e) {
            System.out.println("GAGAL: Error saat membaca data dari ResultSet.");
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.getStatement().getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
