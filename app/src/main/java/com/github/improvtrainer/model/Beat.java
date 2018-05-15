package com.github.improvtrainer.model;

/**
 * An object representing one beat in a measure, and containing the chord that occurs on this beat.
 * If chord is null, that represents the chord from the previous beat sustaining.
 */
public class Beat {
    private Chord chord = null;

    public Beat() {}

    public Beat(Chord chord) {
        this.setChord(chord);
    }

    public Chord getChord() {
        return chord;
    }

    public void setChord(Chord chord) {
        this.chord = chord;
    }
}
