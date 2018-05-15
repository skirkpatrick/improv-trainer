package com.github.improvtrainer.model;

public enum NoteModifier {
    NATURAL(0),
    SHARP(1),
    FLAT(-1);

    private final int toneOffset;

    NoteModifier(int toneOffset) {
        this.toneOffset = toneOffset;
    }

    public int getToneOffset() {
        return toneOffset;
    }

    public static NoteModifier fromString(String s) {
        switch(s) {
            case "#":
                return SHARP;
            case "b":
                return FLAT;
            default:
                return NATURAL;
        }
    }
}
