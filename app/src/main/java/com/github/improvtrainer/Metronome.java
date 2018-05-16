package com.github.improvtrainer;

import android.content.Context;
import android.media.MediaPlayer;

import com.github.improvtrainer.event.BeatEventListener;

public class Metronome implements BeatEventListener {

    private final Context context;
    private final MediaPlayer mediaPlayer;

    public Metronome(Context context) {
        this.context = context;
        this.mediaPlayer = MediaPlayer.create(context, R.raw.click);
    }

    @Override
    public void onBeat() {
        mediaPlayer.start();
    }
}
