package com.github.improvtrainer.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.improvtrainer.model.CandidateNote;
import com.github.improvtrainer.event.CandidateNotesListener;
import com.github.improvtrainer.model.NoteFit;

import java.util.Set;

public class GuitarView extends View implements CandidateNotesListener {

    private final int BACKGROUND_COLOR = Color.WHITE;
    private final int FRETBOARD_COLOR = Color.GRAY;
    private final int FRET_COLOR = Color.BLACK;
    private final int STRING_COLOR = Color.DKGRAY;
    private final int ROOT_COLOR = Color.RED;
    private final int STRONG_COLOR = Color.MAGENTA;
    private NoteFit[][] notes;

    public GuitarView(Context context) {
        super(context);
    }

    public GuitarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GuitarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GuitarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void onCandidateNotesChange(Set<CandidateNote> candidateNotes) {
        notes = new NoteFit[23][6];
        // TODO map candidateNotes to NoteFit[][] notes
        invalidate();
    }

    @Override
    public void onUpcomingCandidateNotesChange(Set<CandidateNote> candidateNotes) {

    }

    @Override
    public void onDraw(Canvas canvas) {
        float viewWidth = (float)getMeasuredWidth();
        float viewHeight = (float)getMeasuredHeight();
        float fretWidth = viewWidth / 25f;
        float fretHeight = viewHeight / 7f;

        drawStaticImage(viewWidth, viewHeight, fretWidth, fretHeight, canvas);
        drawNotes(viewWidth, viewHeight, fretWidth, fretHeight, canvas);
    }

    private void drawStaticImage(float viewWidth, float viewHeight, float fretWidth, float fretHeight, Canvas canvas) {
        drawBackground(viewWidth, viewHeight, canvas);
        drawFretboard(viewWidth, viewHeight, fretWidth, fretHeight, canvas);
        drawFrets(viewHeight, fretWidth, fretHeight, canvas);
        drawStrings(viewWidth, viewHeight, fretWidth, fretHeight, canvas);
        drawFretMarkers(viewHeight, fretWidth, fretHeight, canvas);
    }

    private void drawBackground(float viewWidth, float viewHeight, Canvas canvas) {
        Paint background = new Paint();
        background.setColor(BACKGROUND_COLOR);
        canvas.drawRect(0, 0, viewWidth, viewHeight, background);
    }

    private void drawFretboard(float viewWidth, float viewHeight, float fretWidth, float fretHeight, Canvas canvas) {
        Paint fretBoard = new Paint();
        fretBoard.setColor(FRETBOARD_COLOR);
        canvas.drawRect(2 * fretWidth, fretHeight, viewWidth - fretWidth, viewHeight - fretHeight, fretBoard);
    }

    private void drawFrets(float viewHeight, float fretWidth, float fretHeight, Canvas canvas) {
        Paint fretPaint = new Paint();
        fretPaint.setColor(FRET_COLOR);
        float fretX = 2 * fretWidth;

        for (int i = 0; i < 23; i++) {
            canvas.drawLine(fretX, fretHeight, fretX, viewHeight - fretHeight, fretPaint);
            fretX += fretWidth;
        }
    }

    private void drawStrings(float viewWidth, float viewHeight, float fretWidth, float fretHeight, Canvas canvas) {
        Paint stringPaint = new Paint();
        stringPaint.setColor(STRING_COLOR);
        float stringHeight = fretHeight;
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(2 * fretWidth, stringHeight, viewWidth - fretWidth, stringHeight, stringPaint);
            stringHeight += fretHeight;
        }
    }

    private void drawFretMarkers(float viewHeight, float fretWidth, float fretHeight, Canvas canvas) {
        Paint fretPaint = new Paint();
        fretPaint.setColor(STRING_COLOR);

        // Frets 3, 5, 7, 9
        canvas.drawCircle(fretWidth * 4.5f, viewHeight / 2, 8, fretPaint);
        canvas.drawCircle(fretWidth * 6.5f, viewHeight / 2, 8, fretPaint);
        canvas.drawCircle(fretWidth * 8.5f, viewHeight / 2, 8, fretPaint);
        canvas.drawCircle(fretWidth * 10.5f, viewHeight / 2, 8, fretPaint);

        // Fret 12
        canvas.drawCircle(fretWidth * 13.5f, fretHeight * 2.5f, 8, fretPaint);
        canvas.drawCircle(fretWidth * 13.5f, fretHeight * 4.5f, 8, fretPaint);

        // Fret 15, 17, 19
        canvas.drawCircle(fretWidth * 16.5f, viewHeight / 2, 8, fretPaint);
        canvas.drawCircle(fretWidth * 18.5f, viewHeight / 2, 8, fretPaint);
        canvas.drawCircle(fretWidth * 20.5f, viewHeight / 2, 8, fretPaint);
    }

    private void drawNotes(float viewWidth, float viewHeight, float fretWidth, float fretHeight, Canvas canvas) {
        // TODO iterate NoteFit[][] notes and draw circles in the right color
    }
}
