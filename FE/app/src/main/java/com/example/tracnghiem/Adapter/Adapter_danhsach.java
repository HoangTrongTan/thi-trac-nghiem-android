package com.example.tracnghiem.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracnghiem.Model.model_DanhSach_ngChoi;
import com.example.tracnghiem.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_danhsach extends RecyclerView.Adapter<Adapter_danhsach.Holder>{

    List<model_DanhSach_ngChoi> list_ds;

    public Adapter_danhsach(List<model_DanhSach_ngChoi> list_ds) {
        this.list_ds = list_ds;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if(position == 0){
            holder.vuongmien.setImageResource(R.drawable.vuongmien);
        }
        holder.diemso_ten.setText(list_ds.get(position).getTen()+"\n"+" Điểm hiện tại: "+list_ds.get(position).getDiem());
        Bitmap bitmap = BitmapFactory.decodeByteArray(list_ds.get(position).getAnh(), 0, list_ds.get(position).getAnh().length);
        holder.anhdaidien.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        if(list_ds != null){
            return list_ds.size();
        }
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder{
        CircleImageView anhdaidien,vuongmien;
        TextView diemso_ten;
        public Holder(@NonNull View itemView) {
            super(itemView);
            anhdaidien = itemView.findViewById(R.id.anhdaidien);
            vuongmien = itemView.findViewById(R.id.vuongmien);
            diemso_ten = itemView.findViewById(R.id.diemso_ten);
        }
    }
}
