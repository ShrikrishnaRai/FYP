package com.shreerai.digitalcard.signUp;

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
    CheckBox checkBoxTwo_v;
    CheckBox checkBoxThree_v;
    CheckBox checkBoxFour_v;
    CheckBox checkBoxFive_V;
    RelativeLayout relativeLayoutDesigner_v;
    public static Bitmap designer_V;
    public static int cardValue_V;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_selection);
        init();
        checkBox_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                relativeLayoutDesigner_v.setDrawingCacheEnabled(true);
//                relativeLayoutDesigner_v.buildDrawingCache();
//                designer_V = relativeLayoutDesigner_v.getDrawingCache();
                startActivity(new Intent(CardSelectionActivity.this, PasswordActivity.class));
                finish();
                cardValue_V = 1;
            }
        });
        checkBoxTwo_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CardSelectionActivity.this, PasswordActivity.class));
                finish();
                cardValue_V = 2;
            }
        });
        checkBoxThree_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CardSelectionActivity.this, PasswordActivity.class));
                finish();
                cardValue_V = 3;
            }
        });
        checkBoxFour_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CardSelectionActivity.this, PasswordActivity.class));
                finish();
                cardValue_V = 4;

            }
        });
        checkBoxFive_V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CardSelectionActivity.this, PasswordActivity.class));
                finish();
                cardValue_V = 5;

            }
        });
    }

    void init() {
        checkBox_v = findViewById(R.id.checkbox_one);
        checkBoxTwo_v = findViewById(R.id.checkbox_two);
        checkBoxThree_v = findViewById(R.id.checkbox_three);
        checkBoxFour_v = findViewById(R.id.checkbox_four);
        checkBoxFive_V = findViewById(R.id.checkbox_five);
        relativeLayoutDesigner_v = findViewById(R.id.designer);
    }
}
