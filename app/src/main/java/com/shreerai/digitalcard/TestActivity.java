package com.shreerai.digitalcard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TestActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    Button signUp;
    private FirebaseAuth mAuth;
    String username_value;
    String password_values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
    }

    void init() {
        username = findViewById(R.id.username_test);
        password = findViewById(R.id.password_test);
        login = findViewById(R.id.login_button_test);
        signUp = findViewById(R.id.sigin_test);
    }

    void register() {
        username_value = username.getText().toString().trim();
        password_values = password.getText().toString().trim();
        if (username_value.isEmpty()) {
            username.setError("Email is required");
            username.requestFocus();
            return;
        }
        if (password_values.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username_value).matches()) {
            username.setError("Email pattern didn't matched");
            username.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(username_value, password_values)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User registered sucessfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "User registered failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    void signup() {
        username_value = username.getText().toString().trim();
        password_values = password.getText().toString().trim();
        if (username_value.isEmpty()) {
            username.setError("Email is required");
            username.requestFocus();
            return;
        }
        if (password_values.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username_value).matches()) {
            username.setError("Email pattern didn't matched");
            username.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(username_value, password_values)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Sigin Sucessfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TestActivity.this, TestActivityTwo.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Sigin unsucessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
