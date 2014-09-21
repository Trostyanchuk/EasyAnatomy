package com.example.Simp.about;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import com.quesucede.gameoflife.R;

public class AboutActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            this.finish();
        }
        return super.onTouchEvent(event);
    }
}
