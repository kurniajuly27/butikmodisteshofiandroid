package id.example.butikmodisteshofi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import id.example.butikmodisteshofi.R;
import id.example.butikmodisteshofi.api.ApiEndPoint;
import id.example.butikmodisteshofi.api.ApiRetrofit;
import id.example.butikmodisteshofi.model.DataItem;
import id.example.butikmodisteshofi.model.ResponseAllBarang;
import id.example.butikmodisteshofi.model.ResponseAllPelanggan;
import id.example.butikmodisteshofi.model.ResponseAllPenjualan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDetailPenjualan extends RecyclerView.Adapter<AdapterDetailPenjualan.DetailPenjualanView> {
    private List<DataItem> dataItems;
    private int rowLayout;
    private Context context;

public AdapterDetailPenjualan(List<DataItem> dataItems,int rowLayout, Context context){
    this.dataItems = dataItems;
    this.context = context;
    this.rowLayout = rowLayout;
}

    @NonNull
    @Override
    public AdapterDetailPenjualan.DetailPenjualanView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new DetailPenjualanView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDetailPenjualan.DetailPenjualanView holder, int position) {

        holder.JumlahBarang.setText(String.valueOf(dataItems.get(position).getJumlah()));
        holder.TotalHarga.setText(String.valueOf(dataItems.get(position).getTotal()));


        ApiEndPoint apiEndPoint = ApiRetrofit.getclient().create(ApiEndPoint.class);
        Call<ResponseAllBarang> responseIdBarang = apiEndPoint.getBarangId(dataItems.get(position).getBarangid());
        responseIdBarang.enqueue(new Callback<ResponseAllBarang>() {
            @Override
            public void onResponse(Call<ResponseAllBarang> call, Response<ResponseAllBarang> response) {
                holder.NamaBarang.setText(response.body().getData().get(0).getNamaBarang());
                holder.Ukuran.setText(response.body().getData().get(0).getUkuran());
                holder.Warna.setText(response.body().getData().get(0).getWarna());
            }

            @Override
            public void onFailure(Call<ResponseAllBarang> call, Throwable t) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }
    public class DetailPenjualanView extends RecyclerView.ViewHolder {
        public TextView Tanggal, NamaBarang, Ukuran, Warna, JumlahBarang, TotalHarga, TotalBayar;
        public View view;

        public DetailPenjualanView(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            NamaBarang =itemView.findViewById(R.id.btn_nama_barang);
            Ukuran = itemView.findViewById(R.id.btn_ukuran);
            Warna = itemView.findViewById(R.id.btn_warna);
            JumlahBarang = itemView.findViewById(R.id.btn_jumlah_barang);
            TotalHarga = itemView.findViewById(R.id.btn_total_harga);
            TotalBayar = itemView.findViewById(R.id.btn_total_bayar);
        }
    }
}
