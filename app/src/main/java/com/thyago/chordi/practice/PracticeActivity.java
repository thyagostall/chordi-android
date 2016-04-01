package com.thyago.chordi.practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.thyago.chordi.R;

import org.greenrobot.eventbus.EventBus;

public class PracticeActivity extends AppCompatActivity {

    private static final String LOG_TAG = PracticeActivity.class.getSimpleName();

    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord);

        eventBus = EventBus.getDefault();
        eventBus.register(this);

//        Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//
//            }
//        };
//
//        Metronome metronome = new Metronome(handler);
//
//        metronome.setBeat(beats);
//        metronome.setNoteValue(noteValue);
//        metronome.setBpm(bpm);
//        metronome.setBeatSound(beatSound);
//        metronome.setSound(sound);
//
//        metronome.play();
    }

    public void onEvent(MetronomeStatus status) {
        Log.d(LOG_TAG, "metronome update");
    }

    public void onEvent(TimerStatus status) {
        Log.d(LOG_TAG, "timer update");
    }
}
