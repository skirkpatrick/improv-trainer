package com.github.improvtrainer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Song {
    private List<Measure> measures = new ArrayList<>();

    public Song() {}

    public Song(List<Measure> measures) {
        this.setMeasures(measures);
    }

    public Song(Measure firstMeasure) {
        addMeasure(firstMeasure);
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    public void addMeasure(int pos, Measure measure) {
        measures.add(pos, measure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(measures, song.measures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(measures);
    }
}
