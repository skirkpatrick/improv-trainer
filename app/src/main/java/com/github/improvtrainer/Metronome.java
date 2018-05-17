package com.github.improvtrainer;

import android.content.Context;
import android.media.MediaPlayer;

import com.github.improvtrainer.event.BeatEventListener;
import com.github.improvtrainer.ui.MetronomeView;

public class Metronome implements BeatEventListener {

    private final MediaPlayer mediaPlayer;
    private final MetronomeView view;
    private final boolean playAudio;

    public Metronome(Context context, MetronomeView view, boolean playAudio) {
        this.mediaPlayer = MediaPlayer.create(context, R.raw.click_wav);
        this.view = view;
        this.playAudio = playAudio;
    }

    @Override
    public boolean onBeat() {
        view.flash();
        if (playAudio) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
        return true;
    }
}
