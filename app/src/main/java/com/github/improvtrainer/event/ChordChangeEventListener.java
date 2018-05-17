package com.github.improvtrainer.event;

import com.github.improvtrainer.model.Chord;

public interface ChordChangeEventListener {

    void onChordChange(Chord chord);

}
