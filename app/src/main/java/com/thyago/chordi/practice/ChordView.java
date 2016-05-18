package com.thyago.chordi.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by thyago on 4/3/16.
 */
public class ChordView extends View {

    private static final String LOG_TAG = ChordView.class.getSimpleName();

    private static final int STRING_QTY = Chord.STRING_QTY;
    private static final int STRING_WIDTH = 4;
    private static final int FRET_WIDTH = 3;
    private static final int MARGIN = 65;
    private static final int NUT_HEIGHT = 20;
    private static final int FRET_QTY = Chord.FRET_QTY;
    private static final int MUTED_STRINGS_HEIGHT = 50;

    private Paint mPaintChordView;

    private int mStringDistances;
    private int mFretDistance;
    private int mMarginLeft;
    private int mMarginTop;

    private int mStartFret = 30;
    private int mFingerRadius = 40;

    public ChordView(Context context) {
        super(context);
        init(null, 0);
    }

    public ChordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ChordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        mPaintChordView = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mMarginLeft = MARGIN;
        mMarginTop = NUT_HEIGHT;

        int neckWidth = getWidth() - 2 * MARGIN;
        mStringDistances = neckWidth / (STRING_QTY - 1);
        mFretDistance = getHeight() / (FRET_QTY + 1);
        mFingerRadius = Math.min((int) (mStringDistances * 0.6), (int) (mFretDistance * 0.4));

        drawFrets(canvas);
        drawNut(canvas, MARGIN, 0, getWidth() - 2 * MARGIN, NUT_HEIGHT);

        drawStrings(canvas);

        int chord[][] = getTestChord();
        drawChord(canvas, chord);
        drawStringsToBePlayed(canvas, new boolean[]{false, false, true, true, true, true});
    }

    private void drawStringsToBePlayed(Canvas canvas, boolean muted[]) {
        int left = mMarginLeft;
        for (int i = 0; i < STRING_QTY; i++) {
            if (muted[i]) {
                drawNought(canvas, left, getHeight() - MUTED_STRINGS_HEIGHT, MUTED_STRINGS_HEIGHT);
            } else {
                drawCross(canvas, left, getHeight() - MUTED_STRINGS_HEIGHT, MUTED_STRINGS_HEIGHT);
            }
            left += mStringDistances;
        }
    }

    private void drawChord(Canvas canvas, int chord[][]) {
        Log.e(LOG_TAG, "Test Method -- Mock Data");

        int f = 8;
        for (int i = 0; i < STRING_QTY; i++) {
            for (int j = 0; j < FRET_QTY; j++) {
                if (chord[i][j] != Chord.NO_FINGER) {
                    drawFingerTip(canvas, i, j, chord[i][j]);
                }
            }
        }

        drawBar(canvas, 3, 2, 5);
    }

    private void drawStrings(Canvas canvas) {
        mPaintChordView.setStrokeWidth(STRING_WIDTH);

        if (mStartFret > 0) {
            Shader shader = new LinearGradient(0, 0, 0, mFretDistance, Color.LTGRAY, Color.BLACK, Shader.TileMode.CLAMP);
            mPaintChordView.setShader(shader);
        }

        int left = mMarginLeft;
        for (int i = 0; i < STRING_QTY; i++) {
            mPaintChordView.setColor(Color.BLACK);
            mPaintChordView.setStyle(Paint.Style.FILL);
            canvas.drawLine(left, 0, left, getHeight() - 2 * MUTED_STRINGS_HEIGHT, mPaintChordView);
            left += mStringDistances;
        }

        Shader shader = new LinearGradient(0, getHeight() - 2 * MUTED_STRINGS_HEIGHT, 0, getHeight() - MUTED_STRINGS_HEIGHT, Color.BLACK, Color.LTGRAY, Shader.TileMode.CLAMP);
        mPaintChordView.setShader(shader);

        left = mMarginLeft;
        for (int i = 0; i < STRING_QTY; i++) {
            mPaintChordView.setColor(Color.BLACK);
            mPaintChordView.setStyle(Paint.Style.FILL);
            canvas.drawLine(left, getHeight() - 2 * MUTED_STRINGS_HEIGHT, left, getHeight() - MUTED_STRINGS_HEIGHT, mPaintChordView);
            left += mStringDistances;
        }

        mPaintChordView.setShader(null);
    }

    private void drawFrets(Canvas canvas) {
        mPaintChordView.setColor(Color.GRAY);
        mPaintChordView.setStrokeWidth(FRET_WIDTH);

        int marginRight = getWidth() - MARGIN;
        int top = mMarginTop;

        for (int i = 0; i < STRING_QTY - 1; i++) {
            top += mFretDistance;
            canvas.drawLine(mMarginLeft, top, marginRight, top, mPaintChordView);
        }
    }

    private void drawCross(Canvas canvas, int centerX, int startY, int length) {
        mPaintChordView.setColor(Color.BLACK);
        mPaintChordView.setStyle(Paint.Style.STROKE);

        int r = length / 4;
        int centerY = startY + (length / 2);
        canvas.drawLine(centerX - r, centerY - r, centerX + r, centerY + r, mPaintChordView);
        canvas.drawLine(centerX + r, centerY - r, centerX - r, centerY + r, mPaintChordView);
    }

    private void drawNought(Canvas canvas, int centerX, int startY, int length) {
        mPaintChordView.setColor(Color.BLACK);
        mPaintChordView.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(centerX, startY + length / 2, length / 4, mPaintChordView);
    }

    private void drawNut(Canvas canvas, int startX, int startY, int width, int height) {
        mPaintChordView.setColor(Color.BLACK);
        mPaintChordView.setStyle(Paint.Style.FILL);

        if (mStartFret <= 0) {
            canvas.drawRect(startX, startY, width + startX, height + startY, mPaintChordView);
        } else {
            Rect rect = new Rect();
            String startFret = String.valueOf(mStartFret);
            mPaintChordView.getTextBounds(startFret, 0, startFret.length(), rect);
            canvas.drawText(startFret, mMarginLeft - rect.width() - 10, (mFretDistance + rect.height()) / 2, mPaintChordView);
        }
    }

    private void drawBar(Canvas canvas, int fretNumber, int startString, int stopString) {
        mPaintChordView.setStyle(Paint.Style.FILL);
        int startX = startString * mStringDistances + mMarginLeft;
        int stopX = stopString * mStringDistances + mMarginLeft;
        int y = fretNumber * mFretDistance + mMarginTop;
        int r = (int) (mFingerRadius * 0.75);

        y += mFretDistance / 2;
        canvas.drawCircle(startX, y, r, mPaintChordView);
        canvas.drawRect(startX, y - r, stopX, y + r, mPaintChordView);
        canvas.drawCircle(stopX, y, r, mPaintChordView);
    }

    private void drawFingerTip(Canvas canvas, int stringNumber, int fretNumber, int fingerNumber) {
        mPaintChordView.setStyle(Paint.Style.FILL);
        int x = stringNumber * mStringDistances + mMarginLeft;
        int y = fretNumber * mFretDistance + mMarginTop;
        int r = (int) (mFingerRadius * 0.75);

        y += mFretDistance / 2;
        canvas.drawCircle(x, y, r, mPaintChordView);

        mPaintChordView.setAntiAlias(true);
        mPaintChordView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        mPaintChordView.setTextSize(48);
        mPaintChordView.setColor(Color.WHITE);

        Rect areaRect = new Rect(x - r, y - r, x + r, y + r);

        String number = String.valueOf(fingerNumber);

        RectF bounds = new RectF(areaRect);
        bounds.right = mPaintChordView.measureText(number, 0, number.length());
        bounds.bottom = mPaintChordView.descent() - mPaintChordView.ascent();

        bounds.left += (areaRect.width() - bounds.right) / 2.f;
        bounds.top += (areaRect.height() - bounds.bottom) / 2.f;

        canvas.drawText(number, bounds.left, bounds.top - mPaintChordView.ascent(), mPaintChordView);

        mPaintChordView.setColor(Color.BLACK);
    }

    public int[][] getTestChord() {
        Log.e(LOG_TAG, "Test Method -- Mock Data");
        int result[][] = new int[STRING_QTY][FRET_QTY];

        for (int i = 0; i < STRING_QTY; i++) {
            for (int j = 0; j < FRET_QTY; j++) {
                result[i][j] = Chord.NO_FINGER;
            }
        }
        result[5][0] = Chord.FIRST_FINGER;
        result[4][1] = Chord.SECOND_FINGER;
        result[3][2] = Chord.THIRD_FINGER;
        result[4][2] = Chord.FOURTH_FINGER;

        return result;
    }
}
