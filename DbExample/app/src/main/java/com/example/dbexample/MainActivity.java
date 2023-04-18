package com.example.dbexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ListView userList;
    TextView header;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = findViewById(R.id.header);
        userList = findViewById(R.id.list);

        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();

        db = databaseHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE, null);
        String[] headers = new String[] {DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_YEAR};
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                userCursor, headers, new int[] {android.R.id.text1, android.R.id.text2}, 0);
        header.setText("Найдено элементов: " + userCursor.getCount());
        userList.setAdapter(userAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        userCursor.close();
    }
}