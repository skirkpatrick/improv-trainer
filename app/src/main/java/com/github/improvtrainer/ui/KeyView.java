package com.github.improvtrainer.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class KeyView extends View {
    private static final Paint WHITE_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final Paint BLACK_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);

    public KeyView(Context context) {
        super(context);

        WHITE_PAINT.setColor(0xFFFFFF);
        WHITE_PAINT.setAlpha(255);

        BLACK_PAINT.setColor(0x000000);
        BLACK_PAINT.setAlpha(255);
    }

    protected void onDraw(Canvas canvas) {

    }
}
