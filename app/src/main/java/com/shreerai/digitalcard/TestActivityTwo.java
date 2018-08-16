package com.shreerai.digitalcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TestActivityTwo extends AppCompatActivity {

    TextView textView;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_two);
        init();
        fillData();
    }

    void init() {
        textView = findViewById(R.id.name);
    }

    void fillData() {
        String name = user.getEmail();
        textView.setText(name);
    }
}
