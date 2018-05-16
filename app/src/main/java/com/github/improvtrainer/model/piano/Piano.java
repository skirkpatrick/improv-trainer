package com.github.improvtrainer.model.piano;

import com.github.improvtrainer.ui.PianoModelListener;

import java.util.ArrayList;
import java.util.List;

public class Piano {

    private int numberOfKeys;
    private int startingNote;
    private PianoModelListener pianoModelListener;

    private List<Key> keys;

    public Piano(int numberOfKeys, int startingNote) {
        this.numberOfKeys = numberOfKeys;
        this.startingNote = startingNote;
        initPiano();
    }

    private void initPiano() {
        keys = new ArrayList<>();
        for (int i = 0; i < numberOfKeys; i++) {
            keys.add(new Key(i + startingNote));
        }
    }

    public void setPianoModelListener(PianoModelListener pianoModelListener) {
        this.pianoModelListener = pianoModelListener;
    }
}
