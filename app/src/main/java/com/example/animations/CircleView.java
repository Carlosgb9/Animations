package com.example.animations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CircleView extends View {
    private static final float STROKE_WIDTH = 5f;
    private static final int CIRCLE_COLOR = 0xFFFF0000; // Red color
    private Paint paint;
    private float circleCenterX;
    private float circleCenterY;
    private float circleRadius;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(CIRCLE_COLOR);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WIDTH);

        // Set initial circle properties
        circleCenterX = -200;
        circleCenterY = -200;
        circleRadius = 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(circleCenterX, circleCenterY, circleRadius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Save the initial touch position
                circleCenterX = event.getX();
                circleCenterY = event.getY();
                invalidate(); // Redraw the view
                break;
            case MotionEvent.ACTION_MOVE:
                // Update the circle center position
                circleCenterX = event.getX();
                circleCenterY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                // Hide the circle when the user releases their finger
                circleCenterX = -1;
                circleCenterY = -1;
                invalidate(); // Redraw the view
                break;
            default:
                return super.onTouchEvent(event);
        }
        return true;
    }
}
