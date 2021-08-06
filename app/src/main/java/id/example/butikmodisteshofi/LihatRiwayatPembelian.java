package id.example.butikmodisteshofi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import id.example.butikmodisteshofi.adapter.AdapterPenjualan;
import id.example.butikmodisteshofi.api.ApiEndPoint;
import id.example.butikmodisteshofi.api.ApiRetrofit;
import id.example.butikmodisteshofi.model.DataItem;
import id.example.butikmodisteshofi.model.ResponseAllPenjualan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LihatRiwayatPembelian extends AppCompatActivity {
    RecyclerView recyclerView;
    String LihatRiwayatPembelian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_detailpenjualan);
        recyclerView = findViewById(R.id.rv_detailpenjualan);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        riwayatPembelian();
    }

    public void riwayatPembelian(){

        ApiEndPoint apiEndPoint = ApiRetrofit.getclient().create(ApiEndPoint.class);
        Call<ResponseAllPenjualan> responseAllPenjualanCall = apiEndPoint.getPenjualan();
        responseAllPenjualanCall.enqueue(new Callback<ResponseAllPenjualan>() {
            @Override
            public void onResponse(Call<ResponseAllPenjualan> call, Response<ResponseAllPenjualan> response) {
                List<DataItem> dataItems = response.body().getData();
                recyclerView.setAdapter(new AdapterPenjualan(dataItems,R.layout.activity_lihat_riwayat_pembelian,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<ResponseAllPenjualan> call, Throwable t) {

            }
        });

    }
}