package com.example.EasyAnatomy.results;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import com.example.EasyAnatomy.R;

public class ResultsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            this.finish();
        }
        return super.onTouchEvent(event);
    }
}
