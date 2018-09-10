package com.shreerai.digitalcard.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shreerai.digitalcard.R;

public class DetailActivity extends AppCompatActivity {
    Button save_v;
    EditText position_v;
    EditText company_v;
    public static String Position_V;
    public static String Company_V;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        save_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Position_V = position_v.getText().toString();
                Company_V = company_v.getText().toString();

                if (Position_V.isEmpty()) {
                    position_v.setError("Designation required");
                    position_v.requestFocus();
                    return;
                }
                if (Company_V.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Self-Employed will be set as default", Toast.LENGTH_SHORT).show();
                    Company_V = "Self-Employed";
                } else {
                    Company_V = company_v.getText().toString();

                }
                startActivity(new Intent(DetailActivity.this, EmailActivity.class));
                finish();
            }
        });
    }

    void init() {
        save_v = findViewById(R.id.save_Button_detail);
        position_v = findViewById(R.id.input_position);
        company_v = findViewById(R.id.input_company);
    }
}
