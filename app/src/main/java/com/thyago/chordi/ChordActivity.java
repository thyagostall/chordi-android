package com.thyago.chordi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ChordActivity extends AppCompatActivity {

    private static final String LOG_TAG = ChordActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord);

        Log.d(LOG_TAG, "OK");
    }
}
