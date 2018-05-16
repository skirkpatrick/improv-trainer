package com.github.improvtrainer.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.github.improvtrainer.model.piano.Key;
import com.github.improvtrainer.model.piano.Piano;

import java.util.Collection;
import java.util.List;

public class PianoView extends ViewGroup implements PianoModelListener {
    private static final float BLACK_KEY_WIDTH_SCALING_FACTOR = 0.5f;
    private static final float BLACK_KEY_SCALING_FACTOR = 0.6f;
    private static final Paint WHITE_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);

    Piano pianoModel;
    List<Key> keys;

    public PianoView(Context context) {
        super(context);
    }

    public PianoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PianoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PianoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    public void updateKeyState(List<Integer> indices, Key.State state) {

    }

    @Override
    public void clearAllKeys() {

    }

    public void setModel(Piano pianoModel) {
        this.pianoModel = pianoModel;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        init();
        drawBorder();

        if (pianoModel != null) {
            layoutKeys(canvas);
        }

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), WHITE_PAINT);
        View v = new KeyView(this.getContext());
        this.addView(v);
    }

    private void init() {
        WHITE_PAINT.setColor(0xFFFFFF);
        WHITE_PAINT.setAlpha(255);
    }

    private void drawBorder() {

    }

    private void layoutKeys(Canvas canvas) {
        for (int i = 0; i < 48; i++) {
            keys.add(new Key(i));
        }

        keys = pianoModel.getKeys();
        int noteNumber = pianoModel.getStartingNote();

        float currentXStart = 0;
        final float whiteKeyWidth = canvas.getWidth() / countWhiteKeys(keys);
        final float blackKeyWidth = BLACK_KEY_WIDTH_SCALING_FACTOR * whiteKeyWidth;
        final float keyTop = 0;
        final float whiteKeyBottom = canvas.getHeight();
        final float blackKeyBottom = canvas.getHeight() * BLACK_KEY_SCALING_FACTOR;

        float xStart;
        float xEnd;
        float yStart;
        float yEnd;

        for (Key key : keys) {
            int note = key.getNote();
            if (key.getKeyColor() == Key.KeyColor.WHITE) {
                xStart = currentXStart;
                xEnd = currentXStart + whiteKeyWidth;
                yStart = keyTop;
                yEnd = whiteKeyBottom;
                currentXStart++; //Increment starting position for next key because this key is white
            } else if (key.getKeyColor() == Key.KeyColor.BLACK) {
                xStart = currentXStart + whiteKeyWidth - blackKeyWidth/2;
                xEnd = currentXStart + whiteKeyWidth + blackKeyWidth/2;
                yStart = keyTop;
                yEnd = blackKeyBottom;
                //Don't increment starting position for next key because this key is black
            }

            //draw key and store reference to it

        }
    }

    private int countWhiteKeys(Collection<Key> keys) {
        int count = 0;

        for (Key key : keys) {
            if (key.getKeyColor() == Key.KeyColor.WHITE) {
                count++;
            }
        }

        return count;
    }
}
