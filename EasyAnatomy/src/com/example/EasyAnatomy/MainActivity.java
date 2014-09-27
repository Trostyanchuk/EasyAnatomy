package com.example.EasyAnatomy;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.EasyAnatomy.atlas.AtlasActivity;
import com.example.EasyAnatomy.results.ResultsActivity;
import com.example.EasyAnatomy.test.ChooseTestActivity;
import com.example.EasyAnatomy.util.JSONUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    Button learnButton;
    Button testButton;
    Button resultsButton;
    Button helpButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initiateResultFile();

        //click-handlers for buttons
        learnButton = (Button) findViewById(R.id.learn_button);
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent humanIntent = new Intent(getApplicationContext(), AtlasActivity.class);
                startActivity(humanIntent);
            }
        });

        testButton = (Button) findViewById(R.id.test_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testIntent = new Intent(getApplicationContext(), ChooseTestActivity.class);
                startActivity(testIntent);
            }
        });

        resultsButton = (Button) findViewById(R.id.results_button);
        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultsItent = new Intent(MainActivity.this, ResultsActivity.class);
                startActivity(resultsItent);
            }
        });

        helpButton = (Button) findViewById(R.id.help_button);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
