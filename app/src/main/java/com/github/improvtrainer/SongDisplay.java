package com.github.improvtrainer;

import com.github.improvtrainer.event.BeatEventListener;
import com.github.improvtrainer.event.ChordChangeEventListener;
import com.github.improvtrainer.model.Beat;
import com.github.improvtrainer.model.BeatType;
import com.github.improvtrainer.model.CandidateNote;
import com.github.improvtrainer.event.CandidateNotesListener;
import com.github.improvtrainer.model.Chord;
import com.github.improvtrainer.model.Measure;
import com.github.improvtrainer.model.Song;

import java.util.ArrayList;
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
    List<ChordChangeEventListener> chordChangeEventListeners;

    public SongDisplay(Song song, CandidateNoteService candidateNoteService) {
        this.song = song;
        this.songIterator = new SongIterator(song);
        this.candidateNoteService = candidateNoteService;
        this.candidateNotesListeners = new ArrayList<>();
        this.chordChangeEventListeners = new ArrayList<>();
    }

    public void addCandidateNotesListeners(CandidateNotesListener... candidateNotesListeners) {
        this.candidateNotesListeners.addAll(Arrays.asList(candidateNotesListeners));
    }

    public void addChordChangeEventListeners(ChordChangeEventListener... chordChangeEventListeners) {
        this.chordChangeEventListeners.addAll(Arrays.asList(chordChangeEventListeners));
    }

    @Override
    public boolean onBeat() {
        if (songIterator.hasNext()) {
            Beat nextBeat = songIterator.next();
            if (nextBeat.getBeatType() == BeatType.CHORD) {
                Set<CandidateNote> candidateNotes = candidateNoteService.getCandidates(nextBeat.getChord());
                updateCandidateNotes(candidateNotes);
                updateChord(nextBeat.getChord());
                updateUpcomingChord(null);
            } else if (nextBeat.getBeatType() == BeatType.CLEAR) {
                updateCandidateNotes(new HashSet<CandidateNote>());
                updateChord(null);
            } else {
                // this is BeatType.SUSTAINED, which requires no UI updates, so do nothing
            }
            if (songIterator.hasNext()) {
                Beat upcomingBeat = songIterator.peek();
                if (upcomingBeat.getBeatType() == BeatType.CHORD) {
                    Set<CandidateNote> candidateNotes = candidateNoteService.getCandidates(upcomingBeat.getChord());
                    updateUpcomingCandidateNotes(candidateNotes);
                    updateUpcomingChord(upcomingBeat.getChord());
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

    private void updateChord(final Chord chord) {
        for (ChordChangeEventListener listener : chordChangeEventListeners) {
            listener.onChordChange(chord);
        }
    }

    private void updateUpcomingChord(final Chord chord) {
        for (ChordChangeEventListener listener : chordChangeEventListeners) {
            listener.onUpcomingChordChange(chord);
        }
    }

    public void clearChordsAndCandidateNotes() {
        updateCandidateNotes(new HashSet<CandidateNote>());
        updateChord(null);
        updateUpcomingChord(null);
        songIterator = new SongIterator(song);
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
