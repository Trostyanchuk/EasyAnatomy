package com.example.Simp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.Simp.about.AboutActivity;
import com.example.Simp.results.ResultsActivity;
import com.example.Simp.test.ChooseTestActivity;
import com.quesucede.gameoflife.R;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //click-handlers for buttons
        View learnButton = findViewById(R.id.learn_button);
        learnButton.setOnClickListener(this);
        View testButton = findViewById(R.id.test_button);
        testButton.setOnClickListener(this);
        View resultsButton = findViewById(R.id.results_button);
        resultsButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.learn_button:
                Intent humanIntent = new Intent(this, HumanActivity.class);
                startActivity(humanIntent);
                break;
            case R.id.test_button:
                Intent testIntent = new Intent(this, ChooseTestActivity.class);
                startActivity(testIntent);
                break;
            case R.id.results_button:
                Intent resultsItent = new Intent(this, ResultsActivity.class);
                startActivity(resultsItent);
                break;
            case R.id.about_button:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            case R.id.exit_button:
                System.exit(0);
        }
    }
}
