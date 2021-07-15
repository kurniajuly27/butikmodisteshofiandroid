package id.example.butikmodisteshofi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class TransaksiPembelian extends AppCompatActivity {

    String namaBarang, ukuran, warna;
    EditText namaBarangEt, ukuranEt, warnaEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_pembelian);

        namaBarang = getIntent().getExtras().getString("nama_barang");
        ukuran = getIntent().getExtras().getString("ukuran");
        warna = getIntent().getExtras().getString("warna");

        namaBarangEt = findViewById(R.id.txt_nama_barang);
        ukuranEt = findViewById(R.id.txt_ukuran);
        warnaEt = findViewById(R.id.txt_warna);

        namaBarangEt.setText(namaBarang);
        ukuranEt.setText(ukuran);
        warnaEt.setText(warna);

    }
}