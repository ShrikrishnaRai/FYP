package com.shreerai.digitalcard.Profile;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.images.ImageRequest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.Search.SearchActivity;


public class Profile extends Fragment {
    RelativeLayout frontProfile_v;
    RelativeLayout backProfile_v;
    TextView textViewName_v;
    TextView textViewDesignation_v;
    String current_userid_V;
    FloatingActionButton floatingActionButtonAdd_v;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabaseReference;
    String displayFirstName_V;
    String displayLastName_V;
    String designation_V;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        init(view);
        loadImage();
        floatingActionButtonAdd_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
                Toast.makeText(getActivity(), "Transferrring", Toast.LENGTH_SHORT).show();
            }
        });
        textViewName_v.setText(displayFirstName_V + " " + displayLastName_V);
        textViewDesignation_v.setText(designation_V);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    void init(View view) {
        frontProfile_v = view.findViewById(R.id.profile_cardImage);
        backProfile_v = view.findViewById(R.id.profile_cardImage_Background);
        floatingActionButtonAdd_v = view.findViewById(R.id.fab);
        textViewName_v = view.findViewById(R.id.display_name);
        textViewDesignation_v = view.findViewById(R.id.display_designation);
    }

    boolean loadImage() {
        current_userid_V = user.getUid();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(current_userid_V);
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.child("image").getValue().toString());
                displayFirstName_V = dataSnapshot.child("firstname").getValue().toString();
                displayLastName_V = dataSnapshot.child("lastname").getValue().toString();
                designation_V = dataSnapshot.child("position").getValue().toString();
                switch (value) {
                    case 1:
                        frontProfile_v.setBackgroundResource(R.mipmap.card_one);
                        backProfile_v.setBackgroundResource(R.mipmap.card_one);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return true;
    }

}
