package com.shreerai.digitalcard.EnlargeContact;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.shreerai.digitalcard.R;

import static com.shreerai.digitalcard.MainActivity.FACEBOOK_PAGE_ID;
import static com.shreerai.digitalcard.MainActivity.FACEBOOK_URL;

public class EnlargeCardContact extends AppCompatActivity {
    TextView frontName_v;
    TextView frontAddress_v;
    TextView frontPhone_v;
    TextView facebookLink_v;
    TextView twitterLink_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlarge_card_contact);
        init();
        Intent intent = getIntent();
        String firstname = intent.getExtras().getString("firstname");
        String lastname = intent.getExtras().getString("lastname");
        String position = intent.getExtras().getString("position");
        String phone = intent.getExtras().getString("phone");
        frontName_v.setText(firstname + "" + lastname);
        frontAddress_v.setText(position);
    }

    void init() {
        frontName_v = findViewById(R.id.educationEnlargeFront_name);
        frontAddress_v = findViewById(R.id.educationEnlargeFront_address);
        frontPhone_v = findViewById(R.id.educationEnlargeFront_phone);
        facebookLink_v = findViewById(R.id.facebookLink);
        twitterLink_v = findViewById(R.id.twitterLink);
        SpannableString content = new SpannableString("Facebook Link ?");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        facebookLink_v.setText(content);
        SpannableString twitter_content = new SpannableString("Twitter Link ?");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        twitterLink_v.setText(twitter_content);
        frontPhone_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9805912765"));
                startActivity(intent);
            }
        });
        facebookLink_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getApplicationContext());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
    }

    public String getFacebookPageURL(Context context) {
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
}
