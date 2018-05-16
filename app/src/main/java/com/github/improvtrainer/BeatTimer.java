package com.github.improvtrainer;

import android.os.Handler;

import com.github.improvtrainer.event.BeatEventListener;

import java.util.Arrays;
import java.util.List;

public class BeatTimer {

    private final Handler handler = new Handler();
    private final BeatRunner runner;
    private final List<BeatEventListener> listeners;

    private final int bpm;
    private final int handlerDelay;

    public BeatTimer(int bpm, BeatEventListener... listeners) {
        this.listeners = Arrays.asList(listeners);
        this.runner = new BeatRunner();
        this.bpm = bpm;
        this.handlerDelay = 60_000 / bpm;
    }

    public void start() {
        handler.postDelayed(runner, handlerDelay);
    }

    public void stop() {
        handler.removeCallbacks(runner);
    }

    private class BeatRunner implements Runnable {
        @Override
        public void run() {
            // submit first to avoid timer drift
            handler.postDelayed(this, handlerDelay);
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
}
