package com.thyago.chordi.practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.thyago.chordi.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Random;

public class PracticeActivity extends AppCompatActivity {

    private static final String LOG_TAG = PracticeActivity.class.getSimpleName();

    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord);

        //MVP for the timer
        //MVP for the metronome
    }
}
