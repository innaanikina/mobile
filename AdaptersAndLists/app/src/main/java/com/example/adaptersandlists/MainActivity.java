package com.example.adaptersandlists;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String[] countries = {"Бразилия", "Аргентина", "Чили", "Колумбия", "Уругвай"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView countriesList = findViewById(R.id.gridview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countries);
        countriesList.setAdapter(adapter);

        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        "Вы выбрали " + adapterView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}