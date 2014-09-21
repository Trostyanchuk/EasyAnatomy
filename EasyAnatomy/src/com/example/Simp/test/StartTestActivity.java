package com.example.Simp.test;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.JsonWriter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.quesucede.gameoflife.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StartTestActivity extends Activity {

    private TestType test = TestType.DEFAULT;
    private JSONObject jsonObject;
    private JsonWriter jsonWriter;
    private List<Question> questions = new ArrayList<Question>();

    TextView questionTextView;
    RadioButton variant1;
    RadioButton variant2;
    RadioButton variant3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Bundle extras = getIntent().getExtras();
        test = (TestType) extras.get("test");
        loadQuestions();

        if(test.equals(TestType.BODIES)) {
            questionTextView = (TextView) findViewById(R.id.question);
            questionTextView.setText(questions.get(0).getQuestion());

            variant1 = (RadioButton) findViewById(R.id.variant1);
            variant1.setText(questions.get(0).getVariants().get(0));

            variant2 = (RadioButton) findViewById(R.id.variant2);
            variant2.setText(questions.get(0).getVariants().get(1));

            variant3 = (RadioButton) findViewById(R.id.variant3);
            variant3.setText(questions.get(0).getVariants().get(2));
        }
    }

    private boolean loadQuestions() {
        String source = null;
        if (test.equals(TestType.DEFAULT)) {
            return false;
        }

        if (test.equals(TestType.BODIES)) source = "bodies_test.json";
        else if (test.equals(TestType.MUSCLES)) source = "not created yet";
        else if (test.equals(TestType.CIRC_SYS)) source = "not created yet";

        if(test.equals(TestType.BODIES)) {
            String jsonStr = loadJSONFromAsset(source);
            try {
                jsonObject = new JSONObject(jsonStr);
                JSONArray jArray = jsonObject.getJSONArray("questions");
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject temp = jArray.getJSONObject(i);
                    questions.add(new Question(temp.getString("question"),
                            getArrayFromJSONArray(temp.getJSONArray("variants")),
                            temp.getInt("answer")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private List<String> getArrayFromJSONArray(JSONArray jsonArray) {
        List<String> variants = new ArrayList<String>();
        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                variants.add(String.valueOf(jsonArray.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return variants;
    }

    private String loadJSONFromAsset(String source) {
        String json = null;
        try {
            AssetManager am = getAssets();
            InputStream is = am.open(source);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
