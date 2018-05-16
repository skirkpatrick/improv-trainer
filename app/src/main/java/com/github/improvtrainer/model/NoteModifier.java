package com.github.improvtrainer.model;

public enum NoteModifier {
    NATURAL(0, ""),
    SHARP(1, "#"),
    FLAT(-1, "b");

    private final int toneOffset;
    private final String stringNotation;

    NoteModifier(int toneOffset, String stringNotation) {
        this.toneOffset = toneOffset;
        this.stringNotation = stringNotation;
    }

    public int getToneOffset() {
        return toneOffset;
    }

    public String getStringNotation() {
        return stringNotation;
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
