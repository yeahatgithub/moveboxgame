package com.yescorp.moveboxgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    public static final String KEY_SELECTED_LEVEL = "Selected_Level";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);

        int selected_level = getIntent().getIntExtra(KEY_SELECTED_LEVEL, 1);
        TextView tvLevel = (TextView) findViewById(R.id.tv_level);
        //tvLevel.setText(getResources().getString(R.string.what_you_select) + "第" + selected_level + "关");

        GameView gameView = new GameView(this);
        setContentView(gameView);
    }
}
