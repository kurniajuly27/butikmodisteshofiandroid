package id.example.butikmodisteshofi.api;

import id.example.butikmodisteshofi.model.ResponseAllBarang;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {
    @GET("api/barang")
    Call<ResponseAllBarang> getBarang();
}
