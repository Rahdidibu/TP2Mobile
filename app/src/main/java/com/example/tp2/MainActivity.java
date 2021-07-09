package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final String F_USERNAME = "USERNAME";

    private EditText USERNAME_EDIT;
    private Button PLAY_BUTTON;
    private String USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        PLAY_BUTTON.setOnClickListener(v -> {
            USERNAME = USERNAME_EDIT.getText().toString().trim();
            // @TODO : Vérifier si le nom du joueur n'est pas vide

            Intent i = new Intent(getBaseContext(), MenuActivity.class);
            i.putExtra(F_USERNAME, USERNAME);
            startActivity(i);
        });
    }

    public void init() {
        USERNAME_EDIT = findViewById(R.id.MAIN_EDIT_USERNAME);
        PLAY_BUTTON = findViewById(R.id.MAIN_PLAY_BUTTON);
    }
}