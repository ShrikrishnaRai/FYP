package com.shreerai.digitalcard.detailActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.MainActivity;
import com.shreerai.digitalcard.R;

import java.util.HashMap;

import static com.shreerai.digitalcard.signUp.PasswordActivity.uid_v;

public class DetailsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    TextInputEditText textInputEditText_fb;
    TextInputEditText textInputEditText_twitter;
    TextInputEditText textInputEditText_website;
    String facebookLink_V;
    String twitterLink_V;
    String websiteLink_V;
    Button saveButton_v;
    ImageView profileImage_v;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        saveButton_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebookLink_V = textInputEditText_fb.getText().toString();
                twitterLink_V = textInputEditText_twitter.getText().toString();
                websiteLink_V = textInputEditText_website.getText().toString();
                HashMap<String, String> detail = new HashMap<>();
                detail.put("facebook", facebookLink_V);
                detail.put("twitter", twitterLink_V);
                detail.put("website", websiteLink_V);
                try {
                    databaseReference.child(firebaseAuth.getUid()).child("Detail").setValue(detail);
                    Toast.makeText(getApplicationContext(), "Detail's Saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DetailsActivity.this, MainActivity.class));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        profileImage_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child("Users").child(uid_v).child("facebook").setValue(facebookLink_V).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Suceed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    void init() {
        textInputEditText_fb = findViewById(R.id.fb_input);
        textInputEditText_twitter = findViewById(R.id.twitter_input);
        textInputEditText_website = findViewById(R.id.website_input);
        profileImage_v = findViewById(R.id.profile_image);
        saveButton_v = findViewById(R.id.save);
    }
}
