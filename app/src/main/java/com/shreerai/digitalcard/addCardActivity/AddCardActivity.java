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
    private DatabaseReference mFriends;
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
        mFriends = FirebaseDatabase.getInstance().getReference().child("Friends");
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        setTransferValue(getIntent());
        cardName_v.setText(firstName + " " + lastName);
        cardPosition_v.setText(position);
        loadDetail();
        browse_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Browse button clicked", Toast.LENGTH_LONG).show();
                addCard();
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
//        UserProfileModel userProfileModel = new UserProfileModel();
//        ContactEntity contactEntity = userProfileModel.loadDetail();
        //  Toast.makeText(getApplicationContext(), loadDetail().get(0).getFirstname(), Toast.LENGTH_LONG).show();
        //   Toast.makeText(getApplicationContext(), loadDetail().getFirstname(), Toast.LENGTH_LONG).show();
//        mFriendRegDatabase = FirebaseDatabase.getInstance().getReference()
//                .child("Users")
//                .child(id)
//                .child("friendsRequest")
//                .child(contactEntity.getFirstname())
//                .push();
//    public ContactEntity(String id, String firstname, String lastname, String image, String position, String company) {
        //  ContactEntity contactEntity = new ContactEntity(id, firstName, lastName, image, position, company);
//        mFriendRegDatabase.setValue(contactEntity)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(getApplicationContext(), "browse request sent", Toast.LENGTH_LONG).show();
//                    }
//                });
        /*   friendsDatabaseReferences = FirebaseDatabase.getInstance()
                .getReference()
                .child("Users")
                .child(userId)
                .child("friends")
                .child(firstName);
        friendsDatabaseReferences.setValue(friendRequestEntity)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        deleteFriendRequest(firstName);
                        displayMessage("Friend request accepted");
                        onSuceed();
                    }
                });*/
//        mFriendRegDatabase=FirebaseDatabase.getInstance()
//                .getReference()
//                .child("Users")
//                .child(mCurrentUser.getUid())
//                .child("friendsRequest")
//                .child()

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
        final List<ContactEntity> contactEntityList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(firebaseUser.getUid());
        ContactEntity contactEntity = new ContactEntity();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Toast.makeText(getApplicationContext(), "loadDetail " + dataSnapshot.child("company").getValue().toString(), Toast.LENGTH_LONG).show();
//                ContactEntity contactEntity = new ContactEntity();
//                contactEntity.setCompany(dataSnapshot.child("company").getValue().toString());
//                contactEntity.setPosition(dataSnapshot.child("position").getValue().toString());
//                contactEntity.setImage(dataSnapshot.child("image").getValue().toString());
//                contactEntity.setFirstname(dataSnapshot.child("firstname").getValue().toString());
//                contactEntity.setLastname(dataSnapshot.child("lastname").getValue().toString());
                profileId = dataSnapshot.child("id").getValue().toString();
                profileFirstName = dataSnapshot.child("firstname").getValue().toString();
                profileLastName = dataSnapshot.child("lastname").getValue().toString();
                profileCompanyName = dataSnapshot.child("company").getValue().toString();
                profileImage = dataSnapshot.child("image").getValue().toString();
                profilePositionName = dataSnapshot.child("position").getValue().toString();
//                loadContactDetail(dataSnapshot.child("id").getValue().toString(),
//                        dataSnapshot.child("firstname").getValue().toString(),
//                        dataSnapshot.child("lastname").getValue().toString(),
//                        dataSnapshot.child("image").getValue().toString(),
//                        dataSnapshot.child("company").getValue().toString(),
//                        dataSnapshot.child("position").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    ContactEntity loadContactDetail(String id, String firstName, String lastname, String image, String company, String position) {
        return new ContactEntity(id, firstName, lastname, image, position, company);
    }
}
