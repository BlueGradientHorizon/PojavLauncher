package net.kdt.pojavlaunch.customcontrols.handleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import net.kdt.pojavlaunch.R;
import net.kdt.pojavlaunch.customcontrols.buttons.ControlButton;
import net.kdt.pojavlaunch.customcontrols.buttons.ControlInterface;

public class ControlHandleView extends View {
    public ControlHandleView(Context context) {
        super(context);
        init();
    }

    public ControlHandleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private final Drawable mDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_view_handle, getContext().getTheme());
    private ControlInterface mView;
    private float mXOffset, mYOffset;
    private final ViewTreeObserver.OnPreDrawListener mPositionListener = new ViewTreeObserver.OnPreDrawListener() {
        @Override
        public boolean onPreDraw() {
            if(mView == null || !mView.getControlView().isShown()){
                hide();
                return true;
            }

            setX(mView.getControlView().getX() + mView.getControlView().getWidth());
            setY(mView.getControlView().getY() + mView.getControlView().getHeight());
            return true;
        }
    };

    private void init(){
        int size = getResources().getDimensionPixelOffset(R.dimen._22sdp);
        mDrawable.setBounds(0,0,size,size);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(size, size);
        setLayoutParams(params);
        setBackground(mDrawable);
        setTranslationZ(10.5F);
    }

    public void setControlButton(ControlInterface controlInterface){
        if(mView != null) mView.getControlView().getViewTreeObserver().removeOnPreDrawListener(mPositionListener);

        setVisibility(VISIBLE);
        mView = controlInterface;
        mView.getControlView().getViewTreeObserver().addOnPreDrawListener(mPositionListener);

        setX(controlInterface.getControlView().getX() + controlInterface.getControlView().getWidth());
        setY(controlInterface.getControlView().getY() + controlInterface.getControlView().getHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                mXOffset = event.getX();
                mYOffset = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                setX(getX() + event.getX() - mXOffset);
                setY(getY() + event.getY() - mYOffset);

                float mWidth = getX() - mView.getControlView().getX();
                float mHeight = getY() - mView.getControlView().getY();

                System.out.println("mWidth="+ mWidth +" mHeight="+ mHeight);

                if (mWidth >= 0)
                    mView.getProperties().setWidth(mWidth);
                if (mHeight >= 0)
                    mView.getProperties().setHeight(mHeight);
                mView.regenerateDynamicCoordinates();
                break;
        }

        return true;
    }

    public void hide(){
        if(mView != null)
            mView.getControlView().getViewTreeObserver().removeOnPreDrawListener(mPositionListener);
        setVisibility(GONE);
    }
}
