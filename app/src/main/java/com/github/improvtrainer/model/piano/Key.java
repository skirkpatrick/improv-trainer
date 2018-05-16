package com.github.improvtrainer.model.piano;

public class Key {

    public enum State { OFF, ON, ROOT }
    enum KeyColor { BLACK, WHITE }

    private int note;
    private State state;
    private KeyColor keyColor;

    Key(int note) {
        this.note = note;
        this.state = State.OFF;
        this.keyColor = getKeyColorFromNote(note);
    }

    private KeyColor getKeyColorFromNote(int note) {
        int noteOffsetFromC = note % 12;
        if (noteOffsetFromC == 1
                || noteOffsetFromC == 3
                || noteOffsetFromC == 6
                || noteOffsetFromC == 8
                || noteOffsetFromC == 10) {
            return KeyColor.BLACK;
        } else {
            return KeyColor.WHITE;
        }
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
