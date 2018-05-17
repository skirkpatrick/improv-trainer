package com.github.improvtrainer;

import android.content.Context;
import android.media.MediaPlayer;

import com.github.improvtrainer.event.BeatEventListener;
import com.github.improvtrainer.ui.MetronomeView;

public class Metronome implements BeatEventListener {

    private final MediaPlayer mediaPlayer;
    private final MetronomeView view;

    public Metronome(Context context, MetronomeView view) {
        this.mediaPlayer = MediaPlayer.create(context, R.raw.click_wav);
        this.view = view;
    }

    @Override
    public boolean onBeat() {
        view.flash();
        //mediaPlayer.seekTo(0);
        //mediaPlayer.start();
        return true;
    }
}
