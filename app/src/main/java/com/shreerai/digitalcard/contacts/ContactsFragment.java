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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.contacts.adapter.ContactAdapter;
import com.shreerai.digitalcard.contacts.model.ContactEntity;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {
    RecyclerView recyclerView_contact;
    private DatabaseReference mFriends;
    private FirebaseUser mCurrentUser;
    String userId;
    private List<ContactEntity> contactEntityArrayList;
    private ImageView noFriends_ImageView;
    private TextView noFriends_textView;
    private TextView noFriendsHeadings_textView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_fragment, container, false);
        init(view);
        contactEntityArrayList = new ArrayList<>();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = mCurrentUser.getUid();
        loadContacts();
        return view;
    }

    /**
     * load friends from Firebase database
     */
    void loadContacts() {
        mFriends = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(mCurrentUser.getUid())
                .child("friends");
        mFriends.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ContactEntity contactEntity = dataSnapshot1.getValue(ContactEntity.class);
                        contactEntityArrayList.add(contactEntity);
                    }
                    loadContactsItem(contactEntityArrayList);
                } else {
                    noFriends_ImageView.setVisibility(View.VISIBLE);
                    noFriends_textView.setText("Try adding contacts to list here");
                    noFriends_textView.setVisibility(View.VISIBLE);
                    noFriendsHeadings_textView.setText("No Contacts Found");
                    noFriendsHeadings_textView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void init(View view) {
        recyclerView_contact = view.findViewById(R.id.recyclerView_contact);
        noFriends_ImageView = view.findViewById(R.id.no_friends_imageview);
        noFriends_textView = view.findViewById(R.id.no_friends_text);
        noFriendsHeadings_textView = view.findViewById(R.id.no_friends_text_heading);
    }


    void loadContactsItem(List<ContactEntity> contactEntityArrayList) {
        ContactAdapter contactAdapter = new ContactAdapter(contactEntityArrayList, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_contact.setLayoutManager(linearLayoutManager);
        recyclerView_contact.setHasFixedSize(true);
        recyclerView_contact.setAdapter(contactAdapter);
    }

}
