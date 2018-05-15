package com.github.improvtrainer.model;

import java.util.Objects;

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

    public int getToneValue() {
        int value = base.getBaseTone() + modifier.getToneOffset();
        // handle Cb
        if (value == -1) {
            value = 11;
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChordRoot chordRoot = (ChordRoot) o;
        return base == chordRoot.base &&
                modifier == chordRoot.modifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, modifier);
    }
}
