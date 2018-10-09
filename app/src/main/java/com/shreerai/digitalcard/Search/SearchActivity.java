package com.shreerai.digitalcard.Search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.Search.Adapter.SearchViewAdapter;
import com.shreerai.digitalcard.Search.Dto.SearchDto;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerViewSearchContact_v;
    private ArrayList<SearchDto> searchDtoArrayList;
    SearchViewAdapter searchViewAdapter_ic;
    DatabaseReference mDatbaseReference_searchUser;
    ImageView search_v;
    EditText searchArea_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchDtoArrayList = new ArrayList<>();
        mDatbaseReference_searchUser = FirebaseDatabase.getInstance().getReference().child("Users");
        init();
        LinearLayoutManager linearLayoutManager_users = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewSearchContact_v.setLayoutManager(linearLayoutManager_users);
        recyclerViewSearchContact_v.setHasFixedSize(true);
        recyclerViewSearchContact_v.setAdapter(searchViewAdapter_ic);
        loadUsers();
        search_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  firebaseSearch(user_V);
            }
        });
    }

    void init() {
        recyclerViewSearchContact_v = findViewById(R.id.searchContact_recycler);
        search_v = findViewById(R.id.search);
        searchArea_v = findViewById(R.id.searchArea);
    }

    void loadUsers() {

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
