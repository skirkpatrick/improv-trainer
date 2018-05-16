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
    private long timeToNextBeat = 0L;

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
            if (backingTrack != null) {
                long currentPosition = backingTrack.currentPosition();
                timeToNextBeat = currentPosition == 0 ? 0 : currentPosition % handlerDelay;
            }
            queueNextBeat();
            state = State.STARTED;
        }
    }

    public void pause() {
        if (state == State.STARTED) {
            if (timeToNextBeat == 0L) {
                handler.removeCallbacks(runner);
                timeToNextBeat = nextBeat - SystemClock.uptimeMillis();
                nextBeat = 0L;
            }
            state = State.PAUSED;
        }
    }

    public void stop() {
        handler.removeCallbacks(runner);
        timeToNextBeat = 0L;
        nextBeat = 0L;
        state = State.STOPPED;
    }

    private void queueNextBeat() {
        nextBeat = SystemClock.uptimeMillis() + (timeToNextBeat == 0L ? handlerDelay : timeToNextBeat);
        timeToNextBeat = 0L;
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
