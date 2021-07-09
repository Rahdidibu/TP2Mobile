package com.example.tp2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatButton;

@SuppressLint("ViewConstructor")
public class CardGame extends AppCompatButton {

    protected int ROW;
    protected int COL;
    protected int FRONT_IMAGE_ID;

    protected boolean isFLIPPED = false;
    protected boolean isMATCHED = false;

    protected Drawable FRONT;
    protected Drawable BACK;

    @SuppressLint("UseCompatLoadingForDrawables")
    public CardGame(Context CONTEXT, int ROW, int COL, int FRONT_IMAGE_ID) {
        super(CONTEXT);

        this.ROW = ROW;
        this.COL = COL;
        this.FRONT_IMAGE_ID = FRONT_IMAGE_ID;

        FRONT = CONTEXT.getDrawable(FRONT_IMAGE_ID);
        BACK = CONTEXT.getDrawable(R.drawable.img0);

        setBackground(BACK);

        GridLayout.LayoutParams PARAMS = new GridLayout.LayoutParams(GridLayout.spec(ROW), GridLayout.spec(COL));

        PARAMS.width = (int) getResources().getDisplayMetrics().density * 80;
        PARAMS.height = (int) getResources().getDisplayMetrics().density * 80;

        setLayoutParams(PARAMS);
    }

    public boolean isMatched() {
        return isMATCHED;
    }

    public void setMatched(boolean MATCHED) {
        isMATCHED = MATCHED;
    }

    public int getFrontImageID() {
        return FRONT_IMAGE_ID;
    }

    public void flip() {
        if (isMATCHED) {
            return;
        }

        if (isFLIPPED) {
            setBackground(BACK);
            isFLIPPED = false;
        } else {
            setBackground(FRONT);
            isFLIPPED = true;
        }
    }
}
