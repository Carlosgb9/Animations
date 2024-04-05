package com.example.animations;

import android.animation.TypeEvaluator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;

public class PathEvaluator implements TypeEvaluator<PointF> {
    private Path path;
    public PathEvaluator(Path path) {
        this.path = path;
    }
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        PathMeasure pathMeasure = new PathMeasure((android.graphics.Path) path, false);
        float[] point = new float[2];
        pathMeasure.getPosTan((pathMeasure.getLength() * fraction), point, null);
        return new PointF(point[0], point[1]);
    }
}
