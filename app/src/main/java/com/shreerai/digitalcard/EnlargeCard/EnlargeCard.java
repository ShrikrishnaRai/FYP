package com.shreerai.digitalcard.EnlargeCard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shreerai.digitalcard.R;

public class EnlargeCard extends AppCompatActivity {
    TextView frontName_v;
    TextView frontAddress_v;
    TextView frontPhone_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlarge_card);
        init();
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String address = intent.getExtras().getString("address");
        String phone = intent.getExtras().getString("phone");
        frontName_v.setText(name);
        frontAddress_v.setText(address);
        frontPhone_v.setText(phone);
    }

    void init() {
        frontName_v = findViewById(R.id.educationEnlargeFront_name);
        frontAddress_v = findViewById(R.id.educationEnlargeFront_address);
        frontPhone_v = findViewById(R.id.educationEnlargeFront_phone);
        frontPhone_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9805912765"));
                startActivity(intent);
            }
        });
    }
}
