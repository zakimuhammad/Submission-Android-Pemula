package com.example.submissionpemula;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class DetailFragment extends Fragment {

    public static String EXTRA_NAME = "extra_name";
    public static String EXTRA_TAHUN = "extra_tahun";
    public static String EXTRA_DESC = "extra_desc";
    public static String EXTRA_LOKASI = "extra_lokasi";
    public static String EXTRA_FOTO = "extra_foto";

    private TypedArray fotoMasjid;
    TextView tvNama, tvTahun, tvLokasi, tvDesc;
    ImageView imgFoto;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvNama = view.findViewById(R.id.tv_nama_detail);
        tvDesc = view.findViewById(R.id.tv_deskripsi);
        tvLokasi = view.findViewById(R.id.tv_lokasi_detail);
        tvTahun = view.findViewById(R.id.tv_year_detail);
        imgFoto = view.findViewById(R.id.img_detail_masjid);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        assert getArguments() != null;
        String nama = getArguments().getString(EXTRA_NAME);
        String tahun = getArguments().getString(EXTRA_TAHUN);
        String lokasi = getArguments().getString(EXTRA_LOKASI);
        String desc = getArguments().getString(EXTRA_DESC);
        int foto = getArguments().getInt(EXTRA_FOTO);

        tvNama.setText(nama);
        tvTahun.setText(tahun);
        tvLokasi.setText(lokasi);
        tvDesc.setText(desc);
        Glide.with(Objects.requireNonNull(getContext()))
                .load(foto)
                .into(imgFoto);
    }
}
