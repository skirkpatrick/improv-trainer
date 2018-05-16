package com.github.improvtrainer.event;

public interface BeatEventListener {

    /**
     * @return true if the beat timer should continue to run, false otherwise
     */
    boolean onBeat();

}
