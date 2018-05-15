package com.github.improvtrainer.model;

public class Chord {
    private ChordRoot root;
    private ChordQuality quality;

    public Chord(ChordRoot root, ChordQuality quality) {
        this.setRoot(root);
        this.setQuality(quality);
    }

    public ChordRoot getRoot() {
        return root;
    }

    public void setRoot(ChordRoot root) {
        this.root = root;
    }

    public ChordQuality getQuality() {
        return quality;
    }

    public void setQuality(ChordQuality quality) {
        this.quality = quality;
    }
}
