package com.github.improvtrainer.model.guitar;

import java.util.TreeMap;

public class Guitar {

    private int numberOfStrings;
    private int numberOfFrets;

    private TreeMap<Integer, GuitarString> guitarStringMap;

    public Guitar(int numberOfStrings, int numberOfFrets) {
        this.numberOfStrings = numberOfStrings;
        this.numberOfFrets = numberOfFrets;
        initGuitar();
    }

    private void initGuitar() {
        guitarStringMap = new TreeMap<>();
        guitarStringMap.put(0, new GuitarString(52, numberOfFrets));
        guitarStringMap.put(1, new GuitarString(57, numberOfFrets));
        guitarStringMap.put(2, new GuitarString(62, numberOfFrets));
        guitarStringMap.put(3, new GuitarString(67, numberOfFrets));
        guitarStringMap.put(4, new GuitarString(71, numberOfFrets));
        guitarStringMap.put(5, new GuitarString(76, numberOfFrets));
    }
}
