package com.yescorp.moveboxgame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 612226 on 2016/8/15.
 */
public class GameLevels {
    public static final int DEFAULT_ROW_NUM = 12;
    public static final int DEFAULT_COLUMN_NUM = 12;
    public static ArrayList<String[]> OriginalLevels = new ArrayList<>();

    //游戏区单元格放了什么
    public static final char NOTHING = ' ';         //该单元格啥也没有
    public static final char BOX = 'B';             //该单元格放的是箱子
    public static final char FLAG = 'F';            //红旗，表示箱子的目的地
    public static final char MAN = 'M';              //搬运工
    public static final char WALL = 'W';             //墙
    public static final char MAN_FLAG = 'R';        //搬运工 + 红旗
    public static final char BOX_FLAG = 'X';        //箱子 + 红旗

    public static final String [] LEVEL_1 = {
            "WWWWWWWWWWWW",
            "W         FW",
            "W          W",
            "W          W",
            "W   WWWW   W",
            "W          W",
            "W    B     W",
            "W    M     W",
            "W          W",
            "W          W",
            "W          W",
            "WWWWWWWWWWWW"
    };
    public static final String [] LEVEL_2 = {
            "            ",
            "            ",
            "  WWWWWWW   ",
            "  W FFB W   ",
            "  W W B W   ",
            "  W W W W   ",
            "  W BMW W   ",
            "  WFB   W   ",
            "  WFWWWWW   ",
            "  WWW       ",
            "            ",
            "            "
    };

    public static void loadGameLevels(){
        OriginalLevels.add(LEVEL_1);
        OriginalLevels.add(LEVEL_2);
    }

    public static List<String> getLevelList(){
        List<String> levelList = new ArrayList<>();
        int levelNum = OriginalLevels.size();
        for (int i = 1; i <= levelNum; i++){
            levelList.add(new String("第" + i + "关"));
        }

        return levelList;
    }

    public static String [] getLevel(int level){
        return OriginalLevels.get(level - 1);
    }

}
