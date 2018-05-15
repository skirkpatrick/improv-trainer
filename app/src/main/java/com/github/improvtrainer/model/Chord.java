package com.github.improvtrainer.model;

import java.util.Objects;

public class Chord {
    private ChordRoot root;
    private ChordQuality quality;

    public Chord(ChordRoot root, ChordQuality quality) {
        this.setRoot(root);
        this.setQuality(quality);
    }

    public ChordRoot getRoot() {
        return root;
    }

    public void setRoot(ChordRoot root) {
        this.root = root;
    }

    public ChordQuality getQuality() {
        return quality;
    }

    public void setQuality(ChordQuality quality) {
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chord chord = (Chord) o;
        return Objects.equals(root, chord.root) &&
                quality == chord.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, quality);
    }
}
