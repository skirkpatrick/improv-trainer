package com.github.improvtrainer.model.guitar;

public class Fret {

    enum State { OFF, ON, ROOT }

    private int note;
    private State state;

    Fret(int note) {
        this.note = note;
        this.state = State.OFF;
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
