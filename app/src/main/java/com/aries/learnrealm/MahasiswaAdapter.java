package com.aries.learnrealm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {
    private List<MahasiswaModel> mahasiswaModels;
    Context context;

    public MahasiswaAdapter(Context context, List<MahasiswaModel> mahasiswaModels) {
        this.context = context;
        this.mahasiswaModels = mahasiswaModels;
    }

    @Override
    public MahasiswaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from (parent.getContext ( )).inflate (R.layout.item_mahasiswa, parent, false);
        return new MyViewHolder (v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MahasiswaAdapter.MyViewHolder holder, int position) {
        final MahasiswaModel model = mahasiswaModels.get (position);
        String nim = String.valueOf (model.getNim ( ));

        holder.nim.setText (nim);
        holder.nama.setText (model.getNama ( ));
        holder.itemView.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext ( ), DetailActivity.class);
                intent.putExtra ("id", model.getId ( ) + "");
                intent.putExtra ("nim", model.getNim ( ) + "");
                intent.putExtra ("nama", model.getNama ( ));
                v.getContext ( ).startActivity (intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return mahasiswaModels.size ( );
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nim, nama;

        public MyViewHolder(View itemView) {
            super (itemView);
            nim = itemView.findViewById (R.id.tvNim);
            nama = itemView.findViewById (R.id.tvNama);
        }
    }

}
