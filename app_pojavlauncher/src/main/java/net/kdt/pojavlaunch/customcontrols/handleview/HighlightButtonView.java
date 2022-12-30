package net.kdt.pojavlaunch.customcontrols.handleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class HighlightButtonView extends View {
	private final Paint mRectPaint = new Paint();
	private final Paint mLinePaint = new Paint();

	private int mOpacity = 0;

	public HighlightButtonView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		mRectPaint.setColor(Color.BLUE);
		mRectPaint.setStyle(Paint.Style.STROKE);
		mRectPaint.setStrokeWidth(7);
		mRectPaint.setAlpha(mOpacity);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mRectPaint);

		mLinePaint.setColor(Color.BLUE);
		mLinePaint.setStyle(Paint.Style.STROKE);
		mLinePaint.setStrokeWidth(3);
		mLinePaint.setAlpha(mOpacity);
		int mLineOffset = 40;
		for (int offset = mLineOffset; offset < getWidth()+getHeight(); offset+= mLineOffset)
			canvas.drawLine(offset, 0, 0, offset, mLinePaint);
	}

	public void setOpacity(int opacity) {
		mOpacity = opacity;
		invalidate();
	}
}
