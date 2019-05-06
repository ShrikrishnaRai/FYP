package com.shreerai.digitalcard.welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shreerai.digitalcard.R;

public class Login extends Fragment {
    private EditText emailId_v;
    private EditText password_v;
    private Button login_v;
    private String username_value;
    private String password_values;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        init(view);
        login_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        return view;
    }

    void init(View view) {
        emailId_v = view.findViewById(R.id.userId);
        password_v = view.findViewById(R.id.password);
        login_v = view.findViewById(R.id.login_button);
    }

    void signup() {
        username_value = emailId_v.getText().toString().trim();
        password_values = password_v.getText().toString().trim();
        if (username_value.isEmpty()) {
            emailId_v.setError("Email is required");
            emailId_v.requestFocus();
            return;
        }
        if (password_values.isEmpty()) {
            password_v.setError("Password is required");
            password_v.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username_value).matches()) {
            emailId_v.setError("Email pattern didn't matched");
            emailId_v.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword("cri_rai@outlook.com", "kathmandu24")
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Signin Sucessfull", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "SignIn unsucessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
