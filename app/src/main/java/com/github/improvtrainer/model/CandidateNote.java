package com.github.improvtrainer.model;

import java.util.Objects;

public class CandidateNote {

    private final int baseToneValue;
    private final NoteFit fit;

    public CandidateNote(int baseToneValue, NoteFit fit) {
        this.baseToneValue = baseToneValue;
        this.fit = fit;
    }

    public int getBaseToneValue() {
        return baseToneValue;
    }

    public NoteFit getFit() {
        return fit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateNote that = (CandidateNote) o;
        return baseToneValue == that.baseToneValue &&
                fit == that.fit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseToneValue, fit);
    }
}
