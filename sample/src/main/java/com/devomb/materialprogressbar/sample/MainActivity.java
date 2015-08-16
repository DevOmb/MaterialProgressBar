package com.devomb.materialprogressbar.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.devomb.materialprogressbar.MaterialProgressBar;


public class MainActivity extends Activity {

    private MaterialProgressBar bar1;
    private MaterialProgressBar bar2;
    private MaterialProgressBar bar3;
    private MaterialProgressBar bar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar1 = (MaterialProgressBar) findViewById(R.id.bar_1);
        bar2 = (MaterialProgressBar) findViewById(R.id.bar_2);
        bar3 = (MaterialProgressBar) findViewById(R.id.bar_3);
        bar4 = (MaterialProgressBar) findViewById(R.id.bar_4);

        bar1.setProgress(0.33f);
        bar2.setProgress(0.56f);
        bar3.setProgress(0.80f);
        bar4.setProgress(0.10f);

        bar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar4.setProgress(bar4.getProgress() + 0.10f);
            }
        });
    }
}
