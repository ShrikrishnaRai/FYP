package com.shreerai.digitalcard.Browse;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shreerai.digitalcard.Browse.Adapter.BrowseAdapter;
import com.shreerai.digitalcard.Browse.Dto.BrowseDto;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

public class Browse extends Fragment {
    BrowseAdapter browseAdapter_ic;
    private ArrayList<BrowseDto> browseDtoArrayList;
    RecyclerView recyclerView_v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.browse_fragment, container, false);
        init(view);
        browseDtoArrayList = new ArrayList<>();
        fillData();
        browseAdapter_ic = new BrowseAdapter(getContext(), browseDtoArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_v.setLayoutManager(linearLayoutManager);
        recyclerView_v.setHasFixedSize(true);
        recyclerView_v.setAdapter(browseAdapter_ic);
        return view;
    }

    void init(View view) {
        recyclerView_v = view.findViewById(R.id.browse_recyclerView);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void fillData() {
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
    }
}
