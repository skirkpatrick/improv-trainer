package com.github.improvtrainer.model.guitar;

import java.util.ArrayList;
import java.util.List;

class GuitarString {

    private List<Fret> frets;

    private int startingNote;
    private int numberOfFrets;

    GuitarString(int startingNote, int numberOfFrets) {
        this.startingNote = startingNote;
        this.numberOfFrets = numberOfFrets;
        initGuitarString();
    }

    private void initGuitarString() {
        frets = new ArrayList<>();
        for (int i = 0; i <= numberOfFrets; i ++) {
            frets.add(new Fret(startingNote + i));
        }
    }
}
