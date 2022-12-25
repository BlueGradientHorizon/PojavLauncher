package net.kdt.pojavlaunch.customcontrols;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class HighlightButtonView extends View {
	private final Paint mRectPaint = new Paint();

	public HighlightButtonView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.d("canvasDebug", "onDraw: HighlightButtonView");
		mRectPaint.setColor(Color.BLUE);
		mRectPaint.setStyle(Paint.Style.STROKE);
		mRectPaint.setStrokeWidth(5);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mRectPaint);
	}
}
