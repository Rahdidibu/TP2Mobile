package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    final String F_USERNAME = "USERNAME";
    final String F_LEVEL = "LEVEL";
    final int F_LEVEL_NORMAL = 0;
    final int F_LEVEL_HARDCORE = 1;
    final String F_ROWS = "ROWS";
    final String F_COLS = "COLS";

    private Button BUTTON_3x2;
    private Button BUTTON_6x6;
    private Button BUTTON_CHANGER_JOUEUR;
    private EditText TEXT_USERNAME;
    private String USERNAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();

        BUTTON_3x2.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, GameActivity.class);
            intent.putExtra(F_USERNAME, USERNAME);
            intent.putExtra(F_LEVEL, F_LEVEL_NORMAL);
            intent.putExtra(F_ROWS, 3);
            intent.putExtra(F_COLS, 2);
            startActivity(intent);
        });

        BUTTON_6x6.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, GameActivity.class);
            intent.putExtra(F_USERNAME, USERNAME);
            intent.putExtra(F_LEVEL, F_LEVEL_HARDCORE);
            intent.putExtra(F_ROWS, 6);
            intent.putExtra(F_COLS, 6);
            startActivity(intent);
        });

        BUTTON_CHANGER_JOUEUR.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void init() {
        BUTTON_3x2 = findViewById(R.id.MENU_2x3_BUTTON);
        BUTTON_6x6 = findViewById(R.id.MENU_6x6_BUTTON);
        BUTTON_CHANGER_JOUEUR = findViewById(R.id.MENU_CHANGER_JOUEUR_BUTTON);
        TEXT_USERNAME = findViewById(R.id.MENU_TEXT_USERNAME);

        USERNAME = getIntent().getStringExtra(F_USERNAME);
        String CONCAT_STR = getResources().getString(R.string.joueur) + " : " + USERNAME;
        TEXT_USERNAME.setText(CONCAT_STR);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
