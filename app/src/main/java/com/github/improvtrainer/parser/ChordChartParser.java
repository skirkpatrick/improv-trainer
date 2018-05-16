package com.github.improvtrainer.parser;

import com.github.improvtrainer.model.Beat;
import com.github.improvtrainer.model.BeatType;
import com.github.improvtrainer.model.Chord;
import com.github.improvtrainer.model.ChordQuality;
import com.github.improvtrainer.model.ChordRoot;
import com.github.improvtrainer.model.Measure;
import com.github.improvtrainer.model.NoteBase;
import com.github.improvtrainer.model.NoteModifier;
import com.github.improvtrainer.model.Song;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChordChartParser {
    private static final String DELIMITER = ":";
    private static final String SUSTAIN = "";
    private static final String CLEAR = "0";
    private static final int BEATS_PER_MEASURE = 4;

    public Song parse(InputStream is) {
        Song song = new Song();

        try (Scanner sc = new Scanner(is)) {
            while (sc.hasNextLine()) {
                String measureStr = sc.nextLine();
                List<Beat> beats = new ArrayList<>(BEATS_PER_MEASURE);
                for (String chordStr : measureStr.split(DELIMITER, -1)) {
                    beats.add(makeBeat(chordStr));
                }
                song.addMeasure(new Measure(beats));
            }
        }

        return song;
    }

    private Beat makeBeat(String chordStr) {
        switch (chordStr) {
            case SUSTAIN:
                return new Beat(BeatType.SUSTAIN, null);
            case CLEAR:
                return new Beat(BeatType.CLEAR, null);
            default:
                return new Beat(BeatType.CHORD, makeChord(chordStr));
        }
    }

    private Chord makeChord(String chordStr) {
        NoteBase noteBase = NoteBase.valueOf(chordStr.substring(0,1).toUpperCase());
        NoteModifier noteModifier = NoteModifier.NATURAL;

        int qualityStart = 1;
        if (chordStr.length() > 1) {
            noteModifier = NoteModifier.fromString(chordStr.substring(1,2));
            if (noteModifier != NoteModifier.NATURAL)
                qualityStart = 2;
        }

        return new Chord(new ChordRoot(noteBase, noteModifier), ChordQuality.fromString(chordStr.substring(qualityStart)));
    }
}
