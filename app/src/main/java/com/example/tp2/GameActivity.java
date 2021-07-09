package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    final String F_LEVEL = "LEVEL";
    final String F_USERNAME = "USERNAME";
    final String F_ROWS = "ROWS";
    final String F_COLS = "COLS";
    final String F_WIN_OR_LOOSE = "WIN_OR_LOOSE";
    private final int[] F_TIMER_SECONDES = {30, 60};

    //protected MediaPlayer MEDIA_PLAYER;

    private int NBR_CARTES;
    private CardGame[] TAB_CARTES;
    private int[] TAB_CARTES_PLACES;
    private int[] TAB_CARTES_GRAPHIQUES;
    private CardGame SELECT_CARTE_1;
    private CardGame SELECT_CARTE_2;
    private boolean OCCUPE = false;

    private int NBR_SECONDES;
    private EditText TEXT_TIMER;
    private String USERNAME;
    private CustomTimer TIMER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();
        init_timer(NBR_SECONDES + 1, TEXT_TIMER);

        GridLayout theGridLayout = findViewById(R.id.GAME_GRID_LAYOUT);
        int NBR_COLS = getIntent().getIntExtra(F_COLS, 2);
        int NBR_ROWS = getIntent().getIntExtra(F_ROWS, 3);
        this.NBR_CARTES = NBR_COLS * NBR_ROWS;
        this.TAB_CARTES = new CardGame[NBR_CARTES];
        this.TAB_CARTES_GRAPHIQUES = new int[NBR_CARTES / 2];
        if (NBR_ROWS == 3) {
            set_up_normal_game();
        } else {
            set_up_hardcore_game();
        }
        this.TAB_CARTES_PLACES = new int[NBR_CARTES];
        random_places();
        init_cartes(NBR_ROWS, NBR_COLS, theGridLayout);

        //MEDIA_PLAYER = MediaPlayer.create(getApplicationContext(), R.raw.game_song);
        //MEDIA_PLAYER.start();
    }

    private void init_timer(long NBR_SECONDES, EditText TEXT_TIMER) {
        TIMER = new CustomTimer(NBR_SECONDES * 1000, 1000, TEXT_TIMER, this);
        TIMER.start();
    }

    public void init_cartes(int NBR_ROWS, int NBR_COLS, GridLayout GRID_LAYOUT) {
        for (int ROW = 0; ROW < NBR_ROWS; ROW++) {
            for (int COL = 0; COL < NBR_COLS; COL++) {
                CardGame TEMP_CARD_GAME = new CardGame(this, ROW, COL, TAB_CARTES_GRAPHIQUES[TAB_CARTES_PLACES[ROW * NBR_COLS + COL]]);
                TEMP_CARD_GAME.setId(View.generateViewId());
                TEMP_CARD_GAME.setOnClickListener(this);
                TAB_CARTES[ROW * NBR_COLS + COL] = TEMP_CARD_GAME;
                GRID_LAYOUT.addView(TEMP_CARD_GAME);
            }
        }
    }

    protected void random_places() {
        Random rand = new Random();

        for (int i = 0; i < NBR_CARTES; i++) {
            this.TAB_CARTES_PLACES[i] = i % (NBR_CARTES / 2);
        }
        for (int i = 0; i < NBR_CARTES; i++) {
            int temp = this.TAB_CARTES_PLACES[i];
            int swapIndex = rand.nextInt(NBR_CARTES);
            TAB_CARTES_PLACES[i] = TAB_CARTES_PLACES[swapIndex];
            TAB_CARTES_PLACES[swapIndex] = temp;
        }
    }

    public void onBackPressed() {
        TIMER.cancel();
        helper_menu();
    }

    private void init() {
        EditText TEXT_USERNAME = findViewById(R.id.GAME_TEXT_USERNAME);
        USERNAME = getIntent().getStringExtra(F_USERNAME);
        String CONCAT_STR = getResources().getString(R.string.joueur) + " : " + USERNAME;
        TEXT_USERNAME.setText(CONCAT_STR);

        TEXT_TIMER = findViewById(R.id.GAME_TEXT_TIMER);
        int LEVEL = getIntent().getIntExtra(F_LEVEL, 0);

        NBR_SECONDES = F_TIMER_SECONDES[LEVEL];
    }


    public void set_up_normal_game() {
        this.TAB_CARTES_GRAPHIQUES[0] = R.drawable.img1;
        this.TAB_CARTES_GRAPHIQUES[1] = R.drawable.img2;
        this.TAB_CARTES_GRAPHIQUES[2] = R.drawable.img3;
    }

    public void set_up_hardcore_game() {
        this.TAB_CARTES_GRAPHIQUES[0] = R.drawable.img1;
        this.TAB_CARTES_GRAPHIQUES[1] = R.drawable.img2;
        this.TAB_CARTES_GRAPHIQUES[2] = R.drawable.img3;
        this.TAB_CARTES_GRAPHIQUES[3] = R.drawable.img4;
        this.TAB_CARTES_GRAPHIQUES[4] = R.drawable.img5;
        this.TAB_CARTES_GRAPHIQUES[5] = R.drawable.img6;
        this.TAB_CARTES_GRAPHIQUES[6] = R.drawable.img7;
        this.TAB_CARTES_GRAPHIQUES[7] = R.drawable.img8;
        this.TAB_CARTES_GRAPHIQUES[8] = R.drawable.img9;
        this.TAB_CARTES_GRAPHIQUES[9] = R.drawable.img10;
        this.TAB_CARTES_GRAPHIQUES[10] = R.drawable.img11;
        this.TAB_CARTES_GRAPHIQUES[11] = R.drawable.img12;
        this.TAB_CARTES_GRAPHIQUES[12] = R.drawable.img13;
        this.TAB_CARTES_GRAPHIQUES[13] = R.drawable.img14;
        this.TAB_CARTES_GRAPHIQUES[14] = R.drawable.img15;
        this.TAB_CARTES_GRAPHIQUES[15] = R.drawable.img16;
        this.TAB_CARTES_GRAPHIQUES[16] = R.drawable.img17;
        this.TAB_CARTES_GRAPHIQUES[17] = R.drawable.img18;
    }

    private boolean verification_fin_partie() {
        for (int i = 0; i < NBR_CARTES; i++) {
            if (TAB_CARTES[i].isEnabled()) {
                return false;
            }
        }
        return true;
    }

    private void helper_menu() {
        Intent intent = new Intent(GameActivity.this, MenuActivity.class);
        intent.putExtra(F_USERNAME, USERNAME);
        startActivity(intent);
    }

    public void timer_end() {
        //@TODO : Réussir a arreter la musique lors de la fin de la partie (cas partie perdue)
        Toast.makeText(GameActivity.this, getResources().getString(R.string.loose), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
        intent.putExtra(F_USERNAME, USERNAME);
        intent.putExtra(F_WIN_OR_LOOSE, getResources().getString(R.string.loose));
        intent.putExtra(F_LEVEL, F_LEVEL);
        startActivity(intent);
    }

    @Override
    public void onClick(View VIEW) {
        if (OCCUPE) {
            return;
        }
        CardGame CARTE = (CardGame) VIEW;
        if (CARTE.isMATCHED) {
            return;
        }
        if (SELECT_CARTE_1 == null) {
            SELECT_CARTE_1 = CARTE;
            SELECT_CARTE_1.flip();
            return;
        }
        if (SELECT_CARTE_1.getId() == CARTE.getId()) {
            return;
        }
        if (SELECT_CARTE_1.getFrontImageID() == CARTE.getFrontImageID()) {
            CARTE.flip();
            CARTE.setMatched(true);
            SELECT_CARTE_1.setMatched(true);

            SELECT_CARTE_1.setEnabled(false);
            CARTE.setEnabled(false);
            SELECT_CARTE_1 = null;
            if (verification_fin_partie()) {
                //@TODO : Réussir a arreter la musique lors de la fin de la partie (cas partie gagnante)
                GameActivity.this.runOnUiThread(() -> Toast.makeText(GameActivity.this, getResources().getString(R.string.win), Toast.LENGTH_LONG).show());
                Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
                intent.putExtra(F_USERNAME, USERNAME);
                intent.putExtra(F_WIN_OR_LOOSE, getResources().getString(R.string.win));
                startActivity(intent);
                TIMER.cancel();
            }

        } else {
            SELECT_CARTE_2 = CARTE;
            SELECT_CARTE_2.flip();
            OCCUPE = true;

            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                SELECT_CARTE_2.flip();
                SELECT_CARTE_1.flip();
                SELECT_CARTE_1 = null;
                SELECT_CARTE_2 = null;
                OCCUPE = false;
            }, 500);
        }
    }
}