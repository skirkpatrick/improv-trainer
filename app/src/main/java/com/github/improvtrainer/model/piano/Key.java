package com.github.improvtrainer.model.piano;

public class Key {

    enum State { OFF, ON, ROOT }

    private int note;
    private State state;

    public Key(int note) {
        this.note = note;
    }

}
