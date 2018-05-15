package com.github.improvtrainer.model;

public class SequencerConfiguration {

    private int tempo = 60;
    private Instrument instrument = Instrument.PIANO;

    public SequencerConfiguration() {
    }

    public SequencerConfiguration(Instrument instrument, int tempo) {
        this.instrument = instrument;
        this.tempo = tempo;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}