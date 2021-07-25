package id.example.butikmodisteshofi.api;

import id.example.butikmodisteshofi.model.ResponseAllBarang;
import id.example.butikmodisteshofi.model.ResponseAllDetailPenjualan;
import id.example.butikmodisteshofi.model.ResponseAllPenjualan;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiEndPoint {
    @GET("api/barang")
    Call<ResponseAllBarang> getBarang();

    @GET("api/penjualan")
    Call<ResponseAllPenjualan> getPenjualan();

    @POST("api/penjualan")
    @FormUrlEncoded
    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    Call<ResponseAllPenjualan> setPenjualan(
            @Field("tanggal") String tanggal,
            @Field("total") int total
    );

    @POST("api/detailpenjualan")
    @FormUrlEncoded
    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    Call<ResponseAllDetailPenjualan> setDetailPenjualan(
            @Field("penjualan_id") int penjualanId,
            @Field("pelanggan_id") int pelangganId,
            @Field("barang_id") int barangId,
            @Field("jumlah") int jumlah,
            @Field("total") int total
    );

}
