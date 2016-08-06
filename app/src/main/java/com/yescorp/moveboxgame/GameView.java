package com.yescorp.moveboxgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by 612226 on 2016/8/6.
 */
public class GameView extends View{
    private float mCellWidth;
    public static final int CELL_NUM_PER_LINE = 12;

    public GameView(Context context) {
        super(context);
        GameBitmaps.loadGameBitmaps(getResources());
    }

    //当GameView实例的尺寸发生变化，就会调用onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCellWidth = w / CELL_NUM_PER_LINE;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //背景色
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.background));
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);

        //绘制游戏区域
        Paint linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.separate_line));
        for (int r = 0; r <= CELL_NUM_PER_LINE; r++)
            canvas.drawLine(0, r * mCellWidth, getWidth(), r * mCellWidth, linePaint);
        for (int c = 0; c <= CELL_NUM_PER_LINE; c++)
            canvas.drawLine(c * mCellWidth, 0, c * mCellWidth, CELL_NUM_PER_LINE * mCellWidth, linePaint);

        //绘制搬运工
        Rect srcRect = new Rect(0, 0, GameBitmaps.ManBitmap.getWidth(), GameBitmaps.ManBitmap.getHeight());
        Rect destRect = new Rect(0, 0, (int)mCellWidth, (int)mCellWidth);
        canvas.drawBitmap(GameBitmaps.ManBitmap, srcRect, destRect, null);
    }
}
