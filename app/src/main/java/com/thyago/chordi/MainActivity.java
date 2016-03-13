package com.thyago.chordi;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by thyago on 3/12/16.
 */
public class MainActivity extends Activity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
