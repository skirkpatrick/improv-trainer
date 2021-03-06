package com.github.improvtrainer.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.improvtrainer.R;
import com.github.improvtrainer.model.CandidateNote;
import com.github.improvtrainer.event.CandidateNotesListener;
import com.github.improvtrainer.model.NoteFit;

import java.util.Set;

public class GuitarView extends View implements CandidateNotesListener {

    private static final int FRETBOARD_COLOR = Color.GRAY;
    private static final int FRET_COLOR = Color.BLACK;
    private static final int STRING_COLOR = Color.DKGRAY;
    private static final int ROOT_COLOR = 0xFF4444;
    private static final int STRONG_COLOR = 0x5194FF;
    private static final int UPCOMING_ROOT_COLOR = 0x882222;
    private static final int UPCOMING_STRONG_COLOR = 0x264688;
    // C=0, C#=1, D=2, D#=3, E=4, F=5, F#=6, G=7, G#=8, A=9, A#=10, B=11, C=12
    private static final int[] STRING_START_INDICES = new int[]{4, 9, 2, 7, 11, 4}; //EADGBE
    private static final float NOTE_MARKER_RADIUS = 16f;
    private NoteFit[][] notes;
    private NoteFit[][] upcomingNotes;

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
        notes = candidatesToFit(candidateNotes);
        invalidate();
    }

    @Override
    public void onUpcomingCandidateNotesChange(Set<CandidateNote> candidateNotes) {
        upcomingNotes = candidatesToFit(candidateNotes);
        invalidate();
    }

    private NoteFit[][] candidatesToFit(Set<CandidateNote> candidateNotes) {
        NoteFit[][] fits = new NoteFit[23][6];
        for (CandidateNote candidateNote : candidateNotes) {
            int toneValue = candidateNote.getBaseToneValue();
            for (int stringIndex = 0; stringIndex < 6; stringIndex++) {
                int stringStartingPitch = STRING_START_INDICES[stringIndex];
                int firstMatchingPitch = toneValue - stringStartingPitch;
                if (firstMatchingPitch < 0) {
                    firstMatchingPitch += 12;
                }
                int currentMatch = firstMatchingPitch;
                while (currentMatch < 23) {
                    fits[currentMatch][stringIndex] = candidateNote.getFit();
                    currentMatch += 12;
                }
            }
        }
        return fits;
    }

    @Override
    public void onDraw(Canvas canvas) {
        float viewWidth = (float)getMeasuredWidth();
        float viewHeight = (float)getMeasuredHeight();
        float fretWidth = viewWidth / 23f;
        float fretHeight = viewHeight / 7f;
        float padHeight = fretHeight / 2;

        drawStaticImage(viewWidth, viewHeight, fretWidth, fretHeight, padHeight, canvas);
        drawNotes(fretWidth, fretHeight, canvas);
    }

    private void drawStaticImage(float viewWidth, float viewHeight, float fretWidth, float fretHeight, float padHeight, Canvas canvas) {
        drawFretboard(viewWidth, viewHeight, fretWidth, padHeight, canvas);
        drawFrets(viewHeight, fretWidth, padHeight, canvas);
        drawStrings(viewWidth, fretWidth, fretHeight, padHeight, canvas);
        drawFretMarkers(viewHeight, fretWidth, fretHeight, canvas);
    }

    private void drawFretboard(float viewWidth, float viewHeight, float fretWidth, float padHeight, Canvas canvas) {
        Paint fretBoard = new Paint();
        fretBoard.setColor(FRETBOARD_COLOR);
        canvas.drawRect(fretWidth, padHeight, viewWidth, viewHeight - (3f * padHeight), fretBoard);
    }

    private void drawFrets(float viewHeight, float fretWidth, float padHeight, Canvas canvas) {
        Paint fretPaint = new Paint();
        fretPaint.setColor(FRET_COLOR);
        Paint numberPaint = new Paint();
        numberPaint.setColor(getResources().getColor(R.color.colorHint, null));
        numberPaint.setTextAlign(Paint.Align.CENTER);
        numberPaint.setTextSize(32);
        float fretX = fretWidth;

        for (int i = 0; i < 23; i++) {
            canvas.drawLine(fretX, padHeight, fretX, viewHeight - (2.5f * padHeight), fretPaint);
            canvas.drawText(Integer.toString(i), fretX - (0.5f * fretWidth), viewHeight - 15, numberPaint);
            fretX += fretWidth;
        }
    }

    private void drawStrings(float viewWidth, float fretWidth, float fretHeight, float padHeight, Canvas canvas) {
        Paint stringPaint = new Paint();
        stringPaint.setColor(STRING_COLOR);
        float stringHeight = padHeight;
        for (int i = 0; i < 6; i++) {
            canvas.drawLine(fretWidth , stringHeight, viewWidth, stringHeight, stringPaint);
            stringHeight += fretHeight;
        }
    }

    private void drawFretMarkers(float viewHeight, float fretWidth, float fretHeight, Canvas canvas) {
        Paint fretPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fretPaint.setColor(STRING_COLOR);

        // Frets 3, 5, 7, 9
        canvas.drawCircle(fretWidth * 3.5f, fretHeight * 3f, 8, fretPaint);
        canvas.drawCircle(fretWidth * 5.5f, fretHeight * 3f, 8, fretPaint);
        canvas.drawCircle(fretWidth * 7.5f, fretHeight * 3f, 8, fretPaint);
        canvas.drawCircle(fretWidth * 9.5f, fretHeight * 3f, 8, fretPaint);

        // Fret 12
        canvas.drawCircle(fretWidth * 12.5f, fretHeight * 2f, 8, fretPaint);
        canvas.drawCircle(fretWidth * 12.5f, fretHeight * 4f, 8, fretPaint);

        // Fret 15, 17, 19, 21
        canvas.drawCircle(fretWidth * 15.5f, fretHeight * 3f, 8, fretPaint);
        canvas.drawCircle(fretWidth * 17.5f, fretHeight * 3f, 8, fretPaint);
        canvas.drawCircle(fretWidth * 19.5f, fretHeight * 3f, 8, fretPaint);
        canvas.drawCircle(fretWidth * 21.5f, fretHeight * 3f, 8, fretPaint);
    }

    private void drawNotes(float fretWidth, float fretHeight, Canvas canvas) {
        Paint rootPaint = createPaint(ROOT_COLOR);
        Paint strongPaint = createPaint(STRONG_COLOR);
        Paint upcomingRootPaint = createPaint(UPCOMING_ROOT_COLOR);
        upcomingRootPaint.setAlpha(127);
        Paint upcomingStrongPaint = createPaint(UPCOMING_STRONG_COLOR);
        upcomingStrongPaint.setAlpha(127);
        Paint outlinePaint = createPaint(Color.BLACK);
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setStrokeWidth(3);

        for (int stringIndex = 0; stringIndex < 6; stringIndex++) {
            for (int fretIndex = 0; fretIndex < 23; fretIndex++) {
                if (hasFit(stringIndex, fretIndex, notes)) {
                    NoteFit noteFit = notes[fretIndex][stringIndex];
                    // Outline notes
                    canvas.drawCircle(getX(fretIndex, fretWidth), getY(stringIndex, fretHeight), NOTE_MARKER_RADIUS, outlinePaint);
                    // Fill notes
                    Paint paint = noteFit == NoteFit.ROOT ? rootPaint : strongPaint;
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(getX(fretIndex, fretWidth), getY(stringIndex, fretHeight), NOTE_MARKER_RADIUS, paint);
                } else if (hasFit(stringIndex, fretIndex, upcomingNotes)) {
                    NoteFit noteFit = upcomingNotes[fretIndex][stringIndex];
                    // Outline notes
                    //canvas.drawCircle(getX(fretIndex, fretWidth), getY(stringIndex, fretHeight), NOTE_MARKER_RADIUS, outlinePaint);
                    // Fill notes
                    Paint paint = noteFit == NoteFit.ROOT ? upcomingRootPaint : upcomingStrongPaint;
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(getX(fretIndex, fretWidth), getY(stringIndex, fretHeight), NOTE_MARKER_RADIUS, paint);
                }
            }
        }
    }

    private boolean hasFit(int stringIndex, int fretIndex, NoteFit[][] noteFits) {
        return noteFits != null
                && noteFits.length > fretIndex
                && noteFits[fretIndex].length > stringIndex
                && noteFits[fretIndex][stringIndex] != null;
    }

    private Paint createPaint(int colorVal) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(colorVal);
        paint.setAlpha(255);
        return paint;
    }

    private float getX(int fretIndex, float fretWidth) {
        return (0.5f + fretIndex) * fretWidth;
    }

    private float getY(int stringIndex, float fretHeight) {
        return fretHeight * (5.5f - stringIndex);
    }
}
