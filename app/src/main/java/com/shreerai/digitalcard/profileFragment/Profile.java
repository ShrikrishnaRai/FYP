package com.shreerai.digitalcard.profileFragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.activitySearch.SearchActivity;

import static com.shreerai.digitalcard.MainActivity.FACEBOOK_PAGE_ID;
import static com.shreerai.digitalcard.MainActivity.FACEBOOK_URL;


public class Profile extends Fragment {
    RelativeLayout frontProfile_v;
    RelativeLayout backProfile_v;
    TextView textViewName_v;
    TextView textViewDesignation_v;
    TextView textViewOrganization_v;
    String current_userid_V;
    ImageView facebookLink_v;
    ImageView twitterLink_v;
    ImageView phone_v;
    TextView phoneNumber_v;
    FloatingActionButton floatingActionButtonAdd_v;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabaseReference;
    String displayFirstName_V;
    String displayLastName_V;
    String designation_V;
    String company_V;
    String phone_V;

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
            }
        });
        textViewName_v.setText(displayFirstName_V + " " + displayLastName_V);
        textViewDesignation_v.setText(designation_V);
        textViewOrganization_v.setText(company_V);
        SpannableString content = new SpannableString("Facebook Link ");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        facebookLink_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getContext());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
        phone_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialPad();
            }
        });
        phoneNumber_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialPad();
            }
        });
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
        facebookLink_v = view.findViewById(R.id.facebookLink);
        twitterLink_v = view.findViewById(R.id.twitterLink);
        textViewOrganization_v = view.findViewById(R.id.display_organization);
        phone_v = view.findViewById(R.id.phone);
        phoneNumber_v = view.findViewById(R.id.phone_number);
    }

    /**
     * load detail's from firebase on init
     *
     * @return
     */
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
                company_V = dataSnapshot.child("company").getValue().toString();
                switch (value) {
                    case 1:
                        frontProfile_v.setBackgroundResource(R.mipmap.card_one);
                        backProfile_v.setBackgroundResource(R.mipmap.card_back);
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

    String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else {
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }

    private void openDialPad() {

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber_v.getText().toString()));
        getActivity().startActivity(intent);
    }

}
