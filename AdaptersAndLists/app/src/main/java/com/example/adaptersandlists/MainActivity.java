package com.example.adaptersandlists;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] countries = getResources().getStringArray(R.array.countries);

        TextView selection = findViewById(R.id.selection);
        ListView countriesList = findViewById(R.id.countriesList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, countries);
        countriesList.setAdapter(adapter);

        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SparseBooleanArray selected = countriesList.getCheckedItemPositions();

                String selectedItems = "";

                for (int j = 0; j < countries.length; j++) {
                    if (selected.get(j)) {
                        selectedItems += countries[j] + ", ";
                    }
                }
                selection.setText("Выбрано: " + selectedItems);
            }
        });
    }
}