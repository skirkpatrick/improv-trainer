package com.github.improvtrainer;

import com.github.improvtrainer.model.CandidateNote;
import com.github.improvtrainer.model.Chord;
import com.github.improvtrainer.model.ChordQuality;
import com.github.improvtrainer.model.ChordRoot;
import com.github.improvtrainer.model.NoteBase;
import com.github.improvtrainer.model.NoteFit;
import com.github.improvtrainer.model.NoteModifier;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CandidateNoteServiceTest {

    @Test
    public void testGetCandidates_major() {
        runTest(NoteBase.B, NoteModifier.FLAT, ChordQuality.MAJ, 10, 2, 5);
        runTest(NoteBase.B, NoteModifier.NATURAL, ChordQuality.MAJ, 11, 3, 6);
        runTest(NoteBase.B, NoteModifier.SHARP, ChordQuality.MAJ, 0, 4, 7);
        runTest(NoteBase.C, NoteModifier.FLAT, ChordQuality.MAJ, 11, 3, 6);
        runTest(NoteBase.C, NoteModifier.NATURAL, ChordQuality.MAJ, 0, 4, 7);
        runTest(NoteBase.C, NoteModifier.SHARP, ChordQuality.MAJ, 1, 5, 8);
        runTest(NoteBase.D, NoteModifier.FLAT, ChordQuality.MAJ, 1, 5, 8);
        runTest(NoteBase.D, NoteModifier.NATURAL, ChordQuality.MAJ, 2, 6, 9);
        runTest(NoteBase.D, NoteModifier.SHARP, ChordQuality.MAJ, 3, 7, 10);
    }

    @Test
    public void testGetCandidates_other() {
        runTest(NoteBase.C, NoteModifier.NATURAL, ChordQuality.MAJ6, 0, 4, 7, 9);
        runTest(NoteBase.C, NoteModifier.NATURAL, ChordQuality.MAJ7, 0, 4, 7, 11);
        runTest(NoteBase.C, NoteModifier.NATURAL, ChordQuality.MAJ7_FLAT3_FLAT5, 0, 3, 6, 11);
        runTest(NoteBase.C, NoteModifier.NATURAL, ChordQuality.MAJ7_FLAT3_SHARP5, 0, 3, 8, 11);
        runTest(NoteBase.C, NoteModifier.NATURAL, ChordQuality.MAJ7_SHARP5, 0, 4, 8, 11);
        runTest(NoteBase.C, NoteModifier.NATURAL, ChordQuality.MAJ7_FLAT9, 0, 4, 7, 11, 1);
        runTest(NoteBase.C, NoteModifier.NATURAL, ChordQuality.MAJ7_SHARP9, 0, 4, 7, 11, 3);
        runTest(NoteBase.C, NoteModifier.NATURAL, ChordQuality.MAJ7_SHARP11, 0, 4, 7, 11, 6);
        runTest(NoteBase.C, NoteModifier.NATURAL, ChordQuality.MAJ9_SHARP5, 0, 4, 8, 2);
        runTest(NoteBase.C, NoteModifier.NATURAL, ChordQuality.MAJ9_SHARP11, 0, 4, 7, 11, 2, 6);

        runTest(NoteBase.C, NoteModifier.FLAT, ChordQuality.MAJ9_SHARP11, 11, 3, 6, 10, 1, 5);
        runTest(NoteBase.C, NoteModifier.SHARP, ChordQuality.MAJ9_SHARP11, 1, 5, 8, 0, 3, 7);
        runTest(NoteBase.B, NoteModifier.SHARP, ChordQuality.MAJ9_SHARP11, 0, 4, 7, 11, 2, 6);
    }

    private Set<CandidateNote> getResult(NoteBase base, NoteModifier modifier, ChordQuality quality) {
        ChordRoot root = new ChordRoot(base, modifier);
        return new CandidateNoteService().getCandidates(new Chord(root, quality));
    }

    private void runTest(NoteBase base, NoteModifier modifier, ChordQuality quality, int root, int... offsets) {
        Set<CandidateNote> result = getResult(base, modifier, quality);
        Set<CandidateNote> expectedResult = new HashSet<>();
        expectedResult.add(new CandidateNote(root, NoteFit.ROOT));
        for (int i = 0; i < offsets.length; i++) {
            expectedResult.add(new CandidateNote(offsets[i], NoteFit.STRONG));
        }
        assertEquals(expectedResult, result);
    }
}
