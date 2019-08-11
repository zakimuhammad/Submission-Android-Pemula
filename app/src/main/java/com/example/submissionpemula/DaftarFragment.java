package com.example.submissionpemula;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class DaftarFragment extends Fragment implements MasjidAdapter.OnItemClickListener {
    private String[] namaMasjid, tahunMasjid, lokasiMasjid, descmasjid;
    private TypedArray fotoMasjid;
    private MasjidAdapter adapter = new MasjidAdapter(getActivity());
    private ArrayList<Masjid> masjids;
    private RecyclerView rvDaftar;

    public DaftarFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_daftar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvDaftar = view.findViewById(R.id.rv_daftar);
        rvDaftar.setHasFixedSize(true);
        rvDaftar.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDaftar.setNestedScrollingEnabled(false);

        prepare();
        addItem();
    }


    private void addItem(){
        masjids = new ArrayList<>();
        for (int i=0; i<namaMasjid.length; i++){
            Masjid masjid = new Masjid();
            masjid.setNamaMasjid(namaMasjid[i]);
            masjid.setLokasiMasjid(lokasiMasjid[i]);
            masjid.setTahunMasjid(tahunMasjid[i]);
            masjid.setFoto(fotoMasjid.getResourceId(i, -1));
            masjids.add(masjid);
        }
        adapter.setClickListener(DaftarFragment.this);
        adapter.setMasjids(masjids);
        rvDaftar.setAdapter(adapter);
    }

    private void prepare(){
        namaMasjid = getResources().getStringArray(R.array.nama_masjid);
        tahunMasjid = getResources().getStringArray(R.array.tahun_berdiri);
        lokasiMasjid = getResources().getStringArray(R.array.lokasi_masjid);
        fotoMasjid = getResources().obtainTypedArray(R.array.foto_masjid);
        descmasjid = getResources().getStringArray(R.array.deskrips_masjid);
    }


    @Override
    public void onItemClick(int position) {
        Masjid data = new Masjid();
        data.setNamaMasjid(namaMasjid[position]);
        data.setTahunMasjid(tahunMasjid[position]);
        data.setLokasiMasjid(lokasiMasjid[position]);
        data.setDescMasjid(descmasjid[position]);
        data.setFoto(fotoMasjid.getResourceId(position, -1));

        String nama = data.getNamaMasjid();
        String tahun = data.getTahunMasjid();
        String lokasi = data.getLokasiMasjid();
        String desc = data.getDescMasjid();
        int foto = data.getFoto();

        DetailFragment detailFragment = new DetailFragment();

        Bundle bundle = new Bundle();
        bundle.putString(DetailFragment.EXTRA_NAME, nama);
        bundle.putString(DetailFragment.EXTRA_LOKASI, lokasi);
        bundle.putString(DetailFragment.EXTRA_TAHUN, tahun);
        bundle.putString(DetailFragment.EXTRA_DESC, desc);
        bundle.putInt(DetailFragment.EXTRA_FOTO, foto);

        detailFragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_main, detailFragment, DetailFragment.class.getSimpleName());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
