package com.example.EasyAnatomy.atlas;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.EasyAnatomy.R;

public class InfoActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textView = (TextView) findViewById(R.id.textView);

        int imageResource = getIntent().getIntExtra("imageResource", 0);
        int stringResource = getIntent().getIntExtra("stringResource", 0);
        int titleResource = getIntent().getIntExtra("titleResource", 0);

        if(imageResource != 0) {
            imageView.setImageResource(imageResource);
        }
        if (stringResource != 0) {
            textView.setText(stringResource);
        }
        if(titleResource != 0) {
            this.setTitle(titleResource);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            this.finish();
        }
        return super.onTouchEvent(event);
    }
}
