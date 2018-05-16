package com.github.improvtrainer.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.improvtrainer.model.CandidateNote;
import com.github.improvtrainer.model.CandidateNotesListener;

import java.util.Set;

public class GuitarView extends View implements CandidateNotesListener {
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

    }
}
