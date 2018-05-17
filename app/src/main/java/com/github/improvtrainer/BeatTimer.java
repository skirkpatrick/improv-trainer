package com.github.improvtrainer;

import android.os.Handler;
import android.os.SystemClock;

import com.github.improvtrainer.event.BeatEventListener;

import java.util.Arrays;
import java.util.List;

public class BeatTimer {

    private final Handler handler = new Handler();
    private final BeatRunner runner;
    private final List<BeatEventListener> listeners;
    private final BackingTrackPlayer backingTrack;

    private final int bpm;
    private final int handlerDelay;

    private State state = State.STOPPED;
    private long nextBeat = 0L;
    /** Used only for pause */
    private long pausedTimeToNextBeat = 0L;

    public BeatTimer(int bpm, BeatEventListener... listeners) {
        this(bpm, null, listeners);
    }

    public BeatTimer(int bpm, BackingTrackPlayer backingTrack, BeatEventListener... listeners) {
        this.listeners = Arrays.asList(listeners);
        this.runner = new BeatRunner();
        this.backingTrack = backingTrack;
        this.bpm = bpm;
        this.handlerDelay = 60_000 / bpm;
    }

    public void start() {
        if (state != State.STARTED) {
            nextBeat = SystemClock.uptimeMillis() + pausedTimeToNextBeat;
            pausedTimeToNextBeat = 0L;
            if (state == State.STOPPED) {
                handler.post(runner);
            } else {
                handler.postAtTime(runner, nextBeat);
            }
            state = State.STARTED;
        }
    }

    public void pause() {
        if (state == State.STARTED) {
            handler.removeCallbacks(runner);
            pausedTimeToNextBeat = nextBeat - SystemClock.uptimeMillis();
            state = State.PAUSED;
        }
    }

    public void stop() {
        handler.removeCallbacks(runner);
        pausedTimeToNextBeat = 0L;
        nextBeat = 0L;
        state = State.STOPPED;
    }

    public boolean hasStarted() {
        return state != State.STOPPED;
    }

    private void queueNextBeat() {
        nextBeat = nextBeat + handlerDelay;
        handler.postAtTime(runner, nextBeat);
    }

    private class BeatRunner implements Runnable {
        @Override
        public void run() {
            // submit first to avoid timer drift
            queueNextBeat();
            boolean shouldStop = false;
            for (BeatEventListener listener : listeners) {
                shouldStop = shouldStop || !listener.onBeat();
            }
            // if any beat event listener returns that the timer should stop, then finish going
            // through all listeners then stop
            if (shouldStop) {
                stop();
            }
        }
    }

    private enum State {
        STOPPED,
        STARTED,
        PAUSED
    }
}
