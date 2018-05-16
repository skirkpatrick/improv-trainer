package com.github.improvtrainer;

import android.content.Context;
import android.media.MediaPlayer;

import com.github.improvtrainer.event.BeatEventListener;

public class Metronome implements BeatEventListener {

    private final MediaPlayer mediaPlayer;

    public Metronome(Context context) {
        this.mediaPlayer = MediaPlayer.create(context, R.raw.click_wav);
    }

    @Override
    public boolean onBeat() {
        mediaPlayer.seekTo(0);
        return true;
    }
}
