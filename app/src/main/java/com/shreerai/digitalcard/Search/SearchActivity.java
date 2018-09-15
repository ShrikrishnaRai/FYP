package com.shreerai.digitalcard.Search;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.Browse.Adapter.EducationAdapter.EducationAdapter;
import com.shreerai.digitalcard.Browse.Dto.EducationDto.EducationDto;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerViewSearchContact_v;
    private ArrayList<SearchDto> searchDtoArrayList;
    SearchViewAdapter searchViewAdapter_ic;
    DatabaseReference mDatbaseReference_searchUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchDtoArrayList = new ArrayList<>();
        init();
        //        LinearLayoutManager linearLayoutManager_plumbing = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager_users = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        
        loadUsers();
    }

    void init() {
        recyclerViewSearchContact_v = findViewById(R.id.searchContact_recycler);
    }

    void loadUsers() {

        mDatbaseReference_searchUser = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatbaseReference_searchUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    SearchDto searchDto_icM = dataSnapshot1.getValue(SearchDto.class);
                    searchDtoArrayList.add(searchDto_icM);
                }
                searchViewAdapter_ic = new SearchViewAdapter(searchDtoArrayList, getApplicationContext());
                recyclerViewSearchContact_v.setAdapter(searchViewAdapter_ic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error while Loading", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
