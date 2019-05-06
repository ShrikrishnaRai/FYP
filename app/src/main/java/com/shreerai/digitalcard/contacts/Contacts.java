package com.shreerai.digitalcard.contacts;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.contacts.contactAdapter.ContactAdapter;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.search.dto.SearchDto;

import java.util.ArrayList;

public class Contacts extends Fragment {
    RecyclerView recyclerView_contact;
    private DatabaseReference mFriendReqDatabase;
    private DatabaseReference mReceivedFriendReqDatabase;
    private DatabaseReference mFriends;
    private DatabaseReference mUsers;
    private FirebaseUser mCurrentUser;
    ContactAdapter contactAdapter;
    String userId;
    private ArrayList<SearchDto> searchDtoArrayList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_fragment, container, false);
        init(view);
        searchDtoArrayList = new ArrayList<>();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mFriendReqDatabase = FirebaseDatabase.getInstance().getReference().child("Friend_request");
        mFriends = FirebaseDatabase.getInstance().getReference().child("Friends");
        userId = mCurrentUser.getUid();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_contact.setLayoutManager(linearLayoutManager);
        recyclerView_contact.setHasFixedSize(true);
        recyclerView_contact.setAdapter(contactAdapter);
        checkLoadData();
        return view;
    }

    void init(View view) {
        recyclerView_contact = view.findViewById(R.id.recyclerView_contact);
    }


    void checkLoadData() {
        mFriends.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(mCurrentUser.getUid())) {
                    mReceivedFriendReqDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(mCurrentUser.getUid()).child("value");
                    mReceivedFriendReqDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue(String.class);
                            try {
                                mUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(value);
                                mUsers.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        SearchDto searchDto = new SearchDto();
                                        searchDto.setFirstname(dataSnapshot.child("firstname").getValue(String.class));
                                        searchDto.setLastname(dataSnapshot.child("lastname").getValue(String.class));
                                        searchDto.setPosition(dataSnapshot.child("position").getValue(String.class));
                                        searchDtoArrayList.add(searchDto);
                                        contactAdapter = new ContactAdapter(searchDtoArrayList, getContext());
                                        recyclerView_contact.setAdapter(contactAdapter);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            } catch (Exception e) {
                                Toast.makeText(getActivity(), "Excepiton caught", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
