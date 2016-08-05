package com.yescorp.moveboxgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class GameLevelActivity extends AppCompatActivity {

    String [] levelList = new String[] {"第一关", "第二关", "第三关", "第四关"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level);

        GridView gv_levels = (GridView) findViewById(R.id.gv_levels);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.gv_levels_item_textview, levelList);
        gv_levels.setAdapter(arrayAdapter);


    }
}
