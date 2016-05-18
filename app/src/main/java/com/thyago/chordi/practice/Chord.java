package com.thyago.chordi.practice;

/**
 * Created by thyago on 4/3/16.
 */
public class Chord {
    public static final int NO_FINGER = 0;

    public static final int STRING_QTY = 6;
    public static final int FRET_QTY = 5;

    public static final int FIRST_FINGER = 1;
    public static final int SECOND_FINGER = 2;
    public static final int THIRD_FINGER = 3;
    public static final int FOURTH_FINGER = 4;

    public static final int E = 0;
    public static final int A = 1;
    public static final int D = 2;
    public static final int G = 3;
    public static final int B = 4;
    public static final int e = 5;

    private int mChord[][];
    private boolean mPlayedStrings[];

    public Chord() {
        mChord = new int[STRING_QTY][FRET_QTY];
        mPlayedStrings = new boolean[STRING_QTY];
        zeroFill();
    }

    private void zeroFill() {
        for (int i = 0; i < STRING_QTY; i++) {
            for (int j = 0; j < FRET_QTY; j++) {
                mChord[i][j] = Chord.NO_FINGER;
            }
            mPlayedStrings[i] = true;
        }
    }

    public void setFinger(int string, int fret, int finger) {
        mChord[string][fret] = finger;
    }

    public void setPlayedString(int string, boolean flag) {
        mPlayedStrings[string] = flag;
    }

    public int[][] getChord() {
        return mChord;
    }
}
