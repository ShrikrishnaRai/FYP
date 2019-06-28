package com.shreerai.digitalcard.activityFriendRequest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

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
import com.shreerai.digitalcard.activityFriendRequest.adapter.FriendRequestAdapter;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.activityFriendRequest.model.FriendRequestEntity;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestActivity extends AppCompatActivity implements FriendRequest {
    ImageView noRequest_imageView;
    RecyclerView friendRequestRecycler_v;
    private DatabaseReference mFriendReqDatabase;
    private DatabaseReference friendsDatabaseReferences;
    private FirebaseUser mCurrentUser;
    String userId;
    private List<FriendRequestEntity> friendRequestEntityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        friendRequestEntityList = new ArrayList<>();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = mCurrentUser.getUid();
        init();
        checkFriendRequest();
    }

    /**
     * Check available friends requests
     */
    void checkFriendRequest() {
        mFriendReqDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(userId)
                .child("friendsRequest");
        mFriendReqDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        FriendRequestEntity friendRequestEntity = dataSnapshot1.getValue(FriendRequestEntity.class);
                        friendRequestEntityList.add(friendRequestEntity);
                    }
                    loadFriendRequestItem(friendRequestEntityList);
                } else {
                    noRequest_imageView.setVisibility(View.VISIBLE);
                    Snackbar.make(friendRequestRecycler_v, "You don't have any friend request", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    void init() {
        noRequest_imageView = findViewById(R.id.no_request_image);
        friendRequestRecycler_v = findViewById(R.id.friendRequest);

    }

    void loadFriendRequestItem(List<FriendRequestEntity> friendRequestEntityList) {
        FriendRequestAdapter friendRequestAdapter = new FriendRequestAdapter(friendRequestEntityList, getApplicationContext(), this);
        LinearLayoutManager linearLayoutManager_users = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        friendRequestRecycler_v.setLayoutManager(linearLayoutManager_users);
        friendRequestRecycler_v.setHasFixedSize(true);
        friendRequestRecycler_v.setAdapter(friendRequestAdapter);
    }

    @Override
    public void acceptFriend(String id,
                             final String firstName,
                             String lastName,
                             String company,
                             String position,
                             String image) {
        FriendRequestEntity friendRequestEntity = new FriendRequestEntity(id, firstName, lastName, image, position, company);
        friendsDatabaseReferences = FirebaseDatabase.getInstance()
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
                });
    }

    @Override
    public void deleteFriendRequest(String firstName) {
        friendsDatabaseReferences = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(userId)
                .child("friendsRequest");
        friendsDatabaseReferences.child(firstName).removeValue();
        onSuceed();
    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(friendRequestRecycler_v, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onSuceed() {
        startActivity(new Intent(FriendRequestActivity.this, MainActivity.class));
    }


}
