package id.example.butikmodisteshofi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import id.example.butikmodisteshofi.adapter.AdapterDetailPenjualan;
import id.example.butikmodisteshofi.adapter.AdapterPenjualan;
import id.example.butikmodisteshofi.api.ApiEndPoint;
import id.example.butikmodisteshofi.api.ApiRetrofit;
import id.example.butikmodisteshofi.model.DataItem;
import id.example.butikmodisteshofi.model.ResponseAllDetailPenjualan;
import id.example.butikmodisteshofi.model.ResponseAllPenjualan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPembelian extends AppCompatActivity {

    String tanggal, namaBarang, ukuran, warna;
    int jumlahBarang, totalHarga, totalBayar;
    TextView tanggalTv, namaBarangTv, ukuranTv, warnaTv, jumlahBarangTv, totalHargaTv, totalBayarTv;
    RecyclerView recyclerView;

    int idRiwayatPembelian;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_lihatriwayatpembelian);
        recyclerView = findViewById(R.id.rv_lihatriwayatpembelians);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        idRiwayatPembelian = getIntent().getExtras().getInt("id");
        total = getIntent().getExtras().getInt("total");

        totalBayarTv = findViewById(R.id.btn_total_bayar);

        totalBayarTv.setText(String.valueOf(total));
        riwayatPembelian();
    }

    public void riwayatPembelian(){
        ApiEndPoint apiEndPoint = ApiRetrofit.getclient().create(ApiEndPoint.class);
        Call<ResponseAllDetailPenjualan> responseAllDetailPenjualanCall = apiEndPoint.getdeDetailPenjualan(idRiwayatPembelian);
        responseAllDetailPenjualanCall.enqueue(new Callback<ResponseAllDetailPenjualan>() {
            @Override
            public void onResponse(Call<ResponseAllDetailPenjualan> call, Response<ResponseAllDetailPenjualan> response) {
                List<DataItem> dataItems = response.body().getData();
                recyclerView.setAdapter(new AdapterDetailPenjualan(dataItems,R.layout.activity_riwayat_pembelian,getApplicationContext()));

            }

            @Override
            public void onFailure(Call<ResponseAllDetailPenjualan> call, Throwable t) {

            }
        });
    }
}