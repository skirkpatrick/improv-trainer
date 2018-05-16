package com.github.improvtrainer.model.guitar;

public class FretboardPosition {

    public enum State { OFF, ON, ROOT }

    private int note;
    private GuitarString guitarString;
    private int fret;
    private State state;

    FretboardPosition(int note, GuitarString guitarString, int fret) {
        this.note = note;
        this.guitarString = guitarString;
        this.fret = fret;
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
