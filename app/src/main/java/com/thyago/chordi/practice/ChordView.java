package com.thyago.chordi.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by thyago on 4/3/16.
 */
public class ChordView extends View {

    private static final String LOG_TAG = ChordView.class.getSimpleName();
    private static final int STRING_QTY = 6;
    private static final int STRING_WIDTH = 6;
    private static final int FINGER_RADIUS = 40;
    private static final int NUT_HEIGHT = 20;
    private static final int FRET_QTY = 4;
    private static final int MUTED_STRINGS_HEIGHT = 50;

    private Paint mPaintChordView;

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

        setBackgroundColor(Color.CYAN);

        int neckWidth = getWidth() - 2 * FINGER_RADIUS;
        int distance = neckWidth / (STRING_QTY - 1);
        int marginLeft = FINGER_RADIUS;

        int marginRight = getWidth() - FINGER_RADIUS;

        int marginTop = NUT_HEIGHT;
        int fretDistance = getHeight() / (FRET_QTY + 1);

        Log.d(LOG_TAG, "Distance: " + distance);
        Log.d(LOG_TAG, "Width: " + getWidth());

        drawNut(canvas, FINGER_RADIUS, 0, getWidth() - 2 * FINGER_RADIUS, NUT_HEIGHT);

        mPaintChordView.setColor(Color.GRAY);
        mPaintChordView.setStrokeWidth(STRING_WIDTH);

        for (int i = 0; i < STRING_QTY - 1; i++) {
            marginTop += fretDistance;
            canvas.drawLine(marginLeft, marginTop, marginRight, marginTop, mPaintChordView);
        }

        mPaintChordView.setStrokeWidth(STRING_WIDTH);

        for (int i = 0; i < STRING_QTY; i++) {
            mPaintChordView.setColor(Color.BLACK);
            canvas.drawLine(marginLeft, 0, marginLeft, getHeight() - MUTED_STRINGS_HEIGHT, mPaintChordView);
            drawMutedStringDiagram(canvas, marginLeft, getHeight() - MUTED_STRINGS_HEIGHT, MUTED_STRINGS_HEIGHT);
            marginLeft += distance;
        }
    }

    private void drawMutedStringDiagram(Canvas canvas, int centerX, int startY, int length) {
        mPaintChordView.setColor(Color.RED);
        canvas.drawRect(centerX - length / 2, startY, centerX + length / 2, startY + length, mPaintChordView);
        mPaintChordView.setColor(Color.BLUE);
        canvas.drawCircle(centerX, startY + length / 2, length / 3, mPaintChordView);
        mPaintChordView.setColor(Color.GREEN);
        canvas.drawLine(centerX - length / 2, startY, centerX + length / 2, startY + length, mPaintChordView);
        canvas.drawLine(centerX + length / 2, startY, centerX - length / 2, startY + length, mPaintChordView);
    }

    private void drawNut(Canvas canvas, int startX, int startY, int width, int height) {
        mPaintChordView.setColor(Color.BLACK);
        mPaintChordView.setStrokeWidth(STRING_WIDTH);
        canvas.drawRect(startX, startY, width + startX, height + startY, mPaintChordView);
    }

}
