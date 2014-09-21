package com.example.Simp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import com.example.Simp.human_activity.BrainActivity;
import com.example.Simp.human_activity.HeartActivity;
import com.example.Simp.human_activity.LungsActivity;

public class HumanActivity extends Activity {

    int[] zoomFactor = {0};
    int[] brain = {122, 60, 186, 90};
    int[] lungs = {111, 146, 140, 250};
    int[] heart = {170, 193, 206, 242};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.quesucede.gameoflife.R.layout.human);

        //img = (ImageView) findViewById(com.quesucede.gameoflife.R.id.human_iv);
        /**zoom = (ZoomControls) findViewById(com.quesucede.gameoflife.R.id.zoom_controls);
         zoom.setOnZoomInClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        //Toast.makeText(getBaseContext(), "In", Toast.LENGTH_LONG).show();
        ImageView imageView = (ImageView) findViewById(com.quesucede.gameoflife.R.id.human_iv);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = params.height * 2;
        params.width = params.width * 2;
        imageView.setLayoutParams(params);
        //imageView.refreshDrawableState();
        imageView.invalidate();
        }
        });
         zoom.setOnZoomOutClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        //Toast.makeText(getBaseContext(), "Out", Toast.LENGTH_LONG).show();
        ImageView imageView = (ImageView) findViewById(com.quesucede.gameoflife.R.id.human_iv);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = params.height / 3;
        params.width = params.width / 3;
        imageView.setLayoutParams(params);
        imageView.invalidate();
        }
        }); */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float eventX = event.getX();
        float eventY = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getField(brain, eventX, eventY)) {
                Intent brainIntent = new Intent(this, BrainActivity.class);
                startActivity(brainIntent);
            } else if (getField(lungs, eventX, eventY)) {
                Intent polmonesIntent = new Intent(this, LungsActivity.class);
                startActivity(polmonesIntent);
            } else if (getField(heart, eventX, eventY)) {
                Intent heartIntent = new Intent(this, HeartActivity.class);
                startActivity(heartIntent);
            }

            String text1 = "You click at x = " + event.getX() + " and y = " + event.getY();
            //Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }
        return super.onTouchEvent(event);
    }

    private boolean getField(int[] map, float eventX, float eventY) {
        if (eventX > map[0] && eventX < map[2] && eventY > map[1] && eventY < map[3]) {
            return true;
        }
        return false;
    }
}
