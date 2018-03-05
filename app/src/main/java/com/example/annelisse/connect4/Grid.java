package com.example.annelisse.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class
Grid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        GridView gridView = (GridView) findViewById(R.id.myGridViewComponent);

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 42; i++) {
            stringList.add(" ");
        }

        gridView.setAdapter(new ArrayAdapter<>(this, R.layout.connect_button, stringList));
    }
}
