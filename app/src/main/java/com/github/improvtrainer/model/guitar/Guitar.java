package com.github.improvtrainer.model.guitar;

import com.github.improvtrainer.ui.GuitarModelListener;

import java.util.ArrayList;
import java.util.List;

public class Guitar {

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
        guitarStrings.add(new GuitarString(52, numberOfFrets));
        guitarStrings.add(new GuitarString(57, numberOfFrets));
        guitarStrings.add(new GuitarString(62, numberOfFrets));
        guitarStrings.add(new GuitarString(67, numberOfFrets));
        guitarStrings.add(new GuitarString(71, numberOfFrets));
        guitarStrings.add(new GuitarString(76, numberOfFrets));
    }

    public void setGuitarModelListener(GuitarModelListener guitarModelListener) {
        this.guitarModelListener = guitarModelListener;
    }
}
