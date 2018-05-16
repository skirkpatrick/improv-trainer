package com.github.improvtrainer.ui;

import com.github.improvtrainer.model.guitar.FretboardPosition;

import java.util.List;

public interface GuitarModelListener {
    void updateFretState(List<FretboardPosition> fretboardPositions, FretboardPosition.State state);
    void clearAllFrets();
}
