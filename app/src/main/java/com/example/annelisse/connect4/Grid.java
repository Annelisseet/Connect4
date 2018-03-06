package com.example.annelisse.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class
Grid extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        GridView gridView = findViewById(R.id.myGridViewComponent);
        gridView.setOnItemClickListener(this);

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 42; i++) {
            stringList.add(" ");
        }
        adapter = new ArrayAdapter<>(this, R.layout.connect_text_view, stringList);
        gridView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView btn = (TextView) adapter.getView(position, view, parent);
        int background = position % 2 == 1 ? R.drawable.round_button_red : R.drawable.round_button_yellow;
        btn.setBackgroundResource(background);

    }

}
