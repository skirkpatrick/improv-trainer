package com.github.improvtrainer.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.improvtrainer.model.piano.Key;

import java.util.List;

public class PianoView extends View implements PianoModelListener {
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
    public void updateKeyState(List<Integer> indices, Key.State state) {

    }

    @Override
    public void clearAllKeys() {

    }
}
