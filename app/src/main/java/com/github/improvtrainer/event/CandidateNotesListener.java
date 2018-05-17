package com.github.improvtrainer.event;

import com.github.improvtrainer.model.CandidateNote;

import java.util.Set;

public interface CandidateNotesListener {

    void onCandidateNotesChange(Set<CandidateNote> candidateNotes);

    void onUpcomingCandidateNotesChange(Set<CandidateNote> candidateNotes);

}
