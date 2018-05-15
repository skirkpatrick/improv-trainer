package com.github.improvtrainer.model;

import java.util.ArrayList;
import java.util.List;

public class Measure {
    private List<Beat> beats = new ArrayList<>();

    public Measure(List<Beat> beats) {
        this.setBeats(beats);
    }

    public Measure(Beat firstBeat) {
        addBeat(firstBeat);
    }

    public List<Beat> getBeats() {
        return beats;
    }

    public void setBeats(List<Beat> beats) {
        this.beats = beats;
    }

    public void addBeat(Beat beat) {
        beats.add(beat);
    }

    public void addBeat(int pos, Beat beat) {
        beats.add(pos, beat);
    }
}
