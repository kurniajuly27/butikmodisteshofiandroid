package id.example.butikmodisteshofi;

import androidx.appcompat.app.AppCompatActivity;
import id.example.butikmodisteshofi.api.ApiEndPoint;
import id.example.butikmodisteshofi.api.ApiRetrofit;
import id.example.butikmodisteshofi.model.DataItem;
import id.example.butikmodisteshofi.model.ResponseAllBarang;
import id.example.butikmodisteshofi.model.ResponseAllDetailPenjualan;
import id.example.butikmodisteshofi.model.ResponseAllPenjualan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    int jumlah;
    Button btnBeli;
    EditText namaBarangEt, ukuranEt, warnaEt, jumlahET;
    Date currentTime = Calendar.getInstance().getTime();
    SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("yyyy-MM-dd");
    String timestamp = simpleDateFormat.format(currentTime);

    int idPenjualan;
    int idBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_pembelian);

        namaBarang = getIntent().getExtras().getString("nama_barang");
        ukuran = getIntent().getExtras().getString("ukuran");
        warna = getIntent().getExtras().getString("warna");

        idBarang = getIntent().getExtras().getInt("warna");

        namaBarangEt = findViewById(R.id.txt_nama_barang);
        ukuranEt = findViewById(R.id.txt_ukuran);
        warnaEt = findViewById(R.id.txt_warna);
        jumlahET = findViewById(R.id.txt_jumlah_barang);


        namaBarangEt.setText(namaBarang);
        ukuranEt.setText(ukuran);
        warnaEt.setText(warna);


        String value = jumlahET.getText().toString();

        if (!"".equals(value)){
            jumlah = Integer.parseInt(value);
        }

        btnBeli = (Button)findViewById(R.id.btn_beli);

        klik();

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
                                    idPenjualan = item.getPenjualan_id();
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