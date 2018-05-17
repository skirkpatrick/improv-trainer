package com.github.improvtrainer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.improvtrainer.event.ChordChangeEventListener;
import com.github.improvtrainer.model.Chord;
import com.github.improvtrainer.model.Song;
import com.github.improvtrainer.parser.ChordChartParser;
import com.github.improvtrainer.ui.GuitarView;
import com.github.improvtrainer.ui.MetronomeView;
import com.github.improvtrainer.ui.PianoView;

public class MainActivity extends AppCompatActivity {

//    private Button buttonGuitar;
//    private Button buttonPiano;
    private ImageView buttonStop;
    private ImageView buttonPlay;
    private ImageView buttonPause;
    private EditText editTempo;
    private TextView chordDisplayPiano;
    private TextView chordUpcomingDisplayPiano;
    private TextView chordDisplayGuitar;
    private TextView chordUpcomingDisplayGuitar;
    private PianoView pianoView;
    private GuitarView guitarView;
    private MetronomeView metronomeView;

    private Song song;
    private SongDisplay songDisplay;
    private BeatTimer beatTimer;
    private BackingTrackPlayer backingTrackPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        backingTrackPlayer = new BackingTrackPlayer(this, R.raw.all_of_me_mp3);

        findViews();
        parseChordChart();
        initializeSongDisplay();
        addPlaybackButtonListeners();
    }

    private void findViews() {
        buttonStop = findViewById(R.id.button_stop);
        buttonPlay = findViewById(R.id.button_play);
        buttonPause = findViewById(R.id.button_pause);
        editTempo = findViewById(R.id.tempo_edit);
        chordDisplayPiano = findViewById(R.id.display_chord_piano);
        chordUpcomingDisplayPiano = findViewById(R.id.display_upcoming_chord_piano);
        pianoView = findViewById(R.id.view_piano);
        guitarView = findViewById(R.id.view_guitar);
        chordDisplayGuitar = findViewById(R.id.display_chord_guitar);
        chordUpcomingDisplayGuitar = findViewById(R.id.display_upcoming_chord_guitar);
        metronomeView = findViewById(R.id.view_metronome);
    }

    private void addPlaybackButtonListeners() {
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerNeedsInitializing()) {
                    String tempoString = editTempo.getText().toString();
                    if (tempoString == null || tempoString.isEmpty()) {
                        alert("Must specify tempo", "Please specify a tempo in bpm");
                        return;
                    } else if (song == null) {
                        alert("Must input song", "Please input a song");
                        return;
                    } else {
                        Integer tempo = Integer.valueOf(tempoString);
                        initializeTimer(tempo);
                    }
                }
                startTimer();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
            }
        });
    }

    private void initializeSongDisplay() {
        songDisplay = new SongDisplay(song, new CandidateNoteService());
        songDisplay.addCandidateNotesListeners(guitarView, pianoView);
        songDisplay.addChordChangeEventListeners(new ChordChangeEventListener() {
            @Override
            public void onChordChange(Chord chord) {
                String text = chordText(chord);
                chordDisplayPiano.setText(text);
                chordDisplayGuitar.setText(text);
            }

            @Override
            public void onUpcomingChordChange(Chord chord) {
                String text = chordText(chord);
                chordUpcomingDisplayPiano.setText(text);
                chordUpcomingDisplayGuitar.setText(text);
            }

            private String chordText(Chord chord) {
                if (chord != null) {
                    return chord.getRoot().toString() + chord.getQuality().getDisplayName();
                } else {
                    return "";
                }
            }
        });
    }

    private void initializeTimer(int bpm) {
        Metronome metronome = new Metronome(this, metronomeView);
        this.beatTimer = new BeatTimer(bpm, metronome, songDisplay);
    }

    private boolean timerNeedsInitializing() {
        return beatTimer == null;
    }

    private void startTimer() {
        backingTrackPlayer.start();
        if (beatTimer != null) {
            beatTimer.start();
        }
    }

    private void pauseTimer() {
        backingTrackPlayer.stop();
        if (beatTimer != null) {
            beatTimer.pause();
        }
    }

    private void stopTimer() {
        backingTrackPlayer.stop();
        if (beatTimer != null) {
            beatTimer.stop();
            beatTimer = null; // reset beat timer so that next time around we'll create a new one with a different tempo
            if (songDisplay != null) {
                songDisplay.clearChordsAndCandidateNotes();
            }
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

    private void parseChordChart() {
        ChordChartParser chordChartParser = new ChordChartParser();
        song = chordChartParser.parse(getApplicationContext().getResources().openRawResource(R.raw.all_of_me));
    }
}
