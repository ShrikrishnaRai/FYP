package com.shreerai.digitalcard.Welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.shreerai.digitalcard.R;

public class Login extends Fragment {
    private EditText emailId_v;
    private EditText password_v;
    private Button login_v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        init(view);
        login_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    void init(View view) {
        emailId_v = view.findViewById(R.id.userId);
        password_v = view.findViewById(R.id.password);
        login_v = view.findViewById(R.id.login_button);
    }
}
