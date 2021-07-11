package id.example.butikmodisteshofi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.List;

import id.example.butikmodisteshofi.adapter.AdapterBarang;
import id.example.butikmodisteshofi.api.ApiEndPoint;
import id.example.butikmodisteshofi.api.ApiRetrofit;
import id.example.butikmodisteshofi.model.DataItem;
import id.example.butikmodisteshofi.model.ResponseAllBarang;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Barang extends AppCompatActivity {
    RecyclerView recyclerView;
    String barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_barang);
        recyclerView = findViewById(R.id.rv_barang);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        BarangAll();
    }
    public void BarangAll(){
        ApiEndPoint apiEndPoint = ApiRetrofit.getclient().create(ApiEndPoint.class);
        Call<ResponseAllBarang> responseAllBarangCallCall = apiEndPoint.getBarang();
        responseAllBarangCallCall.enqueue(new Callback<ResponseAllBarang>() {
            @Override
            public void onResponse(Call<ResponseAllBarang> call, Response<ResponseAllBarang> response) {
                List<DataItem> dataItems = response.body().getData();
                recyclerView.setAdapter(new AdapterBarang(dataItems,R.layout.activity_barang,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<ResponseAllBarang> call, Throwable t) {

            }
        });
    }
}