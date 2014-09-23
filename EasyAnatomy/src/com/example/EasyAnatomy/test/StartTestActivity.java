package com.example.EasyAnatomy.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.example.EasyAnatomy.R;
import com.example.EasyAnatomy.util.JSONUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class StartTestActivity extends Activity {

    private TextView questionTextView;
    private RadioGroup radioGroup;
    private RadioButton variant1;
    private RadioButton variant2;
    private RadioButton variant3;
    private Button back;
    private Button next;
    private TestType test = TestType.DEFAULT;
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    private List<Question> questions = new ArrayList<Question>();
    private int counter = 0;
    private int result = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Bundle extras = getIntent().getExtras();
        test = (TestType) extras.get("test");
        loadQuestions();

        questionTextView = (TextView) findViewById(R.id.question);
        variant1 = (RadioButton) findViewById(R.id.variant1);
        variant2 = (RadioButton) findViewById(R.id.variant2);
        variant3 = (RadioButton) findViewById(R.id.variant3);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        next = (Button) findViewById(R.id.next);
        back = (Button) findViewById(R.id.back);

        setDrawableInfo(counter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int checkedId = radioGroup.getCheckedRadioButtonId();

                if (checkedId == -1) {
                    Toast.makeText(StartTestActivity.this, "Ничего не выбрано", Toast.LENGTH_LONG).show();
                } else {
                    questions.get(counter).setTempAnswer(getAnswer(checkedId));
                    back.setVisibility(View.VISIBLE);

                    if (!setDrawableInfo(++counter)) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StartTestActivity.this);
                        alertDialogBuilder.setMessage("Вы уверены, что хотите закончить тест?")
                                .setCancelable(false)
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        saveResults();
                                        Intent congratsActivity = new Intent(StartTestActivity.this, CongratsResultActivity.class);
                                        congratsActivity.putExtra("result", result);
                                        startActivity(congratsActivity);
                                        finish();
                                    }
                                })
                                .setNegativeButton("Нет, вернуться к тесту", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //TODO activity to choose question
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                    questionTextView.invalidate();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!setDrawableInfo(--counter)) {
                    back.setVisibility(View.INVISIBLE);
                }
                questionTextView.invalidate();
            }
        });
    }

    private int getAnswer(int checkedId) {
        int valueToReturn = 0;
        if (checkedId == variant1.getId()) valueToReturn = 1;
        else if (checkedId == variant2.getId()) valueToReturn = 2;
        else if (checkedId == variant3.getId()) valueToReturn = 3;
        return valueToReturn;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (counter != 0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StartTestActivity.this);
                alertDialogBuilder.setMessage("Сохранить Ваш результат?")
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                saveResults();
                                Intent congratsActivity = new Intent(StartTestActivity.this, CongratsResultActivity.class);
                                congratsActivity.putExtra("result", result);
                                startActivity(congratsActivity);
                                finish();
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void saveResults() {
        for (Question q : questions) {
            if (q.getAnswer() == q.getTempAnswer()) {
                result++;
            }
        }
        List<Result> results = new ArrayList<Result>();
        try {
            String jsonString = JSONUtil.loadJSONFromInternalDir(this);
            if(jsonString != null && !jsonString.isEmpty()) {
                jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = (JSONObject) jsonArray.get(i);
                    results.add(new Result(jsonObject.getString("date"), jsonObject.getInt("mark")));
                }
            }
            results.add(new Result(new Date().toString(), result));
            Collections.sort(results, new Comparator<Result>() {
                @Override
                public int compare(Result result, Result result2) {
                    return result.getMark() > result2.getMark() ? result.getMark() : result2.getMark();
                }
            });
            results.get(0);
            if(results.size() > JSONUtil.MAX_RECORDS) {
                results.remove(JSONUtil.MAX_RECORDS);
            }
            jsonArray = new JSONArray();
            for(int i = 0; i < results.size(); i++) {
                jsonObject = new JSONObject();
                jsonObject.put("date", results.get(i).getDate());
                jsonObject.put("mark", results.get(i).getMark());
                jsonArray.put(jsonObject);
            }
            JSONUtil.loadJSONToInternalDir(jsonArray, this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean setDrawableInfo(int counter) {
        if (questions.size() != counter && counter >= 0) {
            radioGroup.clearCheck();
            questionTextView.setText(questions.get(counter).getQuestion());
            variant1.setText(questions.get(counter).getVariants().get(0));
            variant2.setText(questions.get(counter).getVariants().get(1));
            variant3.setText(questions.get(counter).getVariants().get(2));
            if (counter == 0) {
                back.setVisibility(View.INVISIBLE);
            }
            return true;
        }
        return false;
    }

    private boolean loadQuestions() {
        String source = null;
        if (test.equals(TestType.DEFAULT)) {
            return false;
        }

        if (test.equals(TestType.BODIES)) source = JSONUtil.BODY_TEST_FILE;
        else if (test.equals(TestType.MUSCLES)) source = JSONUtil.MUSCLES_TEST_FILE;
        else if (test.equals(TestType.CIRC_SYS)) source = JSONUtil.CIRC_SYSTEM_TEST;

        String jsonStr = JSONUtil.loadJSONFromAsset(source, this);
        try {
            jsonObject = new JSONObject(jsonStr);
            jsonArray = jsonObject.getJSONArray("questions");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject temp = jsonArray.getJSONObject(i);
                questions.add(new Question(temp.getString("question"),
                        getArrayFromJSONArray(temp.getJSONArray("variants")),
                        temp.getInt("answer")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    private List<String> getArrayFromJSONArray(JSONArray jsonArray) {
        List<String> variants = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                variants.add(String.valueOf(jsonArray.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return variants;
    }
}
