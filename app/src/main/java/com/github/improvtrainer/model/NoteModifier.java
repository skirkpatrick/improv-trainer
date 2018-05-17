package com.github.improvtrainer.model;

public enum NoteModifier {
    NATURAL(0, ""),
    SHARP(1, "#"),
    FLAT(-1, "b");

    private final int toneOffset;
    private final String displayName;

    NoteModifier(int toneOffset, String stringNotation) {
        this.toneOffset = toneOffset;
        this.displayName = stringNotation;
    }

    public int getToneOffset() {
        return toneOffset;
    }

    public String getDisplayName() {
        return displayName;
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
