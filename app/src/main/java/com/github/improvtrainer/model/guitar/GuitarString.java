package com.github.improvtrainer.model.guitar;

import java.util.TreeMap;

public class GuitarString {

    private TreeMap<Integer, Fret> fretMap;

    private int startingNote;
    private int numberOfFrets;

    public GuitarString(int startingNote, int numberOfFrets) {
        this.startingNote = startingNote;
        this.numberOfFrets = numberOfFrets;
        initGuitarString();
    }

    private void initGuitarString() {
        fretMap = new TreeMap<>();
        for (int i = 0; i <= numberOfFrets; i ++) {
            fretMap.put(i, new Fret(startingNote + i));
        }
    }
}
