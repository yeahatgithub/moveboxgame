package com.yescorp.moveboxgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 612226 on 2016/8/6.
 */
public class GameView extends View{
    private float mCellWidth;
    public static final int CELL_NUM_PER_LINE = 12;

    private int mManRow = 0;
    private int mManColumn = 0;
    private int mBoxRow = 5;      //箱子一开始处在游戏区域中间位置
    private int mBoxColumn = 5;

    private GameActivity mGameActivity;

    public GameView(Context context) {
        super(context);
        mGameActivity = (GameActivity) context;
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
        linePaint.setColor(Color.BLACK);
        for (int r = 0; r <= CELL_NUM_PER_LINE; r++)
            canvas.drawLine(0, r * mCellWidth, getWidth(), r * mCellWidth, linePaint);
        for (int c = 0; c <= CELL_NUM_PER_LINE; c++)
            canvas.drawLine(c * mCellWidth, 0, c * mCellWidth, CELL_NUM_PER_LINE * mCellWidth, linePaint);

/*        //绘制搬运工
        Rect srcRect = new Rect(0, 0, GameBitmaps.ManBitmap.getWidth(), GameBitmaps.ManBitmap.getHeight());
        //Rect destRect = new Rect(0, 0, (int)mCellWidth, (int)mCellWidth);
        Rect destRect = getRect(mManRow, mManColumn);
        canvas.drawBitmap(GameBitmaps.ManBitmap, srcRect, destRect, null);

        //绘制箱子
        srcRect.set(0, 0, GameBitmaps.BoxBitmap.getWidth(), GameBitmaps.BoxBitmap.getHeight());
        destRect = getRect(mBoxRow, mBoxColumn);
        canvas.drawBitmap(GameBitmaps.BoxBitmap, srcRect, destRect, null);*/

        //根据游戏局面绘制游戏界面
        drawGameBoard(canvas);
    }

    private void drawGameBoard(Canvas canvas) {
        Rect srcRect;
        Rect destRect;
        StringBuffer [] labelInCells = mGameActivity.getCurrentState().getLabelInCells();
        for (int r = 0; r < labelInCells.length; r++)
            for (int c = 0; c < labelInCells[r].length(); c++){
                destRect = getRect(r, c);
                switch (labelInCells[r].charAt(c)){
                    case 'W':
                        srcRect = new Rect(0, 0, GameBitmaps.WallBitmap.getWidth(), GameBitmaps.WallBitmap.getHeight());
                        canvas.drawBitmap(GameBitmaps.WallBitmap, srcRect, destRect, null);
                        break;
                    case 'B':
                        srcRect = new Rect(0, 0, GameBitmaps.BoxBitmap.getWidth(), GameBitmaps.BoxBitmap.getHeight());
                        canvas.drawBitmap(GameBitmaps.BoxBitmap, srcRect, destRect, null);
                        break;
                    case 'F':
                        srcRect = new Rect(0, 0, GameBitmaps.FlagBitmap.getWidth(), GameBitmaps.FlagBitmap.getHeight());
                        canvas.drawBitmap(GameBitmaps.FlagBitmap, srcRect, destRect, null);
                        break;
                    case 'M':
                        srcRect = new Rect(0, 0, GameBitmaps.ManBitmap.getWidth(), GameBitmaps.ManBitmap.getHeight());
                        canvas.drawBitmap(GameBitmaps.ManBitmap, srcRect, destRect, null);
                        break;
                }
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return true;

        int touch_x = (int) event.getX();   //触摸点的x坐标
        int touch_y = (int) event.getY();   //触摸点的y坐标
        if (touch_blow_to_man(touch_x, touch_y, mManRow, mManColumn))  //按在下方
            handleDown();

        if (touch_right_to_man(touch_x, touch_y, mManRow, mManColumn))  //按在右侧
            handleRight();
        if (touch_above_to_man(touch_x, touch_y, mManRow, mManColumn))
            handleAbove();
        if (touch_left_to_man(touch_x, touch_y, mManRow, mManColumn))
            handleLeft();
        postInvalidate();
        return true;
    }

    private void handleLeft() {
        if (isBoxLeftToMan()){
            if (mBoxColumn > 0){
                mBoxColumn--;
                mManColumn--;
            }
        }else if (mManColumn > 0)
            mManColumn--;
    }

    private boolean isBoxLeftToMan() {
        return mBoxRow == mManRow && mBoxColumn == mManColumn - 1;
    }

    private void handleAbove() {
        if (isBoxAboveMan()){
            if (mBoxRow > 0){
                mBoxRow--;
                mManRow--;
            }
        } else if (mManRow > 0)
            mManRow--;
    }

    private boolean isBoxAboveMan() {
        return mBoxColumn == mManColumn && mBoxRow == mManRow - 1;
    }

    private void handleDown() {
        if (isBoxBlowMan()) {
            if (mBoxRow + 1 < CELL_NUM_PER_LINE) {
                mBoxRow++;
                mManRow++;
            }
        } else if (mManRow + 1 < CELL_NUM_PER_LINE)
            mManRow++;
    }

    private void handleRight() {
        if (isBoxRightToMan()) {
            if (mBoxColumn + 1 < CELL_NUM_PER_LINE) {
                mBoxColumn++;
                mManColumn++;
            }
        } else if (mManColumn + 1 < CELL_NUM_PER_LINE)
            mManColumn++;
    }

    private boolean isBoxRightToMan() {
        return mBoxRow == mManRow && mBoxColumn == mManColumn + 1;
    }

    private boolean isBoxBlowMan() {
        return mBoxColumn == mManColumn && mBoxRow == mManRow + 1;
    }


    private boolean touch_blow_to_man(int touch_x, int touch_y, int manRow, int manColumn) {
        int belowRow = manRow + 1;
        Rect belowRect = getRect(belowRow, manColumn);
        return belowRect.contains(touch_x, touch_y);
    }

    private boolean touch_above_to_man(int touch_x, int touch_y, int manRow, int manColumn) {
        int aboveRow = manRow - 1;
        Rect aboveRect = getRect(aboveRow, manColumn);
        return aboveRect.contains(touch_x, touch_y);
    }

    private boolean touch_left_to_man(int touch_x, int touch_y,  int manRow, int manColumn) {
        int leftColumn = manColumn - 1;
        Rect leftRect = getRect(manRow, leftColumn);
        return leftRect.contains(touch_x, touch_y);
    }

    private boolean touch_right_to_man(int touch_x, int touch_y, int manRow, int manColumn) {
        int rightColumn = manColumn + 1;
        Rect rightRect = getRect(manRow, rightColumn);
        return rightRect.contains(touch_x, touch_y);
    }

    @NonNull
    private Rect getRect(int row, int column) {
        int left = (int)(column * mCellWidth);
        int top = (int) (row * mCellWidth);
        int right = (int)((column + 1) * mCellWidth);
        int bottom = (int)((row + 1) * mCellWidth);
        return new Rect(left, top, right, bottom);
    }
}
