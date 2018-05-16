package com.github.improvtrainer.model.piano;

public class Key {

    enum State { OFF, ON, ROOT }

    private int note;
    private State state;

    Key(int note) {
        this.note = note;
    }

    public int getNote() {
        return note;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
