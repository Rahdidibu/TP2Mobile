package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class ScoreActivity extends AppCompatActivity {

    final String F_USERNAME = "USERNAME";
    final String F_WIN_OR_LOOSE = "WIN_OR_LOOSE";

    private Button BACK_BUTTON;
    private String USERNAME;
    protected ImageView IMAGE;
    protected String WIN_OR_LOOSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        init();

        BACK_BUTTON.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreActivity.this, MenuActivity.class);
            intent.putExtra(F_USERNAME, USERNAME);
            startActivity(intent);
        });
    }

    public void onBackPressed() {
        helper_menu();
    }

    private void init() {
        EditText TEXT_USERNAME = findViewById(R.id.SCORE_TEXT_USERNAME);
        USERNAME = getIntent().getStringExtra(F_USERNAME);
        String CONCAT_STR = getResources().getString(R.string.joueur) + " : " + USERNAME;
        TEXT_USERNAME.setText(CONCAT_STR);

        BACK_BUTTON = findViewById(R.id.SCORE_RETOUR_MENU);
        IMAGE = findViewById(R.id.SCORE_IMAGE);

        WIN_OR_LOOSE = getIntent().getStringExtra(F_WIN_OR_LOOSE);
        if (WIN_OR_LOOSE.equals(getResources().getString(R.string.win))) {
            IMAGE.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.win, null));
        } else {
            IMAGE.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.loose, null));
        }
    }

    private void helper_menu() {
        Intent intent = new Intent(ScoreActivity.this, MenuActivity.class);
        intent.putExtra(F_USERNAME, USERNAME);
        startActivity(intent);
    }
}