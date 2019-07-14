package com.shreerai.digitalcard.detailActivity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shreerai.digitalcard.MainActivity;
import com.shreerai.digitalcard.R;

import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {

    private EditText facebookUrl;
    private EditText linkdenUrl;
    private EditText twitterUrl;
    private Button savebutton_v;
    private EditText phoneNumber;
    private EditText websiteUrl;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        savebutton_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> details = new HashMap<>();
                details.put("facebook", facebookUrl.getText().toString());
                details.put("linkden", linkdenUrl.getText().toString());
                details.put("twitter", twitterUrl.getText().toString());
                details.put("website", websiteUrl.getText().toString());
                details.put("phone", phoneNumber.getText().toString());
                try {
                    databaseReference.child(firebaseAuth.getUid()).child("Detail").setValue(details);
                    Toast.makeText(getApplicationContext(), "Detail's Saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DetailsActivity.this, MainActivity.class));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void init() {
        facebookUrl = findViewById(R.id.facebookurl);
        linkdenUrl = findViewById(R.id.linkdenUrl);
        twitterUrl = findViewById(R.id.twitterUrl);
        savebutton_v = findViewById(R.id.save);
        websiteUrl = findViewById(R.id.websiteUrl);
        phoneNumber = findViewById(R.id.phone_number);
    }
}
