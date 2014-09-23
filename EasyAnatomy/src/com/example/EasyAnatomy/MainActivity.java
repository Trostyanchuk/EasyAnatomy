package com.example.EasyAnatomy;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.EasyAnatomy.about.AboutActivity;
import com.example.EasyAnatomy.results.ResultsActivity;
import com.example.EasyAnatomy.test.ChooseTestActivity;
import com.example.EasyAnatomy.test.Question;
import com.example.EasyAnatomy.test.Result;
import com.example.EasyAnatomy.util.JSONUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initiateResultFile();
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

    private void initiateResultFile() {
        String jsonStr = JSONUtil.loadJSONFromInternalDir(this);
        if(jsonStr == null || jsonStr.equals("")) {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject;
            try {
                for(int i = 0; i < JSONUtil.MAX_RECORDS; i++) {
                    jsonObject = new JSONObject();
                    jsonObject.put("date", "Еще не пройден");
                    jsonObject.put("mark", 0);
                    jsonArray.put(jsonObject);
                }
                JSONUtil.loadJSONToInternalDir(jsonArray, this);
            } catch (JSONException e) {
                e.getStackTrace();
            }
        }
    }
}
