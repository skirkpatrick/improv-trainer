package com.github.improvtrainer.model.guitar;

public class Fret {

    enum State { OFF, ON, ROOT }

    private int note;

    public Fret(int note) {
        this.note = note;
    }
}
