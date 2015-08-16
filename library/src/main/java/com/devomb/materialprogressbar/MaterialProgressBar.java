package com.devomb.materialprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ombrax on 18/07/2015.
 */
public class MaterialProgressBar extends View {

    //region declaration
    //region variable
    private Mode mode;
    private float progress;

    private int progressColor;
    private int backgroundColor;
    private float backgroundTransparency;
    //endregion

    //region inner field
    private int width;
    private int height;

    private Paint progressPaint;
    private Paint backgroundPaint;

    private boolean limitReached;
    //endregion
    //endregion

    //region constructor
    public MaterialProgressBar(Context context) {
        super(context);
        init(null);
    }

    public MaterialProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MaterialProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    //endregion

    //region setup
    private void init(AttributeSet set) {
        TypedArray attrs = getContext().obtainStyledAttributes(set, R.styleable.MaterialProgressBar);

        mode = Mode.values()[attrs.getInt(R.styleable.MaterialProgressBar_progress_mode, 0)];
        progressColor = attrs.getColor(R.styleable.MaterialProgressBar_progress_color, Color.BLUE);
        backgroundColor = attrs.getColor(R.styleable.MaterialProgressBar_background_color, Color.DKGRAY);
        backgroundTransparency = attrs.getFloat(R.styleable.MaterialProgressBar_background_transparency, 0.5f);
        progress = 0.5f;

        createResources();
    }
    //endregion

    //region getter setter
    //region getter
    public Mode getMode() {
        return mode;
    }

    public float getProgress() {
        return progress;
    }

    public int getProgressColor() {
        return progressColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public float getBackgroundTransparency() {
        return backgroundTransparency;
    }
    //endregion

    //region setter
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setProgress(float progress) {
        if(progress > 1.0f){
            if(!limitReached) {
                limitReached = true;
                this.progress = 1.0f;
                invalidate();
            }
        }else{
            this.progress = progress;
            invalidate();
        }
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public void setProgressBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setProgressBackgroundTransparency(float backgroundTransparency) {
        this.backgroundTransparency = backgroundTransparency;
    }
    //endregion
    //endregion

    //region measure
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }
    //endregion

    //region draw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        switch (mode) {
            case HORIZONTAL:
                float progressWidth = width * progress;
                canvas.drawRect(0, 0, progressWidth, height, progressPaint);
                canvas.drawRect(progressWidth, 0, width, height, backgroundPaint);
                break;
            case VERTICAL:
                float progressHeight = height * progress;
                canvas.drawRect(0, 0, width, height - progressHeight, backgroundPaint);
                canvas.drawRect(0, height - progressHeight, width, height, progressPaint);
                System.out.println("New progress Length: " + progressHeight + "/" + height);
                break;
        }
    }
    //endregion

    //region helper
    private void createResources() {
        progressPaint = new Paint();
        progressPaint.setColor(progressColor);
        progressPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setAlpha(Math.round(255 * backgroundTransparency));
        backgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    //endregion

    //region enum
    public enum Mode {
        HORIZONTAL,
        VERTICAL
    }
    //endregion
}

