package com.maomao.recycleviewtest;

import android.content.Context;
<<<<<<< HEAD
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mao on 2016/2/24.
 */
public class MyRecyclerView extends RecyclerView{

    /** Ambient light intensity */
    private static final int AMBIENT_LIGHT = 55;
    /** Diffuse light intensity */
    private static final int DIFFUSE_LIGHT = 200;
    /** Specular light intensity */
    private static final float SPECULAR_LIGHT = 70;
    /** Shininess constant */
    private static final float SHININESS = 200;
    /** The max intensity of the light */
    private static final int MAX_INTENSITY = 0xFF;

    private final Camera mCamera = new Camera();
    private final Matrix mMatrix = new Matrix();
    /** Paint object to draw with */
    private Paint mPaint;
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {

        // get top left coordinates
        final int top = child.getTop();
        final int left = child.getLeft();
        Bitmap bitmap = child.getDrawingCache();
        if (bitmap == null) {
            child.setDrawingCacheEnabled(true);
            child.buildDrawingCache();
            bitmap = child.getDrawingCache();
        }

        final int centerY = child.getHeight() / 2;
        final int centerX = child.getWidth() / 2;
        final int radius = getHeight() / 2;
        final int absParentCenterY = getTop() + getHeight() / 2;
        final int absChildCenterY = child.getTop() + centerX;
        final int distanceY = absParentCenterY - absChildCenterY;
        final int absDistance = Math.min(radius, Math.abs(distanceY));

        final float translateZ = (float) Math.sqrt((radius * radius) - (absDistance * absDistance));

        double radians = Math.acos((float) absDistance / radius);
        double degree = 90 - (180 / Math.PI) * radians;

        mCamera.save();
        mCamera.translate(0, 0, radius - translateZ);
        mCamera.rotateX((float) degree); // remove this line..
        if (distanceY < 0) {
            degree = 360 - degree;
        }
        mCamera.rotateY((float) degree); // and change this to rotateX() to get a
        // wheel like effect
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        // create and initialize the paint object
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setFilterBitmap(true);
        }

        //highlight elements in the middle
        //mPaint.setColorFilter(calculateLight((float) degree));

        mMatrix.preTranslate(-centerX, -centerY);
        mMatrix.postTranslate(centerX, centerY);
        mMatrix.postTranslate(left, top);
        canvas.drawBitmap(bitmap, mMatrix, mPaint);
        return false;
=======
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by MaoTong on 2016/2/24.
 */
public class MyRecyclerView extends LinearLayoutManager {

    private int mFirstVisiblePosition = 0;
    private int mLastVisiblePosition = 0;

    public int getFirstVisiblePosition() {
        return mFirstVisiblePosition;
    }

    public void setFirstVisiblePosition(int mFirstVisiblePosition) {
        this.mFirstVisiblePosition = mFirstVisiblePosition;
    }

    public int getmLastVisiblePosition() {
        return mLastVisiblePosition;
    }

    public void setmLastVisiblePosition(int mLastVisiblePosition) {
        this.mLastVisiblePosition = mLastVisiblePosition;
    }

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
       // Log.e("MT", "");

        View view = recycler.getViewForPosition(0);

        Canvas canvas = new Canvas();
        Paint paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.WHITE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
        canvas.drawRect(0 , 0 , 100 , 100 , paint);
        view.draw(canvas);
        view.setBackgroundColor(Color.RED);
        addView(view);

/*        View view = recycler.getViewForPosition(0);
        view.measure(100 , 100);
        addView(view);*/
>>>>>>> origin/master
    }
}
