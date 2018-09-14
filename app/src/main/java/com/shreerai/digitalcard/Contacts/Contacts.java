package com.shreerai.digitalcard.Contacts;

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
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.shreerai.digitalcard.Contacts.ContactAdapter.ContactAdapter;
import com.shreerai.digitalcard.Contacts.ContactDto.ContactDto;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

public class Contacts extends Fragment {
    RecyclerView recyclerView_contact;
    private ArrayList<ContactDto> contactDtoArrayList;
    ContactAdapter contactAdapter_ic;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_fragment, container, false);
        init(view);
        contactDtoArrayList = new ArrayList<>();
        fillContact();
        contactAdapter_ic = new ContactAdapter(contactDtoArrayList, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_contact.setLayoutManager(linearLayoutManager);
        recyclerView_contact.setHasFixedSize(true);
        recyclerView_contact.setAdapter(contactAdapter_ic);
        return view;
    }

    void init(View view) {
        recyclerView_contact = view.findViewById(R.id.recyclerView_contact);
    }

    void fillContact() {
        contactDtoArrayList.add(new ContactDto(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card), "Harry Potter", "Collegues"));
        contactDtoArrayList.add(new ContactDto(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card), "Harry Potter", "Collegues"));
        contactDtoArrayList.add(new ContactDto(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card), "Harry Potter", "Collegues"));
        contactDtoArrayList.add(new ContactDto(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card), "Harry Potter", "Collegues"));
        contactDtoArrayList.add(new ContactDto(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card), "Harry Potter", "Collegues"));
        contactDtoArrayList.add(new ContactDto(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card), "Harry Potter", "Collegues"));
        contactDtoArrayList.add(new ContactDto(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card), "Harry Potter", "Collegues"));
        contactDtoArrayList.add(new ContactDto(BitmapFactory.decodeResource(getResources(), R.mipmap.visiting_card), "Harry Potter", "Collegues"));

    }
}
