package com.github.improvtrainer.ui;

import com.github.improvtrainer.model.piano.Key;

import java.util.List;

public interface PianoModelListener {
    void updateKeyState(List<Integer> indices, Key.State state);
    void clearAllKeys();
}
