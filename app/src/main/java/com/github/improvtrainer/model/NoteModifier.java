package com.github.improvtrainer.model;

enum NoteModifier {
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
}
