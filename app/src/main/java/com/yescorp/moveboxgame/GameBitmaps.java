package com.yescorp.moveboxgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by 612226 on 2016/8/6.
 */
public class GameBitmaps {
    public static Bitmap ManBitmap = null;

    public static void loadGameBitmaps(Resources res){
        if (ManBitmap == null)
            ManBitmap = BitmapFactory.decodeResource(res, R.drawable.eggman_48x48);
    }

    public static void releaseGameBitmaps(){
        if (ManBitmap != null) {
            ManBitmap.recycle();
            ManBitmap = null;
        }
    }
}
