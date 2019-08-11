package com.example.submissionpemula;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class MasjidAdapter extends RecyclerView.Adapter<MasjidAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Masjid> masjids = new ArrayList<>();
    private OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    MasjidAdapter(Context context) {
        this.context = context;
    }

    void setMasjids(ArrayList<Masjid> masjids) {
        this.masjids.clear();
        this.masjids.addAll(masjids);
        notifyDataSetChanged();
    }

    public ArrayList<Masjid> getMasjids() {
        return masjids;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_daftar_masjid, viewGroup, false);
        return new ViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.tvNama.setText(getMasjids().get(i).getNamaMasjid());
        holder.tvTahun.setText(getMasjids().get(i).getTahunMasjid());
        holder.tvLokasi.setText(getMasjids().get(i).getLokasiMasjid());
        Glide.with(holder.imgFoto.getContext())
                .load(getMasjids().get(i).getFoto())
                .into(holder.imgFoto);
    }

    @Override
    public int getItemCount() {
        return getMasjids().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvTahun, tvDesc, tvLokasi;
        private ImageView imgFoto;
        private Button btnDetail;

        ViewHolder(@NonNull View view) {
            super(view);
            tvLokasi = view.findViewById(R.id.tv_lokasi_daftar);
            tvNama = view.findViewById(R.id.tv_nama_daftar);
            tvTahun = view.findViewById(R.id.tv_year_daftar);
            imgFoto = view.findViewById(R.id.img_daftar_masjid);
            btnDetail = view.findViewById(R.id.btn_detail);

            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            clickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
