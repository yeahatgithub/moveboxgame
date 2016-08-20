package com.yescorp.moveboxgame;

/**
 * Created by 612226 on 2016/8/15.
 */
public class GameState {
    private int mManRow;
    private int mManColumn;

    private StringBuffer[] mLabelInCells;

    public GameState(String[] initialState){
        mLabelInCells = new StringBuffer[initialState.length];
        for (int i = 0; i < initialState.length; i++)
            mLabelInCells[i] = new StringBuffer(initialState[i]);
    }

    public StringBuffer[] getLabelInCells() {
        return mLabelInCells;
    }


}
