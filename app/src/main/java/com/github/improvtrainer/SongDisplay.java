package com.github.improvtrainer;

import com.github.improvtrainer.event.BeatEventListener;
import com.github.improvtrainer.model.Beat;
import com.github.improvtrainer.model.BeatType;
import com.github.improvtrainer.model.CandidateNote;
import com.github.improvtrainer.model.Measure;
import com.github.improvtrainer.model.SequencerConfiguration;
import com.github.improvtrainer.model.Song;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class SongDisplay implements BeatEventListener {

    Song song;
    SongIterator songIterator;
    SequencerConfiguration configuration;
    CandidateNoteService candidateNoteService;

    public SongDisplay(Song song, SequencerConfiguration configuration, CandidateNoteService candidateNoteService) {
        this.song = song;
        this.configuration = configuration;
        this.songIterator = new SongIterator(song);
        this.candidateNoteService = candidateNoteService;
    }

    @Override
    public boolean onBeat() {
        if (songIterator.hasNext()) {
            Beat nextBeat = songIterator.next();
            if (nextBeat.getBeatType() == BeatType.CHORD) {
                Set<CandidateNote> candidateNotes = candidateNoteService.getCandidates(nextBeat.getChord());
                // TODO fire off event to update UI with chord
            } else if (nextBeat.getBeatType() == BeatType.CLEAR) {
                // TODO fire off event to update UI with no chord
            } else {
                // this is BeatType.SUSTAINED, which requires no UI updates, so do nothing
            }
            return true;
        } else {
            return false;
        }
    }

    class SongIterator implements Iterator<Beat> {
        private Song song;
        private Iterator<Measure> measureIterator;
        private Iterator<Beat> beatIterator;
        private boolean hasNext = true;
        private Beat next;

        public SongIterator(Song song) {
            this.song = song;
            this.measureIterator = song.getMeasures().iterator();
            prepNext();
        }

        private void prepNext() {
            do {
                if (beatIterator == null || !beatIterator.hasNext()) {
                    if (!measureIterator.hasNext()) {
                        hasNext = false;
                        return;
                    } else
                        beatIterator = measureIterator.next().getBeats().iterator();
                }
            } while (!beatIterator.hasNext());

            next = beatIterator.next();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public Beat next() {
            if (!hasNext)
                throw new NoSuchElementException();
            Beat beat = next;
            prepNext();
            return beat;
        }
    };
}
