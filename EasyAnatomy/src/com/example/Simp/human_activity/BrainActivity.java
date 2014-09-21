package com.example.Simp.human_activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import com.quesucede.gameoflife.R;

public class BrainActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brain);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            this.finish();
        }
        return super.onTouchEvent(event);
    }
}
