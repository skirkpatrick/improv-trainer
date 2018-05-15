package com.github.improvtrainer.model;

public class ChordRoot {
    private NoteBase base;
    private NoteModifier modifier;

    ChordRoot(NoteBase base, NoteModifier modifier) {
        this.setBase(base);
        this.setModifier(modifier);
    }

    public NoteBase getBase() {
        return base;
    }

    public void setBase(NoteBase base) {
        this.base = base;
    }

    public NoteModifier getModifier() {
        return modifier;
    }

    public void setModifier(NoteModifier modifier) {
        this.modifier = modifier;
    }
}
