package com.github.improvtrainer;

import com.github.improvtrainer.event.BeatEventListener;
import com.github.improvtrainer.model.Beat;
import com.github.improvtrainer.model.BeatType;
import com.github.improvtrainer.model.CandidateNote;
import com.github.improvtrainer.model.CandidateNotesListener;
import com.github.improvtrainer.model.Measure;
import com.github.improvtrainer.model.Song;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class SongDisplay implements BeatEventListener {

    Song song;
    SongIterator songIterator;
    CandidateNoteService candidateNoteService;
    List<CandidateNotesListener> candidateNotesListeners;

    public SongDisplay(Song song, CandidateNoteService candidateNoteService, CandidateNotesListener... candidateNotesListeners) {
        this.song = song;
        this.songIterator = new SongIterator(song);
        this.candidateNoteService = candidateNoteService;
        this.candidateNotesListeners = Arrays.asList(candidateNotesListeners);
    }

    @Override
    public boolean onBeat() {
        if (songIterator.hasNext()) {
            Beat nextBeat = songIterator.next();
            if (nextBeat.getBeatType() == BeatType.CHORD) {
                Set<CandidateNote> candidateNotes = candidateNoteService.getCandidates(nextBeat.getChord());
                updateCandidateNotes(candidateNotes);
            } else if (nextBeat.getBeatType() == BeatType.CLEAR) {
                updateCandidateNotes(new HashSet<CandidateNote>());
            } else {
                // this is BeatType.SUSTAINED, which requires no UI updates, so do nothing
            }
            if (songIterator.hasNext()) {
                Beat upcomingBeat = songIterator.peek();
                if (upcomingBeat.getBeatType() == BeatType.CHORD) {
                    Set<CandidateNote> candidateNotes = candidateNoteService.getCandidates(upcomingBeat.getChord());
                    updateUpcomingCandidateNotes(candidateNotes);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private void updateCandidateNotes(final Set<CandidateNote> candidateNotes) {
        for (CandidateNotesListener listener : candidateNotesListeners) {
            listener.onCandidateNotesChange(candidateNotes);
        }
    }

    private void updateUpcomingCandidateNotes(final Set<CandidateNote> candidateNotes) {
        for (CandidateNotesListener listener : candidateNotesListeners) {
            listener.onUpcomingCandidateNotesChange(candidateNotes);
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
            Beat beat = peek();
            prepNext();
            return beat;
        }

        public Beat peek() {
            if (!hasNext) {
                throw new NoSuchElementException();
            }
            return next;
        }
    };
}
