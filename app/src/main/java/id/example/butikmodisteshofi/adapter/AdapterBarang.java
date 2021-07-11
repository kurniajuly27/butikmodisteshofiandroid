package id.example.butikmodisteshofi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.example.butikmodisteshofi.R;
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
        holder.Harga.setText(dataItems.get(position).getHarga());
        holder.Stok.setText(dataItems.get(position).getStokBarang());
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class BarangView extends RecyclerView.ViewHolder {
        public TextView NamaBarang, Harga, Stok;
        public View view;

        public BarangView(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            NamaBarang = itemView.findViewById(R.id.nama);
            Harga = itemView.findViewById(R.id.harga);
            Stok = itemView.findViewById(R.id.stok);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Toast.makeText(context,"Terklik" + pos, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
