package id.example.butikmodisteshofi;

import androidx.appcompat.app.AppCompatActivity;

import id.example.butikmodisteshofi.Utility.PrefManager;
import id.example.butikmodisteshofi.api.ApiEndPoint;
import id.example.butikmodisteshofi.api.ApiRetrofit;
import id.example.butikmodisteshofi.model.DataItem;
import id.example.butikmodisteshofi.model.ResponseAllBarang;
import id.example.butikmodisteshofi.model.ResponseAllDetailPenjualan;
import id.example.butikmodisteshofi.model.ResponseAllPenjualan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransaksiPembelian extends AppCompatActivity {

    String namaBarang, ukuran, warna;
    int jumlah, keranjang;
    Button btnBeli;
    EditText namaBarangEt, ukuranEt, warnaEt, jumlahET;


    Date currentTime = Calendar.getInstance().getTime();
    SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("yyyy-MM-dd");
    String timestamp = simpleDateFormat.format(currentTime);

    int idPenjualan;
    int idBarang;
    int harga,stok;
    int total, updateStok;
    int idPelanggan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_pembelian);

        namaBarang = getIntent().getExtras().getString("nama_barang");
        ukuran = getIntent().getExtras().getString("ukuran");
        warna = getIntent().getExtras().getString("warna");

        idBarang = getIntent().getExtras().getInt("id");
        harga = getIntent().getExtras().getInt("harga");
        stok = getIntent().getExtras().getInt("stok");


        namaBarangEt = findViewById(R.id.txt_nama_barang);
        ukuranEt = findViewById(R.id.txt_ukuran);
        warnaEt = findViewById(R.id.txt_warna);
        jumlahET = findViewById(R.id.txt_jumlah_barang);


        namaBarangEt.setText(namaBarang);
        ukuranEt.setText(ukuran);
        warnaEt.setText(warna);
        PrefManager prefManager = new PrefManager(getApplicationContext());
        idPelanggan = prefManager.getInt("idpelanggan");
        btnBeli = (Button)findViewById(R.id.btn_beli);
        btnBeli = (Button)findViewById(R.id.btn_tambah);

        klik();
        klikKeranjang();
    }

    public void klikKeranjang(){
        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiEndPoint apiEndPoint = ApiRetrofit.getclient().create(ApiEndPoint.class);
                Call<ResponseAllPenjualan> pnj = apiEndPoint.getPenjualan();
                pnj.enqueue(new Callback<ResponseAllPenjualan>() {
                    @Override
                    public void onResponse(Call<ResponseAllPenjualan> call, Response<ResponseAllPenjualan> response) {
                        if (response.isSuccessful()) {
                            List<DataItem> penjualan = response.body().getData();
                            DataItem item = penjualan.get(penjualan.size() - 1);
                            keranjang = item.getKeranjang();
                            idPelanggan = item.getPelangganid();

                            String value = jumlahET.getText().toString();
                            jumlah = Integer.parseInt(value);




                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAllPenjualan> call, Throwable t) {

                    }
                });
                
                        Call<ResponseAllPenjualan> respon = apiEndPoint.getPenjualan();
                        respon.enqueue(new Callback<ResponseAllPenjualan>() {
                            @Override
                            public void onResponse(Call<ResponseAllPenjualan> call, Response<ResponseAllPenjualan> response) {
                                if (response.isSuccessful()) {
                                    List<DataItem> penjualan = response.body().getData();
                                    DataItem item = penjualan.get(penjualan.size() - 1);
                                    keranjang = item.getKeranjang();
                                    idPelanggan = item.getPelangganid();

                                    String value = jumlahET.getText().toString();
                                    jumlah = Integer.parseInt(value);

                                    if (response.body().getData().get(keranjang).getKeranjang() == 1){
                                        Call<ResponseAllDetailPenjualan> responseAllDetailPenjualanCall = apiEndPoint.
                                                setDetailPenjualan(idPenjualan,idPelanggan,idBarang,jumlah,total);
                                        responseAllDetailPenjualanCall.enqueue(new Callback<ResponseAllDetailPenjualan>() {
                                            @Override
                                            public void onResponse(Call<ResponseAllDetailPenjualan> call, Response<ResponseAllDetailPenjualan> response) {
                                                if (response.isSuccessful()){

                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseAllDetailPenjualan> call, Throwable t) {

                                            }
                                        });

                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseAllPenjualan> call, Throwable t) {

                            }
                        });
                        Intent intent = new Intent(getApplicationContext(), Barang.class);
                        startActivity(intent);

            }
        });

    }

    public void klik(){
        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiEndPoint apiEndPoint = ApiRetrofit.getclient().create(ApiEndPoint.class);
                Call<ResponseAllPenjualan> respon = apiEndPoint.setPenjualan(timestamp, 0);
                respon.enqueue(new Callback<ResponseAllPenjualan>() {
                    @Override
                    public void onResponse(Call<ResponseAllPenjualan> call, Response<ResponseAllPenjualan> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseAllPenjualan> call, Throwable t) {

                        Call<ResponseAllPenjualan> responseAllPenjualan = apiEndPoint.getPenjualan();

                        responseAllPenjualan.enqueue(new Callback<ResponseAllPenjualan>() {
                            @Override
                            public void onResponse(Call<ResponseAllPenjualan> call, Response<ResponseAllPenjualan> response) {
                                if(response.isSuccessful()){
                                    List<DataItem> penjualan = response.body().getData();
                                    DataItem item = penjualan.get(penjualan.size() - 1);
                                    idPenjualan = item.getId();

                                    String value = jumlahET.getText().toString();
                                    jumlah = Integer.parseInt(value);

//
//                                    Call<ResponseAllBarang> responseIdBarang = apiEndPoint.getBarangId(idBarang);
//                                    responseIdBarang.enqueue(new Callback<ResponseAllBarang>() {
//                                        @Override
//                                        public void onResponse(Call<ResponseAllBarang> call, Response<ResponseAllBarang> response) {
//                                            harga = response.body().getData().get(0).getHarga();
//                                        }
//                                        @Override
//                                        public void onFailure(Call<ResponseAllBarang> call, Throwable t) {
//
//                                        }
//                                    });


                                    total = harga * jumlah;
                                    updateStok = stok - jumlah;
                                    DataItem ditem = new DataItem();
                                    ditem.setStokBarang(updateStok);
                                    ditem.setHarga(harga);


                                    Call<ResponseAllBarang> responseAllBarang = apiEndPoint.updateHargaBarang(idBarang ,ditem);
                                    responseAllBarang.enqueue(new Callback<ResponseAllBarang>() {
                                        @Override
                                        public void onResponse(Call<ResponseAllBarang> call, Response<ResponseAllBarang> response) {

                                        }

                                        @Override
                                        public void onFailure(Call<ResponseAllBarang> call, Throwable t) {

                                        }
                                    });

                                    ditem.setTotal(total);

                                    Call<ResponseAllPenjualan> responseAllPenjualan = apiEndPoint.updateTotalPenjualan(idPenjualan ,ditem);
                                    responseAllPenjualan.enqueue(new Callback<ResponseAllPenjualan>() {
                                        @Override
                                        public void onResponse(Call<ResponseAllPenjualan> call, Response<ResponseAllPenjualan> response) {

                                        }

                                        @Override
                                        public void onFailure(Call<ResponseAllPenjualan> call, Throwable t) {

                                        }
                                    });

                                    Call<ResponseAllDetailPenjualan> responseAllDetailPenjualanCall = apiEndPoint.
                                            setDetailPenjualan(idPenjualan,idPelanggan,idBarang,jumlah,total);

                                    responseAllDetailPenjualanCall.enqueue(new Callback<ResponseAllDetailPenjualan>() {
                                        @Override
                                        public void onResponse(Call<ResponseAllDetailPenjualan> call, Response<ResponseAllDetailPenjualan> response) {
                                            if (response.isSuccessful()){
                                                Intent intent = new Intent(getApplicationContext(), Barang.class);
                                                startActivity(intent);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseAllDetailPenjualan> call, Throwable t) {

                                        }
                                    });

                                    Toast.makeText(getApplicationContext(), " masuk " + idPenjualan + " " +
                                                    idBarang + " " + jumlah
                                            , Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), RiwayatPembelian.class);
                                    intent.putExtra("id", idPenjualan);
                                    intent.putExtra("total", total);
                                    startActivity(intent);
                                    finish();
                                    // sudah mendapatakan last id penjualan
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseAllPenjualan> call, Throwable t) {

                            }
                        });
                    }
                });



                //Call<ResponseAllDetailPenjualan> res = apiEndPoint.setDetailPenjualan(idPenjualan, 1, idBarang,)
            }
        });
    }
}