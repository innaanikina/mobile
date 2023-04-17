package com.example.adaptersandlists;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<State> states = new ArrayList<>();
    ListView countriesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialData();
        countriesList = findViewById(R.id.countriesList);
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_item, states);
        countriesList.setAdapter(stateAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                State selectedState = (State) adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(),
                        "Был выбран пункт " + selectedState.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        countriesList.setOnItemClickListener(itemListener);
    }

    private void setInitialData() {
        states.add(new State("Бразилия", "Бразилиа", R.drawable.brazil));
        states.add(new State("Аргентина", "Буэнос-Айрес", R.drawable.argentina));
        states.add(new State("Колумбия", "Богота", R.drawable.colombia));
        states.add(new State("Уругвай", "Монтевидео", R.drawable.uruguay));
        states.add(new State("Чили", "Сантьяго", R.drawable.chile));
    }
}