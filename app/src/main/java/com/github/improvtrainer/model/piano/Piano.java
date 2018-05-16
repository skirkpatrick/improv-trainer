package com.github.improvtrainer.model.piano;

import java.util.TreeMap;

public class Piano {

    private int numberOfKeys;
    private int startingNote;

    private TreeMap<Integer, Key> keymap;

    public Piano(int numberOfKeys, int startingNote) {
        this.numberOfKeys = numberOfKeys;
        this.startingNote = startingNote;
        initPiano();
    }

    private void initPiano() {
        keymap = new TreeMap<>();
        for (int i = 0; i < numberOfKeys; i++) {
            keymap.put(i, new Key(i + startingNote));
        }
    }
}
