package com.github.improvtrainer.model.guitar;

import com.github.improvtrainer.model.CandidateNote;
import com.github.improvtrainer.model.CandidateNotesListener;
import com.github.improvtrainer.ui.GuitarModelListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Guitar implements CandidateNotesListener {

    private int numberOfStrings;
    private int numberOfFrets;
    private GuitarModelListener guitarModelListener;

    private List<GuitarString> guitarStrings;

    public Guitar(int numberOfStrings, int numberOfFrets) {
        this.numberOfStrings = numberOfStrings;
        this.numberOfFrets = numberOfFrets;
        initGuitar();
    }

    private void initGuitar() {
        guitarStrings = new ArrayList<>();
        guitarStrings.add(new GuitarString(52, numberOfFrets, 0));
        guitarStrings.add(new GuitarString(57, numberOfFrets, 1));
        guitarStrings.add(new GuitarString(62, numberOfFrets, 2));
        guitarStrings.add(new GuitarString(67, numberOfFrets, 3));
        guitarStrings.add(new GuitarString(71, numberOfFrets, 4));
        guitarStrings.add(new GuitarString(76, numberOfFrets, 5));
    }

    public void setGuitarModelListener(GuitarModelListener guitarModelListener) {
        this.guitarModelListener = guitarModelListener;
    }

    @Override
    public void onCandidateNotesChange(Set<CandidateNote> candidateNotes) {
        if (candidateNotes == null || candidateNotes.isEmpty()) {
            // clear notes
        } else {
            // update with new notes
        }
    }
}
