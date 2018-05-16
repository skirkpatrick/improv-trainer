package com.github.improvtrainer;

import android.content.Context;
import android.media.MediaPlayer;

public class BackingTrackPlayer {

    private final MediaPlayer mediaPlayer;

    public BackingTrackPlayer(Context context, int resourceId) {
        this.mediaPlayer = MediaPlayer.create(context, resourceId);
    }

    public void start() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        pause();
        mediaPlayer.seekTo(0);
    }

    public int currentPosition() {
        return mediaPlayer.getCurrentPosition();
    }
}
