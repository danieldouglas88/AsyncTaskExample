package com.daniel.asynctaskexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    ProgressBar progressBar;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);

        //handle the click event
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Downloader().execute();
            }
            });
        }

    class Downloader extends AsyncTask<Void, Integer, Integer>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressBar.setMax(100);
        }
        @Override
        protected void onProgressUpdate(Integer[] values) {
            super.onProgressUpdate();

            progressBar.setProgress(values[0]);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            for (int i = 0; i < 100; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            return null;
        }
        protected void onPostExecute(Integer result){
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "Task Completed", Toast.LENGTH_LONG).show();
        }
        }
    }

