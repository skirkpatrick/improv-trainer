package com.github.improvtrainer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measure measure = (Measure) o;
        return Objects.equals(beats, measure.beats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beats);
    }
}
