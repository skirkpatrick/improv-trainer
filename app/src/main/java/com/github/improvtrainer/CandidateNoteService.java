package com.github.improvtrainer;

import com.github.improvtrainer.model.CandidateNote;
import com.github.improvtrainer.model.Chord;
import com.github.improvtrainer.model.ChordQuality;
import com.github.improvtrainer.model.ChordRoot;
import com.github.improvtrainer.model.NoteFit;

import java.util.HashSet;
import java.util.Set;

public class CandidateNoteService {

    public Set<CandidateNote> getCandidates(Chord chord) {
        Set<CandidateNote> candidates = new HashSet<>();
        ChordRoot root = chord.getRoot();
        ChordQuality quality = chord.getQuality();
        int rootTone = root.getToneValue();
        candidates.add(new CandidateNote(rootTone, NoteFit.STRONG));
        for (int offset : quality.getStrongToneOffsets()) {
            int actualTone = (rootTone + offset) % 12;
            candidates.add(new CandidateNote(actualTone, NoteFit.STRONG));
        }
        for (int offset : quality.getWeakToneOffsets()) {
            int actualTone = (rootTone + offset) % 12;
            candidates.add(new CandidateNote(actualTone, NoteFit.WEAK));
        }
        return candidates;
    }
}
