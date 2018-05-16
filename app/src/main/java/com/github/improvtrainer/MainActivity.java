package com.github.improvtrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.improvtrainer.model.Song;
import com.github.improvtrainer.model.guitar.Guitar;
import com.github.improvtrainer.model.piano.Piano;
import com.github.improvtrainer.parser.ChordChartParser;
import com.github.improvtrainer.ui.GuitarView;
import com.github.improvtrainer.ui.PianoView;

public class MainActivity extends AppCompatActivity {

    private Guitar guitar;
    private Piano piano;

    private Button buttonGuitar;
    private Button buttonPiano;
    private ImageView buttonRewind;
    private ImageView buttonPlay;
    private ImageView buttonPause;
    private EditText editTempo;
    private TextView chordDisplay;
    private PianoView pianoView;
    private GuitarView guitarView;

    private Song song;

    private static int GUITAR_STRINGS = 6;
    private static int GUITAR_FRETS = 22;
    private static int PIANO_KEYS = 48;
    private static int PIANO_STARTING_NOTE = 48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInstruments();
        findViews();
        subscribeViewsToModels();
        parseChordChart();
    }

    private void initializeInstruments() {
        guitar = new Guitar(GUITAR_STRINGS, GUITAR_FRETS);
        piano = new Piano(PIANO_KEYS, PIANO_STARTING_NOTE);
    }

    private void findViews() {
        buttonGuitar = findViewById(R.id.button_guitar);
        buttonPiano = findViewById(R.id.button_piano);
        buttonRewind = findViewById(R.id.button_rewind);
        buttonPlay = findViewById(R.id.button_play);
        buttonPause = findViewById(R.id.button_pause);
        editTempo = findViewById(R.id.tempo_edit);
        chordDisplay = findViewById(R.id.display_chord);
        pianoView = findViewById(R.id.piano_view);
        guitarView = findViewById(R.id.guitar_view);
    }

    private void addTimerViewsListeners(final BeatTimer timer) {
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.pause();
            }
        });
    }

    private void subscribeViewsToModels() {
        guitar.setGuitarModelListener(guitarView);
        piano.setPianoModelListener(pianoView);
    }

    private void parseChordChart() {
        ChordChartParser chordChartParser = new ChordChartParser();
        song = chordChartParser.parse(getApplicationContext().getResources().openRawResource(R.raw.all_of_me));
    }
}
