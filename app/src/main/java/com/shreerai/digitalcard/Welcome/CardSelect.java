package com.shreerai.digitalcard.Welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.shreerai.digitalcard.R;

public class CardSelect extends Fragment {
    CheckBox checkBox_v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_cardselect, container, false);
        init(view);
        return view;
    }

    void init(View view) {
        checkBox_v = view.findViewById(R.id.checkbox);
        checkBox_v.setSelected(true);
        checkBox_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
