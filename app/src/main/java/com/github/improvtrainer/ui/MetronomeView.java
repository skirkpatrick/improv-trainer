package com.github.improvtrainer.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MetronomeView extends View {

    private final Paint paint;

    public MetronomeView(Context context) {
        super(context);
        paint = getPaint();
    }

    public MetronomeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = getPaint();
    }

    public MetronomeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = getPaint();
    }

    public MetronomeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        paint = getPaint();
    }

    private static Paint getPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.CYAN);
        paint.setAntiAlias(true);
        return paint;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, canvas.getHeight()/2, paint);
    }

    public void flash() {
        this.post(new Runnable() {
            @Override
            public void run() {
                MetronomeView.this.setVisibility(View.VISIBLE);
            }
        });
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                MetronomeView.this.setVisibility(View.INVISIBLE);
            }
        }, 50);
    }
}
