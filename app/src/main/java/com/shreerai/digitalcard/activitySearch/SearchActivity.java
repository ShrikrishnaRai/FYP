package com.shreerai.digitalcard.activitySearch;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.activitySearch.adapter.SearchViewAdapter;
import com.shreerai.digitalcard.activitySearch.model.SearchEntity;
import com.shreerai.digitalcard.contacts.model.ContactEntity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerViewSearchContact_v;
    private ArrayList<SearchEntity> searchEntityArrayList;
    SearchViewAdapter searchViewAdapter_ic;
    DatabaseReference mDatbaseReference_searchUser;
    DatabaseReference mFriendsDatabaseReference;
    ImageView search_v;
    EditText searchArea_v;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchEntityArrayList = new ArrayList<>();
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
                    SearchEntity searchEntity_icM = dataSnapshot1.getValue(SearchEntity.class);
                    if (!searchEntity_icM.getId().equals(user.getUid())) {
                        searchEntityArrayList.add(searchEntity_icM);
                    }
                }
                for (int i = 0; i < searchEntityArrayList.size(); i++)

                    searchViewAdapter_ic = new SearchViewAdapter(searchEntityArrayList, getApplicationContext());
                recyclerViewSearchContact_v.setAdapter(searchViewAdapter_ic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error while Loading", Toast.LENGTH_SHORT).show();
            }
        });
    }

    List<ContactEntity> loadFriends() {
        final List<ContactEntity> contactEntityList = new ArrayList<>();
        mFriendsDatabaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(user.getUid())
                .child("friends");
        mFriendsDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ContactEntity contactEntity = dataSnapshot1.getValue(ContactEntity.class);
                    contactEntityList.add(contactEntity);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return contactEntityList;
    }


}
