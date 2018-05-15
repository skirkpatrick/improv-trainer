package com.github.improvtrainer.model;

public enum NoteBase {
    C(0),
    D(2),
    E(4),
    F(5),
    G(7),
    A(9),
    B(11);

    private final int baseTone;

    NoteBase(int baseTone) {
        this.baseTone = baseTone;
    }

    public int getBaseTone() {
        return baseTone;
    }
}
