package com.shreerai.digitalcard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.shreerai.digitalcard.FriendRequest.FriendRequestActivity;
import com.shreerai.digitalcard.SignUp.IntroActivity;

public class SignInActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    TextView forgetPassword_v;
    Button signUp;
    private FirebaseAuth mAuth;
    String username_value;
    String password_values;
    ProgressBar loginProgessbar_v;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        init();
        mAuth = FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, IntroActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    void init() {
        signUp = findViewById(R.id.Sign_Up_Button);
        email = findViewById(R.id.input_email_login);
        password = findViewById(R.id.input_password_login);
        loginProgessbar_v = findViewById(R.id.login_progress);
        loginProgessbar_v.setVisibility(View.GONE);
        login = findViewById(R.id.login_Button);
        forgetPassword_v = findViewById(R.id.forget_password_text);
        SpannableString content = new SpannableString("Forget Password ?");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        forgetPassword_v.setText(content);
    }


    void signIn() {
        loginProgessbar_v.setVisibility(View.VISIBLE);
        username_value = email.getText().toString().trim();
        password_values = password.getText().toString().trim();
        if (username_value.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            loginProgessbar_v.setVisibility(View.GONE);
            return;
        }
        if (password_values.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            loginProgessbar_v.setVisibility(View.GONE);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username_value).matches()) {
            email.setError("Email pattern didn't matched");
            email.requestFocus();
            loginProgessbar_v.setVisibility(View.GONE);
            return;
        }
        mAuth.signInWithEmailAndPassword(username_value, password_values)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginProgessbar_v.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "SignIn Sucessfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignInActivity.this, MainActivity.class));
                        } else {
                            loginProgessbar_v.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "SignIn Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
