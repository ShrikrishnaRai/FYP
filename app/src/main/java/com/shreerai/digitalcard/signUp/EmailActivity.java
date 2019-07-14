package com.shreerai.digitalcard.signUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shreerai.digitalcard.R;

public class EmailActivity extends AppCompatActivity {
    Button save_v;
    EditText email_v;
    public static String Email_V;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        init();
        save_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email_V = email_v.getText().toString();
                if (Email_V.isEmpty()) {
                    email_v.setError("Email required");
                    email_v.requestFocus();
                    return;
                }
                startActivity(new Intent(EmailActivity.this, CardSelectionActivity.class));
                finish();
            }
        });
    }

    void init() {
        save_v = findViewById(R.id.save_Button_email);
        email_v = findViewById(R.id.input_email);
    }
}
