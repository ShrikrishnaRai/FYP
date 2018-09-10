package com.shreerai.digitalcard.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shreerai.digitalcard.R;

public class Profile extends Fragment {
    ImageView frontProfile_v;
    ImageView backProfile_v;
    String value = "https://firebasestorage.googleapis.com/v0/b/digital-card-ec3bd.appspot.com/o/DigitalCard%2Fkatsy-garcia.jpg?alt=media&token=c3e4e160-261d-40a7-a746-510d3f81f745";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        init(view);
        Glide.with(getActivity()).load(value)
                .into(frontProfile_v);
        return view;
    }

    void init(View view) {
        frontProfile_v = view.findViewById(R.id.profile_cardImage);
        backProfile_v = view.findViewById(R.id.profile_cardImage_Background);
    }
}
