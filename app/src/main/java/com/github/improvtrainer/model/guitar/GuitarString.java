package com.github.improvtrainer.model.guitar;

import java.util.ArrayList;
import java.util.List;

class GuitarString {

    private List<FretboardPosition> fretboardPositions;

    private int startingNote;
    private int numberOfFrets;
    private int index;

    GuitarString(int startingNote, int numberOfFrets, int index) {
        this.startingNote = startingNote;
        this.numberOfFrets = numberOfFrets;
        this.index = index;
        initGuitarString();
    }

    private void initGuitarString() {
        fretboardPositions = new ArrayList<>();
        for (int i = 0; i <= numberOfFrets; i ++) {
            fretboardPositions.add(new FretboardPosition(startingNote + i, this, i));
        }
    }
}
