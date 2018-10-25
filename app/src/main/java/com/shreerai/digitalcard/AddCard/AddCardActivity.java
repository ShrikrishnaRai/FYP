package com.shreerai.digitalcard.AddCard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddCardActivity extends AppCompatActivity {
    TextView cardName_v;
    TextView cardPosition_v;
    Button browse_v;
    private DatabaseReference mFriendRegDatabase;
    private DatabaseReference mFriends;
    private FirebaseUser mCurrentUser;
    private String currentState_V;
    public static String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        init();
        currentState_V = "not_friends";
        mFriendRegDatabase = FirebaseDatabase.getInstance().getReference().child("Friend_request");
        mFriends = FirebaseDatabase.getInstance().getReference().child("Friends");
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        Intent i = getIntent();
        String firstname = i.getExtras().getString("firstname");
        String lastname = i.getExtras().getString("lastname");
        String position = i.getExtras().getString("position");
        id = i.getExtras().getString("id");
        cardName_v.setText(firstname + " " + lastname);
        cardPosition_v.setText(position);
//        mFriendRegDatabase.child(mCurrentUser.getUid()).child("received").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.hasChild(id)) {
//                    String req_type = dataSnapshot.child(id).child("request_type").getValue().toString();
//                    if (req_type.equals("received")) {
//                        currentState_V = "req_received";
//                        browse_v.setText("Allow Request");
//                    } else if (req_type.equals("sent")) {
//                        currentState_V = "req_sent";
//                        browse_v.setText("Deny Request");
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        String value = mFriendRegDatabase.child(mCurrentUser.getUid()).child("recevied").getKey();
        Toast.makeText(getApplicationContext(), "" + value, Toast.LENGTH_LONG).show();
        browse_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browse_v.setEnabled(false);
                if (currentState_V.equals("not_friends")) {
//                    mFriendRegDatabase.child(mCurrentUser.getUid()).child(id).child("request_type")
//                            .setValue("sent").addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                mFriendRegDatabase.child(id).child(mCurrentUser.getUid()).child("request_type")
//                                        .setValue("received").addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        Toast.makeText(getApplicationContext(), "Browse Request Send", Toast.LENGTH_LONG).show();
//                                        currentState_V = "request_sent";
//                                        browse_v.setEnabled(true);
//                                        browse_v.setText("Cancel Friend Request");
//                                    }
//                                });
//
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
                    mFriendRegDatabase.child(mCurrentUser.getUid()).child("sent").setValue(id).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                mFriendRegDatabase.child(id).child("received").setValue(mCurrentUser.getUid()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(), "Browse Request Sent", Toast.LENGTH_LONG).show();
                                        currentState_V = "request_sent";
                                        browse_v.setEnabled(true);
                                        browse_v.setText("Cancel Browse Request");
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

//                if (currentState_V.equals("request_sent")) {
//                    mFriendRegDatabase.child(mCurrentUser.getUid()).child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            mFriendRegDatabase.child(id).child(mCurrentUser.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    browse_v.setEnabled(true);
//                                    currentState_V = "not_friends";
//                                    browse_v.setText("Browse");
//                                }
//                            });
//                        }
//                    });
//                }
                if (currentState_V.equals("request_sent")) {
                    mFriendRegDatabase.child(mCurrentUser.getUid()).child("sent").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mFriendRegDatabase.child(id).child("recieved").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    browse_v.setEnabled(true);
                                    currentState_V = "not_friends";
                                    browse_v.setText("Browse");
                                }
                            });
                        }
                    });
                }
                if (currentState_V.equals("req_received")) {

                }
            }
        });
    }

    void init() {
        cardName_v = findViewById(R.id.addCardName);
        cardPosition_v = findViewById(R.id.addCardPosition);
        browse_v = findViewById(R.id.addBrowse);
    }
}
