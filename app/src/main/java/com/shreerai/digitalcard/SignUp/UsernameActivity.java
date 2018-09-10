package com.shreerai.digitalcard.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.SignUp.SignUpDto.SignUpDto;

import java.util.ArrayList;

public class UsernameActivity extends AppCompatActivity {
    Button save;
    EditText firstName_v;
    EditText lastName_v;
    public static String FirstName_V;
    public static String LastName_V;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        init();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstName_V = firstName_v.getText().toString();
                LastName_V = lastName_v.getText().toString();
                if (FirstName_V.isEmpty()) {
                    firstName_v.setError("FirstName required");
                    firstName_v.requestFocus();
                    return;
                }
                if (LastName_V.isEmpty()) {
                    lastName_v.setError("LastName required");
                    lastName_v.requestFocus();
                    return;
                }

                startActivity(new Intent(UsernameActivity.this, DetailActivity.class));
                finish();
            }
        });
    }

    void init() {
        save = findViewById(R.id.save_Button_name);
        firstName_v = findViewById(R.id.input_firstName);
        lastName_v = findViewById(R.id.input_LastName);
    }
}
