package com.thyago.chordi.practice;

/**
 * Created by thyago on 4/3/16.
 */
public interface PracticePresenter {
    void play();
    void pause();

    void next();
    boolean hasNext();

    void prev();
    boolean hasPrevious();

    void setBpm(int bpm);
    int getBpm();
}
