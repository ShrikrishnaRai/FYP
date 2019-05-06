package com.shreerai.digitalcard.detailActivity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.shreerai.digitalcard.R;

public class DetailsActivity extends AppCompatActivity {

    TextInputEditText textInputEditText_fb;
    TextInputEditText textInputEditText_twitter;
    TextInputEditText textInputEditText_website;
    String facebookLink_V;
    String twitterLink_V;
    String websiteLink_V;
    Button saveButton_v;
    ImageView profileImage_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        saveButton_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebookLink_V = textInputEditText_fb.getText().toString();
                twitterLink_V = textInputEditText_twitter.getText().toString();
                websiteLink_V = textInputEditText_website.getText().toString();
            }
        });
        profileImage_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
