package id.example.butikmodisteshofi.api;

import id.example.butikmodisteshofi.Barang;
import id.example.butikmodisteshofi.model.DataItem;
import id.example.butikmodisteshofi.model.ResponseAllBarang;
import id.example.butikmodisteshofi.model.ResponseAllDetailPenjualan;
import id.example.butikmodisteshofi.model.ResponseAllPelanggan;
import id.example.butikmodisteshofi.model.ResponseAllPenjualan;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {
    @GET("api/barang")
    Call<ResponseAllBarang> getBarang();

    @GET("api/penjualan")
    Call<ResponseAllPenjualan> getPenjualan();

    @GET("api/detailpenjualan/riwayat/{id}")
    Call<ResponseAllDetailPenjualan> getdeDetailPenjualanRiwayat(@Path("id") int id);

    @GET("api/penjualan/{id}")
    Call<ResponseAllPenjualan> getPenjualanId(@Path("id") int id);

    @GET("api/detailpenjualan/{id}")
    Call<ResponseAllDetailPenjualan> getdeDetailPenjualan(@Path("id") int id);

    @GET("api/barang/{id}")
    Call<ResponseAllBarang> getBarangId(@Path("id") int id);

    @GET("api/pelanggan/{id}")
    Call<ResponseAllPelanggan> getPelangganId(@Path("id") int id);

    @GET("api/pelanggan")
    Call<ResponseAllPelanggan> getPelanggan();

    @POST("api/penjualan")
    @FormUrlEncoded
    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    Call<ResponseAllPenjualan> setPenjualan(
            @Field("tanggal") String tanggal,
            @Field("total") int total
    );


    @POST("api/penjualan")
    @FormUrlEncoded
    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    Call<ResponseAllPenjualan> setPenjualanTransaksi(
            @Field("tanggal") String tanggal,
            @Field("total") int total,
            @Field("keranjang") int keranjang

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

    @PUT("api/barang/{id}")
    Call<ResponseAllBarang> updateHargaBarang(
            @Path("id") int id,
            @Body DataItem dataItem
    );

    @PUT("api/penjualan/{id}")
    Call<ResponseAllPenjualan> updateTotalPenjualan(
            @Path("id") int id,
            @Body DataItem dataItem
    );

    @POST("api/pelanggan")
    @FormUrlEncoded
    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    Call<ResponseAllPelanggan> setRegisterPelanggan(
            @Field("nama") String nama,
            @Field("no_hp") String no_hp,
            @Field("email") String email,
            @Field("alamat") String alamat,
            @Field("user_name") String username,
            @Field("password") String password,
            @Field("konfirmasi_password") String compirmation_password
    );

}
