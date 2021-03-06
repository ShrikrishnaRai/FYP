package com.shreerai.digitalcard.activityAdvertisement;

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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.activityAdvertisement.adapter.BrowseAdapter;
import com.shreerai.digitalcard.activityAdvertisement.adapter.EducationAdapter.EducationAdapter;
import com.shreerai.digitalcard.activityAdvertisement.adapter.HotelsAdapter.HotelsAdapter;
import com.shreerai.digitalcard.activityAdvertisement.adapter.PlumbingAdapter.PlumbingAdapter;
import com.shreerai.digitalcard.activityAdvertisement.adapter.ToursAdapter.ToursAdapter;
import com.shreerai.digitalcard.activityAdvertisement.model.BrowseDto;
import com.shreerai.digitalcard.activityAdvertisement.model.educationDto.EducationEntity;
import com.shreerai.digitalcard.activityAdvertisement.model.hotelDto.HotelEntity;
import com.shreerai.digitalcard.activityAdvertisement.model.plumbingDto.PlumbingEntity;
import com.shreerai.digitalcard.activityAdvertisement.model.toursDto.ToursEntity;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

public class Browse extends Fragment {
    HotelsAdapter hotelsAdapter_ic;
    BrowseAdapter browseAdapter_ic;
    EducationAdapter educationAdapter_ic;
    ToursAdapter toursAdapter_ic;
    PlumbingAdapter plumbingAdapter_ic;
    private ArrayList<HotelEntity> hotelEntityArrayList;
    private ArrayList<ToursEntity> toursEntityArrayList;
    private ArrayList<BrowseDto> browseDtoArrayList;
    private ArrayList<EducationEntity> educationDtosList;
    private ArrayList<PlumbingEntity> plumbingEntityArrayList;
    RecyclerView recyclerView_v;
    RecyclerView recyclerViewEducation_v;
    RecyclerView recyclerViewTours_v;
    RecyclerView recyclerViewHotel_v;
    RecyclerView recyclerViewPlumbing_v;
    DatabaseReference mDatabaseReference_education;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.browse_fragment, container, false);
        init(view);

        browseDtoArrayList = new ArrayList<>();
        educationDtosList = new ArrayList<>();
        toursEntityArrayList = new ArrayList<>();
        hotelEntityArrayList = new ArrayList<>();
        plumbingEntityArrayList = new ArrayList<>();
        fillData();
        toursData();
        hotelData();
        plumbingData();
        browseAdapter_ic = new BrowseAdapter(getContext(), browseDtoArrayList);
        toursAdapter_ic = new ToursAdapter(getContext(), toursEntityArrayList);
        hotelsAdapter_ic = new HotelsAdapter(hotelEntityArrayList, getContext());
        plumbingAdapter_ic = new PlumbingAdapter(plumbingEntityArrayList, getContext());
        educationAdapter_ic = new EducationAdapter(educationDtosList, getContext());

        LinearLayoutManager linearLayoutManager_plumbing = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager_hotel = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager_education = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager_tours = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewPlumbing_v.setLayoutManager(linearLayoutManager_plumbing);
        recyclerViewPlumbing_v.setHasFixedSize(true);
        recyclerViewPlumbing_v.setAdapter(plumbingAdapter_ic);

        recyclerViewHotel_v.setLayoutManager(linearLayoutManager_hotel);
        recyclerViewHotel_v.setHasFixedSize(true);
        recyclerViewHotel_v.setAdapter(hotelsAdapter_ic);

        recyclerViewTours_v.setLayoutManager(linearLayoutManager_tours);
        recyclerViewTours_v.setHasFixedSize(true);
        recyclerViewTours_v.setAdapter(toursAdapter_ic);

        loadEducationData();

        recyclerViewEducation_v.setLayoutManager(linearLayoutManager_education);
        recyclerViewEducation_v.setHasFixedSize(true);
        recyclerViewEducation_v.setAdapter(educationAdapter_ic);

        recyclerView_v.setLayoutManager(linearLayoutManager);
        recyclerView_v.setHasFixedSize(true);
        recyclerView_v.setAdapter(browseAdapter_ic);


        return view;
    }

    void init(View view) {
        recyclerView_v = view.findViewById(R.id.browse_recyclerView);
        recyclerViewEducation_v = view.findViewById(R.id.education_recyclerView);
        recyclerViewTours_v = view.findViewById(R.id.tours_recyclerView);
        recyclerViewHotel_v = view.findViewById(R.id.hotel_recyclerView);
        recyclerViewPlumbing_v = view.findViewById(R.id.plumbing_recyclerView);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void fillData() {
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.college_icon), "Education"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.travel_icon), "Tours"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.plumber_icon), "Plumbing"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.hotel_icon), "Hotel"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.college_icon), "Education"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.travel_icon), "Tours"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.college_icon), "Education"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.travel_icon), "Tours"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon), "Background"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "Launcher"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.college_icon), "Education"));
        browseDtoArrayList.add(new BrowseDto(BitmapFactory.decodeResource(getResources(), R.mipmap.travel_icon), "Tours"));
    }


    void toursData() {
        toursEntityArrayList.add(new ToursEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.card_three)));
        toursEntityArrayList.add(new ToursEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.card_five)));
        toursEntityArrayList.add(new ToursEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.card_four)));
        toursEntityArrayList.add(new ToursEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.card_one)));
    }

    void plumbingData() {
        plumbingEntityArrayList.add(new PlumbingEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.card_one)));
        plumbingEntityArrayList.add(new PlumbingEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.card_four)));
        plumbingEntityArrayList.add(new PlumbingEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.card_five)));
        plumbingEntityArrayList.add(new PlumbingEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.card_three)));
        plumbingEntityArrayList.add(new PlumbingEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.card_one)));
    }

    void hotelData() {
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));
        hotelEntityArrayList.add(new HotelEntity(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card)));

    }

    void loadEducationData() {
        mDatabaseReference_education = FirebaseDatabase.getInstance().getReference().child("Advertisement").child("Education");
        mDatabaseReference_education.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    EducationEntity educationEntity_icM = dataSnapshot1.getValue(EducationEntity.class);
                    educationDtosList.add(educationEntity_icM);
                }
                educationAdapter_ic = new EducationAdapter(educationDtosList, getContext());
                recyclerViewEducation_v.setAdapter(educationAdapter_ic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
