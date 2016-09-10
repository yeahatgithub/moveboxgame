package com.yescorp.moveboxgame;

/**
 * Created by 612226 on 2016/8/15.
 */
public class GameState {
    private int mManRow;
    private int mManColumn;
    private int mBoxRow;
    private int mBoxColumn;
    private int mFlagRow;  //红旗行号
    private int mFlagColumn;  //红旗列号

    public int getManRow() {
        return mManRow;
    }

    public int getManColumn() {
        return mManColumn;
    }

    public int getBoxRow() {
        return mBoxRow;
    }

    public int getBoxColumn() {
        return mBoxColumn;
    }

    public void setBoxColumn(int boxColumn) {
        mBoxColumn = boxColumn;
    }

    public void setBoxRow(int boxRow) {
        mBoxRow = boxRow;
    }

    public void setManRow(int manRow) {
        mManRow = manRow;
    }

    public void setManColumn(int manColumn) {
        mManColumn = manColumn;
    }

    private StringBuffer[] mLabelInCells;

    //构造函数
    //什么时候执行？
    //   在生成GameState类实例的时候
    //参数：
    //   initialState  某一关的开局
    public GameState(String[] initialState){
        //为二维矩阵mLabelInCells分配存储空间。每个元素是StringBuffer的引用
        mLabelInCells = new StringBuffer[initialState.length];
        //为每一个元素分配StringBuffer对象
        for (int i = 0; i < initialState.length; i++)
            //生成第i+1个元素的StringBuffer对象
            mLabelInCells[i] = new StringBuffer(initialState[i]);
        get_gongren_chushi_weizhi();   //根据游戏开局初始化搬运工的位置
        get_XiangZi_ChuShi_WeiZhi();   //根据开局初始化箱子的位置。假设游戏界面中只有一个箱子。
        get_HongQi_ChuShi_WeiZhi();

    }

    public boolean isGameOver(){
        return mBoxRow == mFlagRow && mBoxColumn == mFlagColumn;
    }


    private void get_HongQi_ChuShi_WeiZhi() {
        for (int r = 0; r < GameView.CELL_NUM_PER_LINE; r++)
            for (int c = 0; c < GameView.CELL_NUM_PER_LINE; c++){
                if (mLabelInCells[r].charAt(c) == 'F')  {
                    mFlagRow = r;
                    mFlagColumn = c;
                    return;
                }
            }
    }

    //获得表示局面的二维矩阵
    public StringBuffer[] getLabelInCells() {
        return mLabelInCells;
    }
    //根据游戏开局初始化搬运工的位置
    public void get_gongren_chushi_weizhi() {
        //StringBuffer[] labelInCells = getLabelInCells();
        for (int r = 0; r < GameView.CELL_NUM_PER_LINE; r++)
            for (int c = 0; c < GameView.CELL_NUM_PER_LINE; c++){
                if (mLabelInCells[r].charAt(c) == 'M')  {
                    mManRow = r;
                    mManColumn = c;
                    return;
                }
            }
    }

    //根据开局初始化箱子的位置
    public void get_XiangZi_ChuShi_WeiZhi(){
        for (int r=0; r<GameView.CELL_NUM_PER_LINE; r++)
            for (int c = 0; c < GameView.CELL_NUM_PER_LINE; c++){
                if (mLabelInCells[r].charAt(c) == 'B'){
                    mBoxRow = r;
                    mBoxColumn = c;
                    return;
                }
            }

    }

    public void handleLeft() {
        if (isBoxLeftToMan()){
            if (mBoxColumn > 0  && mLabelInCells[mBoxRow].charAt(mBoxColumn - 1) != 'W'){
                mLabelInCells[mBoxRow].setCharAt(mBoxColumn, ' ');
                mLabelInCells[mBoxRow].setCharAt(mBoxColumn - 1, 'B');
                mBoxColumn--;
                mLabelInCells[mManRow].setCharAt(mManColumn, ' ');
                mLabelInCells[mManRow].setCharAt(mManColumn - 1, 'M');
                mManColumn--;
            }
        }else if (mManColumn > 0 && mLabelInCells[mManRow].charAt(mManColumn - 1) != 'W') {
            mLabelInCells[mManRow].setCharAt(mManColumn, ' ');
            mLabelInCells[mManRow].setCharAt(mManColumn - 1, 'M');
            mManColumn--;
        }
    }

    public boolean isBoxLeftToMan() {
        return mBoxRow == mManRow && mBoxColumn == mManColumn - 1;
    }

    public void handleAbove() {
        //StringBuffer[] labelInCells = mGameActivity.getCurrentState().getLabelInCells();
        if (isBoxAboveMan()){
            if (mBoxRow > 0 && mLabelInCells[mBoxRow - 1].charAt(mBoxColumn) != 'W'){  //箱子没出界 并且 到达单元格不是墙
                //移动箱子，修改受影响的单元格所对应的矩阵元素的值
                mLabelInCells[mBoxRow].setCharAt(mBoxColumn, ' ');
                mLabelInCells[mBoxRow - 1].setCharAt(mBoxColumn, 'B');
                mBoxRow--;  //修改记录箱子的位置的变量的值

                //工人走动，修改受影响的单元格所对应的矩阵元素的值
                mLabelInCells[mManRow].setCharAt(mManColumn, ' ');
                mLabelInCells[mManRow - 1].setCharAt(mManColumn, 'M');
                mManRow--; //修改记录工人的位置的变量的值
            }
        } else if (mManRow > 0 && mLabelInCells[mManRow - 1].charAt(mManColumn) != 'W') { //搬运工没出界，并且 到达单元格不是墙
            //工人走动，修改受影响的单元格所对应的矩阵元素的值
            mLabelInCells[mManRow].setCharAt(mManColumn, ' ');
            mLabelInCells[mManRow - 1].setCharAt(mManColumn, 'M');
            mManRow--; //修改记录工人的位置的变量的值
        }
    }

    public boolean isBoxAboveMan() {
        return mBoxColumn == mManColumn && mBoxRow == mManRow - 1;
    }

    public void handleDown() {
        if (isBoxBlowMan()) {
            if (mBoxRow + 1 < GameView.CELL_NUM_PER_LINE) {
                //移动箱子，修改受影响的单元格所对应的矩阵元素的值
                mLabelInCells[mBoxRow].setCharAt(mBoxColumn, ' ');
                mLabelInCells[mBoxRow + 1].setCharAt(mBoxColumn, 'B');
                mBoxRow++;
                //工人走动，修改受影响的单元格所对应的矩阵元素的值
                mLabelInCells[mManRow].setCharAt(mManColumn, ' ');
                mLabelInCells[mManRow + 1].setCharAt(mManColumn, 'M');
                mManRow++;
            }
        } else if (mManRow + 1 < GameView.CELL_NUM_PER_LINE) {
            mLabelInCells[mManRow].setCharAt(mManColumn, ' ');
            mLabelInCells[mManRow + 1].setCharAt(mManColumn, 'M');
            mManRow++;
        }
    }

    public void handleRight() {
        //StringBuffer[] labelInCells = mGameActivity.getCurrentState().getLabelInCells();
        if (isBoxRightToMan()) {
            if (mBoxColumn + 1 < GameView.CELL_NUM_PER_LINE) {
                mLabelInCells[mBoxRow].setCharAt(mBoxColumn, ' ');
                mLabelInCells[mBoxRow].setCharAt(mBoxColumn + 1, 'B');
                mBoxColumn++;
                mLabelInCells[mManRow].setCharAt(mManColumn, ' ');
                mLabelInCells[mManRow].setCharAt(mManColumn + 1, 'M');
                mManColumn++;
            }
        } else if (mManColumn + 1 < GameView.CELL_NUM_PER_LINE) {
            mLabelInCells[mManRow].setCharAt(mManColumn, ' ');
            mLabelInCells[mManRow].setCharAt(mManColumn + 1, 'M');
            mManColumn++;
        }
    }

    public boolean isBoxRightToMan() {
        return mBoxRow == mManRow && mBoxColumn == mManColumn + 1;
    }

    public boolean isBoxBlowMan() {
        return mBoxColumn == mManColumn && mBoxRow == mManRow + 1;
    }


}
