package com.thyago.chordi;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ChordActivity extends AppCompatActivity {

    private static final String LOG_TAG = ChordActivity.class.getSimpleName();

    private short bpm = 100;
    private short noteValue = 4;
    private short beats = 4;

    private double beatSound = 6440;
    private double sound = 6440;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord);

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

            }
        };

        Metronome metronome = new Metronome(handler);

        metronome.setBeat(beats);
        metronome.setNoteValue(noteValue);
        metronome.setBpm(bpm);
        metronome.setBeatSound(beatSound);
        metronome.setSound(sound);

        metronome.play();
    }
}
