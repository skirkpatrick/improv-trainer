package com.github.improvtrainer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
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
    private BeatTimer beatTimer;

    private static int GUITAR_STRINGS = 6;
    private static int GUITAR_FRETS = 22;
    private static int PIANO_KEYS = 48;
    private static int PIANO_STARTING_NOTE = 48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        initializeInstruments();
        findViews();
        subscribeViewsToModels();
        parseChordChart();
        addPlaybackButtonListeners();

        pianoView.setModel(piano);
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
        pianoView = findViewById(R.id.view_piano);
        guitarView = findViewById(R.id.view_guitar);
    }

    private void addPlaybackButtonListeners() {
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerNeedsInitializing()) {
                    String tempoString = editTempo.getText().toString();
                    if (tempoString == null || tempoString.isEmpty()) {
                        alert("Must specify tempo", "Please specify a tempo in bpm");
                    } else if (song == null) {
                        alert("Must input song", "Please input a song");
                    } else {
                        Integer tempo = Integer.valueOf(tempoString);
                        initializeTimer(tempo);
                        startTimer();
                    }
                }
                // TODO if timer is currently paused or ended, start it again, otherwise do nothing
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer();
            }
        });
    }

    private void initializeTimer(int bpm) {
        Metronome metronome = new Metronome(this);
        SongDisplay songDisplay = new SongDisplay(song, new CandidateNoteService());
        this.beatTimer = new BeatTimer(bpm, metronome, songDisplay);
    }

    private boolean timerNeedsInitializing() {
        return beatTimer == null;
    }

    private void startTimer() {
        if (beatTimer != null) {
            beatTimer.start();
        }
    }

    private void pauseTimer() {
        if (beatTimer != null) {
            beatTimer.pause();
        }
    }

    private void alert(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
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
