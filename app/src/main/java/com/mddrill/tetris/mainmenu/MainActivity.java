package com.mddrill.tetris.mainmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mddrill.tetris.R;
import com.mddrill.tetris.game.Game;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView menu = (ListView) findViewById(R.id.main_menu);
        List<String> menuList = Arrays.asList(getResources().getStringArray(R.array.menu_options));
        MainMenuAdapter menuAdapter = new MainMenuAdapter(getBaseContext(), menuList);

        menu.setAdapter(menuAdapter);

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position == 0){
                    startActivity(new Intent(MainActivity.this, Game.class));
                }
                //TO BE IMPLEMENTED LATER
                /*else if(position == 1){
                    startActivity(new Intent(MainActivity.this, HighScores.class));
                }else if(position == 2){
                    startActivity(new Intent(MainActivity.this, Options.class));
                }*/
            }
        });
    }
}
