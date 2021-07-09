package id.example.butikmodisteshofi;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;


import com.google.android.material.navigation.NavigationView;


public class Beranda extends AppCompatActivity {
    DrawerLayout drawerLayout;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

    }

    public void barang(View view) {
        Intent intent = new Intent(Beranda.this, Barang.class);

        Beranda.this.startActivity(intent);
    }

    public void transaksipembelian(View view) {
        Intent intent = new Intent(Beranda.this, TransaksiPembelian.class);

        Beranda.this.startActivity(intent);
    }

    public void riwayatpembelian(View view) {
        Intent intent = new Intent(Beranda.this, RiwayatPembelian.class);

        Beranda.this.startActivity(intent);

    }
}