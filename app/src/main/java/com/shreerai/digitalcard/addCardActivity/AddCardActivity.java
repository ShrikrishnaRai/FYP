package com.shreerai.digitalcard.addCardActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.MainActivity;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.activityUserProfile.UserProfileActivity;
import com.shreerai.digitalcard.activityUserProfile.UserProfileModel;
import com.shreerai.digitalcard.contacts.model.ContactEntity;

import java.util.ArrayList;
import java.util.List;

public class AddCardActivity extends AppCompatActivity {
    TextView cardName_v;
    TextView cardPosition_v;
    Button browse_v;
    private DatabaseReference mFriendRegDatabase;
    private DatabaseReference mUsers;
    private FirebaseUser mCurrentUser;
    private String currentState_V;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private String firstName;
    private String lastName;
    private String position;
    private String company;
    private String id;
    private String image;
    private DatabaseReference databaseReference;
    private String profileFirstName;
    private String profileLastName;
    private String profilePositionName;
    private String profileCompanyName;
    private String profileId;
    private String profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        init();
        currentState_V = "not_friends";
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        setTransferValue(getIntent());
        cardName_v.setText(firstName + " " + lastName);
        cardPosition_v.setText(position);
        loadDetail();
        browse_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard();
                startActivity(new Intent(AddCardActivity.this, MainActivity.class));
            }
        });

    }

    void setTransferValue(Intent intent) {
        firstName = intent.getExtras().getString("firstname");
        lastName = intent.getExtras().getString("lastname");
        position = intent.getExtras().getString("position");
        id = intent.getExtras().getString("id");
        company = intent.getExtras().getString("company");
        image = intent.getExtras().getString("image");
    }

    void init() {
        cardName_v = findViewById(R.id.addCardName);
        cardPosition_v = findViewById(R.id.addCardPosition);
        browse_v = findViewById(R.id.addBrowse);
    }

    void addCard() {
        mFriendRegDatabase = FirebaseDatabase.getInstance()
                .getReference()
                .child("Users")
                .child(id)
                .child("friendsRequest")
                .child(profileFirstName);
        ContactEntity contactEntity = new ContactEntity(profileId, profileFirstName, profileLastName, profileImage, profilePositionName, profileCompanyName);
        mFriendRegDatabase.setValue(contactEntity).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Browse request sent", Toast.LENGTH_LONG).show();
            }
        });

    }

    void loadDetail() {
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(firebaseUser.getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profileId = dataSnapshot.child("id").getValue().toString();
                profileFirstName = dataSnapshot.child("firstname").getValue().toString();
                profileLastName = dataSnapshot.child("lastname").getValue().toString();
                profileCompanyName = dataSnapshot.child("company").getValue().toString();
                profileImage = dataSnapshot.child("image").getValue().toString();
                profilePositionName = dataSnapshot.child("position").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void search(String name){
        mUsers = FirebaseDatabase.getInstance().getReference().child("Users");

    }


}
