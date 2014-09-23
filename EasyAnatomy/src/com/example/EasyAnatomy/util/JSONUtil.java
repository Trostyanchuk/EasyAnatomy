package com.example.EasyAnatomy.util;


import android.content.Context;
import android.content.res.AssetManager;
import org.json.JSONArray;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class JSONUtil {

    public static final String BODY_TEST_FILE = "bodies_test.json";
    public static final String MUSCLES_TEST_FILE = "muscles_test.json";
    public static final String CIRC_SYSTEM_TEST = "circ_system_test.json";
    public static final String RESULTS_FILE = "results_source.json";

    public static final int MAX_RECORDS = 5;

    public static String loadJSONFromAsset(String source, Context context) {
        String json = null;
        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open(source);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    public static String loadJSONFromInternalDir(Context context) {
        FileInputStream stream;
        String json = null;
        try {
            stream = new FileInputStream(new File(context.getFilesDir(), RESULTS_FILE));
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            json = Charset.defaultCharset().decode(bb).toString();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static void loadJSONToInternalDir(JSONArray jsonArray, Context context) {
        try {
            FileWriter file = new FileWriter(new File(context.getFilesDir(), RESULTS_FILE));
            file.write(jsonArray.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
