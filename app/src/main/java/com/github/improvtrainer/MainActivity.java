package com.github.improvtrainer;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.improvtrainer.model.guitar.Guitar;
import com.github.improvtrainer.model.piano.Piano;

public class MainActivity extends AppCompatActivity {

    private Guitar guitar;
    private Piano piano;

    private static int GUITAR_STRINGS = 6;
    private static int GUITAR_FRETS = 22;
    private static int PIANO_KEYS = 48;
    private static int PIANO_STARTING_NOTE = 48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        guitar = new Guitar(GUITAR_STRINGS, GUITAR_FRETS);
        piano = new Piano(PIANO_KEYS, PIANO_STARTING_NOTE);
    }
}
