package com.github.improvtrainer.model;

import java.util.Objects;

/**
 * An object representing one beat in a measure, and containing the chord that occurs on this beat.
 * If chord is null, check isClear and isSust
 */
public class Beat {
    private BeatType beatType;
    private Chord chord = null;

    public Beat(BeatType beatType, Chord chord) {
        this.setBeatType(beatType);
        this.setChord(chord);
    }

    public Chord getChord() {
        return chord;
    }

    public void setChord(Chord chord) {
        this.chord = chord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beat beat = (Beat) o;
        return Objects.equals(chord, beat.chord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chord);
    }

    public BeatType getBeatType() {
        return beatType;
    }

    public void setBeatType(BeatType beatType) {
        this.beatType = beatType;
    }
}
