package com.example.EasyAnatomy.test;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import com.example.EasyAnatomy.R;

public class CongratsResultActivity extends Activity {

    int result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        Bundle extras = getIntent().getExtras();
        result = extras.getInt("result");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            finish();
        }
        return super.onTouchEvent(event);
    }
}
