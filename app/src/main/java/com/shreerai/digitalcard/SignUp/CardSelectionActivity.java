package com.shreerai.digitalcard.SignUp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.shreerai.digitalcard.R;

public class CardSelectionActivity extends AppCompatActivity {

    CheckBox checkBox_v;
    RelativeLayout relativeLayoutDesigner_v;
    public static Bitmap designer_V;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_selection);
        init();
        checkBox_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayoutDesigner_v.setDrawingCacheEnabled(true);
                relativeLayoutDesigner_v.buildDrawingCache();
                designer_V = relativeLayoutDesigner_v.getDrawingCache();
                startActivity(new Intent(CardSelectionActivity.this, PasswordActivity.class));
                finish();
            }
        });

    }

    void init() {
        checkBox_v = findViewById(R.id.checkbox);
        relativeLayoutDesigner_v = findViewById(R.id.designer);
    }
}
