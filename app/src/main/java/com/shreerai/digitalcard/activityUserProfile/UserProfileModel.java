package com.shreerai.digitalcard.activityUserProfile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.contacts.model.ContactEntity;

public class UserProfileModel {

    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference databaseReference;
    private ContactEntity contactEntity;

    public UserProfileModel() {
        contactEntity = new ContactEntity();
        loadDetail();
    }

    public ContactEntity loadDetail() {
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(firebaseUser.getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("firstname").getValue().toString();
                contactEntity = new ContactEntity(dataSnapshot.child("id").getValue().toString(),
                        dataSnapshot.child("firstname").getValue().toString(),
                        dataSnapshot.child("lastname").getValue().toString(),
                        dataSnapshot.child("image").getValue().toString(),
                        dataSnapshot.child("position").getValue().toString(),
                        dataSnapshot.child("company").getValue().toString()

                );
//                nameTextView.setText(dataSnapshot.child("firstname").getValue().toString() + " " + dataSnapshot.child("lastname").getValue().toString());
//                emailTextView.setText(dataSnapshot.child("position").getValue().toString());
//                companyTextView.setText(dataSnapshot.child("company").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return contactEntity;
    }
}
