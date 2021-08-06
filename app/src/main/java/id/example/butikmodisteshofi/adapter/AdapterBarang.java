package id.example.butikmodisteshofi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.example.butikmodisteshofi.Beranda;
import id.example.butikmodisteshofi.Login;
import id.example.butikmodisteshofi.R;
import id.example.butikmodisteshofi.TransaksiPembelian;
import id.example.butikmodisteshofi.model.DataItem;

public class AdapterBarang  extends RecyclerView.Adapter<AdapterBarang.BarangView>{
    private List<DataItem> dataItems;
    private int rowLayout;
    private Context context;

public AdapterBarang(List<DataItem> dataItems, int rowLayout, Context context){
    this.dataItems = dataItems;
    this.context = context;
    this.rowLayout = rowLayout;
}

    @NonNull
    @Override
    public AdapterBarang.BarangView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new BarangView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBarang.BarangView holder, int position) {
        holder.NamaBarang.setText(dataItems.get(position).getNamaBarang());
        holder.Harga.setText(String.valueOf(dataItems.get(position).getHarga()));
        holder.Stok.setText(String.valueOf(dataItems.get(position).getStokBarang()));

        Picasso.get().load("https://rrh.tikblksamarinda.com/img/barang/"+dataItems.get(position).getFoto())
                .into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class BarangView extends RecyclerView.ViewHolder {
        public TextView NamaBarang, Harga, Stok;
        public View view;
        public ImageView foto;

        public BarangView(View itemView) {
            super(itemView);
            view = itemView;
            NamaBarang = itemView.findViewById(R.id.nama);
            Harga = itemView.findViewById(R.id.harga);
            Stok = itemView.findViewById(R.id.stok);
            foto = itemView.findViewById(R.id.foto);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 int posi =getAdapterPosition();
                 if (posi != RecyclerView.NO_POSITION){
                     Intent transaksiPembelian = new Intent(context, TransaksiPembelian.class);

                     transaksiPembelian.putExtra("id",dataItems.get(posi).getId());
                     transaksiPembelian.putExtra("nama_barang",dataItems.get(posi).getNamaBarang());
                     transaksiPembelian.putExtra("harga",dataItems.get(posi).getHarga());
                     transaksiPembelian.putExtra("stok",dataItems.get(posi).getStokBarang());
                     transaksiPembelian.putExtra("ukuran",dataItems.get(posi).getUkuran());
                     transaksiPembelian.putExtra("warna",dataItems.get(posi).getWarna());
                     transaksiPembelian.addFlags(transaksiPembelian.FLAG_ACTIVITY_NEW_TASK);

                     context.startActivity(transaksiPembelian);
                     ((Activity)context).finish();
                 }
                }
            });

        }
    }
}
