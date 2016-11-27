package com.mddrill.tetris.mainmenu;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mddrill.tetris.R;

import java.util.List;

public class MainMenuAdapter extends ArrayAdapter<String> {

    public MainMenuAdapter(Context context, List<String> objects) {
        super(context, R.layout.activity_main_menu_list, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.activity_main_menu_list, parent, false);

        String text = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.menu_option_text);
        textView.setText(text);

        return view;
    }

}
