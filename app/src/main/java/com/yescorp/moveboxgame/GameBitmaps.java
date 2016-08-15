package com.yescorp.moveboxgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by 612226 on 2016/8/6.
 */
public class GameBitmaps {
    public static Bitmap ManBitmap = null;
    public static Bitmap BoxBitmap = null;
    public static Bitmap WallBitmap = null;
    public static Bitmap FlagBitmap = null;

    public static void loadGameBitmaps(Resources res){
        if (ManBitmap == null)
            ManBitmap = BitmapFactory.decodeResource(res, R.drawable.eggman_48x48);
        if (BoxBitmap == null)
            BoxBitmap = BitmapFactory.decodeResource(res, R.drawable.box_48x48);
        if (WallBitmap == null)
            WallBitmap = BitmapFactory.decodeResource(res, R.drawable.stone_48x48);
        if (FlagBitmap == null)
            FlagBitmap = BitmapFactory.decodeResource(res, R.drawable.flag_48x48);
    }

    public static void releaseGameBitmaps(){
        if (ManBitmap != null) {
            ManBitmap.recycle();
            ManBitmap = null;
        }
        if (BoxBitmap != null){
            BoxBitmap.recycle();
            BoxBitmap = null;
        }
        if (WallBitmap != null){
            WallBitmap.recycle();
            WallBitmap = null;
        }
        if (FlagBitmap != null){
            FlagBitmap.recycle();
            FlagBitmap = null;
        }

    }
}
