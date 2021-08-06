package id.example.butikmodisteshofi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.example.butikmodisteshofi.LihatRiwayatPembelian;
import id.example.butikmodisteshofi.R;
import id.example.butikmodisteshofi.RiwayatPembelian;
import id.example.butikmodisteshofi.Utility.PrefManager;
import id.example.butikmodisteshofi.api.ApiEndPoint;
import id.example.butikmodisteshofi.api.ApiRetrofit;
import id.example.butikmodisteshofi.model.DataItem;
import id.example.butikmodisteshofi.model.ResponseAllDetailPenjualan;
import id.example.butikmodisteshofi.model.ResponseAllPenjualan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPenjualan extends RecyclerView.Adapter<AdapterPenjualan.PenjualanView>{
    private List<DataItem> dataItems;
    private int rowLayout;
    private Context context;

public AdapterPenjualan(List<DataItem> dataItems, int rowLayout,Context context){
    this.dataItems = dataItems;
    this.context = context;
    this.rowLayout = rowLayout;
}

    @NonNull
    @Override
    public AdapterPenjualan.PenjualanView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new PenjualanView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPenjualan.PenjualanView holder, int position) {
        PrefManager prefManager = new PrefManager(context);

        ApiEndPoint apiEndPoint = ApiRetrofit.getclient().create(ApiEndPoint.class);
        Call<ResponseAllDetailPenjualan> responseAllPenjualanCall = apiEndPoint.getdeDetailPenjualanRiwayat(prefManager.getInt("idpelanggan"));
        responseAllPenjualanCall.enqueue(new Callback<ResponseAllDetailPenjualan>() {
            @Override
            public void onResponse(Call<ResponseAllDetailPenjualan> call, Response<ResponseAllDetailPenjualan> response) {
                if(response.isSuccessful()){
                    for(int i = 0; i < response.body().getData().size(); i++){
                        if(dataItems.get(position).getId() == response.body().getData().get(i).getPenjualanid()){

//                                holder.root.setVisibility(View.GONE);
//                                holder.root.setVisibility(View.VISIBLE);
                            holder.NoPelanggan.setText(String.valueOf(dataItems.get(position).getId()));
                            holder.Tanggal.setText(dataItems.get(position).getTanggal());
                            holder.TotalHarga.setText(String.valueOf(dataItems.get(position).getTotal()));

                        }

                    }
                }

                if(holder.NoPelanggan.getText() == ""){
                    holder.root.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseAllDetailPenjualan> call, Throwable t) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class PenjualanView extends RecyclerView.ViewHolder {
         public TextView NoPelanggan, Tanggal, TotalHarga;
         public View view;
         public LinearLayout root;

        public PenjualanView(View itemView) {
            super(itemView);
            view = itemView;
            NoPelanggan = itemView.findViewById(R.id.no_pelanggan_riwayat);
            Tanggal = itemView.findViewById(R.id.tanggal);
            TotalHarga = itemView.findViewById(R.id.total_bayar_riwayat);
            root = itemView.findViewById(R.id.root);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posi =getAdapterPosition();
                    if (posi != RecyclerView.NO_POSITION){
                        Intent riwayatPembelian = new Intent(context, RiwayatPembelian.class);

                        riwayatPembelian.putExtra("id",dataItems.get(posi).getId());
                        riwayatPembelian.putExtra("total",dataItems.get(posi).getTotal());

                        riwayatPembelian.addFlags(riwayatPembelian.FLAG_ACTIVITY_NEW_TASK);

                        context.startActivity(riwayatPembelian);
                    }
                }
            });
        }
    }
}
