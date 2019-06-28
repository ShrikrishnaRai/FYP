package com.shreerai.digitalcard.activityUserProfile;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.contacts.model.ContactEntity;

import java.util.ArrayList;
import java.util.List;

public class UserProfileActivity extends AppCompatActivity {


    private TextView nameTextView;
    private TextView emailTextView;
    private TextView companyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //contactEntity = new ContactEntity();
        initView();
        //  loadDetail();
    }

    void initView() {
        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.email);
        companyTextView = findViewById(R.id.company);
    }


}
