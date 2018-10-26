package com.shreerai.digitalcard.FriendRequest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.Contacts.ContactAdapter.ContactAdapter;
import com.shreerai.digitalcard.FriendRequest.Adapter.FriendRequestAdapter;
import com.shreerai.digitalcard.FriendRequest.Dto.FriendRequestDto;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.Search.Dto.SearchDto;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import static com.shreerai.digitalcard.AddCard.AddCardActivity.id;

public class FriendRequestActivity extends AppCompatActivity {
    RecyclerView friendRequestRecycler_v;
    private DatabaseReference mFriendReqDatabase;
    private DatabaseReference mReceivedFriendReqDatabase;
    private DatabaseReference mFriends;
    private DatabaseReference mUsers;
    private FirebaseUser mCurrentUser;
    FriendRequestAdapter friendRequestAdapter;
    String value;
    String userId;
    private ArrayList<SearchDto> searchDtoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        searchDtoArrayList = new ArrayList<>();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mFriendReqDatabase = FirebaseDatabase.getInstance().getReference().child("Friend_request");
        mFriends = FirebaseDatabase.getInstance().getReference().child("Friends");
        userId = mCurrentUser.getUid();
        init();
        loadCheckData();
    }

    void init() {
        friendRequestRecycler_v = findViewById(R.id.friendRequest);
        LinearLayoutManager linearLayoutManager_users = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        friendRequestRecycler_v.setLayoutManager(linearLayoutManager_users);
        friendRequestRecycler_v.setHasFixedSize(true);
        friendRequestRecycler_v.setAdapter(friendRequestAdapter);
    }

    void loadData() {
        mFriendReqDatabase.child(mCurrentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    mReceivedFriendReqDatabase = FirebaseDatabase.getInstance().getReference().child("Friend_request").child(mCurrentUser.getUid());
                    mReceivedFriendReqDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    value = dataSnapshot.child("received").getValue().toString();
                    final String currentDate_v = DateFormat.getDateTimeInstance().format(new Date());

                    mFriends.child(mCurrentUser.getUid()).child("value").setValue(value).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mFriends.child(value).child("value").setValue(mCurrentUser.getUid()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            });
                        }
                    });
                    mFriends.child(mCurrentUser.getUid()).child(value).setValue(currentDate_v).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mFriends.child(value).child(mCurrentUser.getUid()).setValue(currentDate_v).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    mFriendReqDatabase.child(mCurrentUser.getUid()).child("received").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            mFriendReqDatabase.child(value).child("sent").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getApplicationContext(), "Request Accepted", Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "You don't have any request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void loadCheckData() {
        mFriendReqDatabase.child(mCurrentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("received")) {
                    mReceivedFriendReqDatabase = FirebaseDatabase.getInstance().getReference().child("Friend_request").child(mCurrentUser.getUid()).child("received");
                    mReceivedFriendReqDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {
                                mUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(dataSnapshot.getValue().toString());
                                mUsers.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        SearchDto searchDto = new SearchDto();
                                        searchDto.setFirstname(dataSnapshot.child("firstname").getValue(String.class));
                                        searchDto.setLastname(dataSnapshot.child("lastname").getValue(String.class));
                                        searchDto.setPosition(dataSnapshot.child("position").getValue(String.class));
                                        searchDtoArrayList.add(searchDto);
                                        friendRequestAdapter = new FriendRequestAdapter(searchDtoArrayList, getApplicationContext());
                                        friendRequestRecycler_v.setAdapter(friendRequestAdapter);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        loadData();
    }
}
