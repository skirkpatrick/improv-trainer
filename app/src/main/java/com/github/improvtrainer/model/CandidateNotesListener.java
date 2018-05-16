package com.github.improvtrainer.model;

import java.util.Set;

public interface CandidateNotesListener {

    void onCandidateNotesChange(Set<CandidateNote> candidateNotes);

}
