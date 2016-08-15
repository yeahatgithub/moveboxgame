package com.yescorp.moveboxgame;

/**
 * Created by 612226 on 2016/8/15.
 */
public class GameState {
    private int mManRow;
    private int mManColumn;

    private String[] mLabelInCells;

    public GameState(String[] initialState){
        mLabelInCells = initialState;
    }

    public String[] getLabelInCells() {
        return mLabelInCells;
    }


}
