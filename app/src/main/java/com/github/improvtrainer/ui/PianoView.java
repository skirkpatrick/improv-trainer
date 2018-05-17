package com.github.improvtrainer.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.improvtrainer.model.CandidateNote;
import com.github.improvtrainer.event.CandidateNotesListener;
import com.github.improvtrainer.model.NoteFit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PianoView extends View implements CandidateNotesListener {
    private static final int NUMBER_OF_KEYS = 48;
    private static final int WHITE_KEYS_PER_OCTAVE = 7;
    private static final int KEYS_PER_OCTAVE = 12;
    private static final float BLACK_KEY_WIDTH_SCALING_FACTOR = 0.5f;
    private static final float BLACK_KEY_SCALING_FACTOR = 0.6f;
    private static final Paint WHITE_KEY_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final Paint BLACK_KEY_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final Paint WHITE_KEY_ROOT_HIGHLIGHT_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final Paint BLACK_KEY_ROOT_HIGHLIGHT_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final Paint WHITE_KEY_STRONG_HIGHLIGHT_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final Paint BLACK_KEY_STRONG_HIGHLIGHT_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final Paint BORDER_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final Set<Integer> BLACK_KEY_OFFSETS = new HashSet<>(Arrays.asList(1, 3, 6, 8, 10));

    private Set<Integer> rootNoteNumbers = new HashSet<>();
    private Set<Integer> strongNoteNumbers = new HashSet<>();

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
    protected void onDraw(Canvas canvas) {
        init();
        layoutKeys(canvas);
    }

    private void init() {
        WHITE_KEY_PAINT.setColor(0xFFFFFF);
        WHITE_KEY_PAINT.setAlpha(255);

        WHITE_KEY_ROOT_HIGHLIGHT_PAINT.setColor(0x24BB14); // todo change this
        WHITE_KEY_ROOT_HIGHLIGHT_PAINT.setAlpha(255);

        WHITE_KEY_STRONG_HIGHLIGHT_PAINT.setColor(0xF7DD00); // todo change this
        WHITE_KEY_STRONG_HIGHLIGHT_PAINT.setAlpha(255);

        BLACK_KEY_PAINT.setColor(0x000000);
        BLACK_KEY_PAINT.setAlpha(255);

        BLACK_KEY_ROOT_HIGHLIGHT_PAINT.setColor(0x24BB14); // todo change this
        BLACK_KEY_ROOT_HIGHLIGHT_PAINT.setAlpha(255);

        BLACK_KEY_STRONG_HIGHLIGHT_PAINT.setColor(0xF7DD00); // todo change this
        BLACK_KEY_STRONG_HIGHLIGHT_PAINT.setAlpha(255);

        BORDER_PAINT.setColor(0x999999);
        BORDER_PAINT.setAlpha(255);
        BORDER_PAINT.setStyle(Paint.Style.STROKE);
    }

    private void layoutKeys(Canvas canvas) {

        float currentXStart = 0;
        final float whiteKeyWidth = canvas.getWidth() / countWhiteKeys(NUMBER_OF_KEYS);
        final float blackKeyWidth = BLACK_KEY_WIDTH_SCALING_FACTOR * whiteKeyWidth;
        final float keyTop = 0;
        final float whiteKeyBottom = canvas.getHeight();
        final float blackKeyBottom = canvas.getHeight() * BLACK_KEY_SCALING_FACTOR;

        float xStart;
        float xEnd;
        float yStart;
        float yEnd;

        Paint currentColor;

        for (int i = 0; i < NUMBER_OF_KEYS; i++) {
            if (!BLACK_KEY_OFFSETS.contains(i % 12)) {
                // white keys
                xStart = currentXStart;
                xEnd = currentXStart + whiteKeyWidth;
                yStart = keyTop;
                yEnd = whiteKeyBottom;
                currentXStart += whiteKeyWidth; //Increment starting position for next key because this key is white
                if (rootNoteNumbers.contains(i % 12)) {
                    currentColor = WHITE_KEY_ROOT_HIGHLIGHT_PAINT;
                } else if (strongNoteNumbers.contains(i % 12)) {
                    currentColor = WHITE_KEY_STRONG_HIGHLIGHT_PAINT;
                } else {
                    currentColor = WHITE_KEY_PAINT;
                }

                //draw key and store reference to it
                canvas.drawRect(xStart, yStart, xEnd, yEnd, currentColor);
                canvas.drawRect(xStart, yStart, xEnd, yEnd, BORDER_PAINT);
            }

        }

        currentXStart = 0;

        for (int i = 0; i < NUMBER_OF_KEYS; i++) {
            if (BLACK_KEY_OFFSETS.contains(i % 12)) {
                // black keys
                xStart = currentXStart - blackKeyWidth / 2;
                xEnd = currentXStart + blackKeyWidth / 2;
                yStart = keyTop;
                yEnd = blackKeyBottom;
                if (rootNoteNumbers.contains(i % 12)) {
                    currentColor = BLACK_KEY_ROOT_HIGHLIGHT_PAINT;
                } else if (strongNoteNumbers.contains(i % 12)) {
                    currentColor = BLACK_KEY_STRONG_HIGHLIGHT_PAINT;
                } else {
                    currentColor = BLACK_KEY_PAINT;
                }
                //Don't increment starting position for next key because this key is black

                //draw key and store reference to it
                canvas.drawRect(xStart, yStart, xEnd, yEnd, currentColor);
                canvas.drawRect(xStart, yStart, xEnd, yEnd, BORDER_PAINT);
            } else {
                currentXStart += whiteKeyWidth;
            }
        }
    }

    private int countWhiteKeys(int numberOfKeys) {
        return numberOfKeys * WHITE_KEYS_PER_OCTAVE / KEYS_PER_OCTAVE;
    }

    @Override
    public void onCandidateNotesChange(Set<CandidateNote> candidateNotes) {
        rootNoteNumbers = new HashSet<>();
        strongNoteNumbers = new HashSet<>();
        for (CandidateNote candidateNote : candidateNotes) {
            if (candidateNote.getFit() == NoteFit.ROOT) {
                rootNoteNumbers.add(candidateNote.getBaseToneValue());
            } else if (candidateNote.getFit() == NoteFit.STRONG) {
                strongNoteNumbers.add(candidateNote.getBaseToneValue());
            }
        }
        this.invalidate();
    }

    @Override
    public void onUpcomingCandidateNotesChange(Set<CandidateNote> candidateNotes) {

    }
}
