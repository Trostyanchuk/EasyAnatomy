package com.example.EasyAnatomy.test;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.EasyAnatomy.R;

public class ChooseTestActivity extends Activity {

    Button back;
    Button bodyButton;
    Button musclesButton;
    Button circSystemButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_chooser);

        bodyButton = (Button) findViewById(R.id.to_bodies_activity);
        bodyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startBodiesTest = new Intent(getApplicationContext(), StartTestActivity.class);
                startBodiesTest.putExtra("test", TestType.ORGANS);
                startActivity(startBodiesTest);
            }
        });

        musclesButton = (Button) findViewById(R.id.to_muscles_activity);
        musclesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startMusclesTest = new Intent(getApplicationContext(), StartTestActivity.class);
                startMusclesTest.putExtra("test", TestType.MUSCLES);
                startActivity(startMusclesTest);
            }
        });
        circSystemButton = (Button) findViewById(R.id.to_circ_activity);
        circSystemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCircSysTest = new Intent(getApplicationContext(), StartTestActivity.class);
                startCircSysTest.putExtra("test", TestType.CIRC_SYS);
                startActivity(startCircSysTest);
            }
        });
    }
}
