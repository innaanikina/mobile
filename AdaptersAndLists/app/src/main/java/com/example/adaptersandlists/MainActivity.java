package com.example.adaptersandlists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<State> states = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.list);

        StateAdapter.OnStateClickListener stateClickListener = new StateAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(State state, int position) {
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + state.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        StateAdapter adapter = new StateAdapter(this, states,stateClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData() {
        states.add(new State("Бразилия", "Бразилиа", R.drawable.brazil));
        states.add(new State("Аргентина", "Буэнос-Айрес", R.drawable.argentina));
        states.add(new State("Колумбия", "Богота", R.drawable.colombia));
        states.add(new State("Уругвай", "Монтевидео", R.drawable.uruguay));
        states.add(new State("Чили", "Сантьяго", R.drawable.chile));
    }

}