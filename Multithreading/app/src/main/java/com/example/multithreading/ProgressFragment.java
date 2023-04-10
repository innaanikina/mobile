package com.example.multithreading;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ProgressFragment extends Fragment {
    int[] integers = null;
    ProgressBar indicatorBar;
    TextView statusView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        integers = new int[100];
        for (int i = 0; i < 100; i++) {
            integers[i] = i + 1;
        }

        indicatorBar = (ProgressBar) view.findViewById(R.id.indicator);
        statusView = (TextView) view.findViewById(R.id.statusView);
        Button btnFetch = (Button) view.findViewById(R.id.progressBtn);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ProgressTask().execute();
            }
        });
        return view;
    }

    class ProgressTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < integers.length; i++) {
                publishProgress(i);
                SystemClock.sleep(400);
            }
            return (null);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            indicatorBar.setProgress(values[0] + 1);
            statusView.setText("Статус: " + String.valueOf(values[0] + 1));
        }

        @Override
        protected void onPostExecute(Void unused) {
            Toast.makeText(getActivity(), "Задача завершена", Toast.LENGTH_SHORT).show();
        }
    }
}
